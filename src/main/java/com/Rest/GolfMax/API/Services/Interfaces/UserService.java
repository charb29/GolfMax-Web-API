package com.Rest.GolfMax.API.Services.Interfaces;

import com.Rest.GolfMax.API.Models.User;

import java.util.List;
import java.util.Optional;

public interface UserService {
    List<User> getAllUsers();
    User updateUser(User user, Long id);
    User createUser(User user);
    User getUserById(Long id);
    void deleteUser(Long id);
    boolean userExists(User user);
    User getUserData(User user);
}
