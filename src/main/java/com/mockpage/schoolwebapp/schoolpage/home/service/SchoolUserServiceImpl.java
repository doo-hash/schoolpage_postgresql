package com.mockpage.schoolwebapp.schoolpage.home.service;

import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import com.mockpage.schoolwebapp.schoolpage.home.model.Role;
import com.mockpage.schoolwebapp.schoolpage.home.model.SchoolUser;
import com.mockpage.schoolwebapp.schoolpage.home.repository.SchoolUserRepository;

@Service
public class SchoolUserServiceImpl implements ISchoolUserService {

	@Autowired
	private SchoolUserRepository userRepo;
	
	private BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();

	@Override
	public SchoolUser saveSchoolUser(SchoolUser newuser) {
		System.out.println(newuser.toString());
		String psd = passwordEncoder.encode(newuser.getPassword());
		SchoolUser user = new SchoolUser(newuser.getFirstname(),newuser.getLastname(),
				newuser.getEmail(),newuser.getPhonenumber(),
				newuser.getUserid(),newuser.getDesignation(),
				psd,newuser.getRoles(),
				newuser.isCheckterms());
		userRepo.save(user);
		return user;
	}

	@Override
	public boolean existsByUserId(String userid) {
		boolean isUserId = userRepo.existsByUserid(userid);
		return isUserId;
	}

	@Override
	public boolean existsByFirstName(String firstname) {
		boolean isFirstName = userRepo.existsByFirstname(firstname);
		return isFirstName;
	}

	@Override
	public boolean existsByLastName(String lastname) {
		boolean isLastName = userRepo.existsByLastname(lastname);
		return isLastName;
	}

	@Override
	public boolean existsByEmail(String email) {
		boolean isEmail = userRepo.existsByEmail(email);
		return isEmail;
	}

	@Override
	public SchoolUser findUserByUserId(String userid) {
		SchoolUser user = userRepo.findByUserid(userid);
		return user;
	}

	@Override
	public SchoolUser findUserByFirstName(String firstname) {
		SchoolUser user = userRepo.findByFirstname(firstname);
		return user;
	}

	@Override
	public SchoolUser findUserByLastName(String lastname) {
		SchoolUser user = userRepo.findByLastname(lastname);
		return user;
	}

	@Override
	public SchoolUser findUserByEmail(String email) {
		SchoolUser user = userRepo.findByEmail(email);
		return user;
	}

	@Override
	public SchoolUser findUserByPhoneNumber(String phonenumber) {
		SchoolUser user = userRepo.findByPhonenumber(phonenumber);
		return user;
	}
	
	@Override
	public void deleteUser(String userid) {
		SchoolUser user = userRepo.findByUserid(userid);
		SchoolUser userbyemail = userRepo.findByEmail(userid);
		if(user != null) {
			userRepo.delete(user);
		}
		if(userbyemail != null) {
			userRepo.deleteById(userbyemail.getId());
		}
	}

	@Override
	public List<SchoolUser> findAllUsersByDesignation(String designation) {
		List<SchoolUser> allusers = userRepo.findAllByDesignation(designation);
		return allusers;
	}

	@Override
	public List<SchoolUser> findAllUsers() {
		List<SchoolUser> allusers = userRepo.findAll();
		return allusers;
	}

	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		SchoolUser userbyid = userRepo.findByUserid(username);
		SchoolUser userbyemail = userRepo.findByEmail(username);
		
			if(userbyid != null) {
				if(!(userbyid.isDelete() || userbyid.isInactive())) {
					return new org.springframework.security.core.userdetails.User(userbyid.getUserid(), userbyid.getPassword(), mapRolesToAuthorities(userbyid.getRoles()));
				}
			}
			
			if(userbyemail != null) {
				if(!(userbyemail.isDelete() || userbyemail.isInactive())) {
					return new org.springframework.security.core.userdetails.User(userbyemail.getEmail(), userbyemail.getPassword(), mapRolesToAuthorities(userbyemail.getRoles()));
				}
			}
			throw new UsernameNotFoundException("Invalid Username or password");
	}
	
	private Collection<? extends GrantedAuthority> mapRolesToAuthorities(Collection<Role> roles){
		return roles.stream().map(role -> new SimpleGrantedAuthority(role.getRolename())).collect(Collectors.toList());
	}

	public boolean existsByPhoneNumber(String phonenumber) {
		boolean isPhoneNumber = userRepo.existsByPhonenumber(phonenumber);
		return isPhoneNumber;
	}

}
