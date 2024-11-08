package com.email.trackingservice.service;

import com.email.trackingservice.dto.*;
import org.springframework.stereotype.Service;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class EmailServiceClient {
    private final Map<String, EmailServiceResponse> emailServiceMockResponses;

    public EmailServiceClient() {
        this.emailServiceMockResponses = new HashMap<>();
        List<AteDetail> succeeded = new ArrayList<>();
        EmailServiceResponse emailServiceResponse = new EmailServiceResponse();
        emailServiceResponse.setSendJobId("96bbd7c3-25aa-4923-ae3d-bff5ca2a867b");
        emailServiceResponse.setStatus("ACCEPTED");
        emailServiceResponse.setSource("ACCOUNT");
        emailServiceResponse.setTotalNumberOfRequests(3);
        emailServiceResponse.setTotalScheduled(2);
        emailServiceResponse.setTotalImmediateSends(1);
        emailServiceResponse.setTotalSucceeded(0);
        emailServiceResponse.setTotalFinished(0);

        ScheduledDetail scheduled = new ScheduledDetail();

        scheduled.setMessageGuid("96bbd7c3-95aa-4923-ae3d-bff5cd2a867b");
        scheduled.setFeature("Appointments");
        scheduled.setStatus("Scheduled");
        scheduled.setScheduledAt(LocalDateTime.now().plusDays(1));
        scheduled.setAccountId(123);
        scheduled.setEmailId("abc@gmail.com");

        emailServiceResponse.setScheduled(List.of(scheduled));

        ScheduledDetail cancelled = new ScheduledDetail();

        cancelled.setMessageGuid("96bbdjc3-95aa-4923-ae3d-bff5cd2a867b");
        cancelled.setFeature("Appointments");
        cancelled.setStatus("Cancelled");
        cancelled.setScheduledAt(LocalDateTime.now().plusDays(1));
        cancelled.setAccountId(234);
        cancelled.setEmailId("abc@gmail.com");

        emailServiceResponse.setCancelled(List.of(cancelled));

        AteDetail ate = new AteDetail();

        ate.setAccountId(456);
        ate.setFeature("Appointments");
        ate.setScheduled(true);
        ate.setMessageGuid("96bbdjc3-95aa-4923-ae3d-bff5cs2a867b");
        ate.setStatus("ok");
        ate.setStatusDetail("Delivery successful");
        ate.setMailFrom("email-secure@castlighthealth.com");
        ate.setRecipients("smetcalf@castlighthealth.com");
        ate.setAteMailRecordId(1234L);
        ate.setCreatedAt("string");
        ate.setUpdatedAt("string");
        succeeded.add(ate);


        AteDetail success = new AteDetail();

        success.setAccountId(334);
        success.setFeature("Appointments");
        success.setScheduled(true);
        success.setMessageGuid("96bbdjwsc3-95aa-4923-ae3d-bff5cs2a867b");
        success.setStatus("ok");
        success.setStatusDetail("Delivery successful");
        success.setMailFrom("email-secure@castlighthealth.com");
        success.setRecipients("smetcalf@castlighthealth.com");
        success.setAteMailRecordId(2345L);
        success.setCreatedAt("string");
        success.setUpdatedAt("string");
        succeeded.add(success);

        emailServiceResponse.setSucceeded(succeeded);

        AteDetail fail = new AteDetail();

        fail.setScheduled(false);
        fail.setMessageGuid(null);
        fail.setFeature("Appointments");
        fail.setAccountId(789);
        fail.setStatus("Failed");
        fail.setStatusDetail("Failure reason");
        fail.setMailFrom("email-secure@castlighthealth.com");
        fail.setRecipients("smetcalf@castlighthealth.com");
        fail.setAteMailRecordId(3456);
        fail.setCreatedAt("string");
        fail.setUpdatedAt("string");

        emailServiceResponse.setFailed(List.of(fail));


        this.emailServiceMockResponses.put("96bbd7c3-25aa-4923-ae3d-bff5ca2a867b",emailServiceResponse);
    }

    public EmailServiceResponse fetchEmailStatusByBatchId(String batchId) {
        // TOOD: integrate with emailservice feign client
       return emailServiceMockResponses.get(batchId);

    }

}
