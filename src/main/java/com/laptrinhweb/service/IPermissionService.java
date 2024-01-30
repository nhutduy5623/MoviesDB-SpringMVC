package com.laptrinhweb.service;

import org.springframework.stereotype.Service;

import com.laptrinhweb.dto.PermissionDTO;

@Service
public interface IPermissionService {
	PermissionDTO save(PermissionDTO permissionDTO);
	void delete(Long[] ids);
}
