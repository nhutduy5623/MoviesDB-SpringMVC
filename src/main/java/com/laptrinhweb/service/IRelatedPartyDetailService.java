package com.laptrinhweb.service;

import java.util.List;

import org.springframework.data.domain.Pageable;

import com.laptrinhweb.dto.RelatedPartyDTO;
import com.laptrinhweb.dto.RelatedPartyWorkDetailDTO;

public interface IRelatedPartyDetailService {
	RelatedPartyWorkDetailDTO save(RelatedPartyWorkDetailDTO relatedPartyDetailDTO);
	void delete(Long[] ids);
	List<RelatedPartyWorkDetailDTO> findAll();
}
