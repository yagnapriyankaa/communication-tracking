package com.email.trackingservice.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import jakarta.validation.constraints.NotNull;
import lombok.Data;


@Data
@JsonInclude(JsonInclude.Include.NON_NULL)
public class FetchStatusRequest {
    @NotNull(message = "Status Source is required")
    private String statusSource;

    private String batchId;

    @NotNull(message = "Mail Record ID is required")
    private Long ateMailRecordId;
}
