package com.Rest.GolfMax.API;

import java.util.List;

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
        List<User> allUsers = userService.listAllUsers();
        User returningUser = userService.getStoredUsername(user.getUsername());

        if (allUsers.contains(returningUser)) {
            return new ResponseEntity<User> (returningUser, HttpStatus.OK);
        }
        else {
            return ResponseEntity.badRequest().body("Login failed. Invalid credentials.");
        }
    }
}
