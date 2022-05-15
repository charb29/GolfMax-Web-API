package com.Rest.GolfMax.API;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import java.util.List;


@RestController
@RequestMapping("/users")
public class RegistrationController {

    @Autowired
    UserService userService;

    @PostMapping("/register")
    public ResponseEntity<?> registerUser(@RequestBody User newUser) {
        List<User> allUsers = userService.listAllUsers();

        if (allUsers.contains(newUser)) {
            return ResponseEntity.badRequest().body("Registration failed. User already exists.");
        }
        else {
            userService.saveUser(newUser);
            return new ResponseEntity<User> (newUser, HttpStatus.OK);
        }
    }
}