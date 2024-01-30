package com.laptrinhweb.service;

import org.springframework.stereotype.Service;

import com.laptrinhweb.dto.RoleDTO;

@Service
public interface IRoleService {
	RoleDTO save(RoleDTO roleDTO);
	void delete(Long[] ids);
}
