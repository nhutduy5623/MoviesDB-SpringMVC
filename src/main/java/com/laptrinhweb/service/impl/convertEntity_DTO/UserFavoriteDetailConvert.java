package com.laptrinhweb.service.impl.convertEntity_DTO;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.laptrinhweb.dto.UserFavoriteDetailDTO;
import com.laptrinhweb.dto.UserFavoriteDetailFULLDTO;
import com.laptrinhweb.entity.UserFavoriteDetailEntity;
import com.laptrinhweb.repository.IUserRepository;
import com.laptrinhweb.repository.IWorkRepository;

@Service
public class UserFavoriteDetailConvert {
	
	@Autowired
	IUserRepository userRepository;
	
	@Autowired
	IWorkRepository workRepository;
	
	@Autowired
	WorkConvert workConvert;
	
	@Autowired
	UserConvert userConvert;
	
	@Autowired
	ModelMapper modelMapper;
	
	public UserFavoriteDetailEntity toEntity(UserFavoriteDetailDTO userFavoriteDetailDTO) {
		UserFavoriteDetailEntity userFavoriteDetailEntity = new UserFavoriteDetailEntity();
		userFavoriteDetailEntity = modelMapper.map(userFavoriteDetailDTO, userFavoriteDetailEntity.getClass());
		userFavoriteDetailEntity.setUser(userRepository.findOneByCode(userFavoriteDetailDTO.getUserCode()));
		userFavoriteDetailEntity.setWork(workRepository.findOneByCode(userFavoriteDetailDTO.getWorkCode()));
		return userFavoriteDetailEntity;
	}
	
	public UserFavoriteDetailDTO toDTO(UserFavoriteDetailEntity userFavoriteDetailEntity) {
		UserFavoriteDetailDTO userFavoriteDetailDTO = new UserFavoriteDetailDTO();
		userFavoriteDetailDTO = modelMapper.map(userFavoriteDetailEntity, UserFavoriteDetailDTO.class);
		userFavoriteDetailDTO.setWorkCode(userFavoriteDetailEntity.getWork().getCode());
		userFavoriteDetailDTO.setUserCode(userFavoriteDetailEntity.getUser().getCode());
		return userFavoriteDetailDTO;
	}
	
	public UserFavoriteDetailFULLDTO toDtoFULL(UserFavoriteDetailEntity userFavoriteDetailEntity) {
		UserFavoriteDetailFULLDTO userFavoriteDetailFullDTO = new UserFavoriteDetailFULLDTO();
		userFavoriteDetailFullDTO = modelMapper.map(userFavoriteDetailEntity, UserFavoriteDetailFULLDTO.class);
		userFavoriteDetailFullDTO.setWorkDTO(workConvert.toDTO(userFavoriteDetailEntity.getWork()));
		userFavoriteDetailFullDTO.setUserDTO(userConvert.toDTO(userFavoriteDetailEntity.getUser()));
		return userFavoriteDetailFullDTO;
	}
}
