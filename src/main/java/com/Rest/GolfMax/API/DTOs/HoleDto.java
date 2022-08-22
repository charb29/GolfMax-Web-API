package com.Rest.GolfMax.API.DTOs;


public class HoleDto {
    private long id;
    private HoleLayoutDto holeLayoutDto;
    private int holeNumber;
    private int yards;
    private int par;

    public HoleDto() {}

    public HoleDto(int holeNumber, int yards, int par) {
        this.holeNumber = holeNumber;
        this.yards = yards;
        this.par = par;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public HoleLayoutDto getHoleLayoutDto() {
        return holeLayoutDto;
    }

    public void setHoleLayoutDto(HoleLayoutDto holeLayoutDto) {
        this.holeLayoutDto = holeLayoutDto;
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
