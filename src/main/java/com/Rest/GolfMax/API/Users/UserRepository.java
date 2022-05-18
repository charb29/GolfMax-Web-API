package com.Rest.GolfMax.API.Users;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {
    
    public User findByUsername(String username);
    public User findByPassword(String password);
    public User findByEmail(String email);
    public Boolean existsByUsername(String username);
    public Boolean existsByEmail(String email);
}