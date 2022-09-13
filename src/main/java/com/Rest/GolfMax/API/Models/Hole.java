package com.Rest.GolfMax.API.Models;


import com.fasterxml.jackson.annotation.JsonBackReference;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Table(name = "holes")
@Getter
@Setter
@NoArgsConstructor
public class Hole {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Long id;

    @ManyToOne
    @JsonBackReference
    private HoleLayout holeLayout;

    @Column(name = "hole_number", nullable = false)
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
}
