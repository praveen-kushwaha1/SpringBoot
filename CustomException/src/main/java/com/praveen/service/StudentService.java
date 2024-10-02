package com.praveen.service;

import com.praveen.service.model.StudentEntity;

public interface StudentService {

    public StudentEntity createStudent(StudentEntity studentEntity);
    public StudentEntity details(String id);
}