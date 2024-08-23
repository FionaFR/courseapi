package com.example.courseapi.controller;

import com.example.courseapi.model.Course;
import com.example.courseapi.repository.CourseRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class CourseControllerTest {

    private CourseRepository repo;

    @BeforeEach
    public void setUp() {
        repo = new CourseRepository();
    }

    @Test
    public void testAddValidCourse() {
        Course course = new Course(3, "PHY", "201", "Physics Fundamentals");

        repo.addCourse(course);

        assertTrue(repo.findById(3).isPresent());
    }

    @Test
    public void testPreventDuplicateCourse() {
        Course course1 = new Course(3, "PHY", "201", "Physics Fundamentals");
        Course course2 = new Course(4, "PHY", "201", "Advanced Physics");

        repo.addCourse(course1);
        boolean added = repo.addCourse(course2);

        assertFalse(added);  // Should not add the duplicate course
        assertEquals(1, repo.findAll().size());
    }


    @Test
    public void testSearchCourses() {
        CourseRepository repo = new CourseRepository();
        Course course1 = new Course(1, "BIO", "101", "Introduction to Biology");
        Course course2 = new Course(2, "MAT", "045", "Business Statistics");
        Course course3 = new Course(3, "CHE", "102", "Introduction to Chemistry");

        repo.addCourse(course1);
        repo.addCourse(course2);
        repo.addCourse(course3);

        var results = repo.findByDescription("Bio");

        assertEquals(1, results.size());
        assertEquals("BIO", results.get(0).getSubject());
        assertEquals("101", results.get(0).getCourseNumber());
    }

    @Test
    public void testDeleteCourse() {
        Course course = new Course(3, "BIO", "101", "Introduction to Biology");

        repo.addCourse(course);
        boolean deleted = repo.deleteCourse(3);

        assertTrue(deleted);
        assertFalse(repo.findById(3).isPresent());
    }

    @Test
    public void testDeleteNonExistentCourse() {
        boolean deleted = repo.deleteCourse(999);

        assertFalse(deleted);
    }
}
