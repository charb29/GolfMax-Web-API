package com.Rest.GolfMax.API;

import com.Rest.GolfMax.API.Controllers.ScoreController;
import com.Rest.GolfMax.API.Models.Course;
import com.Rest.GolfMax.API.Models.Score;
import com.Rest.GolfMax.API.Models.User;
import com.Rest.GolfMax.API.Repositories.ScoreRepository;
import com.Rest.GolfMax.API.Services.ScoreService;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
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
    private ScoreRepository scoreRepository;
    @MockBean
    private ScoreService scoreService;

    User USER_1 = new User(1, "Olivier", "password", "olivier@gmail.com");
    User USER_2 = new User(2, "Eric", "password", "eric@gmail.com");
    Course COURSE_1 = new Course(1, "Vista Valencia", 61);
    Course COURSE_2 = new Course(1, "Scholl Canyon", 80);
    Score SCORE_1 = new Score(1, USER_1, COURSE_2, 64, 61, 103);
    Score SCORE_2 = new Score(2, USER_1, COURSE_1, 70, 61, 103);
    Score SCORE_3 = new Score(3, USER_2, COURSE_2, 100, 65, 120);

    @Test
    public void getAllScores() throws Exception {
        List<Score> scores = new ArrayList<>(Arrays.asList(SCORE_1, SCORE_2, SCORE_3));

        Mockito.when(scoreService.listAllScores()).thenReturn(scores);

        mockMvc.perform(MockMvcRequestBuilders
                .get("/scores")
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$", hasSize(3)))
                .andExpect(jsonPath("$[2].userScore", is(100)));
    }

    @Test
    public void getScoresById() throws Exception {
        Mockito.when(scoreService.getScoreById(SCORE_1.getScoreId())).thenReturn(SCORE_1);
        mockMvc.perform(MockMvcRequestBuilders
                .get("/scores/1")
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$", notNullValue()))
                .andExpect(jsonPath("$.course.courseName", is("Scholl Canyon")));
    }

    @Test
    public void getScoresByUserId() throws Exception {
        List<Score> scores = new ArrayList<>(Arrays.asList(SCORE_1, SCORE_2, SCORE_3));

        Mockito.when(scoreService
                .getScoresByUserId(USER_1.getId(), Sort.by("userScore").ascending()))
                .thenReturn(scores);

        mockMvc.perform(MockMvcRequestBuilders
                .get("/scores/user/1")
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$", notNullValue()));
    }

    @Test
    public void addScore() throws Exception {
        Score score = new Score(4, USER_1, COURSE_1, 69, 61, 103);

        Mockito.when(scoreRepository.save(score)).thenReturn(score);

        MockHttpServletRequestBuilder mockRequest = MockMvcRequestBuilders.post("/scores")
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON)
                .content(this.mapper.writeValueAsString(score));

        mockMvc.perform(mockRequest)
                .andExpect(status().isCreated())
                .andExpect(jsonPath("$", notNullValue()))
                .andExpect(jsonPath("$.userScore", is(69)));
    }

    @Test
    public void deleteScore() throws Exception {
        Mockito.when(scoreService.getScoreById(SCORE_3.getScoreId())).thenReturn(SCORE_3);

        mockMvc.perform(MockMvcRequestBuilders
                .delete("/scores/3")
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());
    }

    @Test
    public void getScoresByCourseId() throws Exception {
        List<Score> scores = new ArrayList<>(Arrays.asList(SCORE_1, SCORE_2, SCORE_3));

        Mockito.when(scoreService
                .getScoreByCourseId(COURSE_1.getId(), Sort.by("userScore").ascending()))
                .thenReturn(scores);

        mockMvc.perform(MockMvcRequestBuilders
                        .get("/scores/course/1")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$", notNullValue()));
    }
}
