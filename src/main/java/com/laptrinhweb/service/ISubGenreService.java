package com.laptrinhweb.service;

import java.util.List;
import java.util.Map;

import org.springframework.data.domain.Pageable;

import com.laptrinhweb.dto.SubGenreDTO;

public interface ISubGenreService {
	SubGenreDTO save(SubGenreDTO subGenreDTO);
	void delete(Long[] ids);
	List<SubGenreDTO> findAll();
	List<SubGenreDTO> findByGenreList_Code(String genreCode, Pageable pageable);
	List<SubGenreDTO> findByGenreList_Code(String genreCode);
	List<SubGenreDTO> findAll(Pageable pageable);
	Integer countByGenreList_Code(String genreCode);
	Integer countAll();
	SubGenreDTO findOne(Long id);
	SubGenreDTO findOneByCode(String code);
	Map<String, String> findAll_HasMap();
	
	List<SubGenreDTO> findByNamePageable(String name, Pageable pageable);
	Integer countByName(String name);
	
	List<SubGenreDTO> findByWorkCode(String workCode);
}
