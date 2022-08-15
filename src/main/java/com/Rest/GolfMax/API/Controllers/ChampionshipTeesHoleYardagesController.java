package com.Rest.GolfMax.API.Controllers;

import com.Rest.GolfMax.API.Models.ChampionshipTeesHoleYardages;
import com.Rest.GolfMax.API.Services.ChampionshipTeesHoleYardagesService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/courses/championship-tees/yardages")
public class ChampionshipTeesHoleYardagesController {
    @Autowired
    private ChampionshipTeesHoleYardagesService service;

    @GetMapping("/list-all")
    public List<ChampionshipTeesHoleYardages> listAllTeeYardages() {
        return service.listAllHoleYardages();
    }

    @PostMapping("/add-new")
    public ResponseEntity<ChampionshipTeesHoleYardages> addNewTeeYardages(
            @RequestBody ChampionshipTeesHoleYardages teeYardages) {
        service.addNewHoleYardages(teeYardages);
        return new ResponseEntity<>(teeYardages, HttpStatus.CREATED);
    }

    @GetMapping("/course/{id}")
    public ChampionshipTeesHoleYardages getHoleYardagesByCourseId(@PathVariable long id) {
        return service.getYardagesByCourseId(id);
    }

    @DeleteMapping("/delete/{id}")
    public void deleteTeeYardages(@PathVariable long id) {
        service.deleteYardagesById(id);
    }
}
