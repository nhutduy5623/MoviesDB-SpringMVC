package com.laptrinhweb.service.impl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.laptrinhweb.dto.GenreDTO;
import com.laptrinhweb.dto.RelatedPartyDTO;
import com.laptrinhweb.dto.RelatedPartyWorkDetailDTO;
import com.laptrinhweb.dto.SubGenreDTO;
import com.laptrinhweb.entity.GenreEntity;
import com.laptrinhweb.entity.RelatedPartyEntity;
import com.laptrinhweb.entity.SubGenreEntity;
import com.laptrinhweb.repository.IRelatedPartyRepository;
import com.laptrinhweb.repository.IRelatedPartyWorkDetailRepository;
import com.laptrinhweb.service.IRelatedPartyDetailService;
import com.laptrinhweb.service.IRelatedPartyService;
import com.laptrinhweb.service.impl.convertEntity_DTO.RelatedPartyConvert;

@Service
public class RelatedPartyWorkDetailService implements IRelatedPartyDetailService {

	@Autowired
	IRelatedPartyWorkDetailRepository relatedPartyWorkDetailRepository;
	
	@Autowired
	RelatedPartyConvert relatedPartyConvert;

	@Override
	public RelatedPartyWorkDetailDTO save(RelatedPartyWorkDetailDTO relatedPartyDetailDTO) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void delete(Long[] ids) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public List<RelatedPartyWorkDetailDTO> findAll() {
		// TODO Auto-generated method stub
		return null;
	}
	
	
}
