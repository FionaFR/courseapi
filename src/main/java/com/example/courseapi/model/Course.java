package com.example.courseapi.model;

public class Course {
    private int id;
    private String subject;
    private String courseNumber;
    private String description;

    // Constructors, getters, and setters
    public Course(int id, String subject, String courseNumber, String description) {
        this.id = id;
        this.subject = subject;
        this.courseNumber = courseNumber;
        this.description = description;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getSubject() {
        return subject;
    }

    public void setSubject(String subject) {
        this.subject = subject;
    }

    public String getCourseNumber() {
        return courseNumber;
    }

    public void setCourseNumber(String courseNumber) {
        this.courseNumber = courseNumber;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}
