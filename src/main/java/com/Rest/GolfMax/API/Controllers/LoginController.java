package com.Rest.GolfMax.API.Controllers;

import com.Rest.GolfMax.API.Models.User;
import com.Rest.GolfMax.API.Services.UserService;
import org.jetbrains.annotations.NotNull;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/users")
public class LoginController {
    @Autowired
    private UserService service;

    @PostMapping("/login")
    public ResponseEntity<User> login(@RequestBody @NotNull User user) {
        User storedUser = service.getStoredUserData(user.getUsername(), user.getPassword());
        if (storedUser == null)
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        else
            return new ResponseEntity<>(storedUser, HttpStatus.OK);
    }
}
