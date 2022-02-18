package com.mockpage.schoolwebapp.schoolpage.home.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.mockpage.schoolwebapp.schoolpage.home.model.Teacher;

@Repository
public interface UserTeacherRepository extends JpaRepository<Teacher,Long> {

	boolean existsByTeacherId(String teacherid);

	boolean existsByFirstName(String firstName);

	boolean existsByLastName(String lastName);

	boolean existsByEmail(String email);

	Teacher findByTeacherId(String teacherId);

	Teacher findByEmail(String email);

	Teacher findByLastName(String lastName);

	Teacher findByFirstName(String firstName);

	List<Teacher> findAllByDesignation(String designation);

	Teacher findByPhonenumber(String phonenumber);

}
