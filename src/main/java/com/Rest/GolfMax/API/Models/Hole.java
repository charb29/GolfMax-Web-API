package com.Rest.GolfMax.API.Models;

import com.fasterxml.jackson.annotation.JsonBackReference;

import javax.persistence.*;

@Entity
@Table(name = "hole")
public class Hole {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private long id;
    @JsonBackReference
    @ManyToOne
    private HoleLayout holeLayout;
    @Column(name = "holeNumber", nullable = false)
    private int holeNumber;
    @Column(name = "yards", nullable = false)
    private int yards;
    @Column(name = "par", nullable = false)
    private int par;

    public Hole(int holeNumber, int yards, int par) {
        this.holeNumber = holeNumber;
        this.yards = yards;
        this.par = par;
    }

    public Hole() {

    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public HoleLayout getHoleLayout() {
        return holeLayout;
    }

    public void setHoleLayout(HoleLayout holeLayout) {
        this.holeLayout = holeLayout;
    }

    public int getHoleNumber() {
        return holeNumber;
    }

    public void setHoleNumber(int holeNumber) {
        this.holeNumber = holeNumber;
    }

    public int getYards() {
        return yards;
    }

    public void setYards(int yards) {
        this.yards = yards;
    }

    public int getPar() {
        return par;
    }

    public void setPar(int par) {
        this.par = par;
    }
}
