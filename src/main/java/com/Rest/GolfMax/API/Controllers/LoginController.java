package com.Rest.GolfMax.API.Controllers;

import com.Rest.GolfMax.API.Models.User;
import com.Rest.GolfMax.API.Services.Interfaces.UserService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/users/auth")
public class LoginController {

    private final UserService USER_SERVICE;

    public LoginController(UserService userService) {
        super();
        this.USER_SERVICE = userService;
    }

    @PostMapping("/signin")
    public ResponseEntity<User> login(@RequestBody User user) {
        if (USER_SERVICE.validateUser(user))
            return new ResponseEntity<>(user, HttpStatus.OK);
        else
            return new ResponseEntity<>(HttpStatus.UNAUTHORIZED);
    }
}
