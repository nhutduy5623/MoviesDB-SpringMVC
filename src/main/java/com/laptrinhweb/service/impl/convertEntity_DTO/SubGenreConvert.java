package com.laptrinhweb.service.impl.convertEntity_DTO;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.laptrinhweb.dto.SubGenreDTO;
import com.laptrinhweb.entity.GenreEntity;
import com.laptrinhweb.entity.SubGenreEntity;
import com.laptrinhweb.repository.IGenreRepository;
import com.laptrinhweb.repository.ISubGenreRepository;

@Service
public class SubGenreConvert {
	@Autowired
	ISubGenreRepository subGenreRepository;
	
	@Autowired
	IGenreRepository genreRepository;
	
	@Autowired
	ModelMapper modelMapper;
	
	public SubGenreEntity toEntity(SubGenreDTO subGenreDTO) {
		try {
			//Loại bỏ mapper ListGenre
			modelMapper.createTypeMap(SubGenreDTO.class, SubGenreEntity.class)
	        .addMappings(mapping -> mapping.skip(SubGenreEntity::setGenreList));
		} catch (Exception e) {
			// TODO: handle exception
		}
		
		SubGenreEntity subGenreEntity = modelMapper.map(subGenreDTO, SubGenreEntity.class);
		for (String genreCode : subGenreDTO.getGenreCodeList()) {
			GenreEntity genreEntity = new GenreEntity();
			genreEntity = genreRepository.findOneByCode(genreCode);
			if(genreEntity != null)
				subGenreEntity.getGenreList().add(genreEntity);
		}		
		return subGenreEntity;		
	}
	
	public SubGenreDTO toDTO(SubGenreEntity subGenreEntity) {
		SubGenreDTO subGenreDTO = modelMapper.map(subGenreEntity, SubGenreDTO.class);
		subGenreDTO.getGenreCodeList().clear();
		for (GenreEntity genreEntity : subGenreEntity.getGenreList()) {
			subGenreDTO.getGenreCodeList().add(genreEntity.getCode());
		}
		return subGenreDTO;
	}
}
