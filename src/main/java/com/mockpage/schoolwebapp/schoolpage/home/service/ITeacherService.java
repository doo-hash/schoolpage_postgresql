package com.mockpage.schoolwebapp.schoolpage.home.service;

import java.util.List;

import com.mockpage.schoolwebapp.schoolpage.home.model.Teacher;

public interface ITeacherService {

	
	public List<Teacher> findAll();

	public Teacher save(Teacher teacher);
	public void update(Teacher Teacherupdate);

	public Teacher findByEmail(String email);

	public Teacher findByTeacherId(String teacherid);
	
	public void deleteTeacher(String teacherid);
	public int usercount();
}
