package com.laptrinhweb.service;

import com.laptrinhweb.dto.WorkDTO;

public interface IWorkService {
	WorkDTO save(WorkDTO workDTO);
	void delete(Long[] ids);
}
