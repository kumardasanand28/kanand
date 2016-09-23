package com.student.springjpa.service;

import java.util.List;

import com.student.entity.UserEntity;

public interface UserService {

	public void create(UserEntity user);


	public void delete(UserEntity user);


	public List<UserEntity> findAll();
	
	
	public UserEntity findById(Long id);
	
	
	public List<UserEntity> findByNameOrHobby(String name,String hobby);
	
	
	public List<UserEntity> findByHobby(String hobby);
}
