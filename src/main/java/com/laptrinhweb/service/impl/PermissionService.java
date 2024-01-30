package com.laptrinhweb.service.impl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.laptrinhweb.dto.PermissionDTO;
import com.laptrinhweb.entity.PermissionEntity;
import com.laptrinhweb.entity.RoleEntity;
import com.laptrinhweb.repository.IPermissionRepository;
import com.laptrinhweb.service.IPermissionService;
import com.laptrinhweb.service.impl.convertEntity_DTO.PermissionConvert;

@Service
public class PermissionService implements IPermissionService {

	// Coverter
	@Autowired
	PermissionConvert permissionConvert;

	// PermissionRepo
	@Autowired
	IPermissionRepository permissionRepository;

	@Override
	@Transactional
	public PermissionDTO save(PermissionDTO permissionDTO) {
		PermissionEntity permissionEntity = permissionConvert.toEntity(permissionDTO);
		if (permissionEntity.getId() != null) { //Khi update thì xoá hết mấy cái permission ở role_permission đi
			PermissionEntity oldPermission = permissionRepository.findOne(permissionEntity.getId());
			for (RoleEntity roleEntity : oldPermission.getRoleList()) {
				roleEntity.getPermissionList().remove(oldPermission);
			}

		}
		// Thêm dữ liệu cho Table User_Permissiom
		for (RoleEntity roleEntity : permissionEntity.getRoleList()) {
			roleEntity.getPermissionList().add(permissionEntity);
		}
		permissionEntity = permissionRepository.save(permissionEntity);
		return permissionConvert.toDTO(permissionEntity);
	}

	@Override
	@Transactional
	public void delete(Long[] ids) {
		for (Long id : ids) {
			PermissionEntity oldPermission = permissionRepository.findOne(id);
			for (RoleEntity roleEntity : oldPermission.getRoleList()) {
				roleEntity.getPermissionList().remove(oldPermission);
			}
			permissionRepository.save(oldPermission);
			permissionRepository.delete(id);
		}
	}

}
