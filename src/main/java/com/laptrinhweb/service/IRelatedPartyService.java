package com.laptrinhweb.service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.data.domain.Pageable;

import com.laptrinhweb.dto.RelatedPartyDTO;
import com.laptrinhweb.dto.WorkDTO;

public interface IRelatedPartyService {
	RelatedPartyDTO save(RelatedPartyDTO relatedPartyDTO);
	void delete(Long[] ids);
	List<RelatedPartyDTO> findAll();
	List<RelatedPartyDTO> findAll(Pageable pageable);
	Integer countAll();
	List<RelatedPartyDTO> findByNamePageable(String name, Pageable pageable);
	Integer countByName(String name);
	RelatedPartyDTO findOne(Long id);
	List<RelatedPartyDTO> findByRelatedPartyRoleCode(String roleCode, Pageable pageable);
	Integer countByRelatedPartyRoleCode(String roleCode);
	RelatedPartyDTO findOneByCode(String code);
	Map<RelatedPartyDTO, String> findByWork(WorkDTO workDto);
	List<RelatedPartyDTO> findWithoutWork(WorkDTO workDto);

	
	
}
