package com.Rest.GolfMax.API.Controllers;

import com.Rest.GolfMax.API.Models.MenTeesHoleYardages;
import com.Rest.GolfMax.API.Services.MenTeesHoleYardagesService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/courses/men-tees/yardages")
public class MenTeesHoleYardagesController {
    @Autowired
    private MenTeesHoleYardagesService service;

    @GetMapping("/list-all")
    public List<MenTeesHoleYardages> listAllTeeYardages() {
        return service.listAllHoleYardages();
    }

    @PostMapping("/add-new")
    public ResponseEntity<MenTeesHoleYardages> addNewTeeYardages(
            @RequestBody MenTeesHoleYardages teeYardages) {
        service.addNewHoleYardages(teeYardages);
        return new ResponseEntity<>(teeYardages, HttpStatus.CREATED);
    }

    @GetMapping("/course/{id}")
    public MenTeesHoleYardages getHoleYardagesByCourseId(@PathVariable long id) {
        return service.getYardagesByCourseId(id);
    }

    @DeleteMapping("/delete/{id}")
    public void deleteTeeYardages(@PathVariable long id) {
        service.deleteYardagesById(id);
    }
}

