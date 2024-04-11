package com.laptrinhweb.service;

import java.util.List;

import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Service;

import com.laptrinhweb.dto.UserDTO;
import com.laptrinhweb.dto.UserFavoriteDetailDTO;
import com.laptrinhweb.dto.UserFavoriteDetailFULLDTO;
import com.laptrinhweb.entity.UserFavoriteDetailEntity;

@Service
public interface IUserFavoriteDetailService {
	UserFavoriteDetailEntity getOne(Long id);
	UserFavoriteDetailDTO save(UserFavoriteDetailDTO userFavoriteDetail);
	void delete(Long[] ids);
	void deleteWithWorkCode(String[] workCodes);
	List<UserFavoriteDetailFULLDTO> findByUserCodeAndGenreCode(String userCode, String genreCode, Pageable pageable);
	Integer countByUserCodeAndGenreCode(String userCode, String genreCode);
	Integer countAll();
	boolean checkUserFavorited(String workCode, String userCode);
}
