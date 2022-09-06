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
        User existingUser = USER_SERVICE.findByUsernameEmail(userDto.getUsername(), userDto.getPassword());

        if (existingUser != null && existingUser.getUsername() != null && !existingUser.getUsername().isEmpty() &&
                existingUser.getEmail() != null && !existingUser.getEmail().isEmpty()) {
            return new ResponseEntity<>(HttpStatus.UNAUTHORIZED);
        }
        User userRequest = modelMapper.map(userDto, User.class);
        User savedUser = USER_SERVICE.createUser(userRequest);
        UserDto userResponse = modelMapper.map(savedUser, UserDto.class);
        return new ResponseEntity<>(userResponse, HttpStatus.CREATED);
    }
}