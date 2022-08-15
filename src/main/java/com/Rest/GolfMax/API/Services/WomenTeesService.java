package com.Rest.GolfMax.API.Services;

import com.Rest.GolfMax.API.Models.WomenTees;
import com.Rest.GolfMax.API.Repositories.WomenTeesRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.context.annotation.RequestScope;

import javax.transaction.Transactional;
import java.util.List;

@Service
@Transactional
@RequestScope
public class WomenTeesService {
    @Autowired
    private WomenTeesRepository repository;

    public List<WomenTees> listAllTees() {
        return repository.findAll();
    }

    public void addNewTees(WomenTees womenTees) {
        repository.save(womenTees);
    }

    public WomenTees getTeesByCourseId(long courseId) {
        return repository.findByCourseId(courseId);
    }

    public void deleteTeesById(Long id) {
        repository.deleteById(id);
    }
}
