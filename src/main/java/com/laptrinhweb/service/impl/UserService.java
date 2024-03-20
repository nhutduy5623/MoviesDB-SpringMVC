package com.laptrinhweb.service.impl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.laptrinhweb.dto.UserDTO;
import com.laptrinhweb.entity.RoleEntity;
import com.laptrinhweb.entity.UserEntity;
import com.laptrinhweb.repository.IUserRepository;
import com.laptrinhweb.service.IUserService;
import com.laptrinhweb.service.impl.convertEntity_DTO.UserConvert;

@Service
public class UserService implements IUserService {

	// Coverter
	@Autowired
	UserConvert userConvert;
	
	@Autowired
	private IUserRepository userRepository;
	
	@Autowired
	private PasswordEncoder passwordEncoder;

	@Override
	public UserEntity getOne(Long id) {
		return userRepository.getOne(id);
	}

	@Override
	@Transactional
	public UserDTO save(UserDTO userDTO) {
		if(userDTO.getPassWord().length()<50) {
			userDTO.setPassWord(passwordEncoder.encode(userDTO.getPassWord()));
		}
		UserEntity userEntity = userConvert.toEntity(userDTO);		
		if (userEntity.getId() != null) {
			UserEntity oldUser = userRepository.findOne(userEntity.getId());
			for (RoleEntity roleEntity : oldUser.getRoleList()) {
				roleEntity.getUserList().remove(oldUser);
			}
		}			
		//Thêm dữ liệu cho Table User_Role
		for(RoleEntity roleEntity:userEntity.getRoleList()) {
				roleEntity.getUserList().add(userEntity);
		}
		userEntity = userRepository.save(userEntity);
		
		return userConvert.toDTO(userEntity);
	}

	@Override
	@Transactional
	public void delete(Long[] ids) {
		for (Long id : ids) {
			UserEntity oldUser = userRepository.findOne(id);
			for (RoleEntity roleEntity : oldUser.getRoleList()) {
				roleEntity.getUserList().remove(oldUser);
			}
			userRepository.save(oldUser);
			userRepository.delete(id);
		}
	}

	@Override
	public List<UserDTO> findAll() {
		List<UserEntity> userEntities = userRepository.findAll();
		List<UserDTO> userDTOs = new ArrayList<>();
		for(UserEntity userEntity : userEntities) {
			userDTOs.add(userConvert.toDTO(userEntity));
		}
		return userDTOs;
	}

	@Override
	public List<UserDTO> findAll(Pageable pageable) {
		List<UserEntity> userEntities = userRepository.findAll(pageable).getContent();
		List<UserDTO> userDTOs = new ArrayList<>();
		for(UserEntity userEntity : userEntities) {
			userDTOs.add(userConvert.toDTO(userEntity));
		}
		return userDTOs;
	}

	@Override
	public UserDTO findOne(Long id) {
		return userConvert.toDTO(userRepository.findOne(id));
	}

	@Override
	public Integer countAll() {
		return (int) userRepository.count();
	}

	@Override
	public List<UserDTO> findByEmailPageable(String email, Pageable pageable) {
		List<UserEntity> userEntities = userRepository.findByEmailPageable(email, pageable).getContent();
		List<UserDTO> userDTOs = new ArrayList<>();
		for(UserEntity userEntity : userEntities) {
			userDTOs.add(userConvert.toDTO(userEntity));
		}
		return userDTOs;
	}

	@Override
	public Integer countByEmail(String email) {
		return userRepository.countByEmail(email);
	}

}
