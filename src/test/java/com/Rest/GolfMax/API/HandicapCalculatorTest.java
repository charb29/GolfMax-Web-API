package com.Rest.GolfMax.API;


import com.Rest.GolfMax.API.Models.*;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class HandicapCalculatorTest {

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

    private final User USER = new User(1, "Olivier", "password", "email@email.com");
    private final Score SCORE_1 = new Score(1, USER, getCOURSE(), 65,
            CHAMPIONSHIP_LAYOUT.getCourseRating(), CHAMPIONSHIP_LAYOUT.getSlopeRating());
    private final Score SCORE_2 = new Score(2, USER, getCOURSE(), 61,
            CHAMPIONSHIP_LAYOUT.getCourseRating(), CHAMPIONSHIP_LAYOUT.getSlopeRating());
    private final Score SCORE_3 = new Score(3, USER, getCOURSE(), 69,
            CHAMPIONSHIP_LAYOUT.getCourseRating(), CHAMPIONSHIP_LAYOUT.getSlopeRating());
    private final Score SCORE_4 = new Score(4, USER, getCOURSE(), 89,
            CHAMPIONSHIP_LAYOUT.getCourseRating(), CHAMPIONSHIP_LAYOUT.getSlopeRating());
    private final Score SCORE_5 = new Score(5, USER, getCOURSE(), 47,
            CHAMPIONSHIP_LAYOUT.getCourseRating(), CHAMPIONSHIP_LAYOUT.getSlopeRating());

    public List<Score> scores() {
        List<Score> scores = new ArrayList<>();
        scores.add(SCORE_1);
        scores.add(SCORE_2);
        scores.add(SCORE_3);
        scores.add(SCORE_4);
        scores.add(SCORE_5);
        return scores;
    }

    @Test
    public void handicapIndexReturnsOnly_1_DecimalPlace() {
        HandicapCalculator sut = new HandicapCalculator();
        double expectedResult = 0.0;

        double actualResult = sut.getHandicapIndex();

        assertEquals(expectedResult, actualResult);
    }

    @Test
    public void handicapIndexReturnsCorrectResult() {
        HandicapCalculator sut = new HandicapCalculator();
        double expectResult = -18.4;
        sut.setHandicapIndex(scores());
        double actualResult = sut.getHandicapIndex();

        assertEquals(expectResult, actualResult);
    }

    @Test
    public void userWithLessThan_5_ScoresReturns_0_ForHandicapIndex() {
        Score score = new Score(5, USER, getCOURSE(), 47,
                CHAMPIONSHIP_LAYOUT.getCourseRating(), CHAMPIONSHIP_LAYOUT.getSlopeRating());
        List<Score> scores = new ArrayList<>();
        scores.add(score);
        HandicapCalculator sut = new HandicapCalculator();
        double expectedResult = 0;

        sut.setHandicapIndex(scores);
        double actualResult = sut.getHandicapIndex();

        assertEquals(expectedResult, actualResult, 0.0001);
    }

    @Test
    public void averageScoreDifferentialWithLessThan_5_ResultsReturns_0() {
        HandicapCalculator sut = new HandicapCalculator();
        List<Double> averageScoreDifferentials = new ArrayList<>();
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
        List<Double> averageScoreDifferentials = new ArrayList<>();
        averageScoreDifferentials.add(2.3);
        averageScoreDifferentials.add(4.2);
        averageScoreDifferentials.add(3.4);
        int averageScoreDifferentialAmount = 0;
        int expectedResult = 0;

        double actualResult = sut.calculateHandicapIndex(averageScoreDifferentials, averageScoreDifferentialAmount);

        assertEquals(expectedResult, actualResult);
    }
}
