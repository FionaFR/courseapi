package com.example.courseapi.controller;

import com.example.courseapi.model.Course;
import com.example.courseapi.repository.CourseRepository;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.annotation.PostConstruct;
import java.util.List;

@RestController
@RequestMapping("/courses")
public class CourseController {
    private final CourseRepository courseRepository = new CourseRepository();

    @PostConstruct
    public void init() {
        // Add some example courses
        courseRepository.addCourse(new Course(1, "BIO", "101", "Introduction to Biology"));
        courseRepository.addCourse(new Course(2, "MAT", "045", "Business Statistics"));
    }

    @GetMapping
    public List<Course> getAllCourses() {
        return courseRepository.findAll();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Course> getCourseById(@PathVariable int id) {
        return courseRepository.findById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.status(HttpStatus.NOT_FOUND).build());
    }

    @GetMapping("/search")
    public List<Course> searchCourses(@RequestParam String description) {
        return courseRepository.findByDescription(description);
    }

    @PostMapping
    public ResponseEntity<String> createCourse(@RequestBody Course course) {
        if (!course.getCourseNumber().matches("\\d{3}")) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Course number must be a three-digit integer.");
        }
        if (courseRepository.findBySubjectAndNumber(course.getSubject(), course.getCourseNumber()).isPresent()) {
            return ResponseEntity.status(HttpStatus.CONFLICT).body("Course with the same subject and number already exists.");
        }
        courseRepository.addCourse(course);
        return ResponseEntity.status(HttpStatus.CREATED).body("Course created successfully.");
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteCourse(@PathVariable int id) {
        courseRepository.deleteCourse(id);
        return ResponseEntity.status(HttpStatus.NO_CONTENT).body("Course deleted successfully.");
    }
}
