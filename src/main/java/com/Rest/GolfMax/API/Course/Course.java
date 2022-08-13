package com.Rest.GolfMax.API.Course;

import com.Rest.GolfMax.API.Score.Score;

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
    @OneToMany(mappedBy = "course")
    private List<ChampionshipTees> championshipTees;
    @OneToMany(mappedBy = "course")
    private List<MenTees> menTees;
    @OneToMany(mappedBy = "course")
    private List<WomenTees> womenTees;
    @Column(name = "courseName", nullable = false)
    private String courseName;
    @Column(name = "overallPar", nullable = false)
    private int overallPar;

    public Course() {}

    public Course(long id, String courseName, int overallPar) {
        this.id = id;
        this.courseName = courseName;
        this.overallPar = overallPar;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public List<Score> getScores() {
        return scores;
    }

    public void setScores(List<Score> scores) {
        this.scores = scores;
    }

    public String getCourseName() {
        return courseName;
    }

    public void setCourseName(String courseName) {
        this.courseName = courseName;
    }

    public int getOverallPar() {
        return overallPar;
    }

    public void setOverallPar(int overallPar) {
        this.overallPar = overallPar;
    }

    public List<ChampionshipTees> getChampionshipTees() {
        return championshipTees;
    }

    public void setChampionshipTees(List<ChampionshipTees> championshipTees) {
        this.championshipTees = championshipTees;
    }

    public List<MenTees> getMenTees() {
        return menTees;
    }

    public void setMenTees(List<MenTees> menTees) {
        this.menTees = menTees;
    }

    public List<WomenTees> getWomenTees() {
        return womenTees;
    }

    public void setWomenTees(List<WomenTees> womenTees) {
        this.womenTees = womenTees;
    }
}