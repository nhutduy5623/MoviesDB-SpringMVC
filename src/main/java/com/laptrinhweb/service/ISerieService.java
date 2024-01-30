package com.laptrinhweb.service;

import com.laptrinhweb.dto.SerieDTO;

public interface ISerieService {
	SerieDTO save(SerieDTO serieDto);
	void delete(Long[] ids);
}
