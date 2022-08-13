package com.Rest.GolfMax.API;

import com.Rest.GolfMax.API.User.UserController;
import com.Rest.GolfMax.API.User.User;
import com.Rest.GolfMax.API.User.UserService;
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
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@WebMvcTest(UserController.class)
public class UserControllerTest {

    @Autowired
    MockMvc mockMvc;

    @Autowired
    ObjectMapper mapper;

    @MockBean
    UserService userService;

    User USER_1 = new User(1, "Olivier", "password", "olivier@gmail.com");
    User USER_2 = new User(2, "Eric", "password", "eric@gmail.com");
    User USER_3 = new User(3, "Anna", "password", "anna@gmail.com");

    @Test
    public void getAllUsers() throws Exception {
        List<User> users = new ArrayList<>(Arrays.asList(USER_1, USER_2, USER_3));

        Mockito.when(userService.listAllUsers()).thenReturn(users);

        mockMvc.perform(MockMvcRequestBuilders
                .get("/users")
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$", hasSize(3)))
                .andExpect(jsonPath("$[2].username", is("Anna")));
    }

    @Test
    public void getUserById() throws Exception {
        Mockito.when(userService.getUserById(USER_1.getId())).thenReturn(USER_1);

        mockMvc.perform(MockMvcRequestBuilders
                .get("/users/1")
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$", notNullValue()))
                .andExpect(jsonPath("$.username", is("Olivier")));
    }

    @Test
    public void updateUserInfo() throws Exception {
        User updatedUser = new User();
        updatedUser.setId(1);
        updatedUser.setUsername("Olivier");
        updatedUser.setPassword("Olivier@323");
        updatedUser.setEmail("olivier@gmail.com");

        Mockito.when(userService.getUserById(USER_1.getId())).thenReturn(USER_1);
        Mockito.when(userService.updateUser(USER_1.getId(), updatedUser)).thenReturn(updatedUser);

        MockHttpServletRequestBuilder mockRequest = MockMvcRequestBuilders.put("/users/1/update")
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON)
                .content(this.mapper.writeValueAsString(updatedUser));

        mockMvc.perform(mockRequest)
                .andExpect(status().isOk());
    }

    @Test
    public void deleteUser() throws Exception {
        Mockito.when(userService.getUserById(USER_1.getId())).thenReturn(USER_1);

        mockMvc.perform(MockMvcRequestBuilders
                .delete("/users/1")
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());
    }
}
