package com.email.trackingservice.service;

import com.email.trackingservice.dto.*;
import com.email.trackingservice.exception.EmailTrackingServiceException;
import org.apache.commons.lang3.SerializationUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class EmailTrackingService {

    private final EmailServiceClient emailServiceClient;
    private final SfmcClient sfmcClient;
    SfmcServiceResponse sfmcServiceResponse = new SfmcServiceResponse();

    @Autowired
    public EmailTrackingService(EmailServiceClient emailServiceClient, SfmcClient sfmcClient) {
        this.emailServiceClient = emailServiceClient;
        this.sfmcClient = sfmcClient;
    }

    public EmailResponse trackEmailRequest(EmailRequest emailRequest) throws EmailTrackingServiceException {

        EmailServiceResponse emailServiceResponse = SerializationUtils.clone(emailServiceClient.fetchEmailStatusByBatchId(emailRequest.getBatchId()));
        EmailResponse emailResponse = new EmailResponse();
        List<EmailStatusDetail> result = new ArrayList<>();

        long accountId = emailRequest.getAccountId();

        if (emailServiceResponse == null) {
            throw new EmailTrackingServiceException("No records found with given BachId", HttpStatus.NOT_FOUND);
        }

        if (accountId != 0) {
            emailServiceResponse.setCancelled(emailServiceResponse.getCancelled().stream().filter(el -> el.getAccountId() == accountId).collect(Collectors.toList()));
            emailServiceResponse.setScheduled(emailServiceResponse.getScheduled().stream().filter(el -> el.getAccountId() == accountId).collect(Collectors.toList()));
            emailServiceResponse.setSucceeded(emailServiceResponse.getSucceeded().stream().filter(el -> el.getAccountId() == accountId).collect(Collectors.toList()));
            emailServiceResponse.setFailed(emailServiceResponse.getFailed().stream().filter(el -> el.getAccountId() == accountId).collect(Collectors.toList()));
        }
        if (emailServiceResponse.getScheduled() != null) {
            for (ScheduledDetail scheduled : emailServiceResponse.getScheduled()) {
                result.add(mapScheduledDetailToEmailDetail(scheduled, emailServiceResponse.getSendJobId()));
            }
        }
        if (emailServiceResponse.getCancelled() != null) {
            for (ScheduledDetail cancelled : emailServiceResponse.getCancelled()) {
                result.add(mapScheduledDetailToEmailDetail(cancelled, emailServiceResponse.getSendJobId()));
            }
        }
        if (emailServiceResponse.getSucceeded() != null) {
            for (AteDetail succeeded : emailServiceResponse.getSucceeded()) {
                result.add(mapAteDetailToEmailDetail(succeeded, emailServiceResponse.getSendJobId()));
            }
        }
        if (emailServiceResponse.getFailed() != null) {
            for (AteDetail failed : emailServiceResponse.getFailed()) {
                result.add(mapAteDetailToEmailDetail(failed, emailServiceResponse.getSendJobId()));
            }
        }

        emailResponse.setResult(result);
        return emailResponse;
    }

    private EmailStatusDetail mapScheduledDetailToEmailDetail(ScheduledDetail scheduledDetail, String batchId) {
        EmailStatusDetail detail = new EmailStatusDetail();
        detail.setMessageGuid(scheduledDetail.getMessageGuid());
        detail.setBatchId(batchId);
        detail.setAccountId(scheduledDetail.getAccountId());
        detail.setMailFrom("");
        detail.setScheduledDate(scheduledDetail.getScheduledAt());
        detail.setEmailStatus(scheduledDetail.getStatus());
        return detail;
    }

    private EmailStatusDetail mapAteDetailToEmailDetail(AteDetail ateDetail, String batchId) {
        EmailStatusDetail detail = new EmailStatusDetail();
        detail.setMessageGuid(ateDetail.getMessageGuid());
        detail.setBatchId(batchId);
        detail.setAccountId(ateDetail.getAccountId());
        detail.setMailFrom(ateDetail.getMailFrom());
        detail.setScheduledDate(null);
        detail.setAteMailRecordId(ateDetail.getAteMailRecordId());

        detail.setEmailStatus(ateDetail.getStatus());
        detail.setEmailStatusDetail(ateDetail.getStatusDetail());
        return detail;
    }

    public FetchStatusResponse fetchStatus(FetchStatusRequest fetchStatusRequest) throws EmailTrackingServiceException {

        FetchStatusResponse fetchStatusResponse = null;

        if (sfmcServiceResponse == null) {
            throw new EmailTrackingServiceException("No records found with given BachId", HttpStatus.NOT_FOUND);
        }
        if ("email".equalsIgnoreCase(fetchStatusRequest.getStatusSource())) {

            EmailServiceResponse emailServiceResponse = emailServiceClient.fetchEmailStatusByBatchId(fetchStatusRequest.getBatchId());
            fetchStatusResponse = mapEmailServiceResponseToStatusResponse(emailServiceResponse, fetchStatusRequest.getAteMailRecordId());
        } else if ("sfmc".equalsIgnoreCase(fetchStatusRequest.getStatusSource())) {

            sfmcServiceResponse = sfmcClient.fetchStatus(fetchStatusRequest.getAteMailRecordId());
            fetchStatusResponse = mapSfmcServiceResponseToStatusResponse(sfmcServiceResponse);
        }
        return fetchStatusResponse;
    }

    private FetchStatusResponse mapSfmcServiceResponseToStatusResponse(SfmcServiceResponse sfmcServiceResponse) {
        FetchStatusResponse fetchStatusResponse = new FetchStatusResponse();
        FetchStatusDetail statusDetail = new FetchStatusDetail();
        List<Item> items = sfmcServiceResponse.getItems();
        if (!items.isEmpty()) {
            Value value = items.getFirst().getValues();
            if (value.getErrorcode() != null && !value.getErrorcode().isEmpty()) {
                statusDetail.setStatus("Failed");
                statusDetail.setStatusDetail("Failed with error code: " + value.getErrorcode());
            } else {
                statusDetail.setStatus("Success");
                statusDetail.setStatusDetail("Mail Sent Successfully to " + value.getAccount_email() + " At " + value.getDate_sent());
            }
        } else {
            statusDetail.setStatus("Unavailable");
            statusDetail.setStatusDetail("Failed to fetch status, please try again");
        }

        fetchStatusResponse.setStatusDetails(statusDetail);
        return fetchStatusResponse;
    }

    private FetchStatusResponse mapEmailServiceResponseToStatusResponse(EmailServiceResponse emailServiceResponse, long ateMailRecordId) {
        FetchStatusResponse fetchStatusResponse = new FetchStatusResponse();
        FetchStatusDetail statusDetail = new FetchStatusDetail();

        Optional<AteDetail> successDetail = emailServiceResponse.getSucceeded().stream().filter(sd -> sd.getAteMailRecordId() == ateMailRecordId).findFirst();
        if (successDetail.isPresent()) {
            statusDetail.setStatus(successDetail.get().getStatus());
            statusDetail.setStatusDetail(successDetail.get().getStatusDetail());
        } else {
            Optional<AteDetail> failedDetail = emailServiceResponse.getFailed().stream().filter(fd -> fd.getAteMailRecordId() == ateMailRecordId).findFirst();
            if (failedDetail.isPresent()) {
                statusDetail.setStatus(failedDetail.get().getStatus());
                statusDetail.setStatusDetail(failedDetail.get().getStatusDetail());
            } else {
                statusDetail.setStatus("Unavailable");
                statusDetail.setStatusDetail("Failed to fetch status, please try again");
            }
        }

        fetchStatusResponse.setStatusDetails(statusDetail);
        return fetchStatusResponse;

    }


}
