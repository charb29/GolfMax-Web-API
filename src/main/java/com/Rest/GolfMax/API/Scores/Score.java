package com.Rest.GolfMax.API.Scores;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import com.Rest.GolfMax.API.Users.User;

@Entity
@Table(name = "scores")
public class Score {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "userId", nullable = false, unique = true)
    private long id;

    @ManyToOne
    @JoinColumn(name = "id")
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

    public Score(long id, String courseName, int userScore, double courseRating, double slopeRating) {

        this.id = id;
        this.courseName = courseName;
        this.userScore = userScore;
        this.courseRating = courseRating;
        this.slopeRating = slopeRating;
    }

    public long getId() {

        return id;
    }

    public String getCourseName() {

        return courseName;
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
    
    public void setId(long id) {

        this.id = id;
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
}
