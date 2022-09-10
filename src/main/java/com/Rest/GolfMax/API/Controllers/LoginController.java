package com.Rest.GolfMax.API.Controllers;

import com.Rest.GolfMax.API.DTOs.UserDto;
import com.Rest.GolfMax.API.Models.User;
import com.Rest.GolfMax.API.Services.Interfaces.UserService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.*;

import java.security.NoSuchAlgorithmException;
import java.security.spec.InvalidKeySpecException;

@RestController
@RequestMapping("/users/auth")
public class LoginController {

    @Autowired
    private ModelMapper modelMapper;
    private final BCryptPasswordEncoder bCryptPasswordEncoder = new BCryptPasswordEncoder();
    private final UserService USER_SERVICE;

    public LoginController(UserService userService) {
        super();
        this.USER_SERVICE = userService;
    }

    @PostMapping("/signin")
    public ResponseEntity<UserDto> login(@RequestBody UserDto userRequest) throws NoSuchAlgorithmException,
            InvalidKeySpecException {
        User user = modelMapper.map(userRequest, User.class);
        if (USER_SERVICE.isValidLoginRequest(user)) {
            UserDto userResponse = modelMapper.map(user, UserDto.class);
            String hashedPassword = bCryptPasswordEncoder.encode(userResponse.getPassword());
            userResponse.setPassword(hashedPassword);
            return new ResponseEntity<>(userResponse, HttpStatus.OK);
        }
        else {
            return new ResponseEntity<>(HttpStatus.UNAUTHORIZED);
        }
    }
}
