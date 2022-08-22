package com.Rest.GolfMax.API.Controllers;

import com.Rest.GolfMax.API.DTOs.ScoreDto;
import com.Rest.GolfMax.API.Models.Score;
import com.Rest.GolfMax.API.Services.Interfaces.ScoreService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Arrays;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/scores")
public class ScoreController {
    @Autowired
    private ModelMapper modelMapper;

    private final ScoreService SCORE_SERVICE;

    public ScoreController(ScoreService scoreService) {
        super();
        this.SCORE_SERVICE = scoreService;
    }

    @GetMapping
    public List<ScoreDto> getAllScores() {
        return SCORE_SERVICE.getAllScores()
                .stream()
                .map(post -> modelMapper.map(post, ScoreDto.class))
                .collect(Collectors.toList());
    }

    @GetMapping("/{id}")
    public ResponseEntity<ScoreDto> getScoresById(@PathVariable long id) {
        try {
            Score score = SCORE_SERVICE.getScoreById(id);
            ScoreDto scoreResponse = modelMapper.map(score, ScoreDto.class);
            return new ResponseEntity<>(scoreResponse, HttpStatus.OK);
        } catch (NoSuchElementException e) {
            return new ResponseEntity<> (HttpStatus.NOT_FOUND);
        }
    }

    @GetMapping("/users/{id}")
    public ResponseEntity<List<ScoreDto>> getScoresByUserId(@PathVariable long id) {
        try {
            List<Score> scores = SCORE_SERVICE.getScoresByUserId(id);
            ScoreDto scoreResponse = modelMapper.map(scores, ScoreDto.class);
            return new ResponseEntity<>(Arrays.asList(scoreResponse), HttpStatus.OK);
        } catch (NoSuchElementException e) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @PostMapping("/users")
    public ResponseEntity<ScoreDto> addScore(@RequestBody ScoreDto scoreDto) {
        Score scoreRequest = modelMapper.map(scoreDto, Score.class);
        Score score = SCORE_SERVICE.createScore(scoreRequest);
        ScoreDto scoreResponse = modelMapper.map(score, ScoreDto.class);
        return new ResponseEntity<>(scoreResponse, HttpStatus.CREATED);
    }

    @DeleteMapping("/{id}")
    public void deleteScore(@PathVariable long id) {
        SCORE_SERVICE.deleteScore(id);
    }

    @GetMapping("/courses/{id}")
    public ResponseEntity<List<ScoreDto>> getScoresByCourseId(@PathVariable long id) {
        try {
            List<Score> scores = SCORE_SERVICE.getScoresByCourseId(id);
            ScoreDto scoreResponse = modelMapper.map(scores, ScoreDto.class);
            return new ResponseEntity<>(Arrays.asList(scoreResponse), HttpStatus.OK);
        } catch (NoSuchElementException e) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }
}
