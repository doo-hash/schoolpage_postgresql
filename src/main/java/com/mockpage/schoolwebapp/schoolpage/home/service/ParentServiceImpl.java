package com.mockpage.schoolwebapp.schoolpage.home.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.mockpage.schoolwebapp.schoolpage.home.model.Parent;
import com.mockpage.schoolwebapp.schoolpage.home.model.SchoolUser;
import com.mockpage.schoolwebapp.schoolpage.home.repository.SchoolUserRepository;
import com.mockpage.schoolwebapp.schoolpage.home.repository.UserParentRepository;

@Service
public class ParentServiceImpl implements IParentService {

	private UserParentRepository parentRepo;
	private SchoolUserRepository userRepo;
	
	public ParentServiceImpl(UserParentRepository parentRepo, SchoolUserRepository userRepo) {
		super();
		this.parentRepo = parentRepo;
		this.userRepo = userRepo;
	}


	@Override
	public List<Parent> findAll() {
		List<Parent> findAllParents = parentRepo.findAll();
		return findAllParents;
	}


	@Override
	public void update(Parent parentupdate) {
		SchoolUser user = userRepo.findByUserid(parentupdate.getParentId());
		Parent parent = parentRepo.findByParentId(parentupdate.getParentId());
		if(parent == null) {
			parentRepo.save(parentupdate);
		}
		else {
			parent.setFirstName(parentupdate.getFirstName());
			parent.setLastName(parentupdate.getLastName());
			parent.setEmail(parentupdate.getEmail());
			parent.setParentId(parentupdate.getParentId());
			parent.setPhonenumber(parentupdate.getPhonenumber());
			parent.setStudentName(parentupdate.getStudentName());
			parent.setStudentid(parentupdate.getStudentid());
			parent.setPassword(parentupdate.getPassword());
			parent.setRole(parentupdate.getRole());
			parentRepo.save(parent);
		}
		user.setEmail(parentupdate.getEmail());
		user.setPhonenumber(parentupdate.getPhonenumber());
		user.setDesignation(parentupdate.getStudentid());
		userRepo.save(user);
	}


	@Override
	public Parent findByParentId(String parentid) {
		Parent parent = parentRepo.findByParentId(parentid);
		return parent;
	}


	@Override
	public Parent findByEmail(String email) {
		Parent parent = parentRepo.findByEmail(email);
		return parent;
	}


	@Override
	public void deleteParent(String parentid) {
		Parent parent = parentRepo.findByParentId(parentid);
		Parent parentbyemail = parentRepo.findByEmail(parentid);
		if(parent  != null) {
			parentRepo.deleteById(parent.getId());
		}
		if(parentbyemail != null) {
			parentRepo.deleteById(parent.getId());
		}	
	}


	@Override
	public Parent save(Parent parent) {
		parentRepo.save(parent);
		return parent;
	}
}
