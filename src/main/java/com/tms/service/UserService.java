package com.tms.service;

import com.tms.domain.User;
import com.tms.domain.request.UpdatePasswordUserRequest;
import com.tms.domain.response.UserResponse;
import com.tms.exception.BadRequestEx;
import com.tms.exception.ForbiddenEx;
import com.tms.exception.NotFoundEx;
import com.tms.mapper.UserToUserResponseMapper;
import com.tms.repository.UserRepository;
import com.tms.security.CheckingAuthorization;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.sql.Timestamp;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class UserService {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final CheckingAuthorization checkingAuthorization;
    private final UserToUserResponseMapper userToUserResponseMapper;

    @Autowired
    public UserService(UserRepository userRepository, PasswordEncoder passwordEncoder, CheckingAuthorization checkingAuthorization, UserToUserResponseMapper userToUserResponseMapper) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
        this.checkingAuthorization = checkingAuthorization;
        this.userToUserResponseMapper = userToUserResponseMapper;
    }

    public UserResponse getUserResponseById(int id) {
        return userToUserResponseMapper.userToResponse(getUserFromDb(id));
    }

    public User getUserFromDb(int id) {
        return userRepository.findById(id).filter(user -> !user.isDeleted())
                .orElseThrow(() -> new NotFoundEx("User is not found"));
    }

    public List<UserResponse> getAllUsersResponse() {
        List<UserResponse> users = userRepository.findAll().stream()
                .filter(user -> !user.isDeleted())
                .map(userToUserResponseMapper::userToResponse)
                .collect(Collectors.toList());
        if (!users.isEmpty()) {
            return users;
        } else {
            throw new NotFoundEx("No users");
        }
    }

    public void createUser(User user) {
        if (loginExist(user.getEmail())) {
            throw new BadRequestEx("This email doesn't exist");
        } else {
            user.setCreated(new Timestamp(System.currentTimeMillis()));
            user.setChanged(new Timestamp(System.currentTimeMillis()));
            user.setRole("USER");
            user.setDeleted(false);
            user.setPasswordUser(passwordEncoder.encode(user.getPasswordUser()));
            userRepository.save(user);
        }
    }

    public boolean loginExist(String email) {
        return userRepository.findUserByEmail(email).isPresent() ? true : false;
    }

    @Transactional
    public void updateUser(User user) {
        if (checkingAuthorization.check(user.getEmail())) {
            user.setChanged(new Timestamp(System.currentTimeMillis()));
            user.setDeleted(false);
            userRepository.saveAndFlush(user);
        } else {
            throw new ForbiddenEx("Access error.You can't work with another user information");
        }
    }

    @Transactional
    public void deleteUser(int id) {
        if (checkingAuthorization.check(getUserFromDb(id).getEmail())) {
            userRepository.deleteById(id);
        } else {
            throw new ForbiddenEx("You can't delete another user");
        }
    }

    @Transactional
    public void updateUserPassword(UpdatePasswordUserRequest request) {
        if (checkingAuthorization.check(getUserFromDb(request.getId()).getEmail())) {
            User user = getUserFromDb(request.getId());
            if (passwordEncoder.matches(request.getOldPassword(), user.getPasswordUser())) {
                String codePass = passwordEncoder.encode(request.getNewPassword());
                user.setChanged(new Timestamp(System.currentTimeMillis()));
                userRepository.updateUserPassword(request.getId(), codePass);
            } else {
                throw new BadRequestEx("Check your old and new password and try again");
            }
        } else {
            throw new ForbiddenEx("You can't update password another user");
        }
    }

    @Transactional
    public void updateUserPasswordAdmin(UpdatePasswordUserRequest request) {
        User user = getUserFromDb(request.getId());
        if (passwordEncoder.matches(request.getOldPassword(), user.getPasswordUser())) {
            String codePass = passwordEncoder.encode(request.getNewPassword());
            user.setChanged(new Timestamp(System.currentTimeMillis()));
            userRepository.updateUserPassword(request.getId(), codePass);
        } else {
            throw new BadRequestEx("Check your old and new password and try again");
        }
    }

    public List<User> getAllUsers() {
        List<User> users = userRepository.findAll();
        if (!users.isEmpty()) {
            return users;
        } else {
            throw new NotFoundEx("No users");
        }
    }

    public User getUserById(int id) {
        return userRepository.findById(id).orElseThrow(() -> new NotFoundEx("User is not found"));
    }

    @Transactional
    public void deleteUserByAdmin(int id) {
        User user = userRepository.findById(id).orElseThrow(() -> new NotFoundEx("User is not found"));
        if (!user.isDeleted()) {
            user.setDeleted(true);
        } else {
            throw new NotFoundEx("User is already deleted");
        }
    }

    @Transactional
    public void updateUserAdmin(User user) {
        if (getUserFromDb(user.getId()) == null) {
            throw new NotFoundEx("User is not found");
        } else {
            user.setChanged(new Timestamp(System.currentTimeMillis()));
            user.setDeleted(false);
            user.setRole("USER");
            userRepository.saveAndFlush(user);
        }
    }
}