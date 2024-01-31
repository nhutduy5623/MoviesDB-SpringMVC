package com.laptrinhweb.service;

import java.util.List;

import org.springframework.data.domain.Pageable;

import com.laptrinhweb.dto.GenreDTO;

public interface IGenreService {
	GenreDTO save(GenreDTO genre);
	void delete(Long[] ids);
	List<GenreDTO> findAll();
	List<GenreDTO> findAll(Pageable pageable);
	GenreDTO findOne(Long id);
	Integer countAll();
}
