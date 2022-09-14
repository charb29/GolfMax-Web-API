package com.Rest.GolfMax.API.Models;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "holeLayouts")
public class HoleLayout {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Long id;

    @OneToMany(mappedBy = "holeLayout", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @JsonManagedReference
    private List<Hole> holes = new ArrayList<>();

    @ManyToOne
    @JsonBackReference
    private Course course;

    @Enumerated(EnumType.ORDINAL)
    @Column(name = "layout_type")
    private LayoutType layoutType;

    @Column(name = "front_9_yards")
    private long front9Yards;

    @Column(name = "back_9_yards")
    private long back9Yards;

    @Column(name = "overall_par")
    private int overallPar;

    @Column(name = "course_rating")
    private double courseRating;

    @Column(name = "slope_rating")
    private double slopeRating;

    public HoleLayout() {
    }

    public HoleLayout(Long id, List<Hole> holes, Course course, LayoutType layoutType, long front9Yards,
                      long back9Yards, int overallPar, double courseRating, double slopeRating) {
        this.id = id;
        this.holes = holes;
        this.course = course;
        this.layoutType = layoutType;
        this.front9Yards = front9Yards;
        this.back9Yards = back9Yards;
        this.overallPar = overallPar;
        this.courseRating = courseRating;
        this.slopeRating = slopeRating;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public List<Hole> getHoles() {
        return holes;
    }

    public void setHoles(List<Hole> holes) {
        this.holes = holes;
    }

    public Course getCourse() {
        return course;
    }

    public void setCourse(Course course) {
        this.course = course;
    }

    public LayoutType getLayoutType() {
        return layoutType;
    }

    public void setLayoutType(LayoutType layoutType) {
        this.layoutType = layoutType;
    }

    public long getFront9Yards() {
        return front9Yards;
    }

    public void setFront9Yards(long front9Yards) {
        this.front9Yards = front9Yards;
    }

    public long getBack9Yards() {
        return back9Yards;
    }

    public void setBack9Yards(long back9Yards) {
        this.back9Yards = back9Yards;
    }

    public int getOverallPar() {
        return overallPar;
    }

    public void setOverallPar(int par) {
        this.overallPar = par;
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
}
