package com.laptrinhweb.service.impl.convertEntity_DTO;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.laptrinhweb.dto.GenreDTO;
import com.laptrinhweb.entity.GenreEntity;
import com.laptrinhweb.entity.SubGenreEntity;
import com.laptrinhweb.repository.ISubGenreRepository;

@Service
public class GenreConvert {

	@Autowired
	ISubGenreRepository subGenreRepository;

	@Autowired
	ModelMapper modelMapper;

	public GenreEntity toEntity(GenreDTO genreDTO) {
		GenreEntity genreEntity = new GenreEntity(genreDTO.getId(), genreDTO.getCode(), genreDTO.getName(),
				genreDTO.getThumbnail());
		genreEntity.getSubGenreList().clear();
		for (String subGenreCode : genreDTO.getSubGenreCodeList()) {
			SubGenreEntity subGenre = new SubGenreEntity();
			subGenre = subGenreRepository.findOneByCode(subGenreCode);
			if (subGenre != null) {
				genreEntity.getSubGenreList().add(subGenre);
			}
		}
		return genreEntity;
	}

	public GenreDTO toDTO(GenreEntity genreEntity) {
		GenreDTO genreDTO = modelMapper.map(genreEntity, GenreDTO.class);
		genreDTO.getSubGenreCodeList().clear();
		for (SubGenreEntity subGenreEntity : genreEntity.getSubGenreList()) {
			genreDTO.getSubGenreCodeList().add(subGenreEntity.getCode());
		}
		return genreDTO;
	}
}
