package com.Rest.GolfMax.API.Models;

import com.fasterxml.jackson.annotation.JsonManagedReference;

import java.util.ArrayList;
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
    @JsonManagedReference
    @OneToMany(mappedBy = "course", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    private List<HoleLayout> holeLayout = new ArrayList<>();
    @Column(name = "courseName", nullable = false)
    private String courseName;

    public Course() {
    }

    public Course(long id, List<HoleLayout> holeLayout, String courseName) {
        this.id = id;
        this.holeLayout = holeLayout;
        this.courseName = courseName;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    @JsonManagedReference
    public List<HoleLayout> getHoleLayout() {
        return holeLayout;
    }

    public void setHoleLayout(List<HoleLayout> holeLayout) {
        this.holeLayout = holeLayout;
    }

    public String getCourseName() {
        return courseName;
    }

    public void setCourseName(String courseName) {
        this.courseName = courseName;
    }
}