package com.laptrinhweb.service.impl.convertEntity_DTO;

import javax.annotation.PostConstruct;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.laptrinhweb.dto.WorkDTO;
import com.laptrinhweb.entity.SubGenreEntity;
import com.laptrinhweb.entity.WorkEntity;
import com.laptrinhweb.repository.IGenreRepository;
import com.laptrinhweb.repository.ISubGenreRepository;
import com.laptrinhweb.repository.IWorkRepository;

@Service
public class WorkConvert {

	@Autowired
	IWorkRepository workRepository;

	@Autowired
	ISubGenreRepository subGenreRepository;

	@Autowired
	IGenreRepository genreRepository;

	@Autowired
	ModelMapper modelMapper;

	@PostConstruct
	public void init() {
		// Cấu hình ánh xạ
		modelMapper.createTypeMap(WorkDTO.class, WorkEntity.class)
				.addMappings(mapping -> mapping.skip(WorkEntity::setSubGenreList));
		
	}

	public WorkEntity toEntity(WorkDTO workDTO) {
		WorkEntity workEntity = modelMapper.map(workDTO, WorkEntity.class);
		workEntity.getCommentList().clear();
		workEntity.getRelatedPartyDetailList().clear();
		workEntity.getUserFavoriteList().clear();
		workEntity.getUserVoteList().clear();
		workEntity.getSubGenreList().clear();
		workEntity.setGenre(genreRepository.findOneByCode(workDTO.getGenreCode()));
		for (String subGenreCode : workDTO.getSubGenreCodeList())
			workEntity.getSubGenreList().add(subGenreRepository.findOneByCode(subGenreCode));
		return workEntity;
	}

	public WorkDTO toDTO(WorkEntity workEntity) {
		try {
			modelMapper.createTypeMap(WorkEntity.class, WorkDTO.class)
			.addMappings(mapping -> mapping.skip(WorkDTO::setSubGenreCodeList));
			} catch (Exception e) {
			// TODO: handle exception
		}
		WorkDTO workDTO = modelMapper.map(workEntity, WorkDTO.class);
		workDTO.setGenreCode(workEntity.getGenre().getCode());
		for (SubGenreEntity subGenreEntity : workEntity.getSubGenreList())
			workDTO.getSubGenreCodeList().add(subGenreEntity.getCode());
		return workDTO;
		
	}

}
