package com.laptrinhweb.service.impl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
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

	@Override
	public Map<String, String> findAll_HasMap() {
		Map<String, String> listRole = new HashMap<>();
		for(RoleEntity role : roleRepository.findAll()) {
			listRole.put(role.getCode(), role.getName());
		}
		return listRole;
	}

	@Override
	public List<RoleDTO> findAll() {
		List<RoleEntity> roleEntities = roleRepository.findAll();
		List<RoleDTO> roleDTOs = new ArrayList<>();
		for(RoleEntity roleEntity : roleEntities) {
			roleDTOs.add(roleConvert.toDTO(roleEntity));
		}
		return roleDTOs;
	}

	@Override
	public List<RoleDTO> findAll(Pageable pageable) {
		List<RoleEntity> roleEntities = roleRepository.findAll(pageable).getContent();
		List<RoleDTO> roleDTOs = new ArrayList<>();
		for(RoleEntity roleEntity : roleEntities) {
			roleDTOs.add(roleConvert.toDTO(roleEntity));
		}
		return roleDTOs;
	}

	@Override
	public RoleDTO findOne(Long id) {
		return roleConvert.toDTO(roleRepository.findOne(id));
	}

	@Override
	public Integer countAll() {
		return (int) roleRepository.count();
	}

	@Override
	public List<RoleDTO> findByNamePageable(String name, Pageable pageable) {
		List<RoleEntity> roleEntities = roleRepository.findByNamePageable(name, pageable).getContent();
		List<RoleDTO> roleDTOs = new ArrayList<>();
		for(RoleEntity roleEntity : roleEntities) {
			roleDTOs.add(roleConvert.toDTO(roleEntity));
		}
		return roleDTOs;
	}

	@Override
	public Integer countByName(String name) {
		return roleRepository.countByName(name);
	}
}
