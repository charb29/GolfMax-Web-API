package com.Rest.GolfMax.API.Controllers;

import com.Rest.GolfMax.API.DTOs.UserDto;
import com.Rest.GolfMax.API.Models.User;
import com.Rest.GolfMax.API.Services.Interfaces.UserService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/users")
public class UserController {
    @Autowired
    private ModelMapper modelMapper;
    private final UserService USER_SERVICE;

    public UserController(UserService userService) {
        super();
        this.USER_SERVICE = userService;
    }

    @GetMapping
    public List<UserDto> getAllUsers() {
        return USER_SERVICE.getAllUsers()
                .stream()
                .map(post -> modelMapper.map(post, UserDto.class))
                .collect(Collectors.toList());
    }

    @GetMapping("/{id}")
    public ResponseEntity<UserDto> getUserById(@PathVariable long id) {
        try {
            User user = USER_SERVICE.getUserById(id);
            UserDto userResponse = modelMapper.map(user, UserDto.class);
            return new ResponseEntity<>(userResponse, HttpStatus.OK);
        } catch (NoSuchElementException e) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @PutMapping("/{id}")
    public ResponseEntity<UserDto> updateUser(@PathVariable long id, @RequestBody UserDto userDto) {
        User userRequest = modelMapper.map(userDto, User.class);
        User user = USER_SERVICE.updateUser(userRequest, id);
        UserDto userResponse = modelMapper.map(user, UserDto.class);
        return new ResponseEntity<>(userResponse, HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public void deleteUser(@PathVariable long id) {
        USER_SERVICE.deleteUser(id);
    }
}
