package com.cg.BiteBeeFoodApplication.repositry;

import org.springframework.data.jpa.repository.JpaRepository;

import com.cg.BiteBeeFoodApplication.entity.Admin;

public interface AdminRepository extends JpaRepository<Admin, Integer> {
	
	public Admin findByAdminEmail(String email);

}
