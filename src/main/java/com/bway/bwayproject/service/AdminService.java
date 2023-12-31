package com.bway.bwayproject.service;

import com.bway.bwayproject.model.Admin;

public interface AdminService {

	void adminSignup(Admin admin);
	
	Admin adminLogin(String email, String password);
}
