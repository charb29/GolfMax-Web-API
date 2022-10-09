package com.Rest.GolfMax.API.Services.Interfaces;

import com.Rest.GolfMax.API.Models.User;

import javax.mail.MessagingException;
import java.io.UnsupportedEncodingException;
import java.security.NoSuchAlgorithmException;
import java.security.spec.InvalidKeySpecException;
import java.util.List;

public interface UserService {
    List<User> getAllUsers();
    User updateUser(User user, Long id) throws NoSuchAlgorithmException, InvalidKeySpecException;
    User getUserById(Long id);
    void deleteUserById(Long id);
    boolean isValidPassword(User user) throws NoSuchAlgorithmException, InvalidKeySpecException;
    boolean isValidUsername(User user);
    boolean isValidLoginRequest(User user);
    boolean isValidEmail(User user);
    User registerUser(User user, String siteURL)
            throws UnsupportedEncodingException, MessagingException,
            NoSuchAlgorithmException, InvalidKeySpecException;
    boolean isValidVerificationCode(String verificationCode);
}
