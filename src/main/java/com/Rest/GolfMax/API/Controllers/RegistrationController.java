package com.Rest.GolfMax.API.Controllers;

import com.Rest.GolfMax.API.Models.User;
import com.Rest.GolfMax.API.Repositories.UserRepository;
import com.Rest.GolfMax.API.Services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/users")
public class RegistrationController {

    @Autowired
    UserService userService;

    @Autowired
    UserRepository userRepository;

    @PostMapping("/account")
    public ResponseEntity<User> registerUser(@RequestBody User user) {
        if (userRepository.existsByUsername(user.getUsername()) || userRepository.existsByEmail(user.getUsername())) {
            return new ResponseEntity<>(user, HttpStatus.BAD_REQUEST);
        }
        else {
            userRepository.save(user);
            return new ResponseEntity<User>(user, HttpStatus.CREATED);
        }
    }
}