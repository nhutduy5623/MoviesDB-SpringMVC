package com.laptrinhweb.service.impl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
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
	GenreConvert genreConvert;
	
	@Override
	@Transactional
	public GenreDTO save(GenreDTO genreDTO) {
		GenreEntity genre = new GenreEntity();
		if(genreDTO.getId()!=null) { //xử lý dữ liệu cũ
			GenreEntity oldGenre = genreRepository.findOne(genreDTO.getId());		
			for (SubGenreEntity subgenre : oldGenre.getSubGenreList()) {
				subgenre.getGenreList().remove(oldGenre);
			}
		}
		
		//Thêm mới, update
		genre = genreConvert.toEntity(genreDTO);
//		Tạm thời! Vì khi thêm Genre thì bị lỗi không thêm dữ liệu vào subgenre_genre trong khi thêm subgenre lại được
		for (SubGenreEntity subgenre : genre.getSubGenreList()) {
			subgenre.getGenreList().add(genre);
		}				
		genre = genreRepository.save(genre);
		return genreConvert.toDTO(genre);
	}

	@Override
	@Transactional
	public void delete(Long[] ids) {
		GenreEntity genreEntity;
		for(long id: ids) {
			genreEntity = genreRepository.findOne(id);
			for(SubGenreEntity subgenreEntity: genreEntity.getSubGenreList()) {
				subgenreEntity.getGenreList().remove(genreEntity);
			}
			genreEntity.getSubGenreList().clear();
			genreRepository.save(genreEntity);
			genreRepository.delete(id);	
		}
	}

	@Override
	public List<GenreDTO> findAll() {
		List<GenreEntity> genreEntities = genreRepository.findAll();
		List<GenreDTO> genreDTOs = new ArrayList<>();
		for(GenreEntity genreEntity : genreEntities) {
			genreDTOs.add(genreConvert.toDTO(genreEntity));
		}
		return genreDTOs;
	}
	
	@Override
	public List<GenreDTO> findAll(Pageable pageable) {
		List<GenreEntity> genreEntities = genreRepository.findAll(pageable).getContent();
		List<GenreDTO> genreDTOs = new ArrayList<>();
		for(GenreEntity genreEntity : genreEntities) {
			genreDTOs.add(genreConvert.toDTO(genreEntity));
		}
		return genreDTOs;
	}

	@Override
	public GenreDTO findOne(Long id) {
		GenreDTO genreDTO = genreConvert.toDTO(genreRepository.findOne(id));
		return genreDTO;
	}

	@Override
	public Integer countAll() {
		return (int) genreRepository.count();
	}

	@Override
	public List<GenreDTO> findByNamePageable(String name, Pageable pageable) {
		List<GenreEntity> genreEntities = genreRepository.findByNamePageable(name, pageable).getContent();
		List<GenreDTO> genreDTOs = new ArrayList<>();
		for(GenreEntity genreEntity : genreEntities) {
			genreDTOs.add(genreConvert.toDTO(genreEntity));
		}
		return genreDTOs;
	}

	@Override
	public Integer countByName(String name) {
		return genreRepository.countByName(name);
	}

	@Override
	public Map<String, String> findAll_HasMap() {
		Map<String, String> genreCodeList = new HashMap<>();
		List<GenreEntity> listGenreEntity = genreRepository.findAll();
		for(GenreEntity genre: listGenreEntity) {
			System.out.println("-----GenreCode-----:"+genre.getCode());
			genreCodeList.put(genre.getCode(), genre.getName());
		}
		return genreCodeList;
	}
	
}
