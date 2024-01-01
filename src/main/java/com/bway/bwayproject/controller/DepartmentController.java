package com.bway.bwayproject.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.bway.bwayproject.model.Department;
import com.bway.bwayproject.service.DepartmentService;

import jakarta.servlet.http.HttpSession;

@Controller
public class DepartmentController {

	@Autowired
	private DepartmentService deptService;
	
	@GetMapping("/department")
	public String getDepartment(HttpSession session) {
		
		if(session.getAttribute("activeuser")==null) {
			return "Login";
		}
		
		return "DepartmentForm";
	}
	
	@PostMapping("/department")
	public String postDepartment(@ModelAttribute Department dept) {
		
		deptService.addDept(dept);
		return "DepartmentForm";
	}
	
	@GetMapping("/departmentList")
	public String getDepartmentList(Model model, HttpSession session) {
		
		if (session.getAttribute("activeuser")==null) {
			return "Login";
		}
		
		model.addAttribute("dList",deptService.getAllDepts());
		return "DepartmentListForm";
	}
	
	@GetMapping("/department/delete")
	public String delete(@RequestParam int id, HttpSession session) {
		
		if (session.getAttribute("activeuser")==null) {
			return "Login";
		}
		
		deptService.deleteDept(id);
		return "redirect:/departmentList";
	}
	
	@GetMapping("/department/edit")
	public String edit(@RequestParam int id, Model model, HttpSession session) {
		
		if (session.getAttribute("activeuser")==null) {
			return "Login";
		}
		
		model.addAttribute("deptObject", deptService.getDeptById(id));
		return "DepartmentEditForm";
	}
	
	@PostMapping("/department/update")
	public String update(@ModelAttribute Department dept) {
		deptService.updateDept(dept);
		return "redirect:/departmentList";
	}
}
