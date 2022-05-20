package com.Rest.GolfMax.API.Users;

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
        User storedUser = userService.getStoredUserData(user.getUsername(), user.getPassword(), user.getEmail());

        if (storedUser == null) {
            return new ResponseEntity<>(user, HttpStatus.BAD_REQUEST);
        }
        else {
            return new ResponseEntity<User> (storedUser, HttpStatus.OK);
        }
    }
}
