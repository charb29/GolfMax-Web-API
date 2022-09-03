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
    private Long id;

    @OneToMany(mappedBy = "course")
    private List<Score> scores;

    @Column(name = "courseName", nullable = false)
    private String courseName;

    @OneToMany(mappedBy = "course", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @JsonManagedReference
    private List<HoleLayout> holeLayout = new ArrayList<>();

    public Course() {
    }

    public Course(Long id, String courseName, List<HoleLayout> holeLayout) {
        this.id = id;
        this.courseName = courseName;
        this.holeLayout = holeLayout;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getCourseName() {
        return courseName;
    }

    public void setCourseName(String courseName) {
        this.courseName = courseName;
    }

    public List<HoleLayout> getHoleLayout() {
        return holeLayout;
    }

    public void setHoleLayout(List<HoleLayout> holeLayout) {
        this.holeLayout = holeLayout;
    }

}