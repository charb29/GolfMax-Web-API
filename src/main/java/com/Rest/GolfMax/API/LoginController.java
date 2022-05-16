package com.Rest.GolfMax.API;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/users")
public class LoginController {
    
    @Autowired
    UserService userService;

    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody User user) {
        User storedUsername = userService.getStoredUsername(user.getUsername());
        User storedPassword = userService.getStoredEmail(user.getEmail());

        if (storedUsername != storedPassword) {
        return ResponseEntity.badRequest().body("Login failed. Invalid credentials.");
        }
        else {
            return new ResponseEntity<User> (user, HttpStatus.OK);
        }
    }
}
