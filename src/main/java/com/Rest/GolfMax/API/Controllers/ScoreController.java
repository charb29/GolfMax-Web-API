package com.Rest.GolfMax.API.Controllers;

import com.Rest.GolfMax.API.Models.Score;
import com.Rest.GolfMax.API.Services.Interfaces.ScoreService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.NoSuchElementException;



@RestController
@RequestMapping("/scores")
public class ScoreController {

    private final ScoreService SCORE_SERVICE;

    public ScoreController(ScoreService scoreService) {
        super();
        this.SCORE_SERVICE = scoreService;
    }

    @GetMapping
    public List<Score> getAllScores() {
        return SCORE_SERVICE.getAllScores();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Score> getScoresById(@PathVariable Long id) {
        try {
            Score scoreResponse = SCORE_SERVICE.getScoreById(id);
            return new ResponseEntity<>(scoreResponse, HttpStatus.OK);
        } catch (NoSuchElementException e) {
            return new ResponseEntity<> (HttpStatus.NOT_FOUND);
        }
    }

    @GetMapping("/users/{id}")
    public ResponseEntity<List<Score>> getScoresByUserId(@PathVariable Long id) {
        try {
            List<Score> scoreResponse = SCORE_SERVICE.getScoresByUserId(id);
            return new ResponseEntity<>(scoreResponse, HttpStatus.OK);
        } catch (NoSuchElementException e) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @PostMapping("/users")
    public ResponseEntity<Score> addScore(@RequestBody Score scoreRequest) {
        Score scoreResponse = SCORE_SERVICE.createScore(scoreRequest);
        return new ResponseEntity<>(scoreResponse, HttpStatus.CREATED);
    }

    @DeleteMapping("/{id}")
    public void deleteScore(@PathVariable Long id) {
        SCORE_SERVICE.deleteScore(id);
    }

    @GetMapping("/courses/{id}")
    public ResponseEntity<List<Score>> getScoresByCourseId(@PathVariable Long id) {
        try {
            List<Score> scoreResponse = SCORE_SERVICE.getScoresByCourseId(id);
            return new ResponseEntity<>(scoreResponse, HttpStatus.OK);
        } catch (NoSuchElementException e) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }
}
