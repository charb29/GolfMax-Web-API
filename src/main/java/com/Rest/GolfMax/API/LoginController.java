package com.Rest.GolfMax.API;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.nio.file.attribute.UserPrincipalNotFoundException;
import java.util.HashMap;
import java.util.Map;


@RestController
@RequestMapping("/users")
public class LoginController {

    @Autowired
    UserService userService;

    @GetMapping("/login")
    public ResponseEntity<?> login(@RequestBody User user) {

        User storedUsername = userService.getStoredUsername(user.getUsername());
        User storedPassword = userService.getStoredPassword(user.getPassword());

        if (storedUsername == storedPassword) {
            return new ResponseEntity<User> (storedUsername, HttpStatus.OK);
        }
        else if (storedUsername == null) {
            return ResponseEntity.badRequest().body("Login failed. Invalid username or password.");
        }
        else {
            return ResponseEntity.badRequest().body("Login failed. Invalid username or password.");
        }
    }
}
