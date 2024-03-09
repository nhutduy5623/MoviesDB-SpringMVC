package com.laptrinhweb.service.impl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.laptrinhweb.dto.GenreDTO;
import com.laptrinhweb.dto.RelatedPartyDTO;
import com.laptrinhweb.dto.SubGenreDTO;
import com.laptrinhweb.entity.GenreEntity;
import com.laptrinhweb.entity.RelatedPartyEntity;
import com.laptrinhweb.entity.SubGenreEntity;
import com.laptrinhweb.repository.IRelatedPartyRepository;
import com.laptrinhweb.service.IRelatedPartyService;
import com.laptrinhweb.service.impl.convertEntity_DTO.RelatedPartyConvert;

@Service
public class RelatedPartyService implements IRelatedPartyService {

	@Autowired
	IRelatedPartyRepository relatedPartyRepository;
	
	@Autowired
	RelatedPartyConvert relatedPartyConvert;
	
	@Override
	@Transactional
	public RelatedPartyDTO save(RelatedPartyDTO relatedPartyDTO) {
		RelatedPartyEntity relatedPartyEntity = relatedPartyConvert.toEntity(relatedPartyDTO);
		relatedPartyEntity = relatedPartyRepository.save(relatedPartyEntity);
		relatedPartyDTO = relatedPartyConvert.toDTO(relatedPartyEntity);
		return relatedPartyDTO;
	}

	@Override
	@Transactional
	public void delete(Long[] ids) {
		for(long id: ids)	
			relatedPartyRepository.delete(id);	
	}

	@Override
	public List<RelatedPartyDTO> findAll() {
		List<RelatedPartyEntity> relatedPartyEntities = relatedPartyRepository.findAll();
		List<RelatedPartyDTO> relatedPartyDTOs = new ArrayList<>();
		for(RelatedPartyEntity relatedPartyEntity : relatedPartyEntities) {
			relatedPartyDTOs.add(relatedPartyConvert.toDTO(relatedPartyEntity));
		}
		return relatedPartyDTOs;
	}

	@Override
	public List<RelatedPartyDTO> findAll(Pageable pageable) {
		List<RelatedPartyEntity> relatedPartyEntities = relatedPartyRepository.findAll(pageable).getContent();
		List<RelatedPartyDTO> relatedPartyDTOs = new ArrayList<>();
		for(RelatedPartyEntity relatedPartyEntity : relatedPartyEntities) {
			relatedPartyDTOs.add(relatedPartyConvert.toDTO(relatedPartyEntity));
		}
		return relatedPartyDTOs;
	}

	@Override
	public Integer countAll() {
		return (int) relatedPartyRepository.count();
	}

	@Override
	public List<RelatedPartyDTO> findByNamePageable(String name, Pageable pageable) {
		List<RelatedPartyEntity> relatedPartyEntities = relatedPartyRepository.findByNamePageable(name, pageable).getContent();
		List<RelatedPartyDTO> relatedPartyDTOs = new ArrayList<>();
		for(RelatedPartyEntity relatedPartyEntity : relatedPartyEntities) {
			relatedPartyDTOs.add(relatedPartyConvert.toDTO(relatedPartyEntity));
		}
		return relatedPartyDTOs;
	}

	@Override
	public Integer countByName(String name) {
		return relatedPartyRepository.countByName(name);
	}

	@Override
	public RelatedPartyDTO findOne(Long id) {
		RelatedPartyDTO relatedPartyDTO = relatedPartyConvert.toDTO(relatedPartyRepository.findOne(id));
		return relatedPartyDTO;
	}

	@Override
	public List<RelatedPartyDTO> findByRelatedPartyRoleCode(String roleCode, Pageable pageable) {
		List<RelatedPartyEntity> relatedPartyEntities = relatedPartyRepository.findByRelatedPartyRoleCode(roleCode, pageable).getContent();
		List<RelatedPartyDTO> relatedPartyDTOs = new ArrayList<>();
		for(RelatedPartyEntity relatedPartyEntity : relatedPartyEntities) {
			relatedPartyDTOs.add(relatedPartyConvert.toDTO(relatedPartyEntity));
		}
		return relatedPartyDTOs;
	}

	@Override
	public Integer countByRelatedPartyRoleCode(String roleCode) {
		return relatedPartyRepository.countByRelatedPartyRoleCode(roleCode);
	}
	
}
