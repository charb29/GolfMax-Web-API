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
    @OneToOne
    private ChampionshipTees championshipTees;
    @OneToOne
    private MenTees menTees;
    @OneToOne
    private WomenTees womenTees;
    @Column(name = "courseName", nullable = false)
    private String courseName;

    public Course() {}

    public Course(long id, ChampionshipTees championshipTees, MenTees menTees,
                  WomenTees womenTees, String courseName) {
        this.id = id;
        this.championshipTees = championshipTees;
        this.menTees = menTees;
        this.womenTees = womenTees;
        this.courseName = courseName;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public ChampionshipTees getChampionshipTees() {
        return championshipTees;
    }

    public void setChampionshipTees(ChampionshipTees championshipTees) {
        this.championshipTees = championshipTees;
    }

    public MenTees getMenTees() {
        return menTees;
    }

    public void setMenTees(MenTees menTees) {
        this.menTees = menTees;
    }

    public WomenTees getWomenTees() {
        return womenTees;
    }

    public void setWomenTees(WomenTees womenTees) {
        this.womenTees = womenTees;
    }

    public String getCourseName() {
        return courseName;
    }

    public void setCourseName(String courseName) {
        this.courseName = courseName;
    }
}