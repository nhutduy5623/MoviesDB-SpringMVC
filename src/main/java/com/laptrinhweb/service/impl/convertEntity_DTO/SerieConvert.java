package com.laptrinhweb.service.impl.convertEntity_DTO;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.laptrinhweb.dto.SerieDTO;
import com.laptrinhweb.entity.SerieEntity;
import com.laptrinhweb.entity.WorkEntity;
import com.laptrinhweb.repository.ISerieRepository;
import com.laptrinhweb.repository.IWorkRepository;

@Component
public class SerieConvert {
	
	@Autowired
	ISerieRepository serieRepository;
	
	@Autowired
	IWorkRepository workRepository;
	
	@Autowired
	ModelMapper modelMapper;

	public SerieEntity toEntity(SerieDTO serieDto) {
		SerieEntity serieEntity = new SerieEntity();
		serieEntity = modelMapper.map(serieDto, serieEntity.getClass());
		serieEntity.getWorkList().clear();
		for(String workCode: serieDto.getWorkCodeList()) {
			serieEntity.getWorkList().add(workRepository.findOneByCode(workCode));
		}		
		return serieEntity;
	}
	
	public SerieDTO toDTO(SerieEntity serieEntity) {
		SerieDTO serieDto = new SerieDTO();
		
		
		serieDto = modelMapper.map(serieEntity, SerieDTO.class);
		serieDto.getWorkCodeList().clear();
		for(WorkEntity work:serieEntity.getWorkList()) {
			serieDto.getWorkCodeList().add(work.getCode());
		}
		return serieDto;
	}

}
