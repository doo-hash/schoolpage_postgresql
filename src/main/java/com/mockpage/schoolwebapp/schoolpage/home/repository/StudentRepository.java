package com.mockpage.schoolwebapp.schoolpage.home.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.mockpage.schoolwebapp.schoolpage.home.model.Student;

@Repository
public interface StudentRepository extends JpaRepository<Student,Long> {

	boolean existsByStudentId(String studentId);

	Student findByStudentId(String studentId);
}
