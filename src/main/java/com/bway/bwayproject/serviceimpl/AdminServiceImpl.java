package com.bway.bwayproject.serviceimpl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.bway.bwayproject.model.Admin;
import com.bway.bwayproject.repository.AdminRepository;
import com.bway.bwayproject.service.AdminService;

@Service
public class AdminServiceImpl implements AdminService{
	
	@Autowired
	private AdminRepository adminRepo;

	@Override
	public void adminSignup(Admin admin) {
	
		adminRepo.save(admin);		
	}

	@Override
	public Admin adminLogin(String email, String password) {
		
		return adminRepo.findByEmailAndPassword(email, password);
	}

}
