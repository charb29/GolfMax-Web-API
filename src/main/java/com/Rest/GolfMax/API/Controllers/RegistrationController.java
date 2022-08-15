package com.Rest.GolfMax.API.Controllers;

import com.Rest.GolfMax.API.Models.User;
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

    @PostMapping("/account")
    public ResponseEntity<User> registerUser(@RequestBody User user) {
        if (userService.userExists(user.getUsername(), user.getEmail()))
            return new ResponseEntity<>(user, HttpStatus.BAD_REQUEST);
        userService.saveUser(user);
        return new ResponseEntity<>(user, HttpStatus.CREATED);
    }
}