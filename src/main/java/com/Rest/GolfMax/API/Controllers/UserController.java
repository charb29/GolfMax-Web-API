package com.Rest.GolfMax.API.Controllers;

import com.Rest.GolfMax.API.DTOs.UserDto;
import com.Rest.GolfMax.API.Models.User;
import com.Rest.GolfMax.API.Services.Interfaces.UserService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.security.NoSuchAlgorithmException;
import java.security.spec.InvalidKeySpecException;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/users")
public class UserController {

    private final ModelMapper MODEL_MAPPER;
    private final UserService USER_SERVICE;

    @Autowired
    public UserController(UserService userService, ModelMapper modelMapper) {
        super();
        this.USER_SERVICE = userService;
        this.MODEL_MAPPER = modelMapper;
    }

    @GetMapping
    public ResponseEntity<List<UserDto>> getAllUsers() {
        List<UserDto> userResponse = USER_SERVICE.getAllUsers().stream()
                .map(users -> MODEL_MAPPER.map(users, UserDto.class))
                .collect(Collectors.toList());
        if (userResponse.size() == 0) {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
        else {
            return new ResponseEntity<>(userResponse, HttpStatus.OK);
        }
    }

    @GetMapping("/{id}")
    public ResponseEntity<UserDto> getUserById(@PathVariable Long id) {
        try {
            User userRequest = USER_SERVICE.getUserById(id);
            UserDto userResponse = MODEL_MAPPER.map(userRequest, UserDto.class);
            return new ResponseEntity<>(userResponse, HttpStatus.FOUND);
        } catch (NoSuchElementException e) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @PutMapping("/{id}")
    public ResponseEntity<UserDto> updateUserInfo(@PathVariable Long id,
                                                  @RequestBody UserDto userRequest) throws NoSuchAlgorithmException,
            InvalidKeySpecException {
        User user = MODEL_MAPPER.map(userRequest, User.class);
        User updatedUser = USER_SERVICE.updateUser(user, id);
        UserDto userResponse = MODEL_MAPPER.map(updatedUser, UserDto.class);
        return new ResponseEntity<>(userResponse, HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<UserDto> deleteUser(@PathVariable Long id) {
        User user = USER_SERVICE.getUserById(id);
        UserDto userResponse = MODEL_MAPPER.map(user, UserDto.class);
        if (userResponse == null) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        else {
            USER_SERVICE.deleteUser(id);
            return new ResponseEntity<>(userResponse, HttpStatus.OK);
        }
    }
}

