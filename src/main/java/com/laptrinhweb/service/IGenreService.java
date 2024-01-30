package com.laptrinhweb.service;

import java.util.List;

import com.laptrinhweb.dto.GenreDTO;

public interface IGenreService {
	GenreDTO save(GenreDTO genre);
	void delete(Long id);
	List<GenreDTO> findAll();
	GenreDTO findOne(Long id);
}
