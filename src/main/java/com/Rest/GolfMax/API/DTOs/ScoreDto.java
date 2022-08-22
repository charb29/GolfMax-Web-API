package com.Rest.GolfMax.API.DTOs;

public class ScoreDto {
    private long id;
    private UserDto userDto;
    private CourseDto courseDto;
    private int userScore;
    private double courseRating;
    private double slopeRating;

    public ScoreDto() {}

    public ScoreDto(long id, UserDto userDto, CourseDto courseDto, int userScore,
                    double courseRating, double slopeRating) {
        this.id = id;
        this.userDto = userDto;
        this.courseDto = courseDto;
        this.userScore = userScore;
        this.courseRating = courseRating;
        this.slopeRating = slopeRating;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public UserDto getUserDto() {
        return userDto;
    }

    public void setUserDto(UserDto userDto) {
        this.userDto = userDto;
    }

    public CourseDto getCourseDto() {
        return courseDto;
    }

    public void setCourseDto(CourseDto courseDto) {
        this.courseDto = courseDto;
    }

    public int getUserScore() {
        return userScore;
    }

    public void setUserScore(int userScore) {
        this.userScore = userScore;
    }

    public double getCourseRating() {
        return courseRating;
    }

    public void setCourseRating(double courseRating) {
        this.courseRating = courseRating;
    }

    public double getSlopeRating() {
        return slopeRating;
    }

    public void setSlopeRating(double slopeRating) {
        this.slopeRating = slopeRating;
    }
}
