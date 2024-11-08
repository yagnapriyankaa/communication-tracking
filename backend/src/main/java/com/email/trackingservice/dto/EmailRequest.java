package com.email.trackingservice.dto;

import jakarta.validation.constraints.NotNull;
import lombok.*;


@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class EmailRequest {
    @NotNull(message = "Batch ID is required")
    private String batchId;
    private long accountId;
}