package com.laptrinhweb.api.admin.RelatedToAuthorization;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.laptrinhweb.dto.UserDTO;
import com.laptrinhweb.service.IUserService;

@RestController(value = "UserAPI_ADMIN")
public class UserAPI {
	@Autowired
	IUserService userService;
	
	@PostMapping("/api/admin/user") 
	public UserDTO createUser(@RequestBody UserDTO user) {
		return userService.save(user);	
	}
	
	@PutMapping("/api/admin/user") 
	public UserDTO updateUser(@RequestBody UserDTO user) {
		return userService.save(user);	
	}
	
	@DeleteMapping("/api/admin/user") 
	public void deleteUser(@RequestBody Long[] ids) {
		userService.delete(ids);	
	}
}
