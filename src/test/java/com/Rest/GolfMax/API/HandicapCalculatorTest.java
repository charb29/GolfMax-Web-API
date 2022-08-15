package com.Rest.GolfMax.API;


import com.Rest.GolfMax.API.Models.HandicapCalculator;
import com.Rest.GolfMax.API.Models.Score;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class HandicapCalculatorTest {

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
    public void handicapIndexReturnsOnly_1_DecimalPlace() {
        HandicapCalculator sut = new HandicapCalculator();
        double expectedResult = 0.0;

        double actualResult = sut.getHandicapIndex();

        assertEquals(expectedResult, actualResult);
    }

    @Test
    public void userWithLessThan_5_ScoresReturns_0_ForHandicapIndex() {
        HandicapCalculator sut = new HandicapCalculator(); // sut = system under test
        List<Score> scores = new ArrayList<Score>();
        Score SCORE_1 = new Score();
        Score SCORE_2 = new Score();
        Score SCORE_3 = new Score();
        SCORE_1.setUserScore(101); SCORE_1.setCourseRating(79.1); SCORE_1.setSlopeRating(121);
        SCORE_2.setUserScore(130); SCORE_2.setCourseRating(61); SCORE_2.setSlopeRating(103);
        SCORE_3.setUserScore(130); SCORE_3.setCourseRating(54.8); SCORE_3.setSlopeRating(86);
        scores.add(SCORE_1);
        scores.add(SCORE_2);
        scores.add(SCORE_3);
        double expectedResult = 0;

        double actualResult = sut.getHandicapIndex();

        assertEquals(expectedResult, actualResult, 0.0001);
    }

    @Test
    public void averageScoreDifferentialWithLessThan_5_ResultsReturns_0() {
        HandicapCalculator sut = new HandicapCalculator();
        List<Double> averageScoreDifferentials = new ArrayList<Double>();
        averageScoreDifferentials.add(4.5);
        averageScoreDifferentials.add(2.3);
        averageScoreDifferentials.add(5.7);
        int expectedResult = 0;

        int actualResult = sut.determineAverageScoreDifferential(averageScoreDifferentials);

        assertEquals(expectedResult, actualResult);
    }

    @Test
    public void averageScoreDifferentialWithLessThan_5_ResultsReturns_0_HandicapIndex() {
        HandicapCalculator sut = new HandicapCalculator();
        List<Double> averageScoreDifferentials = new ArrayList<Double>();
        averageScoreDifferentials.add(2.3);
        averageScoreDifferentials.add(4.2);
        averageScoreDifferentials.add(3.4);
        int averageScoreDifferentialAmount = 0;
        int expectedResult = 0;

        double actualResult = sut.calculateHandicapIndex(averageScoreDifferentials, averageScoreDifferentialAmount);

        assertEquals(expectedResult, actualResult);
    }
}
