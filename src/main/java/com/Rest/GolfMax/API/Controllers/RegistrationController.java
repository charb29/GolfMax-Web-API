package com.Rest.GolfMax.API.Controllers;

import com.Rest.GolfMax.API.Models.User;
import com.Rest.GolfMax.API.Services.Interfaces.UserService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/users/auth")
public class RegistrationController {

    private final UserService USER_SERVICE;

    public RegistrationController(UserService userService) {
        super();
        this.USER_SERVICE = userService;
    }

    @PostMapping("/signup")
    public ResponseEntity<User> registerUser(@RequestBody User userRequest) {
        if (USER_SERVICE.userExists(userRequest)) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
        User userResponse = USER_SERVICE.createUser(userRequest);
        return new ResponseEntity<>(userResponse, HttpStatus.CREATED);
    }
}