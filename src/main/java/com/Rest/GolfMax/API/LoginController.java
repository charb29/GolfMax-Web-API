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

    @GetMapping("/login")
    public ResponseEntity<User> login(@RequestBody User user) {
        User storedUsername = userService.getStoredUsername(user.getUsername());
        User storedPassword = userService.getStoredPassword(user.getPassword());

        if (storedUsername == storedPassword) {
            return new ResponseEntity<User> (storedUsername, HttpStatus.OK);
        }
        else if (storedUsername == null) {
            return new ResponseEntity<User> (HttpStatus.NOT_FOUND);
        }
        else {
            return new ResponseEntity<User> (HttpStatus.NOT_FOUND);
        }
    }
}
