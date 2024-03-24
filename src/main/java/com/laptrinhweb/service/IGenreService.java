package com.laptrinhweb.service;

import java.util.List;
import java.util.Map;

import org.springframework.data.domain.Pageable;

import com.laptrinhweb.dto.GenreDTO;

public interface IGenreService {
	GenreDTO save(GenreDTO genre);
	void delete(Long[] ids);
	GenreDTO findOneByCode(String code);
	List<GenreDTO> findAll();
	List<GenreDTO> findAll(Pageable pageable);
	GenreDTO findOne(Long id);
	Integer countAll();
	List<GenreDTO> findByNamePageable(String name, Pageable pageable);
	Integer countByName(String name);
	Map<String, String> findAll_HasMap();
}
