package com.Rest.GolfMax.API.Services.Interfaces;

import com.Rest.GolfMax.API.Models.User;

import java.util.List;

public interface UserService {
    List<User> getAllUsers();
    User updateUser(User user, long id);
    User createUser(User user);
    User getUserById(long id);
    void deleteUser(long id);
    boolean userExists(User user);
    User getUserData(User user);
}
