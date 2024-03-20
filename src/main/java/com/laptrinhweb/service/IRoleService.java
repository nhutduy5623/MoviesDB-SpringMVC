package com.laptrinhweb.service;

import java.util.List;
import java.util.Map;

import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.laptrinhweb.dto.RoleDTO;

@Service
public interface IRoleService {
	RoleDTO save(RoleDTO roleDTO);
	void delete(Long[] ids);
	Map<String, String> findAll_HasMap();
	
	List<RoleDTO> findAll();
	List<RoleDTO> findAll(Pageable pageable);
	RoleDTO findOne(Long id);
	Integer countAll();
	List<RoleDTO> findByNamePageable(String name, Pageable pageable);
	Integer countByName(String name);
	
}
