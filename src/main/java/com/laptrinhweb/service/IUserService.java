package com.laptrinhweb.service;

import java.util.List;

import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.laptrinhweb.dto.RoleDTO;
import com.laptrinhweb.dto.UserDTO;
import com.laptrinhweb.entity.UserEntity;

@Service
public interface IUserService {
	UserEntity getOne(Long id);
	UserDTO save(UserDTO userDTO);
	void delete(Long[] ids);
	
	List<UserDTO> findAll();
	List<UserDTO> findAll(Pageable pageable);
	UserDTO findOne(Long id);
	Integer countAll();
	List<UserDTO> findByEmailPageable(String email, Pageable pageable);
	Integer countByEmail(String email);
}
