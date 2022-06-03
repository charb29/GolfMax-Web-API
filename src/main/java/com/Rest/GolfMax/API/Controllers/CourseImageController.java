package com.Rest.GolfMax.API.Controllers;

import com.Rest.GolfMax.API.Models.Course;
import com.Rest.GolfMax.API.Models.CourseImageFile;
import com.Rest.GolfMax.API.Services.CourseImageService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ByteArrayResource;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;
import java.util.NoSuchElementException;

@RestController
@RequestMapping("/course_images")
public class CourseImageController {

    private static final Logger logger = LoggerFactory.getLogger(CourseImageFile.class);

    @Autowired
    private CourseImageService courseImageService;

    @PostMapping("/upload_file")
    public UploadFileResponse uploadFile(@RequestParam("file") MultipartFile file) throws IOException {
        CourseImageFile courseImageFile = courseImageService.storeFile(file);

        String fileDownloadUri = ServletUriComponentsBuilder.fromCurrentContextPath()
                .path("/downloadFile/")
                .path(String.valueOf(courseImageFile.getImageId()))
                .toUriString();

        return new UploadFileResponse(courseImageFile.getFileName(), fileDownloadUri,
                file.getContentType(), file.getSize());
    }

    @GetMapping("/download_file/{imageId}")
    public ResponseEntity<Resource> downloadFile(@PathVariable long imageId) {
        try {
            CourseImageFile courseImageFile = courseImageService.getFile(imageId);

            return ResponseEntity.ok()
                    .contentType(MediaType.parseMediaType(courseImageFile.getFileType()))
                    .header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=\"" + courseImageFile.getFileName() + "\"")
                    .body(new ByteArrayResource(courseImageFile.getData()));

        } catch (NoSuchElementException e) {
            return new ResponseEntity<Resource>(HttpStatus.NOT_FOUND);
        }
    }

    @DeleteMapping("/delete/{id}")
    public void deleteFile(@PathVariable long id) {
        courseImageService.deleteFile(id);
    }
}
