package com.Rest.GolfMax.API.DTOs;

public class PlayerStatisticsDto {

    private long id;
    private UserDto userDto;
    private int roundsPlayed;
    private double handicapIndex;
    private double averageScore;

    public PlayerStatisticsDto() {}

    public PlayerStatisticsDto(long id, UserDto userDto, int roundsPlayed, double handicapIndex, double averageScore) {
        this.id = id;
        this.userDto = userDto;
        this.roundsPlayed = roundsPlayed;
        this.handicapIndex = handicapIndex;
        this.averageScore = averageScore;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public UserDto getUserDto() {
        return userDto;
    }

    public void setUserDto(UserDto userDto) {
        this.userDto = userDto;
    }

    public int getRoundsPlayed() {
        return roundsPlayed;
    }

    public void setRoundsPlayed(int roundsPlayed) {
        this.roundsPlayed = roundsPlayed;
    }

    public double getHandicapIndex() {
        return handicapIndex;
    }

    public void setHandicapIndex(double handicapIndex) {
        this.handicapIndex = handicapIndex;
    }

    public double getAverageScore() {
        return averageScore;
    }

    public void setAverageScore(double averageScore) {
        this.averageScore = averageScore;
    }
}
