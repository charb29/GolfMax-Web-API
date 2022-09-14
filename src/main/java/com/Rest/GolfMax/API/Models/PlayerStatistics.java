package com.Rest.GolfMax.API.Models;

import javax.persistence.*;

@Entity
@Table(name = "player_statistics")
public class PlayerStatistics {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false, unique = true)
    private Long id;

    @OneToOne
    private User user;

    @Column(name = "rounds_played")
    private int roundsPlayed;

    @Column(name = "handicap_index")
    private double handicapIndex;

    @Column(name = "average_score")
    private double averageScore;

    public PlayerStatistics() {}

    public PlayerStatistics(Long id, User user, int roundsPlayed, double handicapIndex, double averageScore) {
        this.id = id;
        this.user = user;
        this.roundsPlayed = roundsPlayed;
        this.handicapIndex = handicapIndex;
        this.averageScore = averageScore;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
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
