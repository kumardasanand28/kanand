package com.student.springjpa.service.impl;

import java.util.List;

import javax.annotation.Resource;

import com.student.entity.UserEntity;
import com.student.springjpa.UserRepositoryJPA;
import com.student.springjpa.service.UserService;

public class UserServiceImpl implements UserService{
	
	@Resource
	private UserRepositoryJPA userRepository;
	

	@Override
	public void create(UserEntity user) {
		userRepository.save(user);
		
	}

	@Override
	public void delete(UserEntity user) {
		userRepository.delete(user);
		
	}

	@Override
	public List<UserEntity> findAll() {
		return  userRepository.findAll();
	}

	@Override
	public UserEntity findById(Long id) {
		return userRepository.findById(id);
	}

	@Override
	public List<UserEntity> findByNameOrHobby(String name, String hobby) {
		return userRepository.findByStudentNameOrStudentHobby(name, hobby);
	}

	@Override
	public List<UserEntity> findByHobby(String hobby) {
		return userRepository.findByHobby(hobby);
	}

}
