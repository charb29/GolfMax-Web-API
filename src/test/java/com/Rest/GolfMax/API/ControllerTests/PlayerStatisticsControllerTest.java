package com.Rest.GolfMax.API.ControllerTests;

import com.Rest.GolfMax.API.Controllers.PlayerStatisticsController;
import com.Rest.GolfMax.API.Models.PlayerStatistics;
import com.Rest.GolfMax.API.Models.User;
import com.Rest.GolfMax.API.Services.Interfaces.PlayerStatisticsService;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import org.mockito.ArgumentMatchers;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockHttpServletRequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import static org.hamcrest.Matchers.notNullValue;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(PlayerStatisticsController.class)
public class PlayerStatisticsControllerTest {
    @Autowired
    private MockMvc mockMvc;
    @Autowired
    private ObjectMapper objectMapper;
    @MockBean
    private PlayerStatisticsService statisticsService;

    private final User USER_1 = new User(1L, "Olivier", "password", "olivier@gmail.com");
    private final PlayerStatistics USER_1_STATS = new PlayerStatistics(1L, USER_1, 20, 5.2, 68);

    @Test
    public void getStatsByUserId_returns_HTTP_OK() throws Exception {
        List<PlayerStatistics> stats = new ArrayList<>(Collections.singletonList(USER_1_STATS));

        Mockito.when(statisticsService.getStatisticsByUserId(USER_1.getId())).thenReturn(stats);

        MockHttpServletRequestBuilder request = MockMvcRequestBuilders
                .get("/player-statistics/users/1")
                .accept(MediaType.APPLICATION_JSON)
                .contentType(this.objectMapper.writeValueAsString(stats))
                .content(this.objectMapper.writeValueAsString(stats));

        mockMvc.perform(request)
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(jsonPath("$", notNullValue()));
    }

    @Test
    public void saveStats_returns_HTTP_CREATED() throws Exception {
        PlayerStatistics stats = new PlayerStatistics();

        Mockito.when(statisticsService.saveUserStatistics(ArgumentMatchers.any(), ArgumentMatchers.anyLong()))
                .thenReturn(stats);

        MockHttpServletRequestBuilder request = MockMvcRequestBuilders
                .put("/player-statistics/users/1")
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON)
                .content(this.objectMapper.writeValueAsString(stats));

        mockMvc.perform(request)
                .andDo(print())
                .andExpect(status().isOk());
    }
}
