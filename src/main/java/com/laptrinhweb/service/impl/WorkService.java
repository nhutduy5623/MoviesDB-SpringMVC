package com.laptrinhweb.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.laptrinhweb.dto.WorkDTO;
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
			workRepository.updateWorkStatus(id, 0);
		}
	}

}
