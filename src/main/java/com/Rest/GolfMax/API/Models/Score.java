package com.Rest.GolfMax.API.Models;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Table(name = "scores")
@Getter
@Setter
@NoArgsConstructor
public class Score {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Long id;

    @ManyToOne
    private User user;

    @ManyToOne
    private Course course;

    @Column(name = "score", nullable = false)
    private int userScore;

    @Column(name = "course_rating")
    private double courseRating;

    @Column(name = "slope_rating")
    private double slopeRating;

    public Score(Long id, User user, Course course, int userScore, double courseRating, double slopeRating) {
        this.id = id;
        this.userScore = userScore;
        this.user = user;
        this.course = course;
        this.courseRating = courseRating;
        this.slopeRating = slopeRating;
    }
}
