package com.Rest.GolfMax.API.Services;

import com.Rest.GolfMax.API.Models.CourseImageFile;
import com.Rest.GolfMax.API.Repositories.CourseImageRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.util.StringUtils;

import java.io.IOException;

@Service
public class CourseImageService {

    @Autowired
    private CourseImageRepository courseImageRepository;

    public CourseImageFile storeFile(MultipartFile file) throws IOException {
        String fileName = StringUtils.cleanPath(file.getOriginalFilename());
        CourseImageFile courseImageFile = new CourseImageFile(fileName, file.getContentType(), file.getBytes());
        return courseImageRepository.save(courseImageFile);
    }

    public CourseImageFile getFile(long fileId) {
        return courseImageRepository.findById(fileId)
                .orElseThrow(() -> new RuntimeException());
    }

    public void deleteFile(long id) {
        courseImageRepository.deleteById(id);
    }
}
