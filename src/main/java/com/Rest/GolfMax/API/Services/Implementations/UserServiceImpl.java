package com.Rest.GolfMax.API.Services.Implementations;

import com.Rest.GolfMax.API.Models.User;
import com.Rest.GolfMax.API.Repositories.UserRepository;
import com.Rest.GolfMax.API.Security.PasswordSecurity;
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
import java.util.Optional;
import java.util.regex.Pattern;


@Service
@Transactional
@RequestScope
public class UserServiceImpl implements UserService {


    private final JavaMailSender MAIL_SENDER;
    private final UserRepository USER_REPOSITORY;
    private final PasswordSecurity PASSWORD_SECURITY = new PasswordSecurity();

    @Autowired
    public UserServiceImpl(UserRepository userRepository, JavaMailSender mailSender) {
        super();
        this.USER_REPOSITORY = userRepository;
        this.MAIL_SENDER = mailSender;
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
        userResponse.setPassword(PASSWORD_SECURITY.getEncrypted(userRequest.getPassword()));
        userResponse.setFirstName(userRequest.getFirstName());
        userResponse.setLastName(userRequest.getLastName());
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
    public User updateUser(@NotNull User userRequest, Long id) throws NoSuchAlgorithmException,
            InvalidKeySpecException {
        User updatedUser = USER_REPOSITORY.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("id"));
        updatedUser.setUsername(userRequest.getUsername());
        updatedUser.setEmail(userRequest.getEmail());
        updatedUser.setPassword(PASSWORD_SECURITY.getEncrypted(userRequest.getPassword()));
        return USER_REPOSITORY.save(updatedUser);
    }


    @Override
    public boolean isValidLoginRequest(User user) throws NoSuchAlgorithmException, InvalidKeySpecException {
        String encryptedPassword = USER_REPOSITORY.getPasswordUsingUsername(user.getUsername());
        String password = user.getPassword();
        String username = user.getUsername();

        if (encryptedPassword == null) {
            return false;
        }

        return PASSWORD_SECURITY.isValid(password, encryptedPassword)
                && USER_REPOSITORY.existsByUsername(username);
    }

    @Override
    public boolean isValidRegistrationRequest(String username, String email) {
        return !isValidUsername(username) && !isValidEmail(email) && isValidFormat(email);
    }

    @Override
    public User registerUser(User userRequest, String siteURL)
            throws UnsupportedEncodingException, MessagingException,
            NoSuchAlgorithmException, InvalidKeySpecException {
        User savedUser = new User();
        savedUser.setPassword(PASSWORD_SECURITY.getEncrypted(userRequest.getPassword()));
        savedUser.setVerificationCode(RandomString.make(64));
        savedUser.setEmail(userRequest.getEmail());
        savedUser.setEnabled(false);
        savedUser.setUsername(userRequest.getUsername());
        savedUser.setFirstName(userRequest.getFirstName());
        savedUser.setLastName(userRequest.getLastName());

        User userResponse = USER_REPOSITORY.save(savedUser);
        sendVerificationEmail(userResponse, siteURL);
        return userResponse;
    }

    @Override
    public boolean isValidVerificationCode(String verificationCode) {
        User user = USER_REPOSITORY.findByVerificationCode(verificationCode);
        if (user == null || user.isEnabled()) {
            return false;
        }
        else {
            user.setVerificationCode(null);
            user.setEnabled(true);
            USER_REPOSITORY.save(user);
            return true;
        }
    }

    private boolean isValidUsername(String username) {
        return USER_REPOSITORY.existsByUsername(username);
    }

    private boolean isValidEmail(String email) {
        return USER_REPOSITORY.existsByEmail(email);
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

        MimeMessage message = MAIL_SENDER.createMimeMessage();
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

        MAIL_SENDER.send(message);
    }
}
