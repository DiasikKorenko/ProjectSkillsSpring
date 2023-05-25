package com.tms.mapper;

import com.tms.domain.User;
import com.tms.domain.response.UserResponse;
import org.springframework.stereotype.Component;

@Component
public class UserToUserResponseMapper {
    public UserResponse userToResponse(User user) {
        UserResponse userResponse = new UserResponse();
        userResponse.setFirstName(user.getFirstName());
        userResponse.setUserName(user.getUserName());
        userResponse.setLastName(user.getLastName());
        userResponse.setEmail(user.getEmail());
        userResponse.setJobTitle(user.getJobTitle());
        userResponse.setOrganizationName(user.getOrganizationName());
        userResponse.setLegalAddress(user.getLegalAddress());
        userResponse.setUnpTin(user.getUnpTin());
        userResponse.setCountries(user.getCountries());
        userResponse.setTelephone1(user.getTelephone1());
        userResponse.setTelephone2(user.getTelephone2());
        userResponse.setTelephone3(user.getTelephone3());
        return userResponse;
    }
}