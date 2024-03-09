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
import com.laptrinhweb.dto.WorkDTO;
import com.laptrinhweb.entity.GenreEntity;
import com.laptrinhweb.entity.SubGenreEntity;
import com.laptrinhweb.entity.WorkEntity;
import com.laptrinhweb.repository.ISubGenreRepository;
import com.laptrinhweb.repository.IWorkRepository;
import com.laptrinhweb.service.IWorkService;
import com.laptrinhweb.service.impl.convertEntity_DTO.WorkConvert;

@Service
public class WorkService implements IWorkService{

	@Autowired
	IWorkRepository workRepository;
	
	@Autowired
	ISubGenreRepository subGenreRepository;
	
	@Autowired
	WorkConvert workConvert;
	
	@Override
	@Transactional
	public WorkDTO save(WorkDTO workDTO) {
		WorkEntity workEntity = workRepository.save(workConvert.toEntity(workDTO));		
		workDTO = workConvert.toDTO(workEntity);
		return workDTO;
	}

	@Override
	@Transactional
	public void delete(Long[] ids) {
		for(Long id:ids) {
			WorkEntity work = workRepository.findOne(id);
			work.getSubGenreList().clear();	
			workRepository.delete(id);
		}
	}

	@Override
	public List<WorkDTO> findAll() {
		List<WorkEntity> workEntities = workRepository.findAll();
		List<WorkDTO> workDTOs = new ArrayList<>();
		for(WorkEntity workEntity : workEntities) {
			workDTOs.add(workConvert.toDTO(workEntity));
		}
		return workDTOs;
	}

	@Override
	public List<WorkDTO> findAll(Pageable pageable) {
		List<WorkEntity> workEntities = workRepository.findAll(pageable).getContent();
		List<WorkDTO> workDTOs = new ArrayList<>();
		for(WorkEntity workEntity : workEntities) {
			workDTOs.add(workConvert.toDTO(workEntity));
		}
		return workDTOs;
	}

	@Override
	public WorkDTO findOne(Long id) {
		return workConvert.toDTO(workRepository.findOne(id));
	}

	@Override
	public Integer countAll() {
		// TODO Auto-generated method stub
		return (int) workRepository.count();
	}

	@Override
	public List<WorkDTO> findByNamePageable(String name, Pageable pageable) {
		List<WorkEntity> workEntities = workRepository.findByNamePageable(name, pageable).getContent();
		List<WorkDTO> workDTOs = new ArrayList<>();
		for(WorkEntity workEntity : workEntities) {
			workDTOs.add(workConvert.toDTO(workEntity));
		}
		return workDTOs;
	}

	@Override
	public Integer countByName(String name) {
		return workRepository.countByName(name);
	}

	@Override
	public Map<String, String> findAll_HasMap() {
		Map<String, String> workCodeList = new HashMap<>();
		List<WorkEntity> listWorkEntity = workRepository.findAll();
		for(WorkEntity workEntity: listWorkEntity) {
			workCodeList.put(workEntity.getCode(), workEntity.getName());
		}
		return workCodeList;
	}

	@Override
	public List<WorkDTO> findByGenre_Code(String genreCode, Pageable pageable) {
		List<WorkEntity> workEntities = workRepository.findByGenre_Code(genreCode, pageable).getContent();
		List<WorkDTO> workDTOs = new ArrayList<>();
		for(WorkEntity workEntity : workEntities) {
			workDTOs.add(workConvert.toDTO(workEntity));
		}
		return workDTOs;
	}

	@Override
	public Integer countByGenre_Code(String genreCode) {
		return workRepository.countByGenre_Code(genreCode);
	}

}
