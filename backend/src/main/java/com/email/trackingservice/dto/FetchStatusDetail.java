package com.email.trackingservice.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter

public class FetchStatusDetail {
    private String statusSource;
    private String status;
    private String statusDetail;
}
