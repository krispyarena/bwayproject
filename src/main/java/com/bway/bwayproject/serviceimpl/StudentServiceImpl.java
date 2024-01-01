package com.bway.bwayproject.serviceimpl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.bway.bwayproject.model.Student;
import com.bway.bwayproject.repository.StudentRepository;
import com.bway.bwayproject.service.StudentService;

@Service
public class StudentServiceImpl implements StudentService{

	@Autowired
	private StudentRepository studentRepo;
	
	@Override
	public void addStudent(Student student) {
		studentRepo.save(student);
	}

	@Override
	public void updateStudent(Student student) {
		studentRepo.save(student);
	}

	@Override
	public void deleteStudent(int id) {
		studentRepo.deleteById(id);
	}

	@Override
	public Student getStudentById(int id) {
		return studentRepo.findById(id).get();
	}

	@Override
	public List<Student> getAllStudents() {
		return studentRepo.findAll();
	}

}
