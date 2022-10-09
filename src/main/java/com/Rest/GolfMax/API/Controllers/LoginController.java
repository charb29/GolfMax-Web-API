package com.Rest.GolfMax.API.Controllers;

import com.Rest.GolfMax.API.Models.User;
import com.Rest.GolfMax.API.Services.Interfaces.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.security.NoSuchAlgorithmException;
import java.security.spec.InvalidKeySpecException;

@RestController
@RequestMapping("/user")
public class LoginController {

    private final UserService userService;

    @Autowired
    public LoginController(UserService userService) {
        super();
        if (userService == null) {
            throw new NullPointerException("User Service");
        }
        this.userService = userService;
    }

    @PostMapping("/sign-in")
    public ResponseEntity<User> login(@RequestBody User userRequest)
            throws NoSuchAlgorithmException, InvalidKeySpecException {

        if (userService.isValidLoginRequest(userRequest)) {
            return new ResponseEntity<>(userRequest, HttpStatus.OK);
        }
        else {
            return new ResponseEntity<>(HttpStatus.UNAUTHORIZED);
        }
    }
}
