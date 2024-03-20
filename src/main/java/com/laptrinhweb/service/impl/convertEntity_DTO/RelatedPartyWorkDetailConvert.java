package com.laptrinhweb.service.impl.convertEntity_DTO;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.laptrinhweb.dto.RelatedPartyRoleDTO;
import com.laptrinhweb.dto.RelatedPartyWorkDetailDTO;
import com.laptrinhweb.entity.RelatedPartyRoleEntity;
import com.laptrinhweb.entity.RelatedPartyWorkDetailEntity;
import com.laptrinhweb.repository.IRelatedPartyRepository;
import com.laptrinhweb.repository.IRelatedPartyWorkDetailRepository;
import com.laptrinhweb.repository.IWorkRepository;

@Service
public class RelatedPartyWorkDetailConvert {

	@Autowired
	IRelatedPartyWorkDetailRepository RB_Work_DetailRepository;

	@Autowired
	IWorkRepository workRepository;
	
	@Autowired
	IRelatedPartyRepository relatedPartyRepository;
	
	@Autowired
	ModelMapper modelMapper;

	public RelatedPartyWorkDetailEntity toEntity(RelatedPartyWorkDetailDTO rB_Work_DetailDTO) {
		RelatedPartyWorkDetailEntity rB_Work_DetailEntity = new RelatedPartyWorkDetailEntity(workRepository.findOneByCode(rB_Work_DetailDTO.getWorkCode()), relatedPartyRepository.findOneByCode(rB_Work_DetailDTO.getRelatedPartyCode()), rB_Work_DetailDTO.getRole()); 
		return rB_Work_DetailEntity;
	}

	public RelatedPartyWorkDetailDTO toDTO(RelatedPartyWorkDetailEntity rB_Work_DetailEntity) {
		RelatedPartyWorkDetailDTO rB_Work_DetailDTO = new RelatedPartyWorkDetailDTO(rB_Work_DetailEntity.getWork().getCode(), rB_Work_DetailEntity.getRelatedParty().getCode(), rB_Work_DetailEntity.getRole()); 
		return rB_Work_DetailDTO;
	}
}
