package com.Rest.GolfMax.API.Models;


import com.fasterxml.jackson.annotation.JsonManagedReference;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;
import javax.persistence.*;

@Entity
@Table(name = "courses")
@Getter
@Setter
@NoArgsConstructor
public class Course {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Long id;

    @OneToMany(mappedBy = "course")
    private List<Score> scores;

    @Column(name = "course_name", nullable = false)
    private String courseName;

    @OneToMany(mappedBy = "course", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @JsonManagedReference
    private List<HoleLayout> holeLayout = new ArrayList<>();

    public Course(Long id, String courseName, List<HoleLayout> holeLayout) {
        this.id = id;
        this.courseName = courseName;
        this.holeLayout = holeLayout;
    }
}