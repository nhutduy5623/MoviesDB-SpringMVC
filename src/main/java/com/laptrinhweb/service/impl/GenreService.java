package com.laptrinhweb.service.impl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.laptrinhweb.dto.GenreDTO;
import com.laptrinhweb.entity.GenreEntity;
import com.laptrinhweb.entity.SubGenreEntity;
import com.laptrinhweb.repository.IGenreRepository;
import com.laptrinhweb.repository.ISubGenreRepository;
import com.laptrinhweb.service.IGenreService;
import com.laptrinhweb.service.impl.convertEntity_DTO.GenreConvert;

@Service
public class GenreService implements IGenreService {

	@Autowired
	IGenreRepository genreRepository;
	
	@Autowired
	ISubGenreRepository subGenreRepository;
	
	@Autowired
	GenreConvert genreCovert;
	
	@Override
	@Transactional
	public GenreDTO save(GenreDTO genreDTO) {
		GenreEntity genre = new GenreEntity();
		genre = genreRepository.save(genreCovert.toEntity(genreDTO));
		
//		Tạm thời! Vì khi thêm Genre thì bị lỗi không thêm dữ liệu vào subgenre_genre trong khi thêm subgenre lại được
		for (SubGenreEntity subgenre : genre.getSubGenreList()) {
			subgenre.getGenreList().add(genre);
			subGenreRepository.save(subgenre);
		}
		
		return genreCovert.toDTO(genre);
	}

	@Override
	@Transactional
	public void delete(Long id) {
		genreRepository.delete(id);	
	}

	@Override
	public List<GenreDTO> findAll() {
		List<GenreEntity> genreEntities = genreRepository.findAll();
		List<GenreDTO> genreDTOs = new ArrayList<>();
		for(GenreEntity genreEntity : genreEntities) {
			genreDTOs.add(genreCovert.toDTO(genreEntity));
		}
		return genreDTOs;
	}

	@Override
	public GenreDTO findOne(Long id) {
		GenreDTO genreDTO = genreCovert.toDTO(genreRepository.findOne(id));
		return genreDTO;
	}
	
}
