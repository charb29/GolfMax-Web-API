package com.Rest.GolfMax.API.Score;
import com.Rest.GolfMax.API.Course.Course;
import com.Rest.GolfMax.API.User.User;

import javax.persistence.*;

@Entity
@Table(name = "scores")
public class Score {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private long scoreId;
    
    @ManyToOne
    private User user;

    @ManyToOne
    private Course course;

    @Column(name = "score", nullable = false)
    private int userScore;

    @Column(name = "courseRating", nullable = true)
    private double courseRating;

    @Column(name = "slopeRating", nullable = true)
    private double slopeRating;

    public Score() {}

    public Score(long scoreId, User user, Course course, int userScore, double courseRating, double slopeRating) {
        this.scoreId = scoreId;
        this.userScore = userScore;
        this.user = user;
        this.course = course;
        this.courseRating = courseRating;
        this.slopeRating = slopeRating;
    }

    public long getScoreId() {
        return scoreId;
    }

    public void setScoreId(long scoreId) {
        this.scoreId = scoreId;
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
