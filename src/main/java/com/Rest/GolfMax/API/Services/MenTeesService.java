package com.Rest.GolfMax.API.Services;

import com.Rest.GolfMax.API.Models.MenTees;
import com.Rest.GolfMax.API.Repositories.MenTeesRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.context.annotation.RequestScope;

import javax.transaction.Transactional;
import java.util.List;

@Service
@Transactional
@RequestScope
public class MenTeesService {
    @Autowired
    MenTeesRepository repository;

    public List<MenTees> listAllTees () {
        return repository.findAll();
    }

    public void addNewTees(MenTees menTees) {
        repository.save(menTees);
    }

    public MenTees getTeesByCourseId(long courseId) {
        return repository.findByCourseId(courseId);
    }

    public void deleteTeesById(Long id) {
        repository.deleteById(id);
    }
}
