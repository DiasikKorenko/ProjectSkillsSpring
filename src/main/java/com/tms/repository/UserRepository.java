package com.tms.repository;

import com.tms.domain.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<User,Integer> {

    Optional<User> findUserByEmail(String email);

    @Modifying
    @Query(
            nativeQuery = true,
            value = "UPDATE users SET password_user = :newPass WHERE id = :id",
            countQuery = "SELECT * FROM users_table WHERE id = :id")
    void updateUserPassword(int id, String newPass);

}
