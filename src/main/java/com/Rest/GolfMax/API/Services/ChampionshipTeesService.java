package com.Rest.GolfMax.API.Services;

import com.Rest.GolfMax.API.Models.ChampionshipTees;
import com.Rest.GolfMax.API.Repositories.ChampionshipTeesRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.context.annotation.RequestScope;

import javax.transaction.Transactional;
import java.util.List;

@Service
@Transactional
@RequestScope
public class ChampionshipTeesService {
    @Autowired
    private ChampionshipTeesRepository repository;

    public List<ChampionshipTees> listAllTees() {
        return repository.findAll();
    }

    public void addNewTees(ChampionshipTees championshipTees) {
        repository.save(championshipTees);
    }

    public ChampionshipTees getTeesByCourseId(long courseId) {
        return repository.findByCourseId(courseId);
    }

    public void deleteTeesById(Long id) {
        repository.deleteById(id);
    }
}
