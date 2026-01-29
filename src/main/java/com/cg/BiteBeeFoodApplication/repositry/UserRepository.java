package com.cg.BiteBeeFoodApplication.repositry;

import org.springframework.data.jpa.repository.JpaRepository;

import com.cg.BiteBeeFoodApplication.entity.User;

public interface UserRepository extends JpaRepository<User, Integer>{
	public User findUserByUemail(String email);
	  boolean existsByUemail(String uemail);

}
