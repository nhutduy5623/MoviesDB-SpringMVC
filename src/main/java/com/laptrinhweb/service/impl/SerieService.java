package com.laptrinhweb.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.laptrinhweb.dto.SerieDTO;
import com.laptrinhweb.entity.SerieEntity;
import com.laptrinhweb.repository.ISerieRepository;
import com.laptrinhweb.service.ISerieService;
import com.laptrinhweb.service.impl.convertEntity_DTO.SerieConvert;

@Service
public class SerieService implements ISerieService{

	@Autowired
	SerieConvert serieConvert;
	
	@Autowired
	ISerieRepository serieRepository;
	
	@Override
	@Transactional
	public SerieDTO save(SerieDTO serieDto) {
		SerieEntity serieEntity = new SerieEntity();
		serieEntity = serieConvert.toEntity(serieDto);
		serieEntity = serieRepository.save(serieEntity);
		return serieConvert.toDTO(serieEntity);
	}

	@Override
	@Transactional
	public void delete(Long[] ids) {
		for(long id: ids)	
			serieRepository.delete(id);	
	}
	

}
