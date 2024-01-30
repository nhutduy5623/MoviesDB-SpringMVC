package com.laptrinhweb.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
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
		for(long id: ids)	
			subGenreRepository.delete(id);	
	}
	
}
