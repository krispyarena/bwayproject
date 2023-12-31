package com.bway.bwayproject.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.bway.bwayproject.model.Admin;

public interface AdminRepository extends JpaRepository<Admin, Integer>{
	
	Admin findByEmailAndPassword(String email, String psw);

}
