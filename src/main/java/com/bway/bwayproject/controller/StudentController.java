package com.bway.bwayproject.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.bway.bwayproject.model.Student;
import com.bway.bwayproject.service.DepartmentService;
import com.bway.bwayproject.service.StudentService;

import jakarta.servlet.http.HttpSession;

@Controller
public class StudentController {
	
	@Autowired
	private StudentService studentService;
	
	@Autowired
	private DepartmentService deptService;

	@GetMapping("/student")
	public String getEmployee(Model model, HttpSession session) {
		
		if (session.getAttribute("activeuser")==null) {
			return "Login";
		}
		
		model.addAttribute("dList", deptService.getAllDepts());
		
		return "StudentForm";
	}
	
	@PostMapping("/student")
	public String postEmployee(@ModelAttribute Student student) {
		
		studentService.addStudent(student);
		
		return "StudentForm";
	}
	
	@GetMapping("/studentList")
	public String getEmployeeList(Model model, HttpSession session) {
		
		if(session.getAttribute("activeuser")==null) {
			return "Login";
		}
		
		model.addAttribute("sList", studentService.getAllStudents());
		return "StudentListForm";
	}
	
	@GetMapping("/student/delete")
	public String delete(@RequestParam int id, HttpSession session) {
		
		if(session.getAttribute("activeuser")==null) {
			return "Login";
		}
		
		studentService.deleteStudent(id);
		return "redirect:/studentListForm";
	}
	
	@GetMapping("/student/edit")
	public String edit(@RequestParam int id, Model model, HttpSession session) {
		
		if (session.getAttribute("activeuser") == null ) {
			return "Login";
		}
		
		model.addAttribute("sObject", studentService.getStudentById(id));
		return "StudentEditForm";
	}
	
	@PostMapping("/student/update")
	public String update(@ModelAttribute Student student) {
		studentService.updateStudent(student);
		return "redirect:/studentListForm";
	}


}
