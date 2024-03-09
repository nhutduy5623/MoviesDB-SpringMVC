package com.laptrinhweb.service.impl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
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

	@Override
	public List<SerieDTO> findAll() {
		List<SerieEntity> serieEntities = serieRepository.findAll();
		List<SerieDTO> serieDTOs = new ArrayList<>();
		for(SerieEntity serieEntity : serieEntities) {
			serieDTOs.add(serieConvert.toDTO(serieEntity));
		}
		return serieDTOs;
	}

	@Override
	public List<SerieDTO> findAll(Pageable pageable) {
		List<SerieEntity> serieEntities = serieRepository.findAll(pageable).getContent();
		List<SerieDTO> serieDTOs = new ArrayList<>();
		for(SerieEntity serieEntity : serieEntities) {
			serieDTOs.add(serieConvert.toDTO(serieEntity));
		}
		return serieDTOs;
	}

	@Override
	public SerieDTO findOne(Long id) {
		SerieDTO serieDTO = serieConvert.toDTO(serieRepository.findOne(id));
		return serieDTO;
	}

	@Override
	public Integer countAll() {
		return (int) serieRepository.count();
	}

	@Override
	public List<SerieDTO> findByNamePageable(String name, Pageable pageable) {
		List<SerieEntity> serieEntities = serieRepository.findByNamePageable(name, pageable).getContent();
		List<SerieDTO> serieDTOs = new ArrayList<>();
		for(SerieEntity serieEntity : serieEntities) {
			serieDTOs.add(serieConvert.toDTO(serieEntity));
		}
		return serieDTOs;
	}

	@Override
	public Integer countByName(String name) {
		return serieRepository.countByName(name);
	}
	

}
