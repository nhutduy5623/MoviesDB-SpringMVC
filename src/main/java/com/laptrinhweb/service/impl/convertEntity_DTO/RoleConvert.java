package com.laptrinhweb.service.impl.convertEntity_DTO;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.laptrinhweb.dto.RoleDTO;
import com.laptrinhweb.entity.PermissionEntity;
import com.laptrinhweb.entity.RoleEntity;
import com.laptrinhweb.entity.UserEntity;
import com.laptrinhweb.repository.IPermissionRepository;
import com.laptrinhweb.repository.IUserRepository;

@Service
public class RoleConvert {
	@Autowired
	IPermissionRepository permissionRepository;

	@Autowired
	IUserRepository userRepository;
	
	@Autowired
	ModelMapper modelMapper;

	public RoleEntity toEntity(RoleDTO roleDTO) {
		RoleEntity roleEntity = new RoleEntity();
		roleEntity = modelMapper.map(roleDTO, RoleEntity.class);
		roleEntity.getPermissionList().clear();
		for (String permissionCode : roleDTO.getPermissionCodeList()) {
			roleEntity.getPermissionList().add(permissionRepository.findOneByCode(permissionCode));
		}
		roleEntity.getUserList().clear();
		for (String userCode : roleDTO.getUserCodeList()) {
			roleEntity.getUserList().add(userRepository.findOneByCode(userCode));
		}
		return roleEntity;
	}

	public RoleDTO toDTO(RoleEntity roleEntity) {
		RoleDTO roleDTO = new RoleDTO();
		roleDTO = modelMapper.map(roleEntity, RoleDTO.class);
		roleDTO.getPermissionCodeList().clear();
		for (PermissionEntity permission: roleEntity.getPermissionList()) {
			roleDTO.getPermissionCodeList().add(permission.getCode());
		}
		
		roleDTO.getUserCodeList().clear();
		for (UserEntity user: roleEntity.getUserList()) {
			roleDTO.getUserCodeList().add(user.getCode());
		}
		return roleDTO;
	}
}
