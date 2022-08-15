package com.Rest.GolfMax.API;

import com.Rest.GolfMax.API.Controllers.ScoreController;
import com.Rest.GolfMax.API.Models.*;
import com.Rest.GolfMax.API.Repositories.ScoreRepository;
import com.Rest.GolfMax.API.Services.ScoreService;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.data.domain.Sort;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockHttpServletRequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;
import static org.hamcrest.Matchers.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@WebMvcTest(ScoreController.class)
public class ScoreControllerTest {
    @Autowired
    private MockMvc mockMvc;
    @Autowired
    private ObjectMapper mapper;
    @MockBean
    private ScoreRepository repository;
    @MockBean
    private ScoreService service;

    private final ChampionshipTees CHAMPIONSHIP_TEES = new ChampionshipTees(1, 65.4, 113,
            4, 3, 3, 4, 3, 3, 4, 3, 4, 4, 3,
            3, 3, 3, 4, 4, 3, 3, 357, 213,
            190, 371, 103, 166, 316, 139, 371,
            439, 182, 71, 82, 121, 332, 404,
            156, 179, 2226, 1966, 61);
    private final MenTees MENS_TEES = new MenTees(1, 63.6, 107, 4, 3, 3,
            4, 3, 3, 4, 3, 4, 4, 3, 3, 3, 3,
            4, 4, 3, 3, 336, 202, 179, 365,
            85, 141, 305, 129, 360, 454, 166,
            71, 74, 117, 311, 389, 145, 169,
            2102, 1866, 61);
    private final WomenTees WOMEN_TEES = new WomenTees(1, 62.1, 104, 4, 4, 3,
            4, 3, 3, 4, 3, 5, 5, 3, 3, 3, 3,
            4, 4, 3, 3, 312, 184, 169, 356, 68,
            124, 288, 114, 340, 393, 147, 71,
            73, 117, 277, 375, 112, 161,
            1955, 1726, 64);
    private final Course COURSE = new Course(1, CHAMPIONSHIP_TEES, MENS_TEES, WOMEN_TEES,
            "Vista Valencia Golf Course");
    private final User USER = new User(1, "Olivier", "password", "email@email.com");
    private final Score SCORE_1 = new Score(1, USER, COURSE, 69,
            CHAMPIONSHIP_TEES.getCourseRating(), CHAMPIONSHIP_TEES.getSlopeRating());
    private final Score SCORE_2 = new Score(2, USER, COURSE, 72,
            CHAMPIONSHIP_TEES.getCourseRating(), CHAMPIONSHIP_TEES.getSlopeRating());
    private final Score SCORE_3 = new Score(3, USER, COURSE, 85,
            MENS_TEES.getCourseRating(), MENS_TEES.getSlopeRating());
    private final Score SCORE_4 = new Score(4, USER, COURSE, 56,
            MENS_TEES.getCourseRating(), MENS_TEES.getSlopeRating());
    private final Score SCORE_5 = new Score(5, USER, COURSE, 61,
            CHAMPIONSHIP_TEES.getCourseRating(), CHAMPIONSHIP_TEES.getSlopeRating());

    @Test
    public void listAllScores() throws Exception {
        List<Score> scores = new ArrayList<>(Arrays.asList(SCORE_1, SCORE_2, SCORE_3, SCORE_3, SCORE_5));

        Mockito.when(service.listAllScores()).thenReturn(scores);

        mockMvc.perform(MockMvcRequestBuilders
                .get("/scores")
                .contentType(MediaType.APPLICATION_JSON));
    }

    @Test
    public void getScoresById() throws Exception {
        Mockito.when(service.getScoreById(SCORE_1.getScoreId())).thenReturn(SCORE_1);

        mockMvc.perform(MockMvcRequestBuilders
                        .get("/scores/user/1")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$", notNullValue()));
    }

    @Test
    public void getScoresByUserId() throws Exception {
        List<Score> scores = new ArrayList<>(Arrays.asList(SCORE_1, SCORE_2, SCORE_3, SCORE_3, SCORE_5));
        Mockito.when(service.getScoresByUserId(USER.getId(), Sort.by("userScore")
                .ascending()))
                .thenReturn(scores);

        mockMvc.perform(MockMvcRequestBuilders
                        .get("/scores/user/1")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$", notNullValue()));
    }

    @Test
    public void addScore() throws Exception {
        Score score = new Score(7, USER, COURSE, 69,
                CHAMPIONSHIP_TEES.getCourseRating(), CHAMPIONSHIP_TEES.getCourseRating());
        Mockito.when(repository.save(SCORE_1)).thenReturn(SCORE_1);

        MockHttpServletRequestBuilder mockRequest = MockMvcRequestBuilders.post("/scores/add-score")
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON)
                .content(this.mapper.writeValueAsString(SCORE_1));

        mockMvc.perform(mockRequest)
                .andExpect(status().isCreated())
                .andExpect(jsonPath("$", notNullValue()))
                .andExpect(jsonPath("$.userScore", is(69)));
    }

    @Test
    public void deleteScore() throws Exception {
        Mockito.when(service.getScoreById(SCORE_3.getScoreId())).thenReturn(SCORE_3);

        mockMvc.perform(MockMvcRequestBuilders
                        .delete("/scores/3")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());
    }

    @Test
    public void getScoresByCourseId() throws Exception {
        List<Score> scores = new ArrayList<>(Arrays.asList(SCORE_1, SCORE_2, SCORE_3));

        Mockito.when(service.getScoreByCourseId(COURSE.getId(), Sort.by("userScore")
                .ascending()))
                .thenReturn(scores);

        mockMvc.perform(MockMvcRequestBuilders
                        .get("/scores/course/1")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$", notNullValue()));
    }
}
