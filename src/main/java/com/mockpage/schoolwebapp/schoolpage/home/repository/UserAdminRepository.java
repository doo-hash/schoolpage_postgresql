package com.mockpage.schoolwebapp.schoolpage.home.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.mockpage.schoolwebapp.schoolpage.home.model.Admin;

@Transactional
@Repository
public interface UserAdminRepository extends JpaRepository<Admin,Long> {

	boolean existsByAdminId(String adminId);

	boolean existsByFirstName(String firstName);

	boolean existsByLastName(String lastName);


	Admin findByAdminId(String adminId);

	Admin findByEmail(String email);

	Admin findByLastName(String lastName);

	Admin findByFirstName(String firstName);

	List<Admin> findAllByDesignation(String designation);

	Admin findByPhonenumber(String phonenumber);

}
