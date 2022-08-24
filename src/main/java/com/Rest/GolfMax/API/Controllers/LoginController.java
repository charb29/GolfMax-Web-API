package com.Rest.GolfMax.API.Controllers;

import com.Rest.GolfMax.API.DTOs.UserDto;
import com.Rest.GolfMax.API.Models.User;
import com.Rest.GolfMax.API.Services.Implementations.UserServiceImpl;
import com.Rest.GolfMax.API.Services.Interfaces.UserService;
import org.jetbrains.annotations.NotNull;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/users/auth")
public class LoginController {
    @Autowired
    private ModelMapper modelMapper;
    private final UserService USER_SERVICE;

    public LoginController(UserService userService) {
        super();
        this.USER_SERVICE = userService;
    }

    @PostMapping("/signin")
    public ResponseEntity<UserDto> login(@RequestBody UserDto userDto) {
        User userRequest = modelMapper.map(userDto, User.class);
        UserDto userResponse = modelMapper.map(userRequest, UserDto.class);

        if (USER_SERVICE.getUserData(userRequest) != null)
            return new ResponseEntity<>(userResponse, HttpStatus.OK);
        else
            return new ResponseEntity<>(HttpStatus.UNAUTHORIZED);
    }
}
