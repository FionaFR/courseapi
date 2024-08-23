# Course Management REST API

## Overview

This is a simple REST API for managing course records. The API supports basic CRUD operations and in-memory storage.

## Features

- Create, read, update, and delete course records.
- Validate course numbers to be three-digit integers.
- Search for courses by partial description matches.
- Prevent duplicate courses based on subject and course number.

## Endpoints

- `GET /courses`: Retrieve all courses.
- `GET /courses/{id}`: Retrieve a course by its ID.
- `GET /courses/search?description={text}`: Search for courses by description.
- `POST /courses`: Add a new course.
- `DELETE /courses/{id}`: Delete a course by its ID.

## Example Data

- `1, "BIO", "101", "Introduction to Biology"`
- `2, "MAT", "045", "Business Statistics"`

## Setup Instructions

1. **Clone the repository**:
   git clone <repository-url>

2. Navigate to the project directory:
   cd courseapi

3. Build the project:
   mvn clean install

4. Run the application:
   .\mvnw.cmd spring-boot:run

5. Test the endpoints using tools like Postman or cURL.
   
Unit tests are provided to validate the behavior of the API. Run the tests with:
  .\mvnw.cmd test# courseapi
