package com.praveen.service.repo;


import org.springframework.data.jpa.repository.JpaRepository;

import com.praveen.service.model.StudentEntity;

public interface StudentRepo extends JpaRepository<StudentEntity,String > {
}