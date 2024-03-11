package com.laptrinhweb.service.impl.convertEntity_DTO;

import javax.annotation.PostConstruct;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.laptrinhweb.dto.RelatedPartyWorkDetailDTO;
import com.laptrinhweb.dto.WorkDTO;
import com.laptrinhweb.entity.RelatedPartyWorkDetailEntity;
import com.laptrinhweb.entity.SubGenreEntity;
import com.laptrinhweb.entity.WorkEntity;
import com.laptrinhweb.repository.IGenreRepository;
import com.laptrinhweb.repository.IRelatedPartyRepository;
import com.laptrinhweb.repository.ISerieRepository;
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
	IRelatedPartyRepository relatedPartyRepository;
	
	@Autowired
	ISerieRepository serieRepository;

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
		workEntity.setSerie(serieRepository.findOneByCode(workDTO.getSerieCode()));
		for (String subGenreCode : workDTO.getSubGenreCodeList())
			workEntity.getSubGenreList().add(subGenreRepository.findOneByCode(subGenreCode));
		for (RelatedPartyWorkDetailDTO relatedPartyCode_Role : workDTO.getListRelatedPartyCode_Role())
			workEntity.getRelatedPartyDetailList().add(new RelatedPartyWorkDetailEntity(workEntity, relatedPartyRepository.findOneByCode(relatedPartyCode_Role.getRelatedPartyCode()), relatedPartyCode_Role.getRole()));
		return workEntity;
	}

	public WorkDTO toDTO(WorkEntity workEntity) {
		try {
			modelMapper.createTypeMap(WorkEntity.class, WorkDTO.class)
			.addMappings(mapping -> mapping.skip(WorkDTO::setSubGenreCodeList));
			} catch (Exception e) {
		}
		
		WorkDTO workDTO = modelMapper.map(workEntity, WorkDTO.class);
		workDTO.setGenreCode(workEntity.getGenre().getCode());
		for (SubGenreEntity subGenreEntity : workEntity.getSubGenreList())
			workDTO.getSubGenreCodeList().add(subGenreEntity.getCode());
		
		for (RelatedPartyWorkDetailEntity relatedPartyDetail : workEntity.getRelatedPartyDetailList()) {
			workDTO.getListRelatedPartyCode_Role().add(new RelatedPartyWorkDetailDTO(relatedPartyDetail.getRelatedParty().getCode(), relatedPartyDetail.getRole()));
		}
		return workDTO;
	}

}
