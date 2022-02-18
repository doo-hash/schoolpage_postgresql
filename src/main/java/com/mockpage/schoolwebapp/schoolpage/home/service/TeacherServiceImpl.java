package com.mockpage.schoolwebapp.schoolpage.home.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.mockpage.schoolwebapp.schoolpage.home.model.SchoolUser;
import com.mockpage.schoolwebapp.schoolpage.home.model.Teacher;
import com.mockpage.schoolwebapp.schoolpage.home.repository.SchoolUserRepository;
import com.mockpage.schoolwebapp.schoolpage.home.repository.UserTeacherRepository;

@Service
public class TeacherServiceImpl implements ITeacherService {

	private UserTeacherRepository teacherRepo;
	private SchoolUserRepository userRepo;
	
	public TeacherServiceImpl(UserTeacherRepository teacherRepo, SchoolUserRepository userRepo) {
		super();
		this.teacherRepo = teacherRepo;
		this.userRepo = userRepo;
	}


	@Override
	public List<Teacher> findAll() {
		List<Teacher> findAllTeachers = teacherRepo.findAll();
		return findAllTeachers;
	}


	@Override
	public void update(Teacher teacherupdate) {
		
		SchoolUser user = userRepo.findByUserid(teacherupdate.getTeacherId());
		Teacher teacher = teacherRepo.findByTeacherId(teacherupdate.getTeacherId());
		
		
		if(teacher == null) {
			teacherRepo.save(teacherupdate);
		}
		else {	
			teacher.setFirstName(teacherupdate.getFirstName());
			teacher.setLastName(teacherupdate.getLastName());
			teacher.setPhonenumber(teacherupdate.getPhonenumber());
			teacher.setEmail(teacherupdate.getEmail());
			teacher.setTeacherId(teacherupdate.getTeacherId());
			teacher.setPassword(teacherupdate.getPassword());
			teacher.setRole(teacherupdate.getRole());
			teacher.setDesignation(teacherupdate.getDesignation());
			teacher.setEducation(teacherupdate.getEducation());
			teacher.setWork_experience(teacherupdate.getWork_experience());
			teacherRepo.save(teacher);
		}
		user.setEmail(teacherupdate.getEmail());
		user.setPhonenumber(teacherupdate.getPhonenumber());
		user.setDesignation(teacherupdate.getDesignation());
		userRepo.save(user);
		}


	@Override
	public Teacher findByEmail(String email) {
		Teacher teacher = teacherRepo.findByEmail(email);
		return teacher;
	}


	@Override
	public Teacher findByTeacherId(String teacherid) {
		Teacher teacher = teacherRepo.findByTeacherId(teacherid);
		return teacher;
	}


	@Override
	public void deleteTeacher(String teacherid) {
		Teacher teacher = teacherRepo.findByTeacherId(teacherid);
		Teacher teacheremail = teacherRepo.findByEmail(teacherid);
		
		if(teacher != null) {
			teacherRepo.deleteById(teacher.getId());
		}
		if(teacheremail != null) {
			teacherRepo.deleteById(teacheremail.getId());
		}
	}


	@Override
	public int usercount() {
		int count = 0;
		List<Teacher> allusers = teacherRepo.findAll();
		for (Teacher teacher : allusers) {
			if(teacher != null) {
				count += 1;
			}
		}
		return count;
	}


	@Override
	public Teacher save(Teacher teacher) {
		teacherRepo.save(teacher);
		return teacher;
	}

}
