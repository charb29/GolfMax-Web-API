package com.Rest.GolfMax.API.Users;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/users")
public class RegistrationController {

    @Autowired
    UserRepository userRepository;

    @PostMapping("/account")
    public ResponseEntity<User> registerUser(@RequestBody User user) {
        if (userRepository.existsByUsername(user.getUsername()) || userRepository.existsByEmail(user.getEmail())) {
            return new ResponseEntity<>(user, HttpStatus.BAD_REQUEST);
        }
        userRepository.save(user);

        return new ResponseEntity<User>(user, HttpStatus.CREATED);
    }
}