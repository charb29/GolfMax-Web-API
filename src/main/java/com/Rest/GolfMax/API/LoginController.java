package com.Rest.GolfMax.API;

import java.io.IOException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/users")
public class LoginController {

    @Autowired
    UserService userService;

    @Autowired
    ServletRequest servletRequest;

    @Autowired
    ServletResponse response;

    @PostMapping("/signin")
    public void login(@RequestBody User user) throws IOException {
        User storedUser = userService.getUsername(user.getUsername());
        // fix below 
        HttpServletRequest httpServletRequest = (HttpServletRequest) servletRequest;

        if (storedUser == null) {
            ((HttpServletResponse) response).sendError(HttpServletResponse.SC_UNAUTHORIZED, 
                    "The username or password you have entered is invalid.");
        }
        else if (storedUser.getPassword() == user.getPassword()) {
            ((HttpServletResponse) response).sendError(HttpServletResponse.SC_ACCEPTED, "valid.");
        }
    }
}
