package com.Rest.GolfMax.API.ControllerTests;

import com.Rest.GolfMax.API.Controllers.ScoreController;
import com.Rest.GolfMax.API.Models.*;
import com.Rest.GolfMax.API.Services.Interfaces.ScoreService;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.RequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultHandlers;

import java.util.ArrayList;
import java.util.List;

import static org.hamcrest.Matchers.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(ScoreController.class)
public class ScoreControllerTest {
    @Autowired
    private MockMvc mockMvc;
    @Autowired
    private ObjectMapper objectMapper;
    @MockBean
    private ScoreService scoreService;

    private final Course COURSE = new Course();
    private final Hole CHAMPIONSHIP_HOLE1 = new Hole(1, 357, 4);
    private final Hole CHAMPIONSHIP_HOLE2 = new Hole(2, 213, 190);
    private final Hole CHAMPIONSHIP_HOLE3 = new Hole(3, 190, 3);
    private final Hole CHAMPIONSHIP_HOLE4 = new Hole(4, 371, 4);
    private final Hole CHAMPIONSHIP_HOLE5 = new Hole(5, 103, 3);
    private final Hole CHAMPIONSHIP_HOLE6 = new Hole(6, 166, 3);
    private final Hole CHAMPIONSHIP_HOLE7 = new Hole(7, 316, 4);
    private final Hole CHAMPIONSHIP_HOLE8 = new Hole(8, 139, 3);
    private final Hole CHAMPIONSHIP_HOLE9 = new Hole(9, 371, 4);
    private final Hole CHAMPIONSHIP_HOLE10 = new Hole(10, 439, 4);
    private final Hole CHAMPIONSHIP_HOLE11 = new Hole(11, 182, 3);
    private final Hole CHAMPIONSHIP_HOLE12 = new Hole(12, 71, 3);
    private final Hole CHAMPIONSHIP_HOLE13 = new Hole(13, 82, 3);
    private final Hole CHAMPIONSHIP_HOLE14 = new Hole(14, 121, 3);
    private final Hole CHAMPIONSHIP_HOLE15 = new Hole(15, 332, 4);
    private final Hole CHAMPIONSHIP_HOLE16 = new Hole(16, 404, 4);
    private final Hole CHAMPIONSHIP_HOLE17 = new Hole(17, 456, 3);
    private final Hole CHAMPIONSHIP_HOLE18 = new Hole(19, 179, 3);

    public List<Hole> getChampionshipHoles() {
        List<Hole> holes = new ArrayList<>();
        holes.add(CHAMPIONSHIP_HOLE1);
        holes.add(CHAMPIONSHIP_HOLE2);
        holes.add(CHAMPIONSHIP_HOLE3);
        holes.add(CHAMPIONSHIP_HOLE4);
        holes.add(CHAMPIONSHIP_HOLE5);
        holes.add(CHAMPIONSHIP_HOLE6);
        holes.add(CHAMPIONSHIP_HOLE7);
        holes.add(CHAMPIONSHIP_HOLE8);
        holes.add(CHAMPIONSHIP_HOLE9);
        holes.add(CHAMPIONSHIP_HOLE10);
        holes.add(CHAMPIONSHIP_HOLE11);
        holes.add(CHAMPIONSHIP_HOLE12);
        holes.add(CHAMPIONSHIP_HOLE13);
        holes.add(CHAMPIONSHIP_HOLE14);
        holes.add(CHAMPIONSHIP_HOLE15);
        holes.add(CHAMPIONSHIP_HOLE16);
        holes.add(CHAMPIONSHIP_HOLE17);
        holes.add(CHAMPIONSHIP_HOLE18);
        return holes;
    }

    private final HoleLayout CHAMPIONSHIP_LAYOUT = new HoleLayout(1, getChampionshipHoles(), COURSE,
            LayoutType.CHAMPIONSHIP, 2226, 1966, 61, 65.4, 113);

    private final Hole MENS_HOLE1 = new Hole(1, 336, 4);
    private final Hole MENS_HOLE2 = new Hole(2, 202, 3);
    private final Hole MENS_HOLE3 = new Hole(3, 179, 3);
    private final Hole MENS_HOLE4 = new Hole(4, 365, 4);
    private final Hole MENS_HOLE5 = new Hole(5, 85, 3);
    private final Hole MENS_HOLE6 = new Hole(6, 141, 3);
    private final Hole MENS_HOLE7 = new Hole(7, 305, 4);
    private final Hole MENS_HOLE8 = new Hole(8, 129, 3);
    private final Hole MENS_HOLE9 = new Hole(9, 360, 4);
    private final Hole MENS_HOLE10 = new Hole(10, 424, 4);
    private final Hole MENS_HOLE11 = new Hole(11, 166, 3);
    private final Hole MENS_HOLE12 = new Hole(12, 71, 3);
    private final Hole MENS_HOLE13 = new Hole(13, 74, 3);
    private final Hole MENS_HOLE14 = new Hole(14, 117, 3);
    private final Hole MENS_HOLE15 = new Hole(15, 311, 4);
    private final Hole MENS_HOLE16 = new Hole(16, 389, 4);
    private final Hole MENS_HOLE17 = new Hole(17, 145, 3);
    private final Hole MENS_HOLE18 = new Hole(19, 169, 3);

    public List<Hole> getMensHoles() {
        List<Hole> holes = new ArrayList<>();
        holes.add(MENS_HOLE1);
        holes.add(MENS_HOLE2);
        holes.add(MENS_HOLE3);
        holes.add(MENS_HOLE4);
        holes.add(MENS_HOLE5);
        holes.add(MENS_HOLE6);
        holes.add(MENS_HOLE7);
        holes.add(MENS_HOLE8);
        holes.add(MENS_HOLE9);
        holes.add(MENS_HOLE10);
        holes.add(MENS_HOLE11);
        holes.add(MENS_HOLE12);
        holes.add(MENS_HOLE13);
        holes.add(MENS_HOLE14);
        holes.add(MENS_HOLE15);
        holes.add(MENS_HOLE16);
        holes.add(MENS_HOLE17);
        holes.add(MENS_HOLE18);
        return holes;
    }

    private final HoleLayout MENS_LAYOUT = new HoleLayout(2, getMensHoles(), COURSE,
            LayoutType.MENS, 2102, 1866, 61, 63.6, 107);

    private final Hole WOMENS_HOLE1 = new Hole(1, 312, 4);
    private final Hole WOMENS_HOLE2 = new Hole(2, 184, 4);
    private final Hole WOMENS_HOLE3 = new Hole(3, 169, 3);
    private final Hole WOMENS_HOLE4 = new Hole(4, 356, 4);
    private final Hole WOMENS_HOLE5 = new Hole(5, 68, 3);
    private final Hole WOMENS_HOLE6 = new Hole(6, 124, 3);
    private final Hole WOMENS_HOLE7 = new Hole(7, 288, 4);
    private final Hole WOMENS_HOLE8 = new Hole(8, 114, 3);
    private final Hole WOMENS_HOLE9 = new Hole(9, 340, 5);
    private final Hole WOMENS_HOLE10 = new Hole(10, 393, 5);
    private final Hole WOMENS_HOLE11 = new Hole(11, 147, 3);
    private final Hole WOMENS_HOLE12 = new Hole(12, 71, 3);
    private final Hole WOMENS_HOLE13 = new Hole(13, 73, 3);
    private final Hole WOMENS_HOLE14 = new Hole(14, 117, 3);
    private final Hole WOMENS_HOLE15 = new Hole(15, 277, 4);
    private final Hole WOMENS_HOLE16 = new Hole(16, 375, 4);
    private final Hole WOMENS_HOLE17 = new Hole(17, 112, 3);
    private final Hole WOMENS_HOLE18 = new Hole(19, 161, 3);

    public List<Hole> getWomensHoles() {
        List<Hole> holes = new ArrayList<>();
        holes.add(WOMENS_HOLE1);
        holes.add(WOMENS_HOLE2);
        holes.add(WOMENS_HOLE3);
        holes.add(WOMENS_HOLE4);
        holes.add(WOMENS_HOLE5);
        holes.add(WOMENS_HOLE6);
        holes.add(WOMENS_HOLE7);
        holes.add(WOMENS_HOLE8);
        holes.add(WOMENS_HOLE9);
        holes.add(WOMENS_HOLE10);
        holes.add(WOMENS_HOLE11);
        holes.add(WOMENS_HOLE12);
        holes.add(WOMENS_HOLE13);
        holes.add(WOMENS_HOLE14);
        holes.add(WOMENS_HOLE15);
        holes.add(WOMENS_HOLE16);
        holes.add(WOMENS_HOLE17);
        holes.add(WOMENS_HOLE18);
        return holes;
    }

    private final HoleLayout WOMENS_LAYOUT = new HoleLayout(3, getWomensHoles(), COURSE,
            LayoutType.WOMENS, 1955, 1726, 64, 62.1, 104);

    public List<HoleLayout> getHoleLayouts() {
        List<HoleLayout> holeLayouts = new ArrayList<>();
        holeLayouts.add(CHAMPIONSHIP_LAYOUT);
        holeLayouts.add(MENS_LAYOUT);
        holeLayouts.add(WOMENS_LAYOUT);
        return holeLayouts;
    }

    public Course getCOURSE() {
        COURSE.setId(1);
        COURSE.setCourseName("Vista Valencia Golf Course");
        COURSE.setHoleLayout(getHoleLayouts());
        return COURSE;
    }

    private final User USER_1 = new User(1, "Olivier", "password", "email@email.com");
    private final User USER_2 = new User(2, "Anna", "password", "anna@gmail.com");
    private final Score SCORE_1 = new Score(1, USER_1, getCOURSE(), 65,
            CHAMPIONSHIP_LAYOUT.getCourseRating(), CHAMPIONSHIP_LAYOUT.getSlopeRating());
    private final Score SCORE_2 = new Score(1, USER_2, getCOURSE(), 85, WOMENS_LAYOUT.getCourseRating(),
            WOMENS_LAYOUT.getSlopeRating());

    public List<Score> getScores() {
        List<Score> scores = new ArrayList<>();
        scores.add(SCORE_1);
        scores.add(SCORE_2);
        return scores;
    }

    @Test
    public void getAllScores_returns_HTTP_OK() throws Exception {
        List<Score> scores = getScores();

        Mockito.when(scoreService.getAllScores()).thenReturn(scores);

        RequestBuilder request = MockMvcRequestBuilders
                .get("/scores")
                .contentType(MediaType.APPLICATION_JSON)
                .contentType(objectMapper.writeValueAsString(scores));

        mockMvc.perform(request)
                .andExpect(status().isOk())
                .andExpect(jsonPath("$", notNullValue()))
                .andDo(MockMvcResultHandlers.print());
    }

    @Test
    public void getScoresById_returns_HTTP_OK() throws Exception {
        Mockito.when(scoreService.getScoreById(SCORE_1.getId())).thenReturn(SCORE_1);

        RequestBuilder builder = MockMvcRequestBuilders
                .get("/scores/ " + SCORE_1.getId())
                .contentType(MediaType.APPLICATION_JSON)
                .contentType(objectMapper.writeValueAsString(SCORE_1));

        mockMvc.perform(builder)
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.userScore", is(65)));
    }

    @Test
    public void getScoresByUserId_returns_HTTP_OK() throws Exception {
        Mockito.when(scoreService.getScoresByUserId(USER_1.getId())).thenReturn(getScores());

        RequestBuilder request = MockMvcRequestBuilders
                .get("/scores/users/1")
                .contentType(MediaType.APPLICATION_JSON)
                .contentType(objectMapper.writeValueAsString(getScores()));

        mockMvc.perform(request)
                .andExpect(status().isOk())
                .andExpect(jsonPath("$", notNullValue()));
    }

    @Test
    public void getScoresByCourseId_returns_HTTP_OK() throws Exception {
        Mockito.when(scoreService.getScoresByCourseId(COURSE.getId())).thenReturn(getScores());

        RequestBuilder request = MockMvcRequestBuilders
                .get("/scores/courses/1")
                .contentType(MediaType.APPLICATION_JSON)
                .contentType(objectMapper.writeValueAsString(getScores()));

        mockMvc.perform(request)
                .andExpect(status().isOk())
                .andExpect(jsonPath("$", notNullValue()));
    }
}
