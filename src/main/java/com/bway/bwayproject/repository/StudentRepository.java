package com.bway.bwayproject.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.bway.bwayproject.model.Student;

public interface StudentRepository extends JpaRepository<Student, Integer>{

}
