package com.Rest.GolfMax.API.Models;

import java.util.List;
import javax.persistence.*;

@Entity
@Table(name = "courses")
public class Course {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private long id;

    @OneToMany(mappedBy = "course")
    private List<Score> scores;

    @Column(name = "courseName", nullable = false)
    private String courseName;

    @Column(name = "courseRating", nullable = false)
    private double courseRating;

    @Column(name = "slopeRating", nullable = false)
    private double slopeRating;

    @Column(name = "overallPar", nullable = false)
    private int overallPar;

    @Column(name = "hole1", nullable = false)
    private int hole1;

    @Column(name = "hole2", nullable = false)
    private int hole2;

    @Column(name = "hole3", nullable = false)
    private int hole3;

    @Column(name = "hole4", nullable = false)
    private int hole4;

    @Column(name = "hole5", nullable = false)
    private int hole5;

    @Column(name = "hole6", nullable = false)
    private int hole6;

    @Column(name = "hole7", nullable = false)
    private int hole7;

    @Column(name = "hole8", nullable = false)
    private int hole8;

    @Column(name = "hole9", nullable = false)
    private int hole9;

    @Column(name = "hole10", nullable = true)
    private int hole10;

    @Column(name = "hole11", nullable = true)
    private int hole11;

    @Column(name = "hole12", nullable = true)
    private int hole12;

    @Column(name = "hole13", nullable = true)
    private int hole13;

    @Column(name = "hole14", nullable = true)
    private int hole14;

    @Column(name = "hole15", nullable = true)
    private int hole15;

    @Column(name = "hole16", nullable = true)
    private int hole16;

    @Column(name = "hole17", nullable = true)
    private int hole17;

    @Column(name = "hole18", nullable = true)
    private int hole18;

    public Course() {}


    public Course(long id, String courseName, double courseRating, double slopeRating,
            int overallPar, int hole1, int hole2, int hole3, int hole4, int hole5, int hole6, int hole7, int hole8,
            int hole9, int hole10, int hole11, int hole12, int hole13, int hole14, int hole15, int hole16, int hole17,
            int hole18) {
        this.id = id;
        this.courseName = courseName;
        this.courseRating = courseRating;
        this.slopeRating = slopeRating;
        this.overallPar = overallPar;
        this.hole1 = hole1;
        this.hole2 = hole2;
        this.hole3 = hole3;
        this.hole4 = hole4;
        this.hole5 = hole5;
        this.hole6 = hole6;
        this.hole7 = hole7;
        this.hole8 = hole8;
        this.hole9 = hole9;
        this.hole10 = hole10;
        this.hole11 = hole11;
        this.hole12 = hole12;
        this.hole13 = hole13;
        this.hole14 = hole14;
        this.hole15 = hole15;
        this.hole16 = hole16;
        this.hole17 = hole17;
        this.hole18 = hole18;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getCourseName() {
        return courseName;
    }

    public void setCourseName(String courseName) {
        this.courseName = courseName;
    }

    public double getCourseRating() {
        return courseRating;
    }

    public void setCourseRating(double courseRating) {
        this.courseRating = courseRating;
    }

    public double getSlopeRating() {
        return slopeRating;
    }

    public void setSlopeRating(double slopeRating) {
        this.slopeRating = slopeRating;
    }

    public int getOverallPar() {
        return overallPar;
    }

    public void setOverallPar(int overallPar) {
        this.overallPar = overallPar;
    }

    public int getHole1() {
        return hole1;
    }

    public void setHole1(int hole1) {
        this.hole1 = hole1;
    }

    public int getHole2() {
        return hole2;
    }

    public void setHole2(int hole2) {
        this.hole2 = hole2;
    }

    public int getHole3() {
        return hole3;
    }

    public void setHole3(int hole3) {
        this.hole3 = hole3;
    }

    public int getHole4() {
        return hole4;
    }

    public void setHole4(int hole4) {
        this.hole4 = hole4;
    }

    public int getHole5() {
        return hole5;
    }

    public void setHole5(int hole5) {
        this.hole5 = hole5;
    }

    public int getHole6() {
        return hole6;
    }

    public void setHole6(int hole6) {
        this.hole6 = hole6;
    }

    public int getHole7() {
        return hole7;
    }

    public void setHole7(int hole7) {
        this.hole7 = hole7;
    }

    public int getHole8() {
        return hole8;
    }

    public void setHole8(int hole8) {
        this.hole8 = hole8;
    }

    public int getHole9() {
        return hole9;
    }

    public void setHole9(int hole9) {
        this.hole9 = hole9;
    }


    public int getHole10() {
        return hole10;
    }

    public void setHole10(int hole10) {
        this.hole10 = hole10;
    }

    public int getHole11() {
        return hole11;
    }

    public void setHole11(int hole11) {
        this.hole11 = hole11;
    }

    public int getHole12() {
        return hole12;
    }

    public void setHole12(int hole12) {
        this.hole12 = hole12;
    }

    public int getHole13() {
        return hole13;
    }

    public void setHole13(int hole13) {
        this.hole13 = hole13;
    }

    public int getHole14() {
        return hole14;
    }

    public void setHole14(int hole14) {
        this.hole14 = hole14;
    }

    public int getHole15() {
        return hole15;
    }

    public void setHole15(int hole15) {
        this.hole15 = hole15;
    }

    public int getHole16() {
        return hole16;
    }

    public void setHole16(int hole16) {
        this.hole16 = hole16;
    }

    public int getHole17() {
        return hole17;
    }

    public void setHole17(int hole17) {
        this.hole17 = hole17;
    }

    public int getHole18() {
        return hole18;
    }

    public void setHole18(int hole18) {
        this.hole18 = hole18;
    }
}
