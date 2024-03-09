package com.laptrinhweb.service;

import java.util.List;

import org.springframework.data.domain.Pageable;

import com.laptrinhweb.dto.RelatedPartyDTO;
import com.laptrinhweb.dto.RelatedPartyRoleDTO;

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
}
