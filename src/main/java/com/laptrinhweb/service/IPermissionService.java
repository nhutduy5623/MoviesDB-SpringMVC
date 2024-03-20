package com.laptrinhweb.service;

import java.util.List;
import java.util.Map;

import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.laptrinhweb.dto.GenreDTO;
import com.laptrinhweb.dto.PermissionDTO;

@Service
public interface IPermissionService {
	PermissionDTO save(PermissionDTO permissionDTO);
	void delete(Long[] ids);
	List<PermissionDTO> findAll();
	List<PermissionDTO> findAll(Pageable pageable);
	PermissionDTO findOne(Long id);
	Integer countAll();
	List<PermissionDTO> findByNamePageable(String name, Pageable pageable);
	Integer countByName(String name);
	Map<String, String> findAll_HasMap();
}
