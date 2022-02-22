package com.mockpage.schoolwebapp.schoolpage.home.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.mockpage.schoolwebapp.schoolpage.home.model.GuestUser;

@Transactional
@Repository
public interface GuestUserRepository extends JpaRepository<GuestUser,Long> {

	boolean existsByUserId(String userId);

	boolean existsByFirstName(String firstName);

	boolean existsByLastName(String lastName);

	boolean existsByEmail(String email);

	GuestUser findByUserId(String userId);

	GuestUser findByEmail(String email);

	GuestUser findByLastName(String lastName);

	GuestUser findByFirstName(String firstName);
		
	GuestUser findByPhonenumber(String phonenumber);

}
