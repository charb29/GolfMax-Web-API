package com.Rest.GolfMax.API.Models;
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

    public Score() {

    }

    public Score(long scoreId, User user, Course course, int userScore) {
        this.scoreId = scoreId;
        this.userScore = userScore;
        this.user = user;
        this.course = course;
    }

    public long getScoreId() {
        return scoreId;
    }

    public User getUser() {
        return user;
    }

    public int getUserScore() {
        return userScore;
    }

    public Course getCourse() {
        return course;
    }

    public void setScoreId(long scoreId) {
        this.scoreId = scoreId;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public void setCourse(Course course) {
        this.course = course;
    }

    public void setUserScore(int userScore) {
        this.userScore = userScore;
    }
}
