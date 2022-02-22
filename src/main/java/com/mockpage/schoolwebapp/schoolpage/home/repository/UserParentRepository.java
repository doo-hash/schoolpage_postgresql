package com.mockpage.schoolwebapp.schoolpage.home.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.mockpage.schoolwebapp.schoolpage.home.model.Parent;

@Transactional
@Repository
public interface UserParentRepository extends JpaRepository<Parent,Long> {

	boolean existsByParentId(String parentId);

	boolean existsByFirstName(String firstName);

	boolean existsByLastName(String lastName);

	boolean existsByEmail(String email);

	Parent findByParentId(String parentId);
	
	Parent findByStudentid(String studentid);

	Parent findByEmail(String email);

	Parent findByLastName(String lastName);

	Parent findByFirstName(String firstName);

	Parent findByStudentName(String studentName);
	
	Parent findByPhonenumber(String phonenumber);

}
