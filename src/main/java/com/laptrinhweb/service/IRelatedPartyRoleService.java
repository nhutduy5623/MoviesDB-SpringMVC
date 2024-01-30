package com.laptrinhweb.service;

import com.laptrinhweb.dto.RelatedPartyRoleDTO;

public interface IRelatedPartyRoleService {
	RelatedPartyRoleDTO save(RelatedPartyRoleDTO relatedPartyRoleDTO);
	void delete(Long[] ids);
}
