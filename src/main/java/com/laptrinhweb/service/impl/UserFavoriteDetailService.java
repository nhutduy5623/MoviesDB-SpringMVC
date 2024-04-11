package com.laptrinhweb.service.impl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.laptrinhweb.dto.UserDTO;
import com.laptrinhweb.dto.UserFavoriteDetailDTO;
import com.laptrinhweb.dto.UserFavoriteDetailFULLDTO;
import com.laptrinhweb.dto.userSecurity;
import com.laptrinhweb.entity.UserEntity;
import com.laptrinhweb.entity.UserFavoriteDetailEntity;
import com.laptrinhweb.entity.WorkEntity;
import com.laptrinhweb.repository.IUserFavoriteDetailRepository;
import com.laptrinhweb.repository.IUserRepository;
import com.laptrinhweb.repository.IWorkRepository;
import com.laptrinhweb.service.IUserFavoriteDetailService;
import com.laptrinhweb.service.impl.convertEntity_DTO.UserFavoriteDetailConvert;
import com.laptrinhweb.util.SecurityUtil;

@Service
public class UserFavoriteDetailService implements IUserFavoriteDetailService {

	// Coverter
	@Autowired
	UserFavoriteDetailConvert userFavoriteDetailConvert;
	
	@Autowired
	IUserFavoriteDetailRepository userFavoriteDetailRepository;
	
	@Autowired
	IUserRepository userRepository;
	
	@Autowired
	IWorkRepository workRepository;
	
	@Override
	public UserFavoriteDetailEntity getOne(Long id) {
		return userFavoriteDetailRepository.getOne(id);
	}

	@Override
	@Transactional
	public UserFavoriteDetailDTO save(UserFavoriteDetailDTO userFavoriteDetailDTO) {
		UserFavoriteDetailEntity userFavoriteDetailEntity = userFavoriteDetailConvert.toEntity(userFavoriteDetailDTO);
		UserFavoriteDetailEntity userFavoriteDetailEntityOld = userFavoriteDetailRepository.findByWorkAndUser(userFavoriteDetailEntity.getWork(), userFavoriteDetailEntity.getUser());
		if(userFavoriteDetailEntityOld !=null) {
			userFavoriteDetailEntity.setId(userFavoriteDetailEntityOld.getId());
		}
		userFavoriteDetailEntity = userFavoriteDetailRepository.save(userFavoriteDetailEntity);
		return userFavoriteDetailConvert.toDTO(userFavoriteDetailEntity);
	}

	@Override
	@Transactional
	public void delete(Long[] ids) {
		for (Long id : ids) {
//			UserFavoriteDetailEntity oldUserFv = userFavoriteDetailRepository.findOne(id);
			userFavoriteDetailRepository.delete(id);
		}
	}

	@Override
	@Transactional
	public void deleteWithWorkCode(String[] workCodes) {
		userSecurity currentUser = SecurityUtil.getPrincipal();
		if(currentUser!=null) {
			UserEntity userEntity = userRepository.findOneByCode(currentUser.getCode());
			WorkEntity workEntity = new WorkEntity();
			for (String workCode: workCodes) {
				workEntity = workRepository.findOneByCode(workCode);
				userFavoriteDetailRepository.deleteByWorkAndUser(workEntity, userEntity);			
			}
		}
	}


	@Override
	public Integer countAll() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<UserFavoriteDetailFULLDTO> findByUserCodeAndGenreCode(String userCode, String genreCode,
			Pageable pageable) {
		List<UserFavoriteDetailEntity> userFvEntities = userFavoriteDetailRepository.findByUserCodeAndGenreCode(userCode, genreCode, pageable).getContent();
		List<UserFavoriteDetailFULLDTO> userFavoriteDetailFullDTOs = new ArrayList<>();
		for(UserFavoriteDetailEntity userFavoriteDetailEntity : userFvEntities) {
			userFavoriteDetailFullDTOs.add(userFavoriteDetailConvert.toDtoFULL(userFavoriteDetailEntity));
		}
		return userFavoriteDetailFullDTOs;
	}

	@Override
	public Integer countByUserCodeAndGenreCode(String userCode, String genreCode) {
		return userFavoriteDetailRepository.countByUserCodeAndGenreCode(userCode, genreCode);
	}

	@Override
	public boolean checkUserFavorited(String workCode, String userCode) {
		UserEntity userEntity = userRepository.findOneByCode(userCode);
		WorkEntity workEntity = workRepository.findOneByCode(workCode);
		if(userFavoriteDetailRepository.findByWorkAndUser(workEntity, userEntity)!=null)
			return true;
		return false;
	}

}
