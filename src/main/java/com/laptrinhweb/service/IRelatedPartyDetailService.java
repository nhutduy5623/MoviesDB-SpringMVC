package com.laptrinhweb.service;

import java.util.List;
import java.util.Map;

import org.springframework.data.domain.Pageable;

import com.laptrinhweb.dto.RelatedPartyDTO;
import com.laptrinhweb.dto.RelatedPartyWorkDetailDTO;
import com.laptrinhweb.dto.WorkDTO;

public interface IRelatedPartyDetailService {
	RelatedPartyWorkDetailDTO save(RelatedPartyWorkDetailDTO relatedPartyDetailDTO);
	void delete(Long[] ids);
	List<RelatedPartyWorkDetailDTO> findAll();
	Map<RelatedPartyDTO, String> findRPDetailByWork(WorkDTO workDTO);
}
