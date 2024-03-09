package com.laptrinhweb.service;

import java.util.List;

import org.springframework.data.domain.Pageable;

import com.laptrinhweb.dto.SerieDTO;

public interface ISerieService {
	SerieDTO save(SerieDTO serieDto);
	void delete(Long[] ids);
	List<SerieDTO> findAll();
	List<SerieDTO> findAll(Pageable pageable);
	SerieDTO findOne(Long id);
	Integer countAll();
	List<SerieDTO> findByNamePageable(String name, Pageable pageable);
	Integer countByName(String name);
}
