package com.Rest.GolfMax.API.Services.Implementations;

import com.Rest.GolfMax.API.Models.User;
import com.Rest.GolfMax.API.Repositories.UserRepository;
import com.Rest.GolfMax.API.Security.GolfMax.PasswordSecurity;
import com.Rest.GolfMax.API.Services.Interfaces.UserService;
import net.bytebuddy.utility.RandomString;
import org.apache.velocity.exception.ResourceNotFoundException;
import org.jetbrains.annotations.NotNull;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;
import org.springframework.web.context.annotation.RequestScope;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;
import javax.transaction.Transactional;
import java.io.UnsupportedEncodingException;
import java.security.NoSuchAlgorithmException;
import java.security.spec.InvalidKeySpecException;
import java.util.List;
import java.util.regex.Pattern;


@Service
@Transactional
@RequestScope
public class UserServiceImpl implements UserService {

    private final JavaMailSender mailSender;
    private final UserRepository userRepository;
    private final PasswordSecurity passwordSecurity = new PasswordSecurity();

    @Autowired
    public UserServiceImpl(UserRepository userRepository, JavaMailSender mailSender) {
        super();
        this.userRepository = userRepository;
        this.mailSender = mailSender;
    }

    @Override
    public List<User> getAllUsers() {
        return userRepository.findAll(Sort.by(Sort.Direction.ASC, "username"));
    }

    @Override
    public User getUserById(Long id) {
        return userRepository.findById(id).get();
    }

    @Override
    public void deleteUserById(Long id) {
        userRepository.deleteById(id);
    }

    @Override
    public User updateUser(@NotNull User userRequest, Long id) throws NoSuchAlgorithmException,
            InvalidKeySpecException {
        User updatedUser = userRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("id"));
        updatedUser.setUsername(userRequest.getUsername());
        updatedUser.setEmail(userRequest.getEmail());
        updatedUser.setPassword(passwordSecurity.getEncrypted(userRequest.getPassword()));
        return userRepository.save(updatedUser);
    }

    @Override
    public boolean isValidPassword(User user) throws NoSuchAlgorithmException, InvalidKeySpecException {
        String encryptedPassword = null;
        boolean isEnabled = false;
        String password = user.getPassword();

        if (userRepository.existsByUsername(user.getUsername())) {
            encryptedPassword = userRepository.getPasswordUsingUsername(user.getUsername());
            isEnabled = userRepository.getIsEnabledUsingUsername(user.getUsername());
        }
        if (encryptedPassword == null && !isEnabled) {
            return false;
        }
        else {
            return passwordSecurity.isValid(password, encryptedPassword);
        }
    }

    @Override
    public boolean isValidUsername(User user) {
        String username = user.getUsername();
        return !userRepository.existsByUsername(username);
    }

    @Override
    public boolean isValidLoginRequest(User user) {
        String username = user.getUsername();
        String password = userRepository.getPasswordUsingUsername(username);
        return password != null;
    }

    @Override
    public boolean isValidEmail(User user) {
        String email = user.getEmail();
        if (!isValidFormat(email)) {
            return false;
        }
        return !userRepository.existsByEmail(email);
    }

    @Override
    public User registerUser(User userRequest, String siteURL) throws UnsupportedEncodingException, MessagingException,
            NoSuchAlgorithmException, InvalidKeySpecException {
        User savedUser = new User();
        savedUser.setPassword(passwordSecurity.getEncrypted(userRequest.getPassword()));
        savedUser.setVerificationCode(RandomString.make(64));
        savedUser.setEmail(userRequest.getEmail());
        savedUser.setEnabled(false);
        savedUser.setUsername(userRequest.getUsername());
        savedUser.setFirstName(userRequest.getFirstName());
        savedUser.setLastName(userRequest.getLastName());

        User userResponse = userRepository.save(savedUser);
        sendVerificationEmail(userResponse, siteURL);

        return userResponse;
    }

    @Override
    public boolean isValidVerificationCode(String verificationCode) {
        User user = userRepository.findByVerificationCode(verificationCode);
        if (user == null || user.isEnabled()) {
            return false;
        }
        else {
            user.setVerificationCode(null);
            user.setEnabled(true);
            userRepository.save(user);
            return true;
        }
    }

    private boolean isValidFormat(String email) {
        String regexPattern = "^[a-zA-Z\\d_+&*-]+(?:\\.[a-zA-Z\\d_+&*-]+)*@(?:[a-zA-Z\\d-]+\\.)+[a-zA-Z]{2,7}$";
        Pattern pattern = Pattern.compile(regexPattern);
        if (email == null) {
            return false;
        }
        return pattern.matcher(email).matches();
    }

    private void sendVerificationEmail(User user, String siteURL)
            throws MessagingException, UnsupportedEncodingException {
        String recipient = user.getEmail();
        String sender = "golfmax.user.services@gmail.com";
        String companyName = "Golf Max";
        String emailSubject = "Verify Your GolfMax Account";
        String emailContent = "Hey there [[name]]!,<br>"
                + "You're receiving this email because you recently signed up to GolfMax. To complete the"
                + " registration process, hit the link below to verify your account:<br>"
                + "<h3><a href=\"[[URL]]\" target=\"_self\">VERIFY</a></h3>"
                + "Thank you,<br>"
                + companyName;

        MimeMessage message = mailSender.createMimeMessage();
        MimeMessageHelper messageHelper = new MimeMessageHelper(message);

        messageHelper.setFrom(sender, companyName);
        messageHelper.setTo(recipient);
        messageHelper.setSubject(emailSubject);

        emailContent = emailContent.replace("[[name]]", user.getFirstName()
                + " "
                + user.getLastName());

        String verifyURL = siteURL + "/verify?code=" + user.getVerificationCode();
        emailContent = emailContent.replace("[[URL]]", verifyURL);
        messageHelper.setText(emailContent, true);

        mailSender.send(message);
    }
}
