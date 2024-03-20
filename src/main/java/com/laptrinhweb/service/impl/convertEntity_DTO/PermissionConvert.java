package com.laptrinhweb.service.impl.convertEntity_DTO;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.laptrinhweb.dto.PermissionDTO;
import com.laptrinhweb.entity.PermissionEntity;
import com.laptrinhweb.entity.RoleEntity;
import com.laptrinhweb.repository.IPermissionRepository;
import com.laptrinhweb.repository.IRoleRepository;

@Service
public class PermissionConvert {
	
	@Autowired
	IRoleRepository roleRepository;
	
	@Autowired
	IPermissionRepository permissionRepository;
	
	@Autowired
	ModelMapper modelMapper;
	
	public PermissionEntity toEntity(PermissionDTO permissionDTO) {
		PermissionEntity permissionEntity = new PermissionEntity();
		permissionEntity = modelMapper.map(permissionDTO, permissionEntity.getClass());
		permissionEntity.getRoleList().clear();
		for(String roleCode:permissionDTO.getRoleCodeList()) {
			permissionEntity.getRoleList().add(roleRepository.findOneByCode(roleCode));
		}
		return permissionEntity;
	}
	
	public PermissionDTO toDTO(PermissionEntity permissionEntity) {
		PermissionDTO permissionDTO = new PermissionDTO();
		permissionDTO = modelMapper.map(permissionEntity, PermissionDTO.class);
		permissionDTO.getRoleCodeList().clear();
		for(RoleEntity role: permissionEntity.getRoleList()) {
			permissionDTO.getRoleCodeList().add(role.getCode());
		}
		return permissionDTO;
	}
}
