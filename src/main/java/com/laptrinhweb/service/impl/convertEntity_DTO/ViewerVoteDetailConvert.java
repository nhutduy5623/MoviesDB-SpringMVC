package com.laptrinhweb.service.impl.convertEntity_DTO;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.laptrinhweb.dto.ViewerVoteDetailDTO;
import com.laptrinhweb.entity.ViewerVoteDetailEntity;
import com.laptrinhweb.repository.IUserRepository;
import com.laptrinhweb.repository.IWorkRepository;

@Service
public class ViewerVoteDetailConvert {
	@Autowired
	IWorkRepository workRepository;

	@Autowired
	IUserRepository userRepository;
	
	@Autowired
	ModelMapper modelMapper;

	public ViewerVoteDetailEntity toEntity(ViewerVoteDetailDTO voteDTO) {
		ViewerVoteDetailEntity voteEntity = new ViewerVoteDetailEntity();
		voteEntity = modelMapper.map(voteDTO, ViewerVoteDetailEntity.class);
		voteEntity.setUser(userRepository.findOne(voteDTO.getUser().getId()));
		voteEntity.setWork(workRepository.findOneByCode(voteDTO.getWorkCode()));
		return voteEntity;
	}

	public ViewerVoteDetailDTO toDTO(ViewerVoteDetailEntity voteEntity) {
		ViewerVoteDetailDTO voteDTO = new ViewerVoteDetailDTO();
		voteDTO = modelMapper.map(voteEntity, ViewerVoteDetailDTO.class);
		voteDTO.setWorkCode(voteEntity.getWork().getCode());
		return voteDTO;
	}
}