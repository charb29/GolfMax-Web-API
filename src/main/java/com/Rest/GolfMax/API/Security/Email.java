package com.Rest.GolfMax.API.Security;


import java.util.regex.Pattern;

public class Email {

    public boolean isValidFormat(String email) {
        String regexPattern = "^[a-zA-Z\\d_+&*-]+(?:\\.[a-zA-Z\\d_+&*-]+)*@(?:[a-zA-Z\\d-]+\\.)+[a-zA-Z]{2,7}$";
        Pattern pattern = Pattern.compile(regexPattern);
        if (email == null) {
            return false;
        }
        return pattern.matcher(email).matches();
    }
}
