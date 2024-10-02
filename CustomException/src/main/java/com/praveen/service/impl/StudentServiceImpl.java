package com.praveen.service.impl;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.praveen.service.StudentService;
import com.praveen.service.exception.StudentExistsException;
import com.praveen.service.exception.StudentNotFoundException;
import com.praveen.service.model.StudentEntity;
import com.praveen.service.repo.StudentRepo;

import java.util.Optional;

@Service
public class StudentServiceImpl implements StudentService {

    @Autowired
    private StudentRepo studentRepo;
    @Override
    public StudentEntity createStudent(StudentEntity studentEntity) {
        //before saving the student record we have to check either its exists or not
        Optional<StudentEntity> isStudentExists = studentRepo.findById(studentEntity.getStudentId());
        if(isStudentExists.isPresent()) {
            throw new StudentExistsException("Student exists with id: "+studentEntity.getStudentId());
        }
        return studentRepo.save(studentEntity);
    }

    @Override
    public StudentEntity details(String id) {
        return studentRepo.findById(id).orElseThrow(() -> new StudentNotFoundException("student not present with id ::" + id));
    }
}