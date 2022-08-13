package com.Rest.GolfMax.API;


import com.Rest.GolfMax.API.PlayerStatistics.HandicapCalculator;
import com.Rest.GolfMax.API.Score.Score;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class HandicapCalculatorTest {

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
