package com.Rest.GolfMax.API.Models;


import javax.persistence.*;

@Entity
@Table(name = "mensTees")
public class MenTees {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private long id;
    @OneToOne(mappedBy = "mensTees")
    private Course course;
    @Column(name = "courseRating", nullable = false)
    private double courseRating;
    @Column(name = "slopeRating", nullable = false)
    private double slopeRating;
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
    @Column(name = "hole10")
    private int hole10;
    @Column(name = "hole11")
    private int hole11;
    @Column(name = "hole12")
    private int hole12;
    @Column(name = "hole13")
    private int hole13;
    @Column(name = "hole14")
    private int hole14;
    @Column(name = "hole15")
    private int hole15;
    @Column(name = "hole16")
    private int hole16;
    @Column(name = "hole17")
    private int hole17;
    @Column(name = "hole18")
    private int hole18;
    @Column(name = "hole1Yards", nullable = false)
    private int hole1Yards;
    @Column(name = "hole2Yards", nullable = false)
    private int hole2Yards;
    @Column(name = "hole3Yards", nullable = false)
    private int hole3Yards;
    @Column(name = "hole4Yards", nullable = false)
    private int hole4Yards;
    @Column(name = "hole5Yards", nullable = false)
    private int hole5Yards;
    @Column(name = "hole6Yards", nullable = false)
    private int hole6Yards;
    @Column(name = "hole7Yards", nullable = false)
    private int hole7Yards;
    @Column(name = "hole8Yards", nullable = false)
    private int hole8Yards;
    @Column(name = "hole9Yards", nullable = false)
    private int hole9Yards;
    @Column(name = "hole10Yards")
    private int hole10Yards;
    @Column(name = "hole11Yards")
    private int hole11Yards;
    @Column(name = "hole12Yards")
    private int hole12Yards;
    @Column(name = "hole13Yards")
    private int hole13Yards;
    @Column(name = "hole14Yards")
    private int hole14Yards;
    @Column(name = "hole15Yards")
    private int hole15Yards;
    @Column(name = "hole16Yards")
    private int hole16Yards;
    @Column(name = "hole17Yards")
    private int hole17Yards;
    @Column(name = "hole18Yards")
    private int hole18Yards;
    @Column(name = "totalFront9Yards", nullable = false)
    private long totalFront9Yards;
    @Column(name = "totalBack9Yards", nullable = false)
    private long totalBack9Yards;
    @Column(name = "overallPar", nullable = false)
    private int overallPar;

    public MenTees() {}

    public MenTees(long id, double courseRating, double slopeRating, int hole1, int hole2, int hole3,
                   int hole4, int hole5, int hole6, int hole7, int hole8, int hole9, int hole10, int hole11, int hole12,
                   int hole13, int hole14, int hole15, int hole16, int hole17, int hole18, int hole1Yards,
                   int hole2Yards, int hole3Yards, int hole4Yards, int hole5Yards, int hole6Yards, int hole7Yards,
                   int hole8Yards, int hole9Yards, int hole10Yards, int hole11Yards, int hole12Yards, int hole13Yards,
                   int hole14Yards, int hole15Yards, int hole16Yards, int hole17Yards, int hole18Yards,
                   long totalFront9Yards, long totalBack9Yards, int overallPar) {
        this.id = id;
        this.course = course;
        this.courseRating = courseRating;
        this.slopeRating = slopeRating;
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
        this.hole1Yards = hole1Yards;
        this.hole2Yards = hole2Yards;
        this.hole3Yards = hole3Yards;
        this.hole4Yards = hole4Yards;
        this.hole5Yards = hole5Yards;
        this.hole6Yards = hole6Yards;
        this.hole7Yards = hole7Yards;
        this.hole8Yards = hole8Yards;
        this.hole9Yards = hole9Yards;
        this.hole10Yards = hole10Yards;
        this.hole11Yards = hole11Yards;
        this.hole12Yards = hole12Yards;
        this.hole13Yards = hole13Yards;
        this.hole14Yards = hole14Yards;
        this.hole15Yards = hole15Yards;
        this.hole16Yards = hole16Yards;
        this.hole17Yards = hole17Yards;
        this.hole18Yards = hole18Yards;
        this.totalFront9Yards = totalFront9Yards;
        this.totalBack9Yards = totalBack9Yards;
        this.overallPar = overallPar;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public Course getCourse() {
        return course;
    }

    public void setCourse(Course course) {
        this.course = course;
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

    public int getHole1Yards() {
        return hole1Yards;
    }

    public void setHole1Yards(int hole1Yards) {
        this.hole1Yards = hole1Yards;
    }

    public int getHole2Yards() {
        return hole2Yards;
    }

    public void setHole2Yards(int hole2Yards) {
        this.hole2Yards = hole2Yards;
    }

    public int getHole3Yards() {
        return hole3Yards;
    }

    public void setHole3Yards(int hole3Yards) {
        this.hole3Yards = hole3Yards;
    }

    public int getHole4Yards() {
        return hole4Yards;
    }

    public void setHole4Yards(int hole4Yards) {
        this.hole4Yards = hole4Yards;
    }

    public int getHole5Yards() {
        return hole5Yards;
    }

    public void setHole5Yards(int hole5Yards) {
        this.hole5Yards = hole5Yards;
    }

    public int getHole6Yards() {
        return hole6Yards;
    }

    public void setHole6Yards(int hole6Yards) {
        this.hole6Yards = hole6Yards;
    }

    public int getHole7Yards() {
        return hole7Yards;
    }

    public void setHole7Yards(int hole7Yards) {
        this.hole7Yards = hole7Yards;
    }

    public int getHole8Yards() {
        return hole8Yards;
    }

    public void setHole8Yards(int hole8Yards) {
        this.hole8Yards = hole8Yards;
    }

    public int getHole9Yards() {
        return hole9Yards;
    }

    public void setHole9Yards(int hole9Yards) {
        this.hole9Yards = hole9Yards;
    }

    public int getHole10Yards() {
        return hole10Yards;
    }

    public void setHole10Yards(int hole10Yards) {
        this.hole10Yards = hole10Yards;
    }

    public int getHole11Yards() {
        return hole11Yards;
    }

    public void setHole11Yards(int hole11Yards) {
        this.hole11Yards = hole11Yards;
    }

    public int getHole12Yards() {
        return hole12Yards;
    }

    public void setHole12Yards(int hole12Yards) {
        this.hole12Yards = hole12Yards;
    }

    public int getHole13Yards() {
        return hole13Yards;
    }

    public void setHole13Yards(int hole13Yards) {
        this.hole13Yards = hole13Yards;
    }

    public int getHole14Yards() {
        return hole14Yards;
    }

    public void setHole14Yards(int hole14Yards) {
        this.hole14Yards = hole14Yards;
    }

    public int getHole15Yards() {
        return hole15Yards;
    }

    public void setHole15Yards(int hole15Yards) {
        this.hole15Yards = hole15Yards;
    }

    public int getHole16Yards() {
        return hole16Yards;
    }

    public void setHole16Yards(int hole16Yards) {
        this.hole16Yards = hole16Yards;
    }

    public int getHole17Yards() {
        return hole17Yards;
    }

    public void setHole17Yards(int hole17Yards) {
        this.hole17Yards = hole17Yards;
    }

    public int getHole18Yards() {
        return hole18Yards;
    }

    public void setHole18Yards(int hole18Yards) {
        this.hole18Yards = hole18Yards;
    }

    public long getTotalFront9Yards() {
        return totalFront9Yards;
    }

    public void setTotalFront9Yards(long totalFront9Yards) {
        this.totalFront9Yards = totalFront9Yards;
    }

    public long getTotalBack9Yards() {
        return totalBack9Yards;
    }

    public void setTotalBack9Yards(long totalBack9Yards) {
        this.totalBack9Yards = totalBack9Yards;
    }

    public int getOverallPar() {
        return overallPar;
    }

    public void setOverallPar(int overallPar) {
        this.overallPar = overallPar;
    }
}
