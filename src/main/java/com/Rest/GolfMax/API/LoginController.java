package com.Rest.GolfMax.API;

import java.util.NoSuchElementException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/users")
public class LoginController {

    @Autowired
    UserService userService;

    @GetMapping("/login")
    public ResponseEntity<User> login(@RequestBody User user) {
        try {
            User storedUsername = userService.getUsername(user.getUsername());
            return new ResponseEntity<User> (storedUsername, HttpStatus.OK);
        } catch (NoSuchElementException e) {
            return new ResponseEntity<User> (HttpStatus.NOT_FOUND);
        }
    }
}
