package com.Rest.GolfMax.API.Controllers;

import com.Rest.GolfMax.API.Models.WomenTeesHoleYardages;
import com.Rest.GolfMax.API.Services.WomenTeesHoleYardagesService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/courses/women-tees/yardages")
public class WomenTeesHoleYardagesController {
    @Autowired
    private WomenTeesHoleYardagesService service;

    @GetMapping("/list-all")
    public List<WomenTeesHoleYardages> listAllTeeYardages() {
        return service.listAllHoleYardages();
    }

    @PostMapping("/add-new")
    public ResponseEntity<WomenTeesHoleYardages> addNewTeeYardages(
            @RequestBody WomenTeesHoleYardages teeYardages) {
        service.addNewHoleYardages(teeYardages);
        return new ResponseEntity<>(teeYardages, HttpStatus.CREATED);
    }

    @GetMapping("/course/{id}")
    public WomenTeesHoleYardages getHoleYardagesByCourseId(@PathVariable long id) {
        return service.getYardagesByCourseId(id);
    }

    @DeleteMapping("/delete/{id}")
    public void deleteTeeYardages(@PathVariable long id) {
        service.deleteYardagesById(id);
    }
}
