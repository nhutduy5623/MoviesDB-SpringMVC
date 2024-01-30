package com.laptrinhweb.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.laptrinhweb.dto.RelatedPartyDTO;
import com.laptrinhweb.entity.RelatedPartyEntity;
import com.laptrinhweb.repository.IRelatedPartyRepository;
import com.laptrinhweb.service.IRelatedPartyService;
import com.laptrinhweb.service.impl.convertEntity_DTO.RelatedPartyConvert;

@Service
public class RelatedPartyService implements IRelatedPartyService {

	@Autowired
	IRelatedPartyRepository relatedPartyRepository;
	
	@Autowired
	RelatedPartyConvert relatedPartyConver;
	
	@Override
	@Transactional
	public RelatedPartyDTO save(RelatedPartyDTO relatedPartyDTO) {
		RelatedPartyEntity relatedPartyEntity = relatedPartyConver.toEntity(relatedPartyDTO);
		relatedPartyEntity = relatedPartyRepository.save(relatedPartyEntity);
		relatedPartyDTO = relatedPartyConver.toDTO(relatedPartyEntity);
		return relatedPartyDTO;
	}

	@Override
	@Transactional
	public void delete(Long[] ids) {
		for(long id: ids)	
			relatedPartyRepository.delete(id);	
	}
	
}
