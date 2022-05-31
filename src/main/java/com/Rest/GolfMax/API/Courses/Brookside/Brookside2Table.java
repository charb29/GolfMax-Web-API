package com.Rest.GolfMax.API.Courses.Brookside;

import com.Rest.GolfMax.API.Users.User;

import javax.persistence.*;

@Entity
@Table(name = "brookside2Table")
public class Brookside2Table {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "scoreId", nullable = false)
    private long id;

    @ManyToOne
    private User user;

    @Column(name = "score", nullable = false)
    private int userScore;


    public Brookside2Table() {

    }

    public Brookside2Table(long id, int userScore, User user) {
        this.id = id;
        this.userScore = userScore;
        this.user = user;
    }

    public long getId() {
        return id;
    }

    public int getUserScore() {
        return userScore;
    }

    public User getUser() {
        return user;
    }

    public void setId(long id) {
        this.id = id;
    }

    public void setUserScore(int userScore) {
        this.userScore = userScore;
    }

    public void setUser(User user) {
        this.user = user;
    }
}
