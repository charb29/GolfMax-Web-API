package com.Rest.GolfMax.API.Scores;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import java.util.List;
import java.util.NoSuchElementException;

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
    public List<Score> getScoresByUserId(@PathVariable long id){
        return scoreService.getScoreByUserId(id);
    }

    @PostMapping("")
    public ResponseEntity<Score> addScore(@RequestBody Score score) {
        scoreRepository.save(score);
        return new ResponseEntity<Score>(score, HttpStatus.CREATED);
    }

    @DeleteMapping("/{id}")
    public void deleteScore(@PathVariable Long id) {
        scoreService.deleteScore(id);
    }

}
