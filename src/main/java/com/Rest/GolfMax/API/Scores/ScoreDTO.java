package com.Rest.GolfMax.API.Scores;

import com.Rest.GolfMax.API.Users.User;

public class ScoreDTO {
    
    private String courseName;
    private int userScore;
    private double courseRating;
    private double slopeRating;
    private User user;


    public ScoreDTO() {

    }

    public void ScoredDTO(String courseName, int userScore, double courseRating, double slopeRating, User user) {
        this.courseName = courseName;
        this.userScore = userScore;
        this.courseRating = courseRating;
        this.slopeRating = slopeRating;
        this.user = user;
    }

    public User getUser() {
        return user;
    }

    public String getCourseName() {
        return courseName;
    }

    public int getScore() {
        return userScore;
    }

    public double getCourseRating() {
        return courseRating;
    }

    public double getSlopeRating() {
        return slopeRating;
    }

    public void setCourseName(String courseName) {
        this.courseName = courseName;
    }

    public void setScore(int userScore) {
        this.userScore = userScore;
    }

    public void setCourseRating(double courseRating) {
        this.courseRating = courseRating;
    }

    public void setSlopeRating(double slopeRating) {
        this.slopeRating = slopeRating;
    }

    public void setUser(User user) {
        this.user = user;
    }
    
}
