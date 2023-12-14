package com.laptrinhweb.service.impl;

import org.springframework.beans.factory.annotation.Autowired;

import com.laptrinhweb.entity.UserEntity;
import com.laptrinhweb.repository.IUserRepository;
import com.laptrinhweb.service.IUserService;

public class UserService implements IUserService{

	@Autowired
	private IUserRepository userRepositoty;
	
	@Override
	public UserEntity getOne(Long id) {
		return userRepositoty.getOne(id);
	}
	
}
