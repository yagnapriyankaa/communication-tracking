package com.email.trackingservice.dto;

import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
public class EmailStatusDetail {
    private String messageGuid;
    private String batchId;
    private long accountId;
    private String mailFrom;
    private LocalDateTime scheduledDate;
    private String emailStatus;
    private String emailStatusDetail;
    private long ateMailRecordId;

}
