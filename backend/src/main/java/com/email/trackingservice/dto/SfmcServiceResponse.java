package com.email.trackingservice.dto;

import lombok.Getter;
import lombok.Setter;

import java.util.Date;
import java.util.List;

@Getter
@Setter
public class SfmcServiceResponse {
    private Link links;
    private String requestToken;
    private Date tokenExpireDateUtc;
    private String customObjectId;
    private String customObjectKey;
    private int pageSize;
    private int page;
    private int count;
    private int top;
    private List<Item> items;

}
