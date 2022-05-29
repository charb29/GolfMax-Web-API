package com.Rest.GolfMax.API.Scores;

import javax.persistence.*;

import com.Rest.GolfMax.API.Users.User;

@Entity
@Table(name = "scores")
public class Score {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "scoreId", nullable = false)
    private long id;    
    
    @ManyToOne
    private User user;

    @Column(name = "courseName", nullable = false)
    private String courseName;

    @Column(name = "score", nullable = false)
    private int userScore;

    @Column(name = "courseRating", nullable = false)
    private double courseRating;

    @Column(name = "slopeRating", nullable = false)
    private double slopeRating;
    
    public Score() {

    }

    public Score(long id, String courseName, int userScore, double courseRating, double slopeRating, User user) {
        
        this.id = id;
        this.courseName = courseName;
        this.userScore = userScore;
        this.courseRating = courseRating;
        this.slopeRating = slopeRating;
        this.user = user;
    }

    public User getUser() {

        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public String getCourseName() {

        return courseName;
    }

    public long getId() {
        
        return id;
    }

    public int getUserScore() {

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

    public void setId(long id) {
        
        this.id = id;
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
}
