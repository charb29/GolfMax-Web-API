package com.Rest.GolfMax.API.Controllers;

import com.Rest.GolfMax.API.Models.ChampionshipTees;
import com.Rest.GolfMax.API.Services.ChampionshipTeesService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/courses/championship-tees")
public class ChampionshipTeesController {
    @Autowired
    private ChampionshipTeesService service;

    @GetMapping("/list-all")
    public List<ChampionshipTees> listAllTees() {
        return service.listAllTees();
    }

    @PostMapping("/add-new")
    public ResponseEntity<ChampionshipTees> addNewTees(@RequestBody ChampionshipTees tees) {
        service.addNewTees(tees);
        return new ResponseEntity<>(tees, HttpStatus.CREATED);
    }

    @GetMapping("/course/{id}")
    public ChampionshipTees getChampionshipTeesByCourseId(@PathVariable long id) {
        return service.getTeesByCourseId(id);
    }

    @DeleteMapping("/delete/{id}")
    public void deleteTees(@PathVariable long id) {
        service.deleteTeesById(id);
    }
}
