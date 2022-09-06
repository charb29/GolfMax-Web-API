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
        return USER_SERVICE.getAllUsers().stream()
                .map(user -> modelMapper.map(user, UserDto.class))
                .collect(Collectors.toList());
    }

    @GetMapping("/{id}")
    public ResponseEntity<UserDto> getUserById(@PathVariable Long id) {
        try {
            User userRequest = USER_SERVICE.getUserById(id);
            UserDto userResponse = modelMapper.map(userRequest, UserDto.class);
            return new ResponseEntity<>(userResponse, HttpStatus.FOUND);
        } catch (NoSuchElementException e) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @PutMapping("/{id}")
    public ResponseEntity<UserDto> updateUserInfo(@PathVariable Long id,
                                                  @RequestBody UserDto userRequest) {
        User user = modelMapper.map(userRequest, User.class);
        User updatedUser = USER_SERVICE.updateUser(user, id);
        UserDto userResponse = modelMapper.map(updatedUser, UserDto.class);
        return new ResponseEntity<>(userResponse, HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public void deleteUser(@PathVariable Long id) {
        USER_SERVICE.deleteUser(id);
    }
}
