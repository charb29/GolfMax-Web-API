package com.Rest.GolfMax.API.Scores;

import javax.persistence.*;

import com.Rest.GolfMax.API.Users.User;

@Entity
@Table(name = "scores")
public class Score {

    /**@ManyToOne
    @JoinColumn(name = "user_id")
    @Autowired
    User user;

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "scoreId", nullable = false, unique = true)
    private long scoreId;**/

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "score_generator")
    private Long id;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id", nullable = false, referencedColumnName = "id")
    private User user;

    @Column(name = "courseName", nullable = false)
    private String courseName;

    @Column(name = "score", nullable = false)
    private int userScore;

    @Column(name = "courseRating", nullable = false)
    private double courseRating;

    @Column(name = "slopeRating", nullable = false)
    private double slopeRating;

    /**public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }**/

    public Score() {

    }

    public Score(String courseName, int userScore, double courseRating, double slopeRating) {

        //this.scoreId = scoreId;
        this.courseName = courseName;
        this.userScore = userScore;
        this.courseRating = courseRating;
        this.slopeRating = slopeRating;
    }

    /**public long getId() {

        return scoreId;
    }**/

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
    
    /**public void setId(long id) {

        this.scoreId = scoreId;
    }**/

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
