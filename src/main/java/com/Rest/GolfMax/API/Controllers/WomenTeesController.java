package com.Rest.GolfMax.API.Controllers;

import com.Rest.GolfMax.API.Models.WomenTees;
import com.Rest.GolfMax.API.Services.WomenTeesService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/courses/women-tees")
public class WomenTeesController {
    @Autowired
    private WomenTeesService service;

    @GetMapping("/list-all")
    public List<WomenTees> listAllTees() {
        return service.listAllTees();
    }

    @PostMapping("/add-new")
    public ResponseEntity<WomenTees> addNewTees(@RequestBody WomenTees tees) {
        service.addNewTees(tees);
        return new ResponseEntity<>(tees, HttpStatus.CREATED);
    }

    @GetMapping("/course/{id}")
    public WomenTees getChampionshipTeesByCourseId(@PathVariable long id) {
        return service.getTeesByCourseId(id);
    }

    @DeleteMapping("/delete/{id}")
    public void deleteTees(@PathVariable long id) {
        service.deleteTeesById(id);
    }
}
