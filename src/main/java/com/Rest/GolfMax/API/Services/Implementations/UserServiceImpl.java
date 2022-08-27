package com.Rest.GolfMax.API.Services.Implementations;

import com.Rest.GolfMax.API.Models.User;
import com.Rest.GolfMax.API.Repositories.UserRepository;
import com.Rest.GolfMax.API.Services.Interfaces.UserService;
import org.apache.velocity.exception.ResourceNotFoundException;
import org.jetbrains.annotations.NotNull;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.web.context.annotation.RequestScope;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;


@Service
@Transactional
@RequestScope
public class UserServiceImpl implements UserService {
    private final UserRepository USER_REPOSITORY;

    public UserServiceImpl(UserRepository USER_REPOSITORY) {
        super();
        this.USER_REPOSITORY = USER_REPOSITORY;
    }

    @Override
    public List<User> getAllUsers() {
        return USER_REPOSITORY.findAll(Sort.by(Sort.Direction.ASC, "username"));
    }

    @Override
    public User createUser(User user) {
        USER_REPOSITORY.save(user);
        return user;
    }

    @Override
    public Optional<User> getUserById(long id) {
        return USER_REPOSITORY.findById(id);
    }

    @Override
    public void deleteUser(long id) {
        USER_REPOSITORY.deleteById(id);
    }

    @Override
    public User updateUser(@NotNull User userRequest, long id) {
        User updatedUser = USER_REPOSITORY.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("id"));
        updatedUser.setUsername(userRequest.getUsername());
        updatedUser.setEmail(userRequest.getEmail());
        updatedUser.setPassword(userRequest.getPassword());
        return USER_REPOSITORY.save(updatedUser);
    }
    @Override
    public boolean userExists(User user) {
        return USER_REPOSITORY.existsByUsername(user.getUsername()) && !USER_REPOSITORY.existsByEmail(user.getEmail());
    }
    @Override
    public User getUserData(User user) {
        return USER_REPOSITORY.findUserData(user.getUsername(), user.getPassword());
    }
}
