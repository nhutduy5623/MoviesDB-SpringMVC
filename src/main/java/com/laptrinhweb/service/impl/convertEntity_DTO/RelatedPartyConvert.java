package com.laptrinhweb.service.impl.convertEntity_DTO;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.laptrinhweb.dto.RelatedPartyDTO;
import com.laptrinhweb.entity.RelatedPartyEntity;
import com.laptrinhweb.repository.IRelatedPartyRepository;
import com.laptrinhweb.repository.IRelatedPartyRoleRepository;

@Service
public class RelatedPartyConvert {

	@Autowired
	IRelatedPartyRepository relatedPartyRepository;

	@Autowired
	IRelatedPartyRoleRepository relatedPartyRoleRepository;
	
	@Autowired
	ModelMapper modelMapper;

	public RelatedPartyEntity toEntity(RelatedPartyDTO relatedPartyDTO) {
		RelatedPartyEntity relatedPartyEntity = new RelatedPartyEntity();
		relatedPartyEntity = modelMapper.map(relatedPartyDTO, RelatedPartyEntity.class);
		relatedPartyEntity.setRProle(relatedPartyRoleRepository.findOneByCode(relatedPartyDTO.getRoleCode()));
		return relatedPartyEntity;
		
	}

	public RelatedPartyDTO toDTO(RelatedPartyEntity relatedPartyEntity) {
		RelatedPartyDTO relatedPartyDTO = new RelatedPartyDTO();
		relatedPartyDTO = modelMapper.map(relatedPartyEntity, RelatedPartyDTO.class);
		relatedPartyDTO.setRoleCode(relatedPartyEntity.getRProle().getCode());
		return relatedPartyDTO;
	}
}
