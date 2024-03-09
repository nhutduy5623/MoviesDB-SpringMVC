package com.laptrinhweb.service;

import java.util.List;
import java.util.Map;

import org.springframework.data.domain.Pageable;

import com.laptrinhweb.dto.RelatedPartyRoleDTO;

public interface IRelatedPartyRoleService {
	RelatedPartyRoleDTO save(RelatedPartyRoleDTO relatedPartyRoleDTO);
	void delete(Long[] ids);
	List<RelatedPartyRoleDTO> findAll();
	List<RelatedPartyRoleDTO> findAll(Pageable pageable);
	RelatedPartyRoleDTO findOne(Long id);
	Integer countAll();
	List<RelatedPartyRoleDTO> findByNamePageable(String name, Pageable pageable);
	Integer countByName(String name);
	Map<String, String> findAll_HasMap();
}
