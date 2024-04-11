package com.laptrinhweb.service;

import java.util.List;

import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.laptrinhweb.dto.ViewerVoteDetailDTO;

@Service
public interface IViewerVoteDetailService {
	ViewerVoteDetailDTO save(ViewerVoteDetailDTO viewerVoteDetailDTO);
	void delete(Long[] ids);
	
	List<ViewerVoteDetailDTO> findByWorkCode(String workCode);
	
	List<ViewerVoteDetailDTO> findByWorkCodePageable(String workCode, Pageable pageable);
	
	List<ViewerVoteDetailDTO> findByWorkCodeNotUserPageable(String workCode, long userId, Pageable pageable);
	
	ViewerVoteDetailDTO findByWorkCodeAndUser(String workCode, long userId);
	
	long countByWorkCode(String workCode);
	
	void delete(long id);
}