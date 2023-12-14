package com.laptrinhweb.service;

import org.springframework.stereotype.Service;

import com.laptrinhweb.entity.UserEntity;

@Service
public interface IUserService {
	UserEntity getOne(Long id);
}
