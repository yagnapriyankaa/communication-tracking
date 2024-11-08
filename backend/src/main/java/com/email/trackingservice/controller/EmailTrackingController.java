package com.email.trackingservice.controller;

import com.email.trackingservice.dto.*;
import com.email.trackingservice.exception.EmailTrackingServiceException;
import com.email.trackingservice.service.EmailTrackingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import javax.validation.Valid;


@RestController
@RequestMapping("/api")
@CrossOrigin(value="http://localhost:4200/")
public class EmailTrackingController {

    private final EmailTrackingService emailTrackingService;

    @Autowired
    public EmailTrackingController(EmailTrackingService emailTrackingService) {
        this.emailTrackingService = emailTrackingService;
    }

    @PostMapping("/email-request")
    public ResponseEntity<EmailResponse> trackEmailRequest(@Valid @RequestBody EmailRequest emailRequest, BindingResult bindingResult) {
        EmailResponse emailResponse = new EmailResponse();
        if (bindingResult.hasErrors()) {
            emailResponse.setErrorMessage(bindingResult.getAllErrors().get(0).getDefaultMessage());
            return new ResponseEntity<>(emailResponse, HttpStatus.BAD_REQUEST);
        }
        try {
            emailResponse = emailTrackingService.trackEmailRequest(emailRequest);
            return new ResponseEntity<>(emailResponse, HttpStatus.OK);
        } catch (Exception e) {
            emailResponse.setErrorMessage(e.getMessage());
            if (e instanceof EmailTrackingServiceException) {
                return new ResponseEntity<>(emailResponse, ((EmailTrackingServiceException) e).getHttpStatus());
            } else {
                return new ResponseEntity<>(emailResponse, HttpStatus.INTERNAL_SERVER_ERROR);
            }
        }
    }

    @PostMapping("/fetch-status")
    public ResponseEntity<FetchStatusResponse> fetchStatus(@Valid @RequestBody FetchStatusRequest fetchStatusRequest,
                                                           BindingResult bindingResult) {

        FetchStatusResponse fetchStatusResponse = new FetchStatusResponse();
        if (bindingResult.hasErrors()) {
            fetchStatusResponse.setErrorMessage(bindingResult.getAllErrors().get(0).getDefaultMessage());
            return new ResponseEntity<>(fetchStatusResponse, HttpStatus.BAD_REQUEST);
        }
        if (fetchStatusRequest.getBatchId()==null && "email".equals(fetchStatusRequest.getStatusSource()))
        {
            fetchStatusResponse.setErrorMessage("BatchId is required for Email Status");
            return new ResponseEntity<>(fetchStatusResponse, HttpStatus.BAD_REQUEST);
        }
        try {
            fetchStatusResponse = emailTrackingService.fetchStatus(fetchStatusRequest);
            return new ResponseEntity<>(fetchStatusResponse, HttpStatus.OK);
        } catch (Exception e) {
            fetchStatusResponse.setErrorMessage(e.getMessage());
            if (e instanceof EmailTrackingServiceException) {
                return new ResponseEntity<>(fetchStatusResponse, ((EmailTrackingServiceException) e).getHttpStatus());
            } else {
                return new ResponseEntity<>(fetchStatusResponse, HttpStatus.INTERNAL_SERVER_ERROR);
            }
        }

    }
}