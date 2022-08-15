package com.Rest.GolfMax.API;

import com.Rest.GolfMax.API.Controllers.RegistrationController;
import com.Rest.GolfMax.API.Models.User;
import com.Rest.GolfMax.API.Repositories.UserRepository;
import com.Rest.GolfMax.API.Services.UserService;
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
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;
import static org.hamcrest.Matchers.*;


@WebMvcTest(RegistrationController.class)
public class RegistrationControllerTest {
    @Autowired
    private MockMvc mockMvc;
    @Autowired
    private ObjectMapper mapper;
    @MockBean
    private UserRepository userRepository;
    @MockBean
    private UserService userService;

    User USER_1 = new User(1, "Dvean", "password", "dvean@gmail.com");

    @Test
    public void register() throws Exception {
        Mockito.when(userRepository.save(USER_1)).thenReturn(USER_1);

        MockHttpServletRequestBuilder mockRequest = MockMvcRequestBuilders.post("/users/account")
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON)
                .content(this.mapper.writeValueAsString(USER_1));

        mockMvc.perform(mockRequest)
                .andExpect(status().isCreated())
                .andExpect(jsonPath("$", notNullValue()))
                .andExpect(jsonPath("$.username", is("Dvean")));
    }
}
