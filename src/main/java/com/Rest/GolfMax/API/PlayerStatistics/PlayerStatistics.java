package com.Rest.GolfMax.API.PlayerStatistics;

import com.Rest.GolfMax.API.User.User;

import javax.persistence.*;

@Entity
@Table(name = "playerStatistics")
public class PlayerStatistics {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false, unique = true)
    private long id;
    @OneToOne
    private User user;
    @Column(name = "roundsPlayed")
    private int roundsPlayed;
    @Column(name = "handicapIndex")
    private double handicapIndex;
    @Column(name = "averageScore")
    private double averageScore;

    public PlayerStatistics() {}

    public PlayerStatistics(long id, User user, int roundsPlayed, double handicapIndex, double averageScore) {
        this.id = id;
        this.user = user;
        this.roundsPlayed = roundsPlayed;
        this.handicapIndex = handicapIndex;
        this.averageScore = averageScore;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public int getRoundsPlayed() {
        return roundsPlayed;
    }

    public void setRoundsPlayed(int roundsPlayed) {
        this.roundsPlayed = roundsPlayed;
    }

    public double getHandicapIndex() {
        return handicapIndex;
    }

    public void setHandicapIndex(double handicapIndex) {
        this.handicapIndex = handicapIndex;
    }

    public double getAverageScore() {
        return averageScore;
    }

    public void setAverageScore(double averageScore) {
        this.averageScore = averageScore;
    }
}
