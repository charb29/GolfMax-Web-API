package com.Rest.GolfMax.API.Repositories;

import com.Rest.GolfMax.API.Models.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {

    boolean existsByUsername(String username);

    boolean existsByEmail(String email);

    @Query("SELECT u.password FROM User u WHERE u.username = :username")
    String getPasswordUsingUsername(@Param("username") String username);

    @Query("SELECT u FROM User u WHERE u.verificationCode = ?1")
    User findByVerificationCode(String verificationCode);

    @Query("SELECT u.isEnabled FROM User u WHERE u.username = :username")
    boolean getIsEnabledUsingUsername(@Param("username") String username);

}
