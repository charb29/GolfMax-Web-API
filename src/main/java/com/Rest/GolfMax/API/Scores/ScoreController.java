package com.Rest.GolfMax.API.Scores;

import com.Rest.GolfMax.API.Users.User;
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
    public ResponseEntity<Score> getScoresByUserId(@PathVariable Long id){
        try {
            Score scores = scoreService.getScoreByUser(id);
            return new ResponseEntity<Score> (scores, HttpStatus.OK);
        } catch (NoSuchElementException e) {
            return new ResponseEntity<Score> (HttpStatus.NOT_FOUND);
        }
    }

    @PostMapping("")
    public ResponseEntity<Score> saveNewScore(@RequestBody ScoreDTO scoreDTO) {
        Score score = new Score();

        score.setCourseName(scoreDTO.getCourseName());
        score.setScore(scoreDTO.getScore());
        score.setCourseRating(scoreDTO.getCourseRating());
        score.setSlopeRating(scoreDTO.getSlopeRating());
        score.setUser(scoreDTO.getUser());

        scoreRepository.save(score);
        return new ResponseEntity<Score>(score, HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public void deleteScore(@PathVariable Long id) {
        scoreService.deleteScore(id);
    }

}
