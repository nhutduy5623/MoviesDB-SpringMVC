package com.laptrinhweb.service.impl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
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

	// Converter
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

	@Override
	public List<PermissionDTO> findAll() {
		List<PermissionEntity> permissionEnties = permissionRepository.findAll();
		List<PermissionDTO> permissionDTOs = new ArrayList<>();
		for(PermissionEntity permissionEntity : permissionEnties) {
			permissionDTOs.add(permissionConvert.toDTO(permissionEntity));
		}
		return permissionDTOs;
	}

	@Override
	public List<PermissionDTO> findAll(Pageable pageable) {
		List<PermissionEntity> permissionEnties = permissionRepository.findAll(pageable).getContent();
		List<PermissionDTO> permissionDTOs = new ArrayList<>();
		for(PermissionEntity permissionEntity : permissionEnties) {
			permissionDTOs.add(permissionConvert.toDTO(permissionEntity));
		}
		return permissionDTOs;
	}

	@Override
	public PermissionDTO findOne(Long id) {
		PermissionDTO permissionDTO = permissionConvert.toDTO(permissionRepository.findOne(id));
		return permissionDTO;
	}

	@Override
	public Integer countAll() {
		return (int) permissionRepository.count();
	}

	@Override
	public List<PermissionDTO> findByNamePageable(String name, Pageable pageable) {
		List<PermissionEntity> permissionEnties = permissionRepository.findByNamePageable(name, pageable).getContent();
		List<PermissionDTO> permissionDTOs = new ArrayList<>();
		for(PermissionEntity permissionEntity : permissionEnties) {
			permissionDTOs.add(permissionConvert.toDTO(permissionEntity));
		}
		return permissionDTOs;
	}

	@Override
	public Integer countByName(String name) {
		return permissionRepository.countByName(name);
	}

	@Override
	public Map<String, String> findAll_HasMap() {
		Map<String, String> permissionCodeList = new HashMap<String, String>();
		List<PermissionEntity> permissionEntities = permissionRepository.findAll();
		for(PermissionEntity permissionEntity: permissionEntities) {
			permissionCodeList.put(permissionEntity.getCode(), permissionEntity.getName());
		}		
		return permissionCodeList;
	}

}
