package com.example.courseapi.repository;

import com.example.courseapi.model.Course;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class CourseRepository {
    private final List<Course> courses = new ArrayList<>();

    public List<Course> findAll() {
        return courses;
    }

    public Optional<Course> findById(int id) {
        return courses.stream().filter(course -> course.getId() == id).findFirst();
    }

    public Optional<Course> findBySubjectAndNumber(String subject, String courseNumber) {
        return courses.stream().filter(course ->
                course.getSubject().equals(subject) &&
                        course.getCourseNumber().equals(courseNumber)).findFirst();
    }

    public List<Course> findByDescription(String description) {
        List<Course> result = new ArrayList<>();
        for (Course course : courses) {
            if (course.getDescription().toLowerCase().contains(description.toLowerCase())) {
                result.add(course);
            }
        }
        return result;
    }

    public boolean addCourse(Course course) {
        if (findBySubjectAndNumber(course.getSubject(), course.getCourseNumber()).isPresent()) {
            return false; // Course already exists
        }
        courses.add(course);
        return true; // Course added successfully
    }

    public boolean deleteCourse(int id) {
        return courses.removeIf(course -> course.getId() == id);
    }
}