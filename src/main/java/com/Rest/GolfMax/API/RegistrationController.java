package com.Rest.GolfMax.API;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/users")
public class RegistrationController {
    
    @Autowired
    UserService userService;

    @PostMapping("/register")
    public ResponseEntity<User> authenticateUser(@RequestBody User user) {
        User existingEmail = userService.getStoredEmail(user.getEmail());

        if (existingEmail != null) {
            return null;
        }
        else {
            userService.saveUser(user);
            return new ResponseEntity<User>(user, HttpStatus.OK);
        }
    }
}