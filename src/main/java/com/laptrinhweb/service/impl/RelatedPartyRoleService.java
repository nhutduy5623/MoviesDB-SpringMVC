package com.laptrinhweb.service.impl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.laptrinhweb.dto.RelatedPartyRoleDTO;
import com.laptrinhweb.entity.RelatedPartyRoleEntity;
import com.laptrinhweb.repository.IRelatedPartyRoleRepository;
import com.laptrinhweb.service.IRelatedPartyRoleService;
import com.laptrinhweb.service.impl.convertEntity_DTO.RelatedPartyRoleConvert;

@Service
public class RelatedPartyRoleService implements IRelatedPartyRoleService {

	@Autowired
	IRelatedPartyRoleRepository relatedPartyRoleRepository;
	
	@Autowired
	RelatedPartyRoleConvert relatedPartyRoleConvert;
	
	@Override
	@Transactional
	public RelatedPartyRoleDTO save(RelatedPartyRoleDTO relatedPartyRoleDTO) {
		RelatedPartyRoleEntity relatedPartyRoleEntity = new RelatedPartyRoleEntity();
		relatedPartyRoleEntity = relatedPartyRoleRepository.save(relatedPartyRoleConvert.toEntity(relatedPartyRoleDTO));
		relatedPartyRoleDTO = relatedPartyRoleConvert.toDTO(relatedPartyRoleEntity);
		return relatedPartyRoleDTO;
	}

	@Override
	@Transactional
	public void delete(Long[] ids) {
		for(long id: ids)	
			relatedPartyRoleRepository.delete(id);	
	}

	@Override
	public List<RelatedPartyRoleDTO> findAll() {
		List<RelatedPartyRoleEntity> relatedPartyRoleEntities = relatedPartyRoleRepository.findAll();
		List<RelatedPartyRoleDTO> relatedPartyRoleDTOs = new ArrayList<>();
		for(RelatedPartyRoleEntity relatedPartyRoleEntity : relatedPartyRoleEntities) {
			relatedPartyRoleDTOs.add(relatedPartyRoleConvert.toDTO(relatedPartyRoleEntity));
		}
		return relatedPartyRoleDTOs;
	}

	@Override
	public List<RelatedPartyRoleDTO> findAll(Pageable pageable) {
		List<RelatedPartyRoleEntity> relatedPartyRoleEntities = relatedPartyRoleRepository.findAll(pageable).getContent();
		List<RelatedPartyRoleDTO> relatedPartyRoleDTOs = new ArrayList<>();
		for(RelatedPartyRoleEntity relatedPartyRoleEntity : relatedPartyRoleEntities) {
			relatedPartyRoleDTOs.add(relatedPartyRoleConvert.toDTO(relatedPartyRoleEntity));
		}
		return relatedPartyRoleDTOs;
	}

	@Override
	public RelatedPartyRoleDTO findOne(Long id) {
		RelatedPartyRoleDTO relatedPartyRoleDTO = relatedPartyRoleConvert.toDTO(relatedPartyRoleRepository.findOne(id));
		return relatedPartyRoleDTO;
	}

	@Override
	public Integer countAll() {
		return (int) relatedPartyRoleRepository.count();
	}

	@Override
	public List<RelatedPartyRoleDTO> findByNamePageable(String name, Pageable pageable) {
		List<RelatedPartyRoleEntity> relatedPartyRoleEntities = relatedPartyRoleRepository.findByNamePageable(name, pageable).getContent();
		List<RelatedPartyRoleDTO> relatedPartyRoleDTOs = new ArrayList<>();
		for(RelatedPartyRoleEntity relatedPartyRoleEntity : relatedPartyRoleEntities) {
			relatedPartyRoleDTOs.add(relatedPartyRoleConvert.toDTO(relatedPartyRoleEntity));
		}
		return relatedPartyRoleDTOs;
	}

	@Override
	public Integer countByName(String name) {
		return relatedPartyRoleRepository.countByName(name);
	}

	@Override
	public Map<String, String> findAll_HasMap() {
		Map<String, String> listRelatedPartyRole = new HashMap<>();
		for(RelatedPartyRoleEntity role : relatedPartyRoleRepository.findAll()) {
			listRelatedPartyRole.put(role.getCode(), role.getName());
		}
		return listRelatedPartyRole;
	}
	
}