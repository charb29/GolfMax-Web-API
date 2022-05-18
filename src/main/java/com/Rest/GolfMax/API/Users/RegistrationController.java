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

    @PostMapping("/register")
    public ResponseEntity<?> authenticateUser(@RequestBody User user) {
        if (userRepository.existsByUsername(user.getUsername()) || userRepository.existsByEmail(user.getEmail())) {
            return new ResponseEntity<>("Username or email is already taken.", HttpStatus.BAD_REQUEST);
        }

        User registerUser = new User();
        registerUser.setUsername(user.getUsername());
        registerUser.setPassword(user.getPassword());
        registerUser.setEmail(user.getEmail());

        userRepository.save(registerUser);

        return new ResponseEntity<>("User registered successfully.", HttpStatus.OK);
    }
}