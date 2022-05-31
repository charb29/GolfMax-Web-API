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

    @Column(name = "tee", nullable = false)
    private String tee;
    
    public Score() {

    }

    public Score(long id, String courseName, int userScore, String tee, User user) {
        this.id = id;
        this.courseName = courseName;
        this.userScore = userScore;
        this.tee = tee;
        this.user = user;
    }

    public String getTee() {
        return tee;
    }

    public User getUser() {
        return user;
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
    
    public void setCourseName(String courseName) {
        this.courseName = courseName;
    }

    public void setTee(String tee) {
        this.tee = tee;
    }

    public void setId(long id) {
        this.id = id;
    }

    public void setScore(int userScore) {
        this.userScore = userScore;
    }

    public void setUser(User user) {
        this.user = user;
    }
}
