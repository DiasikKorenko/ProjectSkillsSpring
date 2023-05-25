package com.tms.domain.request;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.Size;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class UpdatePasswordUserRequest {

    private int id;

    @Size(min = 2, max = 100)
    private String oldPassword;

    @Size(min = 2, max = 100)
    private String newPassword;
}