package com.Rest.GolfMax.API.ServiceTests;

import com.Rest.GolfMax.API.Models.Course;
import com.Rest.GolfMax.API.Models.Score;
import com.Rest.GolfMax.API.Models.User;
import com.Rest.GolfMax.API.Repositories.ScoreRepository;
import com.Rest.GolfMax.API.Services.Implementations.ScoreServiceImpl;
import org.assertj.core.error.ShouldBeSorted;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.BDDMockito.willDoNothing;
import static org.mockito.Mockito.*;

@RunWith(MockitoJUnitRunner.class)
public class ScoreServiceTest {
    @Mock
    private ScoreRepository scoreRepository;
    @InjectMocks
    private ScoreServiceImpl scoreService;

    @Test
    public void getAllScores_returns_list_size_3() {
        Score score1 = new Score();
        score1.setId(1L);
        Score score2 = new Score();
        score2.setId(2L);
        Score score3 = new Score();
        score3.setId(3L);

        List<Score> scoreRequest = new ArrayList<>(Arrays.asList(score1, score2, score3));

        when(scoreService.getAllScores()).thenReturn(scoreRequest);

        List<Score> scoreResponse = scoreService.getAllScores();

        assertThat(scoreRequest.size()).isSameAs(scoreResponse.size());
        assertThat(scoreResponse.size()).isEqualTo(3);
    }

    @Test
    public void createScore_should_return_score() {
        Score scoreRequest = new Score();
        scoreRequest.setCourseRating(96);

        when(scoreRepository.save(scoreRequest)).thenReturn(new Score());

        Score createdScore = scoreService.createScore(scoreRequest);

        assertThat(createdScore.getCourseRating()).isSameAs(scoreRequest.getCourseRating());
    }

    @Test
    public void getScoreById_should_return_score() {
        Score scoreRequest = new Score();
        scoreRequest.setId(1L);

        when(scoreRepository.findById(scoreRequest.getId())).thenReturn(Optional.of(scoreRequest));

        Score scoreResponse = scoreService.getScoreById(scoreRequest.getId()).get();

        assertThat(scoreResponse.getCourseRating()).isSameAs(scoreRequest.getCourseRating());
    }

    @Test
    public void deleteScore_whenScoreDeleted_thenNothing() {
        long scoreId = 1;

        willDoNothing().given(scoreRepository).deleteById(scoreId);

        scoreService.deleteScore(scoreId);

        verify(scoreRepository, times(1)).deleteById(scoreId);
    }

    @Test
    public void getScoresByUserId_should_return_user_scores_ASC_order() {
        User userRequest = new User(1L, "Olivier", "password", "email");

        Score userScore1 = new Score();
        userScore1.setUser(userRequest);
        userScore1.setUserScore(80);

        Score userScore2 = new Score();
        userScore2.setUser(userRequest);
        userScore2.setUserScore(65);

        Score userScore3 = new Score();
        userScore3.setUser(userRequest);
        userScore3.setUserScore(95);

        List<Score> scoreRequest = new ArrayList<>(Arrays.asList(userScore1, userScore2, userScore3));

        when(scoreRepository.findByUserIdOrderByUserScoreAsc(userRequest.getId())).thenReturn(scoreRequest);

        List<Score> scoreResponse = scoreService.getScoresByUserId(userRequest.getId());

        assertEquals(scoreResponse.get(0).getUserScore(), scoreRequest.get(0).getUserScore());
        assertEquals(scoreResponse.get(1).getUserScore(), scoreRequest.get(1).getUserScore());
        assertEquals(scoreResponse.get(2).getUserScore(), scoreRequest.get(2).getUserScore());
    }

    @Test
    public void getScoresByCourseId_should_return_user_scores_ASC_order() {
        Course course = new Course();
        course.setId(1L);

        User user1 = new User(1L, "Olivier", "password", "email");
        Score user1Score = new Score();
        user1Score.setUser(user1);
        user1Score.setUserScore(65);
        user1Score.setCourse(course);

        User user2 = new User(2L, "Anna", "password", "email");
        Score user2Score = new Score();
        user2Score.setUser(user2);
        user2Score.setUserScore(89);
        user2Score.setCourse(course);

        User user3 = new User(3L, "John Doe", "password", "email");
        Score user3Score = new Score();
        user3Score.setUser(user3);
        user3Score.setUserScore(67);
        user2Score.setCourse(course);

        List<Score> scoreRequest = new ArrayList<>(Arrays.asList(user1Score, user2Score, user3Score));

        when(scoreRepository.findByCourseIdOrderByUserScoreAsc(course.getId())).thenReturn(scoreRequest);

        List<Score> scoreResponse = scoreService.getScoresByCourseId(course.getId());

        assertEquals(scoreResponse.get(0).getUserScore(), scoreRequest.get(0).getUserScore());
        assertEquals(scoreResponse.get(1).getUserScore(), scoreRequest.get(1).getUserScore());
        assertEquals(scoreResponse.get(2).getUserScore(), scoreRequest.get(2).getUserScore());
    }
}

