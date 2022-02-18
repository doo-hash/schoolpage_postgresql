package com.mockpage.schoolwebapp.schoolpage.home.service;

import java.util.List;

import com.mockpage.schoolwebapp.schoolpage.home.model.GuestUser;

public interface IGuestUserService {

	
	public List<GuestUser> findAll();

	public GuestUser save(GuestUser guestUser);
	public void update(GuestUser guestUser);

	public GuestUser findByUserId(String userid);

	public GuestUser findByEmail(String email);
	
	public void deleteGuestUser(String userid);
}
