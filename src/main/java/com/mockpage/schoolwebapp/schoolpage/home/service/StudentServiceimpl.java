package com.mockpage.schoolwebapp.schoolpage.home.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.mockpage.schoolwebapp.schoolpage.home.model.Student;
import com.mockpage.schoolwebapp.schoolpage.home.repository.StudentRepository;

@Service
public class StudentServiceimpl implements IStudentService{

	private StudentRepository studentRepo;
	
	public StudentServiceimpl(StudentRepository studentRepo) {
		super();
		this.studentRepo = studentRepo;
	}

	@Override
	public List<Student> findAll() {
		List<Student> allstudents = studentRepo.findAll();
		return allstudents;
	}

	@Override
	public int usercount() {
		int count = 0;
		List<Student> allstudents = studentRepo.findAll();
		for (Student student : allstudents) {
			if(student != null) {
				count += 1;
			}
		}
		return count;
	}

	@Override
	public Student save(Student student) {
		if(student != null) {
			studentRepo.save(student);
		}
		return student;
	}

	@Override
	public boolean existsByStudentId(String studentId) {
		boolean isstudentid = studentRepo.existsByStudentId(studentId);
		return isstudentid;
	}

	@Override
	public Student findByStudentId(String studentid) {
		Student student = studentRepo.findByStudentId(studentid);
		return student;
	}

	@Override
	public void update(Student studentupdate) {
		Student student = studentRepo.findByStudentId(studentupdate.getStudentId());
		student.setFirstName(studentupdate.getFirstName());
		student.setLastName(studentupdate.getLastName());
		studentRepo.save(student);
	}
	
}
