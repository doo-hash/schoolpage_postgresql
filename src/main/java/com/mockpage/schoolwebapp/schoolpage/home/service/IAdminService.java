package com.mockpage.schoolwebapp.schoolpage.home.service;

import java.util.List;

import com.mockpage.schoolwebapp.schoolpage.home.model.Admin;

public interface IAdminService {

	
	public List<Admin> findAll();
	
	public Admin save(Admin admin);
	
	public Admin findByAdminId(String adminid);
	
	public Admin findByEmail(String email);

	public void update(Admin admin);

	public void deleteAdmin(String adminid);
	public int usercount();
}
