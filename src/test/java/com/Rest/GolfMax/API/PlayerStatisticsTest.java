package com.Rest.GolfMax.API;

import com.Rest.GolfMax.API.PlayerStatistics.PlayerStatisticsController;
import com.Rest.GolfMax.API.PlayerStatistics.PlayerStatistics;
import com.Rest.GolfMax.API.User.User;
import com.Rest.GolfMax.API.PlayerStatistics.PlayerStatisticsRepository;
import com.Rest.GolfMax.API.PlayerStatistics.PlayerStatisticsService;
import com.Rest.GolfMax.API.Score.ScoreService;
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

    @MockBean
    ScoreService scoreService;

    User USER_1 = new User(1, "Olivier", "password", "olivier@gmail.com");

    PlayerStatistics USER_1_STATS = new PlayerStatistics(1, USER_1, 20, 5.2, 68);

    @Test
    public void getStatsByUserId() throws Exception {

        PlayerStatistics stats = new PlayerStatistics();

        Mockito.when(service.getStatsByUserId(USER_1.getId())).thenReturn(stats);

        mockMvc.perform(MockMvcRequestBuilders
                .get("/stats/user/1")
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$", notNullValue()));
    }

    @Test
    public void updatePlayerStats() throws Exception {
        PlayerStatistics statistics = new PlayerStatistics();

        Mockito.when(repository.save(USER_1_STATS)).thenReturn(USER_1_STATS);

        MockHttpServletRequestBuilder mockRequest = MockMvcRequestBuilders.put("/stats/1")
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON)
                .content(this.mapper.writeValueAsString(USER_1_STATS));

        mockMvc.perform(mockRequest)
                .andExpect(status().isCreated())
                .andExpect(jsonPath("$", notNullValue()));
    }
}
