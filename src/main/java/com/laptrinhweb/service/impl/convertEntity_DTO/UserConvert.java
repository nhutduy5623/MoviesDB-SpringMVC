package com.laptrinhweb.service.impl.convertEntity_DTO;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.laptrinhweb.dto.UserDTO;
import com.laptrinhweb.entity.RoleEntity;
import com.laptrinhweb.entity.UserEntity;
import com.laptrinhweb.repository.IRoleRepository;

@Service
public class UserConvert {
	
	@Autowired
	IRoleRepository roleRepository;
	
	@Autowired
	ModelMapper modelMapper;
	
	public UserEntity toEntity(UserDTO userDTO) {
		UserEntity userEntity = new UserEntity();
		userEntity = modelMapper.map(userDTO, userEntity.getClass());
		userEntity.getRoleList().clear();
		for(String roleCode:userDTO.getRoleCodeList()) {
			userEntity.getRoleList().add(roleRepository.findOneByCode(roleCode));
		}
		return userEntity;
	}
	
	public UserDTO toDTO(UserEntity userEntity) {
		UserDTO userDTO = new UserDTO();
		userDTO = modelMapper.map(userEntity, UserDTO.class);
		userDTO.getRoleCodeList().clear();
		for(RoleEntity role: userEntity.getRoleList()) {
			userDTO.getRoleCodeList().add(role.getCode());
		}
		return userDTO;
	}
}
