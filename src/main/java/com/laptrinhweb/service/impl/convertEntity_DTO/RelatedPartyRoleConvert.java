package com.laptrinhweb.service.impl.convertEntity_DTO;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.laptrinhweb.dto.RelatedPartyRoleDTO;
import com.laptrinhweb.entity.RelatedPartyRoleEntity;
import com.laptrinhweb.repository.IRelatedPartyRoleRepository;

@Service
public class RelatedPartyRoleConvert {

	@Autowired
	IRelatedPartyRoleRepository relatedPartyRoleRepository;

	@Autowired
	ModelMapper modelMapper;

	public RelatedPartyRoleEntity toEntity(RelatedPartyRoleDTO relatedPartyRoleDTO) {
		RelatedPartyRoleEntity relatedPartyRoleEntity = new RelatedPartyRoleEntity();
		relatedPartyRoleEntity = modelMapper.map(relatedPartyRoleDTO, RelatedPartyRoleEntity.class);
		return relatedPartyRoleEntity;
		
	}

	public RelatedPartyRoleDTO toDTO(RelatedPartyRoleEntity relatedPartyRoleEntity) {
		RelatedPartyRoleDTO relatedPartyRoleDTO = new RelatedPartyRoleDTO();
		relatedPartyRoleDTO = modelMapper.map(relatedPartyRoleEntity, RelatedPartyRoleDTO.class);
		return relatedPartyRoleDTO;
	}
}
