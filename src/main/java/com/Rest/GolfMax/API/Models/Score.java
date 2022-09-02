package com.Rest.GolfMax.API.Models;

import javax.persistence.*;

@Entity
@Table(name = "scores")
public class Score {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Long id;

    @ManyToOne
    private User user;

    @ManyToOne
    private Course course;

    @Column(name = "score", nullable = false)
    private int userScore;

    @Column(name = "courseRating")
    private double courseRating;

    @Column(name = "slopeRating")
    private double slopeRating;

    public Score() {}

    public Score(Long id, User user, Course course, int userScore, double courseRating, double slopeRating) {
        this.id = id;
        this.userScore = userScore;
        this.user = user;
        this.course = course;
        this.courseRating = courseRating;
        this.slopeRating = slopeRating;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long scoreId) {
        this.id = scoreId;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public Course getCourse() {
        return course;
    }

    public void setCourse(Course course) {
        this.course = course;
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
