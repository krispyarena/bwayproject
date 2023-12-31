package com.bway.bwayproject.service;

import com.bway.bwayproject.model.Admin;

public interface UserService {

	
   void adminSignup(Admin admin);
	
	Admin adminLogin(String email, String password);
	
	void add(int a, int b);
}
