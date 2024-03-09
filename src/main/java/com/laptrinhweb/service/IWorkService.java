package com.laptrinhweb.service;

import java.util.List;
import java.util.Map;

import org.springframework.data.domain.Pageable;

import com.laptrinhweb.dto.WorkDTO;

public interface IWorkService {
	WorkDTO save(WorkDTO workDTO);
	void delete(Long[] ids);
	
	List<WorkDTO> findAll();
	List<WorkDTO> findAll(Pageable pageable);
	WorkDTO findOne(Long id);
	Integer countAll();
	List<WorkDTO> findByNamePageable(String name, Pageable pageable);
	Integer countByName(String name);
	Map<String, String> findAll_HasMap();
	
	List<WorkDTO> findByGenre_Code(String genreCode, Pageable pageable);
	Integer countByGenre_Code(String genreCode);
}
