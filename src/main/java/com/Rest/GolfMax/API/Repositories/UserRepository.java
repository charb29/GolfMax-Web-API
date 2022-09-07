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
    @Query("SELECT u.password FROM User u where u.username = :username")
    String getPasswordUsingUsername(@Param("username") String username);
}
