package com.mockpage.schoolwebapp.schoolpage.home.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.mockpage.schoolwebapp.schoolpage.home.model.Admin;
import com.mockpage.schoolwebapp.schoolpage.home.model.SchoolUser;
import com.mockpage.schoolwebapp.schoolpage.home.repository.SchoolUserRepository;
import com.mockpage.schoolwebapp.schoolpage.home.repository.UserAdminRepository;

@Service
public class AdminServiceImpl implements IAdminService {

	private UserAdminRepository adminRepo;
	private SchoolUserRepository userRepo;
	
	public AdminServiceImpl(UserAdminRepository adminRepo, SchoolUserRepository userRepo) {
		super();
		this.adminRepo = adminRepo;
		this.userRepo = userRepo;
	}


	@Override
	public List<Admin> findAll() {
		List<Admin> findAlladmins = adminRepo.findAll();
		return findAlladmins;
	}


	@Override
	public void update(Admin adminupdate) {
		SchoolUser user = userRepo.findByUserid(adminupdate.getAdminId());
		Admin admin = adminRepo.findByAdminId(adminupdate.getAdminId());
		if(admin == null) {
			
			adminRepo.save(adminupdate);
		}
		else {
			admin.setFirstName(adminupdate.getFirstName());
			admin.setLastName(adminupdate.getLastName());
			admin.setEmail(adminupdate.getEmail());
			admin.setPhonenumber(adminupdate.getPhonenumber());
			admin.setAdminId(adminupdate.getAdminId());
			admin.setPassword(user.getPassword());
			admin.setRole(user.getRoles().toString());
			admin.setDesignation(adminupdate.getDesignation());
			admin.setEducation(adminupdate.getEducation());
			admin.setWork_experience(adminupdate.getWork_experience());
			adminRepo.save(admin);
		}
		user.setEmail(adminupdate.getEmail());
		user.setPhonenumber(adminupdate.getPhonenumber());
		user.setDesignation(adminupdate.getDesignation());
		userRepo.save(user);
	}


	@Override
	public Admin findByAdminId(String adminid) {
		if(adminid != null) {
			Admin admin = adminRepo.findByAdminId(adminid);
			return admin;
		}
		return null;
	}


	@Override
	public Admin findByEmail(String email) {
		if(email != null) {
			Admin admin = adminRepo.findByEmail(email);
			return admin;
		}
		return null;
	}


	@Override
	public void deleteAdmin(String adminid) {
		Admin admin = adminRepo.findByAdminId(adminid);
		Admin adminemail = adminRepo.findByEmail(adminid);
		if(admin != null) {
			adminRepo.deleteById(admin.getId());
		}
		if(adminemail != null) {
			adminRepo.deleteById(adminemail.getId());
		}
	}


	@Override
	public int usercount() {
		int count = 0;
		List<Admin> allusers = adminRepo.findAll();
		for (Admin admin : allusers) {
			if(admin != null) {
				count += 1;
			}
		}
		return count;
	}


	@Override
	public Admin save(Admin admin) {
		adminRepo.save(admin);
		return admin;
	}
}
