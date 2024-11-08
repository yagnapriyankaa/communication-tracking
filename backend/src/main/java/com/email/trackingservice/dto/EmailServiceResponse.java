package com.email.trackingservice.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.util.List;
import java.util.Optional;

@Data
@NoArgsConstructor
@AllArgsConstructor
@JsonInclude(JsonInclude.Include.NON_NULL)
public class EmailServiceResponse implements Serializable {
    private String sendJobId;
    private String source;
    private int totalNumberOfRequests;
    private String status;
    private int totalScheduled;
    private int totalImmediateSends;
    private int totalSucceeded;
    private int totalFinished;
    private List<ScheduledDetail> scheduled;
    private List<ScheduledDetail> cancelled;
    private List<AteDetail> succeeded;
    private List<AteDetail> failed;


}
