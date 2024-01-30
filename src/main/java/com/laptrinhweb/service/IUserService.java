package com.laptrinhweb.service;

import org.springframework.stereotype.Service;

import com.laptrinhweb.dto.UserDTO;
import com.laptrinhweb.entity.UserEntity;

@Service
public interface IUserService {
	UserEntity getOne(Long id);
	UserDTO save(UserDTO userDTO);
	void delete(Long[] ids);
}
