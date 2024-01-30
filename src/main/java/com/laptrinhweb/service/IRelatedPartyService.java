package com.laptrinhweb.service;

import com.laptrinhweb.dto.RelatedPartyDTO;

public interface IRelatedPartyService {
	RelatedPartyDTO save(RelatedPartyDTO relatedPartyDTO);
	void delete(Long[] ids);
}
