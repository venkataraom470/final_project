package com.cg.BiteBeeFoodApplication.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cg.BiteBeeFoodApplication.entity.Admin;
import com.cg.BiteBeeFoodApplication.repositry.AdminRepository;



@Service
public class AdminServices {
	@Autowired
	private AdminRepository adminRepository;
	
	public void addAdmin(Admin admin)
	{
		this.adminRepository.save(admin);
	}
	public void delete(int id)
	{
		this.adminRepository.deleteById(id);
	}
	public List<Admin>getAll()
	{
		 List<Admin> admins=(List<Admin>)this.adminRepository.findAll();
		 return admins;
	}
	public Admin getAdmin(int id)
	{
		Admin admin=this.adminRepository.findById(id).get();
		return admin;
	}
	public void update(Admin admin,int id)
	{
		for(Admin ad:getAll())
		{
			if(ad.getAdminId()==id)
			{
				this.adminRepository.save(admin);
			}
		}
	}
	public boolean validateAdminCredentials(String email,String password)
	{
		Admin admin=adminRepository.findByAdminEmail(email);
		if(admin!=null && admin.getAdminPassword().equals(password))
		{
			return true;
		}
		return false;
	}
	
	

}
