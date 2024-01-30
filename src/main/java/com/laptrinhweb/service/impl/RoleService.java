package com.laptrinhweb.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.laptrinhweb.dto.RoleDTO;
import com.laptrinhweb.entity.RoleEntity;
import com.laptrinhweb.repository.IRoleRepository;
import com.laptrinhweb.service.IRoleService;
import com.laptrinhweb.service.impl.convertEntity_DTO.RoleConvert;

@Service
public class RoleService implements IRoleService {
	
	// Coverter
	@Autowired
	RoleConvert roleConvert;

	// PermissionRepo
	@Autowired
	IRoleRepository roleRepository;

	@Override
	@Transactional
	public RoleDTO save(RoleDTO roleDTO) {
		RoleEntity roleEntity = roleConvert.toEntity(roleDTO);
		roleEntity = roleRepository.save(roleEntity);
		return roleConvert.toDTO(roleEntity);
	}

	@Override
	@Transactional
	public void delete(Long[] ids) {
		for (Long id : ids) {
			roleRepository.delete(id);
		}
	}
}
