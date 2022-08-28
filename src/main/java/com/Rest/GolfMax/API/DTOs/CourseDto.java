package com.Rest.GolfMax.API.DTOs;

import java.util.List;

public class CourseDto {

    private long id;
    private List<HoleLayoutDto> holeLayoutDto;
    private String courseName;

    public CourseDto() {}

    public CourseDto(long id, List<HoleLayoutDto> holeLayoutDto, String courseName) {
        this.id = id;
        this.holeLayoutDto = holeLayoutDto;
        this.courseName = courseName;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public List<HoleLayoutDto> getHoleLayoutDto() {
        return holeLayoutDto;
    }

    public void setHoleLayoutDto(List<HoleLayoutDto> holeLayoutDto) {
        this.holeLayoutDto = holeLayoutDto;
    }

    public String getCourseName() {
        return courseName;
    }

    public void setCourseName(String courseName) {
        this.courseName = courseName;
    }
}
