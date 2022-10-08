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

    private final ScoreService scoreService;

    public ScoreController(ScoreService scoreService) {
        super();
        if (scoreService == null) {
            throw new NullPointerException("Score Service");
        }
        this.scoreService = scoreService;
    }

    @GetMapping
    public ResponseEntity<List<Score>> getAllScores() {
        try {
            List<Score> scoreResponse = scoreService.getAllScores();
            return new ResponseEntity<>(scoreResponse, HttpStatus.OK);
        } catch (NoSuchElementException e) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @GetMapping("/{id}")
    public ResponseEntity<Score> getScoreByScoreId(@PathVariable Long id) {
        try {
            Score scoreResponse = scoreService.getScoreById(id);
            return new ResponseEntity<>(scoreResponse, HttpStatus.OK);
        } catch (NoSuchElementException e) {
            return new ResponseEntity<> (HttpStatus.NOT_FOUND);
        }
    }

    @GetMapping("/users/{id}")
    public ResponseEntity<List<Score>> getScoresByUserId(@PathVariable Long id) {
        try {
            List<Score> scoreResponse = scoreService.getAllScoresByUserId(id);
            return new ResponseEntity<>(scoreResponse, HttpStatus.OK);
        } catch (NoSuchElementException e) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @PostMapping("/users")
    public ResponseEntity<Score> addScore(@RequestBody Score scoreRequest) {
        Score scoreResponse = scoreService.addScore(scoreRequest);
        return new ResponseEntity<>(scoreResponse, HttpStatus.CREATED);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Score> deleteScore(@PathVariable Long id) {
        try {
            scoreService.deleteScoreById(id);
            return new ResponseEntity<>(HttpStatus.OK);
        } catch (NoSuchElementException e) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @GetMapping("/courses/{id}")
    public ResponseEntity<List<Score>> getScoresByCourseId(@PathVariable Long id) {
        try {
            List<Score> scoreResponse = scoreService.getAllScoresByCourseId(id);
            return new ResponseEntity<>(scoreResponse, HttpStatus.OK);
        } catch (NoSuchElementException e) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }
}
