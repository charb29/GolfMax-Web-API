package com.Rest.GolfMax.API.Repositories;

import com.Rest.GolfMax.API.Models.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {
    
    public User findByUsername(String username);
    
    public User findByPassword(String password);

    public User findByEmail(String email);

    public Boolean existsByUsername(String username);

    public Boolean existsByEmail(String email);

    @Query("SELECT u FROM User u WHERE u.username = :username and u.password = :password")
    public User findUserData(@Param("username") String username,
                                   @Param("password") String password);
    
}
