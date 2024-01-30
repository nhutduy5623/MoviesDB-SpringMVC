package com.laptrinhweb.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
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
	
}
