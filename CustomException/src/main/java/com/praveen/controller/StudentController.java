package com.praveen.controller;



import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.praveen.service.StudentService;
import com.praveen.service.model.StudentEntity;

@RestController
@RequestMapping("/api/student")
public class StudentController {

    @Autowired
    private StudentService studentService;
    @PostMapping("/create")
    public ResponseEntity<?> createStudent(@RequestBody @Valid StudentEntity studentEntity) {
        return new ResponseEntity<>(studentService.createStudent(studentEntity), HttpStatus.CREATED);
    }
    @GetMapping("/details/{id}")
    public ResponseEntity<?> createStudent(@PathVariable("id") String id) {
        return new ResponseEntity<>(studentService.details(id), HttpStatus.OK);
    }
}