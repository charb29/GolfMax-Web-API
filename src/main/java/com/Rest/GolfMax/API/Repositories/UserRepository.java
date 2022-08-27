package com.Rest.GolfMax.API.Repositories;

import com.Rest.GolfMax.API.Models.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {

    Boolean existsByUsername(String username);

    Boolean existsByEmail(String email);

    @Query("SELECT u FROM User u WHERE u.username = :username and u.password = :password")
    User findUserData(@Param("username") String username,
                                   @Param("password") String password);
    
}
