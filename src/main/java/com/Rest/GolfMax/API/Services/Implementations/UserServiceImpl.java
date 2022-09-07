package com.Rest.GolfMax.API.Services.Implementations;

import com.Rest.GolfMax.API.Models.User;
import com.Rest.GolfMax.API.Repositories.UserRepository;
import com.Rest.GolfMax.API.Services.Interfaces.UserService;
import org.apache.velocity.exception.ResourceNotFoundException;
import org.jetbrains.annotations.NotNull;
import org.springframework.data.domain.Sort;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.web.context.annotation.RequestScope;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;
import java.util.regex.Pattern;


@Service
@Transactional
@RequestScope
public class UserServiceImpl implements UserService {

    private final UserRepository USER_REPOSITORY;
    private final BCryptPasswordEncoder bCryptPasswordEncoder = new BCryptPasswordEncoder();

    public UserServiceImpl(UserRepository USER_REPOSITORY) {
        super();
        this.USER_REPOSITORY = USER_REPOSITORY;
    }

    @Override
    public List<User> getAllUsers() {
        return USER_REPOSITORY.findAll(Sort.by(Sort.Direction.ASC, "username"));
    }

    @Override
    public User createUser(User userRequest) {
        User userResponse = new User();
        userResponse.setUsername(userRequest.getUsername());
        userResponse.setEmail(userRequest.getEmail());
        userResponse.setPassword(encryptPassword(userRequest.getPassword()));
        USER_REPOSITORY.save(userResponse);

        return userResponse;
    }

    @Override
    public Optional<User> getUserById(Long id) {
        return USER_REPOSITORY.findById(id);
    }

    @Override
    public void deleteUser(Long id) {
        USER_REPOSITORY.deleteById(id);
    }

    @Override
    public User updateUser(@NotNull User userRequest, Long id) {
        User updatedUser = USER_REPOSITORY.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("id"));
        updatedUser.setUsername(userRequest.getUsername());
        updatedUser.setEmail(userRequest.getEmail());
        updatedUser.setPassword(encryptPassword(userRequest.getPassword()));
        return USER_REPOSITORY.save(updatedUser);
    }


    @Override
    public boolean isValidLoginRequest(User user) {
        String hashedPassword = USER_REPOSITORY.getPasswordUsingUsername(user.getUsername());
        String password = user.getPassword();
        String username = user.getUsername();

        return bCryptPasswordEncoder.matches(password, hashedPassword)
                && USER_REPOSITORY.existsByUsername(username);
    }

    @Override
    public boolean isValidRegistrationRequest(String username, String email) {
        return !isValidUsername(username) && !isValidEmail(email);
    }

    private String encryptPassword(String password) {
        return bCryptPasswordEncoder.encode(password);
    }

    private boolean isValidUsername(String username) {
        return USER_REPOSITORY.existsByUsername(username);
    }

    private boolean isValidEmail(String email) {
        return USER_REPOSITORY.existsByEmail(email);
    }
}
