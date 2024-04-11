package com.laptrinhweb.api.web;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.laptrinhweb.dto.UserFavoriteDetailDTO;
import com.laptrinhweb.service.IUserFavoriteDetailService;

@RestController(value = "userFavorite_API")
public class userFavoriteAPI {

	@Autowired
	IUserFavoriteDetailService userFavoriteDetailService;

	@PostMapping("/api/userfavorite")
	public UserFavoriteDetailDTO createUserFavoriteDetail(@RequestBody UserFavoriteDetailDTO userFavoriteDetailDTO) {
		return userFavoriteDetailService.save(userFavoriteDetailDTO);
	}

	@PutMapping("/api/userfavorite")
	public UserFavoriteDetailDTO updateUserFavoriteDetail(@RequestBody UserFavoriteDetailDTO userFavoriteDetailDTO) {
		return userFavoriteDetailService.save(userFavoriteDetailDTO);
	}

	@DeleteMapping("/api/userfavorite")
	public void deleteUserFavoriteDetail(@RequestBody Long[] ids) {
			userFavoriteDetailService.delete(ids);
	}	

	@DeleteMapping("/api/userfavorite_withworkcode")
	public void deleteUserFavoriteDetail(@RequestBody String[] workCodes) {
			userFavoriteDetailService.deleteWithWorkCode(workCodes);
	}
}
