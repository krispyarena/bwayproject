package com.bway.bwayproject.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import com.bway.bwayproject.model.Admin;
import com.bway.bwayproject.service.AdminService;

import jakarta.servlet.http.HttpSession;
import lombok.extern.slf4j.Slf4j;

@Slf4j

@Controller
public class AuthController {
	
	@Autowired
	private AdminService adminservice;
	
	@GetMapping({"/", "/login"})
	public String getLogin(){
		return "Login";
	}
	
	@PostMapping("/login")
	public String postLogin(@ModelAttribute Admin admin, Model model, HttpSession session) {
		
		Admin adm = adminservice.adminLogin(admin.getEmail(), admin.getPassword());
		
		if(adm != null) {
			
			log.info("-----User Logged in Successfully-----");
			
			session.setAttribute("activeuser", adm);
			session.setMaxInactiveInterval(300);
			
			return "Home";
		}
		
		model.addAttribute("message", "Invalid Credentials");
		
		return "Login";
	}
	
	
	@GetMapping("/signup")
	public String getSignup() {
		return "Signup";
	}
	
	@PostMapping("/signup")
	public String postSignup(@ModelAttribute Admin admin) {
		
		adminservice.adminSignup(admin);
		return "Login";
	}
	
	@GetMapping("/home")
	public String getError() {
		return "Home";
	}
	
	@GetMapping("/logout")
	public String getLogout(HttpSession session) {
		session.invalidate();
		log.info("----------------Logout Success-----------------------");
		return "Login";
	}
}
