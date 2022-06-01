package com.Rest.GolfMax.API.Models;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "courses")
public class Course {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "courseId", nullable = false)
    private long courseId;

    @OneToMany(mappedBy = "course")
    private List<Score> scores;

    @Column(name = "courseName")
    private String courseName;

    @Column(name = "courseRating")
    private double courseRating;

    @Column(name = "slopeRating")
    private double slopeRating;

    public Course() {

    }

    public Course(long courseId, String courseName, double courseRating, double slopeRating) {
        this.courseId = courseId;
        this.courseName = courseName;
        this.courseRating = courseRating;
        this.slopeRating = slopeRating;
    }

    public long getCourseId() {
        return courseId;
    }

    public String getCourseName() {
        return courseName;
    }

    public double getCourseRating() {
        return courseRating;
    }

    public double getSlopeRating() {
        return slopeRating;
    }

    public void setCourseId(long courseId) {
        this.courseId = courseId;
    }

    public void setCourseName(String courseName) {
        this.courseName = courseName;
    }

    public void setCourseRating(double courseRating) {
        this.courseRating = courseRating;
    }

    public void setSlopeRating(double slopeRating) {
        this.slopeRating = slopeRating;
    }
}
