package com.tms.domain.response;

import lombok.Data;

import java.util.Date;

@Data
public class ReviewsResponse {

    private String review;
    private Date created;

}
