package com.bway.bwayproject.controller;

import java.util.Random;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.bway.bwayproject.model.Admin;
import com.bway.bwayproject.service.AdminService;
import com.bway.bwayproject.utils.MailUtil;

import jakarta.servlet.http.HttpSession;
import lombok.extern.slf4j.Slf4j;

@Slf4j

@Controller
public class AuthController {
	
	Random random = new Random(10000);
	
	@Autowired
	private MailUtil mailUtil;
	
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
	
	@GetMapping("/forgetPassword")
	public String getForgetPassword() {
		return "ForgetPw";
	}
	
	@PostMapping("/forgetPassword")
	public String postForgetPassword(@RequestParam("email") String email) {
		
		System.out.println("Email : " + email);
		
		//Generate otp of 6 digits
		Integer otp = random.nextInt(999999);
		
		System.out.println("OTP = "+ otp);
		log.info("-------------"+otp+" -----------------");
		
		mailUtil.sendEmail(email,"Reset Password", otp.toString());
		
		return "VerifyOTP";
	}
}
