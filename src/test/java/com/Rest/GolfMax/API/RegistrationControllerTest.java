package com.Rest.GolfMax.API;

import com.Rest.GolfMax.API.Controllers.RegistrationController;
import com.Rest.GolfMax.API.Models.User;
import com.Rest.GolfMax.API.Repositories.UserRepository;
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

import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(RegistrationController.class)
public class RegistrationControllerTest {
    @Autowired
    private MockMvc mockMvc;
    @Autowired
    private ObjectMapper objectMapper;
    @MockBean
    private UserService userService;

    @Test
    public void registerUser_returns_HTTP_CREATED() throws Exception {
        User user = new User();
        Mockito.when(userService.createUser(Mockito.any(User.class))).thenReturn(user);

        MockHttpServletRequestBuilder request = MockMvcRequestBuilders
                .post("/users/auth/signup")
                .contentType(MediaType.APPLICATION_JSON)
                .contentType(objectMapper.writeValueAsString(user));

        mockMvc.perform(request)
                .andExpect(status().isCreated());
    }
}
