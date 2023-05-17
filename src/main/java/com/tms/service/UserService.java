package com.tms.service;


import com.tms.domain.User;
import com.tms.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;


import java.sql.Date;
import java.util.ArrayList;


@Service
public class UserService {

    UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    private User currentUser = null;

    @Autowired
    public UserService(UserRepository userRepository, PasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
    }

    public User getUserById(int id) {
        return userRepository.findById(id).orElse(null); //+
    }

    public User createUser(User user) {
        user.setCreated(new Date(System.currentTimeMillis()));
        user.setChanged(new Date(System.currentTimeMillis()));
        user.setPasswordUser(passwordEncoder.encode(user.getPasswordUser()));
        return userRepository.save(user);
    }

    public User updateUser(User user) {
        user.setCreated(new Date(System.currentTimeMillis()));
        user.setPasswordUser(passwordEncoder.encode(user.getPasswordUser()));
        return userRepository.saveAndFlush(user);
    }

    @Transactional
    public void deleteUser(int id) {
        userRepository.deleteById(id);
    }

    public ArrayList<User> getAllUsers() {
        return (ArrayList<User>) userRepository.findAll();
    }


    public User getCurrentUser() {
        if (this.currentUser != null)
            return this.currentUser;
        else {
            Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
            String email = authentication.getName();
            User user = userRepository.findUserByEmail(email);
            this.currentUser = user;
            return user;
        }
    }

    public void updateCurrentUser(User user) {
        User currentUser = getCurrentUser();
        if (currentUser != null)
            updateUserById(currentUser.getId(), user);
    }

    public Boolean updateUserById(int id, User user) {
        User userDBEntity = getUserById(id);
        if (userDBEntity == null)
            return false;
        if (user.getPasswordUser() != null)
            userDBEntity.setPasswordUser(passwordEncoder.encode(user.getPasswordUser()));
        userDBEntity.setChanged(new Date(new java.util.Date().getTime()));
        userRepository.saveAndFlush(userDBEntity);
        return true;
    }

    @Transactional
    public void deleteCurrentUser(int id) {
        userRepository.deleteById(id);
        userRepository.flush();
    }


    public boolean loginExist(String login) {
        boolean b = userRepository.findUserByEmail(login) != null ? true : false;
        return b;
    }

    public boolean findUserById(int id) {
        return userRepository.findUserById(id) != null;
    }

    public Integer getCurrentUserId() {
        if (currentUser != null)
            return currentUser.getId();
        return null;
    }
}


