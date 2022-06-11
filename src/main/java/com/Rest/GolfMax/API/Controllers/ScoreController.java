package com.Rest.GolfMax.API.Controllers;

import com.Rest.GolfMax.API.Models.Score;
import com.Rest.GolfMax.API.Repositories.ScoreRepository;
import com.Rest.GolfMax.API.Services.ScoreService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;

@RestController
@RequestMapping("/scores")
public class ScoreController {
    
    @Autowired
    ScoreService scoreService;

    @Autowired
    ScoreRepository scoreRepository;

    @GetMapping("")
    public List<Score> getAllScores() {
        return scoreService.listAllScores();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Score> getScoresById(@PathVariable Long id) {
        try {
            Score score = scoreService.getScoreById(id);
            return new ResponseEntity<Score> (score, HttpStatus.OK);
        } catch (NoSuchElementException e) {
            return new ResponseEntity<Score> (HttpStatus.NOT_FOUND);
        }
    }

    @GetMapping("/user/{id}")
    public List<Score> getScoresByUserId(@PathVariable long id, Sort sort) {
        return scoreService.getScoreByUserId(id, Sort.by("userScore").ascending());
    }

    @PostMapping("")
    public ResponseEntity<?> addScore(@RequestBody Score score) {
            scoreRepository.save(score);
            return new ResponseEntity<Score>(score, HttpStatus.CREATED);
    }

    @DeleteMapping("/{id}")
    public void deleteScore(@PathVariable Long id) {
        scoreService.deleteScore(id);
    }

    @GetMapping("/course/{id}")
    public List<Score> getScoresByCourseId(@PathVariable long id) {
        return scoreService.getScoreByCourseId(id);
    }
}
