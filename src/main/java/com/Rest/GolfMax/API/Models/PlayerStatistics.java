package com.Rest.GolfMax.API.Models;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Table(name = "player_statistics")
@Getter
@Setter
@NoArgsConstructor
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

    public PlayerStatistics(Long id, User user, int roundsPlayed, double handicapIndex, double averageScore) {
        this.id = id;
        this.user = user;
        this.roundsPlayed = roundsPlayed;
        this.handicapIndex = handicapIndex;
        this.averageScore = averageScore;
    }
}
