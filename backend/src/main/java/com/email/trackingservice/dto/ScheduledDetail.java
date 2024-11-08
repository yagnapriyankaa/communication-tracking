package com.email.trackingservice.dto;

import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;
import java.time.LocalDateTime;

@Getter
@Setter
public class ScheduledDetail implements Serializable {
    private String messageGuid;
    private String feature;
    private String status;
    private LocalDateTime scheduledAt;
    private long accountId;
    private String emailId;
}
