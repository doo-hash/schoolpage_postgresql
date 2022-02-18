package com.mockpage.schoolwebapp.schoolpage.home.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.mockpage.schoolwebapp.schoolpage.home.model.GuestUser;
import com.mockpage.schoolwebapp.schoolpage.home.model.SchoolUser;
import com.mockpage.schoolwebapp.schoolpage.home.repository.GuestUserRepository;
import com.mockpage.schoolwebapp.schoolpage.home.repository.SchoolUserRepository;

@Service
public class GuestUserServiceImpl implements IGuestUserService {

	private GuestUserRepository guestuserRepo;
	private SchoolUserRepository userRepo;
	
	public GuestUserServiceImpl(GuestUserRepository guestuserRepo, SchoolUserRepository userRepo) {
		super();
		this.guestuserRepo = guestuserRepo;
		this.userRepo = userRepo;
	}


	@Override
	public List<GuestUser> findAll() {
		List<GuestUser> findAllGuestUsers = guestuserRepo.findAll();
		return findAllGuestUsers;
	}


	@Override
	public void update(GuestUser guestuserupdate) {
		SchoolUser user = userRepo.findByUserid(guestuserupdate.getUserId());
		GuestUser guestuser = guestuserRepo.findByUserId(guestuserupdate.getUserId());
		
		if(guestuser == null) {
			guestuserRepo.save(guestuserupdate);
		}
		else {
			guestuser.setFirstName(guestuserupdate.getFirstName());
			guestuser.setLastName(guestuserupdate.getLastName());
			guestuser.setEmail(guestuserupdate.getEmail());
			guestuser.setPhonenumber(guestuserupdate.getPhonenumber());
			guestuser.setUserId(guestuserupdate.getUserId());
			guestuser.setProfession(guestuserupdate.getProfession());
			guestuser.setEducation(guestuserupdate.getEducation());
			guestuser.setDescription(guestuserupdate.getDescription());
			guestuserRepo.save(guestuser);
		}
		
		user.setDesignation(guestuserupdate.getProfession());
		user.setEmail(guestuserupdate.getEmail());
		user.setPhonenumber(guestuserupdate.getPhonenumber());
		userRepo.save(user);
	}


	@Override
	public GuestUser findByUserId(String userid) {
		GuestUser user = guestuserRepo.findByUserId(userid);
		return user;
	}


	@Override
	public GuestUser findByEmail(String email) {
		GuestUser user = guestuserRepo.findByEmail(email);
		return user;
	}


	@Override
	public void deleteGuestUser(String userid) {
		GuestUser user = guestuserRepo.findByUserId(userid);
		GuestUser userbyemail = guestuserRepo.findByEmail(userid);
		
		if(user != null) {
			guestuserRepo.deleteById(user.getId());
		}
		if(userbyemail != null) {
			guestuserRepo.deleteById(userbyemail.getId());
		}
	}


	@Override
	public GuestUser save(GuestUser guestUser) {
		guestuserRepo.save(guestUser);
		return guestUser;
	}

}
