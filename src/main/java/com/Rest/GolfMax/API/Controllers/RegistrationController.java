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
        this.userService = userService;
    }

    @PostMapping("/user/signup/verification")
    public ResponseEntity<User> registerUser(@RequestBody User userRequest, HttpServletRequest request)
            throws UnsupportedEncodingException, MessagingException, NoSuchAlgorithmException, InvalidKeySpecException {

        if (!userService.isValidRegistrationRequest(userRequest.getUsername(), userRequest.getEmail()))
            return new ResponseEntity<>(HttpStatus.UNAUTHORIZED);
        else {
            User userResponse = userService.registerUser(userRequest, getVerificationSiteUrl(request));
            return new ResponseEntity<>(userResponse, HttpStatus.CREATED);
        }
    }

    @GetMapping("/verify")
    public String sendVerificationSite(@Param("code") String code, Model model) {
        boolean verified = userService.isValidVerificationCode(code);
        String pageTitle = verified ? "Successful Verification" : "Verification failed";
        model.addAttribute("pageTitle", pageTitle);

        if (verified)
            return "successful_verification";
        else
            return "failed_verification";

    }

    private String getVerificationSiteUrl(HttpServletRequest request) {
        String siteURL = request.getRequestURL().toString();
        return siteURL.replace(request.getServletPath(), "");
    }
}