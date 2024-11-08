package com.email.trackingservice.dto;

import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;

@Getter
@Setter
public class AteDetail implements Serializable {

    private  long ateMailRecordId;
    private boolean isScheduled;
    private String messageGuid;
    private String feature;
    private long accountId;
    private String status;
    private String statusDetail;
    private String mailFrom;
    private String recipients;
    private String createdAt;
    private String updatedAt;
}
