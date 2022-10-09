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
    public ResponseEntity login(@RequestBody User userRequest)
            throws NoSuchAlgorithmException, InvalidKeySpecException {

        boolean isValidUsername = userService.isValidUsername(userRequest);
        boolean isValidPassword = userService.isValidPassword(userRequest);
        boolean isValidLoginRequest = userService.isValidLoginRequest(userRequest);

        if (isValidUsername && isValidPassword && isValidLoginRequest) {
            return ResponseEntity.status(HttpStatus.OK).body(userRequest);
        }
        else if (!isValidUsername && !isValidPassword) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Invalid credentials.");
        }
        else if (!isValidUsername) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Username is invalid.");
        }
        else  {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Password is invalid.");
        }
    }
}
