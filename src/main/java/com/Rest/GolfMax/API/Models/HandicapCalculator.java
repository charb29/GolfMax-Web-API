package com.Rest.GolfMax.API.Models;

import java.util.ArrayList;
import java.util.List;

public class HandicapCalculator {

    private List<Double> averageScoreDifferentials;
    private double handicapIndex;

    public double getHandicapIndex() {
        return handicapIndex;
    }

    public void setHandicapIndex(List<Score> scores) {
        setAverageScoreDifferentials(averageScoreDifferentials, scores);
        int averageScoreDifferentialAmount = determineAverageScoreDifferential(getAverageScoreDifferentials());
        handicapIndex = calculateHandicapIndex(getAverageScoreDifferentials(), averageScoreDifferentialAmount);
    }

    private List<Double> getAverageScoreDifferentials() {
        return averageScoreDifferentials;
    }

    private void setAverageScoreDifferentials(List<Double> averageScoreDifferentials,
                                             List<Score> scores) {
        final int AVERAGE_COURSE_DIFFICULTY = 113;
        double result;

        for (int i = 0; i < scores.size(); i++) {
            result = (scores.get(i).getUserScore() - scores.get(i).getCourse().getCourseRating()) *
                    (AVERAGE_COURSE_DIFFICULTY / scores.get(i).getCourse().getSlopeRating());

            result = Math.round(result * 1000.0) / 1000.0;
            averageScoreDifferentials.add(result);
        }

        this.averageScoreDifferentials = mergeSort(averageScoreDifferentials);
    }

    private List<Double> mergeSort(List<Double> averageScoreDifferentials) {
        List<Double> left = new ArrayList<Double>();
        List<Double> right = new ArrayList<Double>();
        int mid;

        if (averageScoreDifferentials.size() == 1) {
            return averageScoreDifferentials;
        }
        else {
            mid = averageScoreDifferentials.size() / 2;

            for (int i = 0; i < mid; i++) {
                left.add(averageScoreDifferentials.get(i));
            }
            for (int i = mid; i < averageScoreDifferentials.size(); i++) {
                right.add(averageScoreDifferentials.get(i));
            }

            left = mergeSort(left);
            right = mergeSort(right);

            merge(left, right, averageScoreDifferentials);
        }
        return averageScoreDifferentials;
    }

    private void merge(List<Double> left, List<Double> right, List<Double> averageScoreDifferentials) {
        int leftIndex = 0;
        int rightIndex = 0;
        int wholeIndex = 0;

        while (leftIndex < left.size() && rightIndex < right.size()) {

            if ((left.get(leftIndex).compareTo(right.get(rightIndex))) < 0) {
                averageScoreDifferentials.set(wholeIndex, left.get(leftIndex));
                leftIndex++;
            }
            else {
                averageScoreDifferentials.set(wholeIndex, right.get(rightIndex));
                rightIndex++;
            }
            wholeIndex++;
        }

        List<Double> rest;
        int restIndex;

        if (leftIndex >= left.size()) {
            rest = right;
            restIndex = rightIndex;
        }
        else {
            rest = left;
            restIndex = leftIndex;
        }

        for (int i = restIndex; i < rest.size(); i++) {
            averageScoreDifferentials.set(wholeIndex, rest.get(i));
            wholeIndex++;
        }
    }

    private int determineAverageScoreDifferential(List<Double> averageScoreDifferentials) {

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
        throw new RuntimeException();
    }

    private double calculateHandicapIndex(List<Double> averageScoreDifferentials,
                                          int averageScoreDifferentialsAmount) {
        return averageScoreDifferentials.stream()
                .mapToDouble(d -> d)
                .limit(averageScoreDifferentialsAmount)
                .average()
                .orElseThrow(RuntimeException::new);
    }
}
