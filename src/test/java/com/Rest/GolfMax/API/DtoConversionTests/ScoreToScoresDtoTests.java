package com.Rest.GolfMax.API.DtoConversionTests;

import com.Rest.GolfMax.API.DTOs.CourseDto;
import com.Rest.GolfMax.API.DTOs.ScoreDto;
import com.Rest.GolfMax.API.DTOs.UserDto;
import com.Rest.GolfMax.API.Models.Course;
import com.Rest.GolfMax.API.Models.Score;
import com.Rest.GolfMax.API.Models.User;
import org.junit.Test;
import org.modelmapper.ModelMapper;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
public class ScoreToScoresDtoTests {

    private ModelMapper modelMapper = new ModelMapper();

    @Test
    public void whenConvertScoreEntityToScoreDto_thenCorrect() {
        User user = new User(1, "Olivier", "password", "email");
        Course course = new Course();
        course.setId(1);
        Score score = new Score(1, user, course, 65, 67, 68);

        UserDto userDto = modelMapper.map(user, UserDto.class);
        CourseDto courseDto = modelMapper.map(course, CourseDto.class);
        ScoreDto scoreDto = modelMapper.map(score, ScoreDto.class);

        scoreDto.setUserDto(userDto);
        scoreDto.setCourseDto(courseDto);

        assertEquals(score.getId(), scoreDto.getId());
        assertEquals(score.getUser().getUsername(), scoreDto.getUserDto().getUsername());
        assertEquals(score.getCourse().getId(), scoreDto.getCourseDto().getId());
        assertEquals(score.getUserScore(), scoreDto.getUserScore());
        assertEquals(score.getCourseRating(), scoreDto.getCourseRating());
        assertEquals(score.getSlopeRating(), scoreDto.getSlopeRating());
    }

    @Test
    public void whenConvertScoreEntityListToScoreDtoList_thenCorrect() {
        User user = new User(1, "Olivier", "password", "email");
        Course course = new Course();
        course.setId(1L);
        course.setCourseName("Vista Valencia");

        Score score1 = new Score(1, user, course, 65, 67, 68);

        List<Score> scoreList = new ArrayList<>(Collections.singletonList(score1));

        UserDto userDto = modelMapper.map(user, UserDto.class);
        CourseDto courseDto = modelMapper.map(course, CourseDto.class);
        List<ScoreDto> scoreDtoList = Arrays.asList(modelMapper.map(scoreList, ScoreDto[].class));

        scoreDtoList.get(0).setUserDto(userDto);
        scoreDtoList.get(0).setCourseDto(courseDto);

        assertEquals(scoreList.size(), scoreDtoList.size());
        assertEquals(scoreList.get(0).getUser().getUsername(), scoreDtoList.get(0).getUserDto().getUsername());
    }
}
