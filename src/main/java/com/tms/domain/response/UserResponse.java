package com.tms.domain.response;

import lombok.Data;

@Data
public class UserResponse {

    private String firstName;
    private String userName;
    private String lastName;
    private String email;
    private String jobTitle;
    private String organizationName;
    private String legalAddress;
    private String unpTin;
    private String countries;
    private String telephone1;
    private String telephone2;
    private String telephone3;
}