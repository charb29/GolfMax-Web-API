package com.Rest.GolfMax.API.ControllerTests;

import com.Rest.GolfMax.API.Controllers.PlayerStatisticsController;
import com.Rest.GolfMax.API.Models.PlayerStatistics;
import com.Rest.GolfMax.API.Models.User;
import com.Rest.GolfMax.API.Repositories.PlayerStatisticsRepository;
import com.Rest.GolfMax.API.Services.Interfaces.PlayerStatisticsService;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;

@WebMvcTest(PlayerStatisticsController.class)
public class PlayerStatisticsControllerTest {
    @Autowired
    private MockMvc mockMvc;
    @Autowired
    private ObjectMapper mapper;
    @MockBean
    private PlayerStatisticsRepository repository;
    @MockBean
    private PlayerStatisticsService service;

    User USER_1 = new User(1, "Olivier", "password", "olivier@gmail.com");
    PlayerStatistics USER_1_STATS = new PlayerStatistics(1, USER_1, 20, 5.2, 68);


}
