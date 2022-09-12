package com.Rest.GolfMax.API.Controllers;

import com.Rest.GolfMax.API.DTOs.UserDto;
import com.Rest.GolfMax.API.Models.User;
import com.Rest.GolfMax.API.Services.Interfaces.UserService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.query.Param;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.mail.MessagingException;
import javax.servlet.http.HttpServletRequest;
import java.io.UnsupportedEncodingException;
import java.security.NoSuchAlgorithmException;
import java.security.spec.InvalidKeySpecException;

@Controller
public class RegistrationController {

    private final ModelMapper MODEL_MAPPER;
    private final UserService USER_SERVICE;

    @Autowired
    public RegistrationController(UserService userService, ModelMapper modelMapper) {
        super();
        this.USER_SERVICE = userService;
        this.MODEL_MAPPER = modelMapper;
    }

    @PostMapping("/users/auth/signup/verification")
    public ResponseEntity<UserDto> registerUser(@RequestBody UserDto userRequest, HttpServletRequest request)
        throws UnsupportedEncodingException, MessagingException,
            NoSuchAlgorithmException, InvalidKeySpecException {

        if (!USER_SERVICE.isValidRegistrationRequest(userRequest.getUsername(), userRequest.getEmail())) {
            return new ResponseEntity<>(HttpStatus.UNAUTHORIZED);
        }
        else {
            User user = MODEL_MAPPER.map(userRequest, User.class);
            User registeredUser = USER_SERVICE.registerUser(user, getSiteURL(request));
            UserDto userResponse = MODEL_MAPPER.map(registeredUser, UserDto.class);
            return new ResponseEntity<>(userResponse, HttpStatus.CREATED);
        }
    }

    @GetMapping("/verify")
    public String verifyUser(@Param("code") String code, Model model) {
        boolean verified = USER_SERVICE.isValidVerificationCode(code);

        String pageTitle = verified ? "Successful Verification" : "Verification failed";
        model.addAttribute("pageTitle", pageTitle);

        if (verified) {
            return "successful_verification";
        }
        else {
            return "failed_verification";
        }
    }

    private String getSiteURL(HttpServletRequest request) {
        String siteURL = request.getRequestURL().toString();
        return siteURL.replace(request.getServletPath(), "");
    }
}