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
    @OneToMany(mappedBy = "course")
    private List<ChampionshipTees> championshipTees;
    @OneToMany(mappedBy = "course")
    private List<ChampionshipTeesHoleYardages> championshipTeesHoleYardages;
    @OneToMany(mappedBy = "course")
    private List<MenTees> menTees;
    @OneToMany
    private List<MenTeesHoleYardages> menTeesHoleYardages;
    @OneToMany(mappedBy = "course")
    private List<WomenTees> womenTees;
    @OneToMany(mappedBy = "course")
    private List<WomenTeesHoleYardages> womenTeesHoleYardages;
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

    public List<ChampionshipTees> getChampionshipTees() {
        return championshipTees;
    }

    public void setChampionshipTees(List<ChampionshipTees> championshipTees) {
        this.championshipTees = championshipTees;
    }

    public List<ChampionshipTeesHoleYardages> getChampionshipTeesHoleYardages() {
        return championshipTeesHoleYardages;
    }

    public void setChampionshipTeesHoleYardages(List<ChampionshipTeesHoleYardages> championshipTeesHoleYardages) {
        this.championshipTeesHoleYardages = championshipTeesHoleYardages;
    }

    public List<MenTees> getMenTees() {
        return menTees;
    }

    public void setMenTees(List<MenTees> menTees) {
        this.menTees = menTees;
    }

    public List<MenTeesHoleYardages> getMenTeesHoleYardages() {
        return menTeesHoleYardages;
    }

    public void setMenTeesHoleYardages(List<MenTeesHoleYardages> menTeesHoleYardages) {
        this.menTeesHoleYardages = menTeesHoleYardages;
    }

    public List<WomenTees> getWomenTees() {
        return womenTees;
    }

    public void setWomenTees(List<WomenTees> womenTees) {
        this.womenTees = womenTees;
    }

    public List<WomenTeesHoleYardages> getWomenTeesHoleYardages() {
        return womenTeesHoleYardages;
    }

    public void setWomenTeesHoleYardages(List<WomenTeesHoleYardages> womenTeesHoleYardages) {
        this.womenTeesHoleYardages = womenTeesHoleYardages;
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
}