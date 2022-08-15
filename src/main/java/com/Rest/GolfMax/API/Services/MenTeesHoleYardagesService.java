package com.Rest.GolfMax.API.Services;

import com.Rest.GolfMax.API.Models.MenTeesHoleYardages;
import com.Rest.GolfMax.API.Repositories.MenTeesHoleYardagesRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.context.annotation.RequestScope;

import javax.transaction.Transactional;
import java.util.List;

@Service
@Transactional
@RequestScope
public class MenTeesHoleYardagesService {
    @Autowired
    private MenTeesHoleYardagesRepository repository;

    public List<MenTeesHoleYardages> listAllHoleYardages() {
        return repository.findAll();
    }

    public void addNewHoleYardages(MenTeesHoleYardages menTeesHoleYardages) {
        repository.save(menTeesHoleYardages);
    }

    public MenTeesHoleYardages getYardagesByCourseId(long courseId) {
        return repository.findByCourseId(courseId);
    }

    public void deleteYardagesById(Long id) {
        repository.deleteById(id);
    }
}
