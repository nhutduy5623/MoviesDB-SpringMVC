package com.laptrinhweb.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.laptrinhweb.entity.GenreEntity;
import com.laptrinhweb.repository.IGenreRepository;
import com.laptrinhweb.service.IGenreService;

@Service
public class GenreService implements IGenreService {

	@Autowired
	IGenreRepository genreRepository;
	
	@Override
	@Transactional
	public GenreEntity save(GenreEntity entity) {
		GenreEntity genre = new GenreEntity();
		genre = genreRepository.save(entity);
		return genre;
	}

	@Override
	@Transactional
	public void delete(Long[] ids) {
		for(long id: ids)	
			genreRepository.delete(id);	
	}
	
}
