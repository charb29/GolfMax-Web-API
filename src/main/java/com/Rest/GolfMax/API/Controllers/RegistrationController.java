package com.Rest.GolfMax.API.Controllers;

import com.Rest.GolfMax.API.Models.User;
import com.Rest.GolfMax.API.Services.Interfaces.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.query.Param;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import javax.mail.MessagingException;
import javax.servlet.http.HttpServletRequest;
import java.io.UnsupportedEncodingException;
import java.security.NoSuchAlgorithmException;
import java.security.spec.InvalidKeySpecException;

@Controller
public class RegistrationController {

    private final UserService userService;

    @Autowired
    public RegistrationController(UserService userService) {
        super();
        if (userService == null) {
            throw new NullPointerException("User Service");
        }
        this.userService = userService;
    }

    @PostMapping("/user/signup/verification")
    public ResponseEntity registerUser(@RequestBody User userRequest, HttpServletRequest request)
            throws UnsupportedEncodingException, MessagingException, NoSuchAlgorithmException, InvalidKeySpecException {

        boolean isValidUsername = userService.isValidUsername(userRequest);
        boolean isValidEmail = userService.isValidEmail(userRequest);

        if (!isValidUsername) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Username is already taken.");
        }
        else if (!isValidEmail) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Email is already taken.");
        }
        else {
            User userResponse = userService.registerUser(userRequest, getVerificationSiteUrl(request));
            return ResponseEntity.status(HttpStatus.CREATED).body(userResponse);
        }
    }

    @GetMapping("/verify")
    public String sendVerificationSite(@Param("code") String code, Model model) {
        boolean verified = userService.isValidVerificationCode(code);
        String pageTitle = verified ? "Successful Verification" : "Verification failed";
        model.addAttribute("pageTitle", pageTitle);

        if (verified) {
            return "successful_verification";
        }
        else {
            return "failed_verification";
        }

    }

    private String getVerificationSiteUrl(HttpServletRequest request) {
        String siteURL = request.getRequestURL().toString();
        return siteURL.replace(request.getServletPath(), "");
    }
}