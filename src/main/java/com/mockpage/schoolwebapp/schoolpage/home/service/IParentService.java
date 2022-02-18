package com.mockpage.schoolwebapp.schoolpage.home.service;

import java.util.List;

import com.mockpage.schoolwebapp.schoolpage.home.model.Parent;

public interface IParentService {

	
	public List<Parent> findAll();

	public void update(Parent parent);

	public Parent save(Parent parent);
	public Parent findByParentId(String parentid);

	public Parent findByEmail(String email);
	
	public void deleteParent(String parentid);
}
