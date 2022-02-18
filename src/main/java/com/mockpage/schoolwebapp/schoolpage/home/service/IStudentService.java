package com.mockpage.schoolwebapp.schoolpage.home.service;

import java.util.List;

import com.mockpage.schoolwebapp.schoolpage.home.model.Student;

public interface IStudentService {

	public Student save(Student student);
	
	public List<Student> findAll();

	public int usercount();
	boolean existsByStudentId(String studentId);

	public Student findByStudentId(String designation);

	public void update(Student student);
}
