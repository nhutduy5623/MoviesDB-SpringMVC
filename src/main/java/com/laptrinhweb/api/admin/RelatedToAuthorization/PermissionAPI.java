package com.laptrinhweb.api.admin.RelatedToAuthorization;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.laptrinhweb.dto.PermissionDTO;
import com.laptrinhweb.service.IPermissionService;

@RestController(value = "PermissionAPI_ADMIN")
public class PermissionAPI {
	@Autowired
	IPermissionService permissionService;
	
	@PostMapping("/api/admin/permission") 
	public PermissionDTO createPermission(@RequestBody PermissionDTO permission) {
		return permissionService.save(permission);	
	}
	
	@PutMapping("/api/admin/permission") 
	public PermissionDTO updatePermission(@RequestBody PermissionDTO permission) {
		return permissionService.save(permission);	
	}
	
	@DeleteMapping("/api/admin/permission") 
	public void deletePermission(@RequestBody Long[] ids) {
		permissionService.delete(ids);	
	}
}
