package com.Rest.GolfMax.API.Models;

import javax.persistence.*;

@Entity
@Table(name = "scores")
public class Score {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "scoreId", nullable = false)
    private long scoreId;
    
    @ManyToOne
    private User user;

    @ManyToOne
    private Course course;

    @Column(name = "score", nullable = false)
    private int userScore;

    public Score() {

    }

    public Score(long scoreId, User user, Course course) {
        this.scoreId = scoreId;
        this.user = user;
        this.course = course;
    }

    public long getScoreId() {
        return scoreId;
    }

    public User getUser() {
        return user;
    }

    public Course getCourse() {
        return course;
    }

    public int getUserScore() {
        return userScore;
    }

    public void setScoreId(long scoreId) {
        this.scoreId = scoreId;
    }
}
