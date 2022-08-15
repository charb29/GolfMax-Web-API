package com.Rest.GolfMax.API.Controllers;

import com.Rest.GolfMax.API.Models.MenTees;
import com.Rest.GolfMax.API.Services.MenTeesService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/courses/men-tees")
public class MenTeesController {
    @Autowired
    private MenTeesService service;

    @GetMapping("/list-all")
    public List<MenTees> listAllTees() {
        return service.listAllTees();
    }

    @PostMapping("/add-new")
    public ResponseEntity<MenTees> addNewTees(@RequestBody MenTees menTees) {
        service.addNewTees(menTees);
        return new ResponseEntity<>(menTees, HttpStatus.CREATED);
    }

    @GetMapping("/course/{id}")
    public MenTees getMenTeesByCourseId(@PathVariable long id) {
        return service.getTeesByCourseId(id);
    }

    @DeleteMapping("/delete/{id}")
    public void deleteTees(@PathVariable long id) {
        service.deleteTeesById(id);
    }
}
