package com.Rest.GolfMax.API.Security;


import com.Rest.GolfMax.API.Models.User;
import org.springframework.mail.javamail.JavaMailSenderImpl;
import org.springframework.mail.javamail.MimeMessageHelper;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;
import java.io.UnsupportedEncodingException;
import java.util.regex.Pattern;

public class EmailSecurity {

    private JavaMailSenderImpl mailSender = new JavaMailSenderImpl();

    public boolean isValidFormat(String email) {
        String regexPattern = "^[a-zA-Z\\d_+&*-]+(?:\\.[a-zA-Z\\d_+&*-]+)*@(?:[a-zA-Z\\d-]+\\.)+[a-zA-Z]{2,7}$";
        Pattern pattern = Pattern.compile(regexPattern);
        if (email == null) {
            return false;
        }
        return pattern.matcher(email).matches();
    }

    public void sendVerificationEmail(User user, String siteURL)
    throws MessagingException, UnsupportedEncodingException {
        String recipient = user.getEmail();
        String sender = "golfmax.user.services@gmail.com";
        String companyName = "Golf Max";
        String emailSubject = "Please Verify Your Account";
        String emailContent = "Dear [[name]],<br>"
                + "Please click teh link below to verify your account:<br>"
                + "<h3><a href=\"[[URL]]\" target=\"_self\">VERIFY</a></h3>"
                + "Thank you,<br>"
                + companyName;

        MimeMessage message = mailSender.createMimeMessage();
        MimeMessageHelper messageHelper = new MimeMessageHelper(message);

        messageHelper.setFrom(sender, companyName);
        messageHelper.setText(recipient);
        messageHelper.setSubject(emailSubject);

        emailContent = emailContent.replace("[[name]]", user.getFirstName()
                + " "
                + user.getLastName());
        messageHelper.setText(emailContent, true);
        mailSender.send(message);
    }
}
