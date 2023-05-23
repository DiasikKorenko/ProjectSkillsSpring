package com.tms.service;


import com.tms.domain.User;
import com.tms.domain.request.UpdatePasswordUserRequest;
import com.tms.domain.response.UserResponse;
import com.tms.mapper.UserToUserResponseMapper;
import com.tms.repository.UserRepository;
import com.tms.security.CheckingAuthorization;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.security.crypto.password.PasswordEncoder;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class UserServiceTest {

    @InjectMocks
    private UserService userService;

    @Mock
    private UserRepository userRepository;

    @Mock
    private UserToUserResponseMapper userToUserResponseMapper;

    @Mock
    private PasswordEncoder passwordEncoder;

    @Mock
    private CheckingAuthorization checkingAuthorization;

    private UpdatePasswordUserRequest updatePasswordUserRequest;

    private int id;

    private User user;

    private UserResponse userResponse;

    private final List<User> users = new ArrayList<>();

    private final List<UserResponse> userResponses = new ArrayList<>();
    private final Timestamp time = new Timestamp(System.currentTimeMillis());

    @BeforeEach
    public void init() {
        id = 1;
        user = new User();
        user.setId(1);
        user.setFirstName("FirstNameTest");
        user.setUserName("UserNameTest");
        user.setLastName("LastNAmeTest");
        user.setEmail("test@gmail.com");
        user.setPasswordUser("PasswordTest");
        user.setJobTitle("TestJob");
        user.setOrganizationName("TetsNameOrganization");
        user.setLegalAddress("TestAddress 6");
        user.setUnpTin("111111111");
        user.setCountries("TestCountry");
        user.setTelephone1("+375297650954");
        user.setTelephone2("+375296280523");
        user.setTelephone3("+375447654327");
        user.setCreated(time);
        user.setChanged(time);
        user.setDeleted(false);
        user.setRole("USER");
        users.add(user);
        userResponse = new UserResponse();
        userResponses.add(userResponse);
        updatePasswordUserRequest = new UpdatePasswordUserRequest();
        updatePasswordUserRequest.setId(1);
        updatePasswordUserRequest.setOldPassword("PasswordTest");
        updatePasswordUserRequest.setNewPassword("NewPasswordTest");
    }

    @Test
    public void getUserResponseByIdTest() {
        when(userRepository.findById(id)).thenReturn(Optional.of(user));
        when(userToUserResponseMapper.userToResponse(user)).thenReturn(userResponse);
        UserResponse returned = userService.getUserResponseById(id);
        verify(userRepository).findById(id);
        verify(userToUserResponseMapper).userToResponse(user);
        assertEquals(userResponse, returned);
    }

    @Test
    public void getAllUsersResponseTest() {
        when(userRepository.findAll()).thenReturn(users);
        when(userToUserResponseMapper.userToResponse(user)).thenReturn(userResponse);
        assertEquals(userResponses, userService.getAllUsersResponse());
    }

    @Test
    public void createUserTest() {
        user.setCreated(time);
        user.setChanged(time);
        user.setRole("USER");
        when(passwordEncoder.encode("PasswordTest")).thenReturn("encodedPassword");
        userService.createUser(user);
        assertEquals("encodedPassword", user.getPasswordUser());
        verify(userRepository).save(user);
    }

    @Test
    public void updateUserTest() {
        when(checkingAuthorization.check(user.getEmail())).thenReturn(true);
        user.setChanged(time);
        userService.updateUser(user);
        verify(userRepository).saveAndFlush(user);
    }

    @Test
    public void updateUserPasswordTest() {
        when(checkingAuthorization.check(user.getEmail())).thenReturn(true);
        when(userRepository.findById(id)).thenReturn(Optional.of(user));
        when(passwordEncoder.matches(updatePasswordUserRequest.getOldPassword(), user.getPasswordUser())).thenReturn(true);
        when(passwordEncoder.encode(updatePasswordUserRequest.getNewPassword())).thenReturn("NewPasswordTest");
        user.setChanged(new Timestamp(System.currentTimeMillis()));
        userService.updateUserPassword(updatePasswordUserRequest);
        verify(userRepository).updateUserPassword(updatePasswordUserRequest.getId(), "NewPasswordTest");

    }

    @Test
    public void deleteUserTest() {
        when(userRepository.findById(id)).thenReturn(Optional.of(user));
        when(checkingAuthorization.check(user.getEmail())).thenReturn(true);
        userService.deleteUser(id);
        verify(userRepository).deleteById(id);
    }
}