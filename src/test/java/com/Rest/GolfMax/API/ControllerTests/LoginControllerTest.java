package com.Rest.GolfMax.API.ControllerTests;

import com.Rest.GolfMax.API.Controllers.LoginController;
import com.Rest.GolfMax.API.Models.User;
import com.Rest.GolfMax.API.Security.GolfMax.PasswordSecurity;
import com.Rest.GolfMax.API.Services.Interfaces.UserService;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockHttpServletRequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import java.security.NoSuchAlgorithmException;
import java.security.spec.InvalidKeySpecException;

import static org.hamcrest.Matchers.is;
import static org.hamcrest.Matchers.notNullValue;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(LoginController.class)
public class LoginControllerTest {

    @Autowired
    private MockMvc mockMvc;
    @Autowired
    private ObjectMapper objectMapper;
    @MockBean
    private UserService userService;


    @Test
    public void successful_login_returns_HTTP_OK() throws Exception {
        User user = new User();
        user.setId(1L);
        user.setUsername("Olivier");
        user.setPassword("password");
        user.setEmail("olivier@gmail.com");

        Mockito.when(userService.isValidPassword(Mockito.any(User.class))).thenReturn(true);

        MockHttpServletRequestBuilder request = MockMvcRequestBuilders
                .post("/user/sign-in")
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON)
                .content(this.objectMapper.writeValueAsString(user));

        mockMvc.perform(request)
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(jsonPath("$", notNullValue()))
                .andExpect(jsonPath("$.username", is("Olivier")))
                .andExpect(jsonPath("$.password", is("password")))
                .andExpect(jsonPath("$.email", is("olivier@gmail.com")));
    }

    @Test
    public void stringPasswordMatchesEncryptedPassword() throws NoSuchAlgorithmException, InvalidKeySpecException {
        PasswordSecurity password = new PasswordSecurity();
        User user = new User(1L, "firstname", "lastname", "username", "password", "email");
        String encryptedPassword = password.getEncrypted(user.getPassword());

        boolean expectedResult = true;
        boolean actualResult = password.isValid(user.getPassword(), encryptedPassword);

        assertTrue(expectedResult, String.valueOf(actualResult));
    }
}
