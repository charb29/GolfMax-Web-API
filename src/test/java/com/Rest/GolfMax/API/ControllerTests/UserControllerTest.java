package com.Rest.GolfMax.API.ControllerTests;

import com.Rest.GolfMax.API.Controllers.UserController;
import com.Rest.GolfMax.API.DTOs.UserDto;
import com.Rest.GolfMax.API.Models.PlayerStatistics;
import com.Rest.GolfMax.API.Models.Score;
import com.Rest.GolfMax.API.Models.User;
import com.Rest.GolfMax.API.Services.Interfaces.UserService;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import org.mockito.ArgumentMatchers;
import org.mockito.Mockito;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.RequestBuilder;
import org.springframework.test.web.servlet.request.MockHttpServletRequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.hamcrest.Matchers.is;
import static org.hamcrest.Matchers.notNullValue;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(UserController.class)
public class UserControllerTest {
    @Autowired
    private ModelMapper modelMapper;
    @Autowired
    private ObjectMapper objectMapper;
    @Autowired
    private MockMvc mockMvc;
    @MockBean
    private UserService userService;

    User USER_1 = new User(1, "Olivier", "password", "olivier@gmail.com");
    User USER_2 = new User(2, "Eric", "password", "eric@gmail.com");
    User USER_3 = new User(3, "Anna", "password", "anna@gmail.com");

    UserDto USER_DTO_1 = new UserDto(USER_1.getId(), USER_1.getUsername(), USER_1.getPassword(), USER_1.getEmail());
    UserDto USER_DTO_2 = new UserDto(USER_2.getId(), USER_2.getUsername(), USER_2.getPassword(), USER_2.getEmail());
    UserDto USER_DTO_3 = new UserDto(USER_3.getId(), USER_3.getUsername(), USER_3.getPassword(), USER_3.getEmail());

    @Test
    public void getAllUsers_returns_HTTP_OK() throws Exception {
        List<UserDto> userRequest = new ArrayList<>(Arrays.asList(USER_DTO_1, USER_DTO_2, USER_DTO_3));
        List<User> userResponse = new ArrayList<>(Arrays.asList(modelMapper.map(userRequest, User.class)));

        Mockito.when(userService.getAllUsers()).thenReturn(userResponse);

        RequestBuilder request = MockMvcRequestBuilders
                .get("/users")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(USER_1.getId()));

        mockMvc.perform(request)
                .andExpect(status().isOk())
                .andExpect(jsonPath("$", notNullValue()));
    }

    @Test
    public void getUserById_returns_HTTP_OK() throws Exception {
        Mockito.when(userService.getUserById(USER_1.getId())).thenReturn(USER_1);

        RequestBuilder request = MockMvcRequestBuilders
                .get("/users/1")
                .contentType(MediaType.APPLICATION_JSON)
                .contentType(objectMapper.writeValueAsString(USER_1));

        mockMvc.perform(request)
                .andExpect(status().isOk())
                .andExpect(jsonPath("$", notNullValue()))
                .andExpect(jsonPath("$.username", is("Olivier")));
    }

    @Test
    public void updateUserInfo_returns_HTTP_OK() throws Exception {
        USER_1.setPassword("pass");

        Mockito.when(userService.updateUser(ArgumentMatchers.any(), ArgumentMatchers.anyLong()))
                .thenReturn(USER_1);

        MockHttpServletRequestBuilder request = MockMvcRequestBuilders
                .put("/users/1")
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON)
                .content(this.objectMapper.writeValueAsString(USER_1));

        mockMvc.perform(request)
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.password", is("pass")));
    }

    @Test
    public void deleteUser_returns_HTTP_OK() throws Exception {
        Mockito.when(userService.getUserById(USER_1.getId())).thenReturn(USER_1);

        mockMvc.perform(MockMvcRequestBuilders
                        .delete("/users/1")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());
    }
}