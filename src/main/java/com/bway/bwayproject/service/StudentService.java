package com.bway.bwayproject.service;

import java.util.List;

import com.bway.bwayproject.model.Student;

public interface StudentService {

	void addStudent(Student student);
	
	void updateStudent(Student student);
	
	void deleteStudent(int id);
	
	Student getStudentById(int id);
	
	List<Student> getAllStudents();
}
