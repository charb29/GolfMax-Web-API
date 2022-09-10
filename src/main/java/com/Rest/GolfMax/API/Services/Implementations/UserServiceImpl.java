package com.Rest.GolfMax.API.Services.Implementations;

import com.Rest.GolfMax.API.Models.User;
import com.Rest.GolfMax.API.Repositories.UserRepository;
import com.Rest.GolfMax.API.Services.Interfaces.UserService;
import org.apache.velocity.exception.ResourceNotFoundException;
import org.jetbrains.annotations.NotNull;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.web.context.annotation.RequestScope;

import javax.crypto.SecretKeyFactory;
import javax.crypto.spec.PBEKeySpec;
import javax.transaction.Transactional;
import java.math.BigInteger;
import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;
import java.security.spec.InvalidKeySpecException;
import java.util.List;
import java.util.Optional;
import java.util.regex.Pattern;


@Service
@Transactional
@RequestScope
public class UserServiceImpl implements UserService {

    private final UserRepository USER_REPOSITORY;

    public UserServiceImpl(UserRepository USER_REPOSITORY) {
        super();
        this.USER_REPOSITORY = USER_REPOSITORY;
    }

    @Override
    public List<User> getAllUsers() {
        return USER_REPOSITORY.findAll(Sort.by(Sort.Direction.ASC, "username"));
    }

    @Override
    public User createUser(User userRequest) throws NoSuchAlgorithmException, InvalidKeySpecException {
        User userResponse = new User();
        userResponse.setUsername(userRequest.getUsername());
        userResponse.setEmail(userRequest.getEmail());
        userResponse.setPassword(encryptPassword(userRequest.getPassword()));
        USER_REPOSITORY.save(userResponse);

        return userResponse;
    }

    @Override
    public Optional<User> getUserById(Long id) {
        return USER_REPOSITORY.findById(id);
    }

    @Override
    public void deleteUser(Long id) {
        USER_REPOSITORY.deleteById(id);
    }

    @Override
    public User updateUser(@NotNull User userRequest, Long id) throws NoSuchAlgorithmException, InvalidKeySpecException {
        User updatedUser = USER_REPOSITORY.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("id"));
        updatedUser.setUsername(userRequest.getUsername());
        updatedUser.setEmail(userRequest.getEmail());
        updatedUser.setPassword(encryptPassword(userRequest.getPassword()));
        return USER_REPOSITORY.save(updatedUser);
    }


    @Override
    public boolean isValidLoginRequest(User user) throws NoSuchAlgorithmException, InvalidKeySpecException {
        String encryptedPassword = USER_REPOSITORY.getPasswordUsingUsername(user.getUsername());
        String password = user.getPassword();
        String username = user.getUsername();

        return isValidPassword(password, encryptedPassword)
                && USER_REPOSITORY.existsByUsername(username);
    }

    @Override
    public boolean isValidRegistrationRequest(String username, String email) {
        return !isValidUsername(username) && !isValidEmail(email) && isValidEmailFormat(email);
    }

    @Override
    public String encryptPassword(String password) throws NoSuchAlgorithmException, InvalidKeySpecException {
        return generateHash(password);
    }

    @Override
    public boolean isValidPassword(String originalPassword, String storedPassword) throws NoSuchAlgorithmException,
            InvalidKeySpecException {
        String[] parts = storedPassword.split(":");
        int iterations = Integer.parseInt(parts[0]);
        byte[] salt = fromHex(parts[1]);
        byte[] hash = fromHex(parts[2]);

        PBEKeySpec pbeKeySpec = new PBEKeySpec(originalPassword.toCharArray(), salt, iterations, hash.length * 8);
        SecretKeyFactory secretKeyFactory = SecretKeyFactory.getInstance("PBKDF2WithHmacSHA1");
        byte[] testHash = secretKeyFactory.generateSecret(pbeKeySpec).getEncoded();

        int diff = hash.length ^ testHash.length;
        for(int i = 0; i < hash.length && i < testHash.length; i++) {
            diff |= hash[i] ^ testHash[i];
        }
        return diff == 0;
    }

    private boolean isValidUsername(String username) {
        return USER_REPOSITORY.existsByUsername(username);
    }

    private boolean isValidEmail(String email) {
        return USER_REPOSITORY.existsByEmail(email);
    }

    private boolean isValidEmailFormat(String email) {
        String regexPattern = "^[a-zA-Z0-9_+&*-]+(?:\\.[a-zA-Z0-9_+&*-]+)*@(?:[a-zA-Z0-9-]+\\.)+[a-zA-Z]{2,7}$";
        Pattern pattern = Pattern.compile(regexPattern);
        if (email == null) {
            return false;
        }
        return pattern.matcher(email).matches();
    }

    private String generateHash(String password) throws NoSuchAlgorithmException, InvalidKeySpecException {
        int iterations = 1000;
        char[] passwordCharArr = password.toCharArray();
        byte[] salt = getSalt();
        int keyLength = 64 * 8;

        PBEKeySpec pbeKeySpec = new PBEKeySpec(passwordCharArr, salt, iterations, keyLength);
        SecretKeyFactory secretKeyFactory = SecretKeyFactory.getInstance("PBKDF2WithHmacSHA1");
        byte[] hash = secretKeyFactory.generateSecret(pbeKeySpec).getEncoded();
        return iterations + ":" + toHex(salt) + ":" + toHex(hash);
    }

    private byte[] getSalt() throws NoSuchAlgorithmException{
        SecureRandom secureRandom = SecureRandom.getInstance("SHA1PRNG");
        byte[] salt = new byte[16];
        secureRandom.nextBytes(salt);
        return salt;
    }

    private String toHex(byte[] array) throws NoSuchAlgorithmException {
        BigInteger bi = new BigInteger(1, array);
        String hex = bi.toString(16);

        int paddingLength = (array.length * 2) - hex.length();
        if(paddingLength > 0) {
            return String.format("%0"  +paddingLength + "d", 0) + hex;
        }
        else {
            return hex;
        }
    }

    private byte[] fromHex(String hex) throws NoSuchAlgorithmException {
        byte[] bytes = new byte[hex.length() / 2];
        for(int i = 0; i < bytes.length ;i++) {
            bytes[i] = (byte)Integer.parseInt(hex.substring(2 * i, 2 * i + 2), 16);
        }
        return bytes;
    }
}
