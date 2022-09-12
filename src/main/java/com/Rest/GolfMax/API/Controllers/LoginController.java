package com.Rest.GolfMax.API.Controllers;

import com.Rest.GolfMax.API.DTOs.UserDto;
import com.Rest.GolfMax.API.Models.User;
import com.Rest.GolfMax.API.Repositories.UserRepository;
import com.Rest.GolfMax.API.Services.Interfaces.UserService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.security.NoSuchAlgorithmException;
import java.security.spec.InvalidKeySpecException;

@RestController
@RequestMapping("/users/auth")
public class LoginController {
    private final ModelMapper MODEL_MAPPER;
    private final UserService USER_SERVICE;

    private final UserRepository USER_REPO;
    @Autowired
    public LoginController(UserService userService, ModelMapper modelMapper, UserRepository userRepository) {
        super();
        this.USER_SERVICE = userService;
        this.MODEL_MAPPER = modelMapper;
        this.USER_REPO = userRepository;
    }

    @PostMapping("/signin")
    public ResponseEntity<UserDto> login(@RequestBody UserDto userRequest) throws NoSuchAlgorithmException,
            InvalidKeySpecException {
        User user = MODEL_MAPPER.map(userRequest, User.class);
        if (USER_SERVICE.isValidLoginRequest(user)) {
            User userInfo = USER_REPO.findByUsername(user.getUsername());
            UserDto userResponse = MODEL_MAPPER.map(userInfo, UserDto.class);
            return new ResponseEntity<>(userResponse, HttpStatus.OK);
        }
        else {
            return new ResponseEntity<>(HttpStatus.UNAUTHORIZED);
        }
    }
}
