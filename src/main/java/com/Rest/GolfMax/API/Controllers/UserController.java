package com.Rest.GolfMax.API.Controllers;

import com.Rest.GolfMax.API.Models.User;
import com.Rest.GolfMax.API.Services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;
import java.util.NoSuchElementException;

@RestController
@RequestMapping("/users")
public class UserController {
    @Autowired
    private UserService userService;

    @GetMapping("")
    public List<User> getAllUsers() {
        return userService.listAllUsers();
    }

    @GetMapping("/{id}")
    public ResponseEntity<User> getUserById(@PathVariable Long id) {
        try {
            User user = userService.getUserById(id);
            return new ResponseEntity<> (user, HttpStatus.OK);
        } catch (NoSuchElementException e) {
            return new ResponseEntity<> (HttpStatus.NOT_FOUND);
        }
    }

    @PutMapping("/{id}/update")
    public ResponseEntity<User> updateExistingUser(@RequestBody User updateUser,
                                                   @PathVariable long id) {
        return new ResponseEntity<>(userService.updateUser(id, updateUser), HttpStatus.OK);
    }   

    @DeleteMapping("/{id}")
    public void deleteUser(@PathVariable Long id) {
        userService.deleteUser(id);
    }
}
