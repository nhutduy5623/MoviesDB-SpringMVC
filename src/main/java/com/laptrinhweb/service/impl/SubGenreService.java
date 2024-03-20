package com.laptrinhweb.service.impl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.laptrinhweb.dto.SubGenreDTO;
import com.laptrinhweb.entity.SubGenreEntity;
import com.laptrinhweb.repository.ISubGenreRepository;
import com.laptrinhweb.service.ISubGenreService;
import com.laptrinhweb.service.impl.convertEntity_DTO.SubGenreConvert;

@Service
public class SubGenreService implements ISubGenreService {

	@Autowired
	ISubGenreRepository subGenreRepository;
	
	@Autowired
	SubGenreConvert subGenreConvert;
	
	@Override
	@Transactional
	public SubGenreDTO save(SubGenreDTO subGenreDTO) {
		SubGenreEntity subGenreEntity = new SubGenreEntity();
		subGenreEntity = subGenreRepository.save(subGenreConvert.toEntity(subGenreDTO));
		return subGenreConvert.toDTO(subGenreEntity);
	}

	@Override
	@Transactional
	public void delete(Long[] ids) {
		SubGenreEntity subGenreEntity;
		for(long id: ids) {
			subGenreEntity = subGenreRepository.findOne(id);
			subGenreEntity.getGenreList().clear();
			subGenreRepository.save(subGenreEntity);
			subGenreRepository.delete(id);	
		} 			
	}

	@Override
	public List<SubGenreDTO> findAll() {
		List<SubGenreEntity> subGenreEntities = subGenreRepository.findAll();
		List<SubGenreDTO> subGenreDTOs = new ArrayList<>();
		for(SubGenreEntity subGenreEntity:subGenreEntities) {
			subGenreDTOs.add(subGenreConvert.toDTO(subGenreEntity));
		}
		return subGenreDTOs;
	}

	@Override
	public List<SubGenreDTO> findByGenreList_Code(String genreCode, Pageable pageable) {
		List<SubGenreEntity> subGenreEntities = subGenreRepository.findByGenreList_Code(genreCode, pageable).getContent();
		List<SubGenreDTO> subGenreDTOs = new ArrayList<>();
		for(SubGenreEntity subGenreEntity:subGenreEntities) {
			subGenreDTOs.add(subGenreConvert.toDTO(subGenreEntity));
		}
		return subGenreDTOs;
	}

	@Override
	public List<SubGenreDTO> findAll(Pageable pageable) {
		List<SubGenreEntity> subGenreEntities = subGenreRepository.findAll(pageable).getContent();
		List<SubGenreDTO> subGenreDTOs = new ArrayList<>();
		for(SubGenreEntity subGenreEntity:subGenreEntities) {
			subGenreDTOs.add(subGenreConvert.toDTO(subGenreEntity));
		}
		return subGenreDTOs;
	}

	@Override
	public Integer countByGenreList_Code(String genreCode) {
		return subGenreRepository.countByGenreList_Code(genreCode);
	}

	@Override
	public Integer countAll() {
		return (int) subGenreRepository.count();
	}

	@Override
	public SubGenreDTO findOne(Long id) {
		SubGenreDTO subGenreDTO = subGenreConvert.toDTO(subGenreRepository.findOne(id));
		return subGenreDTO;
	}

	@Override
	public Map<String, String> findAll_HasMap() {
		List<SubGenreEntity> subGenreEntities = subGenreRepository.findAll();
		Map<String, String> subGenreDTO_Map = new HashMap<>();
		for(SubGenreEntity subGenreEntity:subGenreEntities) {
			subGenreDTO_Map.put(subGenreEntity.getCode(), subGenreEntity.getName());
		}
		return subGenreDTO_Map;
	}

	@Override
	public List<SubGenreDTO> findByNamePageable(String name, Pageable pageable) {
		List<SubGenreEntity> subGenreEntities = subGenreRepository.findByNamePageable(name, pageable).getContent();
		List<SubGenreDTO> subGenreDTOs = new ArrayList<>();
		for(SubGenreEntity subGenreEntity:subGenreEntities) {
			subGenreDTOs.add(subGenreConvert.toDTO(subGenreEntity));
		}
		return subGenreDTOs;
	}

	@Override
	public Integer countByName(String name) {
		return subGenreRepository.countByName(name);
	}

	@Override
	public List<SubGenreDTO> findByGenreList_Code(String genreCode) {
		List<SubGenreEntity> subGenreEntities = subGenreRepository.findByGenreList_Code(genreCode);
		List<SubGenreDTO> subGenreDTOs = new ArrayList<>();
		for(SubGenreEntity subGenreEntity:subGenreEntities) {
			subGenreDTOs.add(subGenreConvert.toDTO(subGenreEntity));
		}
		return subGenreDTOs;
	}

	@Override
	public SubGenreDTO findOneByCode(String code) {
		SubGenreEntity subgenreEntity;
		subgenreEntity= subGenreRepository.findOneByCode(code);
		if(subgenreEntity == null)
			return null;
		return subGenreConvert.toDTO(subGenreRepository.findOneByCode(code));
	}	
}
