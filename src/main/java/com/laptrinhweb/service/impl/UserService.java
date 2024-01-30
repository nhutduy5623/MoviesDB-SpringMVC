package com.laptrinhweb.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.laptrinhweb.dto.UserDTO;
import com.laptrinhweb.entity.RoleEntity;
import com.laptrinhweb.entity.UserEntity;
import com.laptrinhweb.repository.IRoleRepository;
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
	private IRoleRepository roleRepository;

	@Override
	public UserEntity getOne(Long id) {
		return userRepository.getOne(id);
	}

	@Override
	@Transactional
	public UserDTO save(UserDTO userDTO) {
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

}
