package com.laptrinhweb.service;

import com.laptrinhweb.entity.GenreEntity;

public interface IGenreService {
	GenreEntity save(GenreEntity entity);
	void delete(Long[] ids);
}
