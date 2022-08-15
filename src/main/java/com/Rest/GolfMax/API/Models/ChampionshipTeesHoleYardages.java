package com.Rest.GolfMax.API.Course;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "championshipTeesHoleYardages")
public class ChampionshipTeesHoleYardages {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private long id;
    @OneToOne
    private ChampionshipTees championshipTees;
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

    public ChampionshipTeesHoleYardages() {
    }

    public ChampionshipTeesHoleYardages(long id, ChampionshipTees championshipTees, int hole1Yards, int hole2Yards,
                                        int hole3Yards, int hole4Yards, int hole5Yards, int hole6Yards, int hole7Yards,
                                        int hole8Yards, int hole9Yards, int hole10Yards, int hole11Yards,
                                        int hole12Yards, int hole13Yards, int hole14Yards, int hole15Yards,
                                        int hole16Yards, int hole17Yards, int hole18Yards) {
        this.id = id;
        this.championshipTees = championshipTees;
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

    public List<Integer> getFront9HoleYards() {
        List<Integer> front9HoleYards = new ArrayList<>();
        front9HoleYards.add(getHole1Yards());
        front9HoleYards.add(getHole2Yards());
        front9HoleYards.add(getHole3Yards());
        front9HoleYards.add(getHole4Yards());
        front9HoleYards.add(getHole5Yards());
        front9HoleYards.add(getHole6Yards());
        front9HoleYards.add(getHole7Yards());
        front9HoleYards.add(getHole8Yards());
        front9HoleYards.add(getHole9Yards());
        return front9HoleYards;
    }

    public List<Integer> getBack9HoleYards() {
        List<Integer> back9HoleYards = new ArrayList<>();
        back9HoleYards.add(getHole10Yards());
        back9HoleYards.add(getHole11Yards());
        back9HoleYards.add(getHole12Yards());
        back9HoleYards.add(getHole13Yards());
        back9HoleYards.add(getHole14Yards());
        back9HoleYards.add(getHole15Yards());
        back9HoleYards.add(getHole16Yards());
        back9HoleYards.add(getHole17Yards());
        back9HoleYards.add(getHole18Yards());
        return back9HoleYards;
    }
}
