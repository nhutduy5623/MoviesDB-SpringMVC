package com.laptrinhweb.service;

import com.laptrinhweb.dto.SubGenreDTO;

public interface ISubGenreService {
	SubGenreDTO save(SubGenreDTO subGenreDTO);
	void delete(Long[] ids);
}
