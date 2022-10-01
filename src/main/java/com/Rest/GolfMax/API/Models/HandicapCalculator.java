package com.Rest.GolfMax.API.Models;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class HandicapCalculator {

    private final List<Double> averageScoreDifferentials = new ArrayList<>();
    private double handicapIndex;

    public double getHandicapIndex() {
        return Math.round(handicapIndex * 10.0) / 10.0;
    }

    public void setHandicapIndex(List<Score> scores) {
        setAverageScoreDifferentials(scores);
        int averageScoreDifferentialAmount = determineAverageScoreDifferential(getAverageScoreDifferentials());
        handicapIndex = calculateHandicapIndex(getAverageScoreDifferentials(), averageScoreDifferentialAmount);
    }

    public List<Double> getAverageScoreDifferentials() {
        return averageScoreDifferentials;
    }

    public void setAverageScoreDifferentials(List<Score> scores) {
        calculateAverageScoreDifferentials(scores);
    }

    private void calculateAverageScoreDifferentials(List<Score> scores) {
        final int AVERAGE_COURSE_DIFFICULTY = 113;
        for (Score score : scores) {
            int userScore = score.getUserScore();
            double courseRating = score.getCourseRating();
            double slopeRating = score.getSlopeRating();
            double avgScoreDif = (userScore - courseRating) * (AVERAGE_COURSE_DIFFICULTY / slopeRating);
            this.averageScoreDifferentials.add(Math.round(avgScoreDif * 1000.0) / 1000.0);
        }
        Collections.sort(this.averageScoreDifferentials);
    }

    public int determineAverageScoreDifferential(List<Double> averageScoreDifferentials) {
        switch (averageScoreDifferentials.size()) {
            case 0:
                return 0;
            case 5:
                return 1;
            case 6, 7, 8:
                return 2;
            case 9, 10, 11:
                return 3;
            case 12, 13, 14:
                return 4;
            case 15, 16:
                return 5;
            case 17, 18:
                return 6;
            case 19:
                return 7;
            case 20:
                return 8;
        }
        if (averageScoreDifferentials.size() > 20)
            return 8;
        return 0;
    }

    public double calculateHandicapIndex(List<Double> averageScoreDifferentials, int averageScoreDifferentialsAmount) {
        return averageScoreDifferentials.stream()
                .mapToDouble(d -> d)
                .limit(averageScoreDifferentialsAmount)
                .average()
                .orElse(0.0);
    }
}