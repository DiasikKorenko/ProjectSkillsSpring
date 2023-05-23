package com.tms.controller;

import com.tms.domain.User;
import com.tms.domain.request.UpdatePasswordUserRequest;
import com.tms.domain.response.UserResponse;
import com.tms.exception.BadRequestEx;
import com.tms.service.UserService;
import io.swagger.v3.oas.annotations.Operation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/user")
public class UserController {
    UserService userService;

    @Autowired
    public UserController(UserService userService) {
        this.userService = userService;
    }

    @Operation(summary = "All user information for the user")
    @GetMapping
    public ResponseEntity<List<UserResponse>> getAllUsersResponse() {
        return new ResponseEntity<>(userService.getAllUsersResponse(), HttpStatus.OK);
    }

    @Operation(summary = "User information for the user")
    @GetMapping("/{id}")
    public ResponseEntity<UserResponse> getUserResponseById(@PathVariable int id) {
        return new ResponseEntity<>(userService.getUserResponseById(id), HttpStatus.OK);
    }

    @Operation(summary = "User creation")
    @PostMapping("/created")
    public ResponseEntity<User> createUser(@RequestBody @Valid User user, BindingResult bindingResult) {
        if (!bindingResult.hasErrors()) {
            userService.createUser(user);
            return new ResponseEntity<>(HttpStatus.CREATED);
        } else {
            throw new BadRequestEx("Validation error.Check the entered data");
        }
    }

    @Operation(summary = "User change")
    @PutMapping
    public ResponseEntity<HttpStatus> updateUser(@RequestBody @Valid User user, BindingResult bindingResult) {
        if (!bindingResult.hasErrors()) {
            userService.updateUser(user);
            return new ResponseEntity<>(HttpStatus.OK);
        } else {
            throw new BadRequestEx("Validation error.Check the entered data");
        }
    }

    @Operation(summary = "Deleting a user")
    @DeleteMapping()
    public ResponseEntity<HttpStatus> deleteUser(@RequestParam int id) {
        userService.deleteUser(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @Operation(summary = "Changing the user's password")
    @PutMapping("/changePassword")
    public ResponseEntity<HttpStatus> updateUserPassword(@RequestBody @Valid UpdatePasswordUserRequest request, BindingResult bindingResult) {
        if (!bindingResult.hasErrors()) {
            userService.updateUserPassword(request);
            return new ResponseEntity<>(HttpStatus.OK);
        } else {
            throw new BadRequestEx("Validation error.Check the entered data");
        }
    }

    @Operation(summary = "Changing the user's password")
    @PutMapping("/admin/changePasswordAdmin")
    public ResponseEntity<HttpStatus> updateUserPasswordAdmin(@RequestBody @Valid UpdatePasswordUserRequest request, BindingResult bindingResult) {
        if (!bindingResult.hasErrors()) {
            userService.updateUserPasswordAdmin(request);
            return new ResponseEntity<>(HttpStatus.OK);
        } else {
            throw new BadRequestEx("Validation error.Check the entered data");
        }
    }

    @Operation(summary = "User change")
    @PutMapping("/admin")
    public ResponseEntity<HttpStatus> updateUserAdmin(@RequestBody @Valid User user, BindingResult bindingResult) {
        if (!bindingResult.hasErrors()) {
            userService.updateUserAdmin(user);
            return new ResponseEntity<>(HttpStatus.OK);
        } else {
            throw new BadRequestEx("Validation error.Check the entered data");
        }
    }

    @Operation(summary = "All users information")
    @GetMapping("/admin")
    public ResponseEntity<List<User>> getAllUsers() {
        return new ResponseEntity<>(userService.getAllUsers(), HttpStatus.OK);
    }

    @Operation(summary = "All information from one user")
    @GetMapping("/admin/{id}")
    public ResponseEntity<User> getUserById(@PathVariable int id) {
        return new ResponseEntity<>(userService.getUserById(id), HttpStatus.OK);
    }

    @Operation(summary = "Deleting a user")
    @DeleteMapping("/admin")
    public ResponseEntity<HttpStatus> deleteUserByAdmin(@RequestParam int id) {
        userService.deleteUserByAdmin(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}