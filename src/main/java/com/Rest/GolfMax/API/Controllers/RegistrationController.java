package com.Rest.GolfMax.API.Controllers;

import com.Rest.GolfMax.API.DTOs.UserDto;
import com.Rest.GolfMax.API.Models.User;
import com.Rest.GolfMax.API.Services.Interfaces.UserService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/users/auth")
public class RegistrationController {
    @Autowired
    private ModelMapper modelMapper;
    private final UserService USER_SERVICE;

    public RegistrationController(UserService userService) {
        super();
        this.USER_SERVICE = userService;
    }

    @PostMapping("/signup")
    public ResponseEntity<UserDto> registerUser(@RequestBody UserDto userDto) {
        User userRequest = modelMapper.map(userDto, User.class);

        if (USER_SERVICE.userExists(userDto.getUsername(), userDto.getPassword()))
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);

        User user = USER_SERVICE.createUser(userRequest);
        UserDto userResponse = modelMapper.map(user, UserDto.class);
        return new ResponseEntity<>(userResponse, HttpStatus.CREATED);
    }
}