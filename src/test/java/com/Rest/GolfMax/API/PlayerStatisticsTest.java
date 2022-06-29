package com.Rest.GolfMax.API;

import com.Rest.GolfMax.API.Controllers.PlayerStatisticsController;
import com.Rest.GolfMax.API.Models.PlayerStatistics;
import com.Rest.GolfMax.API.Models.User;
import com.Rest.GolfMax.API.Repositories.PlayerStatisticsRepository;
import com.Rest.GolfMax.API.Services.PlayerStatisticsService;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;
import static org.hamcrest.Matchers.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@WebMvcTest(PlayerStatisticsController.class)
public class PlayerStatisticsTest {

    @Autowired
    MockMvc mockMvc;

    @Autowired
    ObjectMapper mapper;

    @MockBean
    PlayerStatisticsRepository repository;

    @MockBean
    PlayerStatisticsService service;

    User USER_1 = new User(1, "Olivier", "password", "olivier@gmail.com");

    PlayerStatistics USER_1_STATS = new PlayerStatistics(1, USER_1, 20, 5.2, 68);

    @Test
    public void getStatsByUserId() throws Exception {

        PlayerStatistics stats = USER_1_STATS;

        Mockito.when(service.getStatsByUserId(USER_1.getId())).thenReturn(stats);

        mockMvc.perform(MockMvcRequestBuilders
                .get("/stats/user/1")
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$", notNullValue()));
    }
}
