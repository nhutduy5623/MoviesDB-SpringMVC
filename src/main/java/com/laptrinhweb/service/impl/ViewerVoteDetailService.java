package com.laptrinhweb.service.impl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.laptrinhweb.dto.RelatedPartyDTO;
import com.laptrinhweb.dto.ViewerVoteDetailDTO;
import com.laptrinhweb.dto.ViewerVoteDetailFullDTO;
import com.laptrinhweb.dto.WorkDTO;
import com.laptrinhweb.entity.GenreEntity;
import com.laptrinhweb.entity.RelatedPartyEntity;
import com.laptrinhweb.entity.SubGenreEntity;
import com.laptrinhweb.entity.ViewerVoteDetailEntity;
import com.laptrinhweb.entity.WorkEntity;
import com.laptrinhweb.repository.IWorkRepository;
import com.laptrinhweb.repository.ViewerVoteDetailRepository;
import com.laptrinhweb.service.IViewerVoteDetailService;
import com.laptrinhweb.service.impl.convertEntity_DTO.ViewerVoteDetailConvert;

@Service
public class ViewerVoteDetailService implements IViewerVoteDetailService{

	@Autowired
	ViewerVoteDetailConvert voteConvert;
	
	@Autowired ViewerVoteDetailRepository voteRepo;
	
	@Autowired
	IWorkRepository workRepository;
	
	@Override
	public ViewerVoteDetailDTO save(ViewerVoteDetailDTO viewerVoteDetailDTO) {
		ViewerVoteDetailEntity voteEntity = voteRepo.
				findByWorkCodeAndUser(viewerVoteDetailDTO.getUser().getId(), viewerVoteDetailDTO.getWorkCode());
		if(voteEntity != null) {
			voteEntity.setScore(viewerVoteDetailDTO.getScore());
			voteEntity.setContent(viewerVoteDetailDTO.getContent());
			voteEntity = voteRepo.save(voteEntity);
			WorkEntity work = voteEntity.getWork();
			work.setScore((int) voteRepo.scoreAverageByWorkCode(work.getCode()));
			workRepository.save(work);
			viewerVoteDetailDTO = voteConvert.toDTO(voteEntity);
			return viewerVoteDetailDTO;
		}
		ViewerVoteDetailEntity newVoteEntity = voteConvert.toEntity(viewerVoteDetailDTO);
		voteRepo.save(newVoteEntity);
		WorkEntity work = newVoteEntity.getWork();
		work.setVoteCount(voteRepo.countByWorkCode(work.getCode()));
		work.setScore((int) voteRepo.scoreAverageByWorkCode(work.getCode()));
		workRepository.save(work);
		viewerVoteDetailDTO = voteConvert.toDTO(newVoteEntity);
		return viewerVoteDetailDTO;

	}

	@Override
	public void delete(Long[] ids) {
		for(long id: ids) {
			voteRepo.delete(id);	
		}
		
	}

	@Override
	public List<ViewerVoteDetailDTO> findByWorkCode(String workCode) {
		List<ViewerVoteDetailEntity> entities = voteRepo.findByWorkCode(workCode);
		List<ViewerVoteDetailDTO> dTOs = new ArrayList<>();
		for(ViewerVoteDetailEntity entity : entities) {
			dTOs.add(voteConvert.toDTO(entity));
		}
		return dTOs;
	}

	@Override
	public List<ViewerVoteDetailDTO> findByWorkCodePageable(String workCode, Pageable pageable) {
		List<ViewerVoteDetailEntity> entities = voteRepo.findByWorkCodePageable(workCode, pageable).getContent();
		List<ViewerVoteDetailDTO> dTOs = new ArrayList<>();
		for(ViewerVoteDetailEntity entity : entities) {
			dTOs.add(voteConvert.toDTO(entity));
		}
		return dTOs;
	}

	@Override
	public long countByWorkCode(String workCode) {		
		return voteRepo.countByWorkCode(workCode);
	}

	@Override
	public List<ViewerVoteDetailDTO> findByWorkCodeNotUserPageable(String workCode, long userId, Pageable pageable) {
		List<ViewerVoteDetailEntity> entities = voteRepo.findByWorkCodeNotUserPageable(workCode, userId, pageable).getContent();
		List<ViewerVoteDetailDTO> dTOs = new ArrayList<>();
		for(ViewerVoteDetailEntity entity : entities) {
			dTOs.add(voteConvert.toDTO(entity));
		}
		return dTOs;
	}

	@Override
	public ViewerVoteDetailDTO findByWorkCodeAndUser(String workCode, long userId) {
		return voteConvert.toDTO(voteRepo.findByWorkCodeAndUser(userId, workCode));
	}

	@Override
	public void delete(long id) {
		voteRepo.delete(id);
	}
	
	@Override
	public List<ViewerVoteDetailFullDTO> findByUserPageable(long userId, Pageable pageable) {
		List<ViewerVoteDetailEntity> entities = voteRepo.findByUserPageable(userId, pageable).getContent();
		List<ViewerVoteDetailFullDTO> dTOs = new ArrayList<>();
		for(ViewerVoteDetailEntity entity : entities) {
			dTOs.add(voteConvert.toDTOFull(entity));
		}
		return dTOs;
	}

	@Override
	public long countByUser(long userId) {
		return voteRepo.countByUser(userId);
	}

}