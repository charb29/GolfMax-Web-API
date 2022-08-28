package com.Rest.GolfMax.API.DTOs;

import com.Rest.GolfMax.API.Models.LayoutType;

import java.util.List;

public class HoleLayoutDto {

    private long id;
    private List<HoleDto> holeDto;
    private CourseDto courseDto;
    private LayoutType layoutType;
    private long front9yards;
    private long back9yards;
    private int overallPar;
    private double courseRating;
    private double slopeRating;

    public HoleLayoutDto() {}

    public HoleLayoutDto(long id, List<HoleDto> holeDto, CourseDto courseDto, LayoutType layoutType, long front9yards,
                         long back9yards, int overallPar, double courseRating, double slopeRating) {
        this.id = id;
        this.holeDto = holeDto;
        this.courseDto = courseDto;
        this.layoutType = layoutType;
        this.front9yards = front9yards;
        this.back9yards = back9yards;
        this.overallPar = overallPar;
        this.courseRating = courseRating;
        this.slopeRating = slopeRating;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public List<HoleDto> getHoleDto() {
        return holeDto;
    }

    public void setHoleDto(List<HoleDto> holeDto) {
        this.holeDto = holeDto;
    }

    public CourseDto getCourseDto() {
        return courseDto;
    }

    public void setCourseDto(CourseDto courseDto) {
        this.courseDto = courseDto;
    }

    public LayoutType getLayoutType() {
        return layoutType;
    }

    public void setLayoutType(LayoutType layoutType) {
        this.layoutType = layoutType;
    }

    public long getFront9yards() {
        return front9yards;
    }

    public void setFront9yards(long front9yards) {
        this.front9yards = front9yards;
    }

    public long getBack9yards() {
        return back9yards;
    }

    public void setBack9yards(long back9yards) {
        this.back9yards = back9yards;
    }

    public int getOverallPar() {
        return overallPar;
    }

    public void setOverallPar(int overallPar) {
        this.overallPar = overallPar;
    }

    public double getCourseRating() {
        return courseRating;
    }

    public void setCourseRating(double courseRating) {
        this.courseRating = courseRating;
    }

    public double getSlopeRating() {
        return slopeRating;
    }

    public void setSlopeRating(double slopeRating) {
        this.slopeRating = slopeRating;
    }
}
