package com.laptrinhweb.service;

import java.util.List;
import java.util.Map;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.laptrinhweb.dto.SubGenreDTO;
import com.laptrinhweb.dto.WorkDTO;
import com.laptrinhweb.entity.WorkEntity;

public interface IWorkService {
	WorkDTO save(WorkDTO workDTO);
	void delete(Long[] ids);
	
	List<WorkDTO> findAll();
	List<WorkDTO> findAll(Pageable pageable);
	WorkDTO findOne(Long id);
	WorkDTO findOneByCode(String code);
	Integer countAll();
	List<WorkDTO> findByNamePageable(String name, Pageable pageable);
	Integer countByName(String name);
	Map<String, String> findAll_HasMap();
	
	List<WorkDTO> findByGenre_Code(String genreCode, Pageable pageable);
	Integer countByGenre_Code(String genreCode);
//	Map<String, Object> find_CountByGenreCodeAndSubgenreCodes(String genreCode, List<String> subgenreCodeList, Pageable pageable);
	Map<String, Object> findBy_OrName_OrGenre_OrSubGenres(String genreCode, List<String> subGenreCodeList, String searchValue, Pageable pageable);
	
	List<WorkDTO> findByRelatedPartyCode(String code);
	
}
