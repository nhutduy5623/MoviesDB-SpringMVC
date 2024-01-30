package com.laptrinhweb.api.admin.RelatedToAuthorization;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.laptrinhweb.dto.RoleDTO;
import com.laptrinhweb.service.IRoleService;

@RestController(value = "RoleAPI_ADMIN")
public class RoleAPI {
	@Autowired
	IRoleService roleService;
	
	@PostMapping("/api/admin/role") 
	public RoleDTO createRole(@RequestBody RoleDTO role) {
		return roleService.save(role);	
	}
	
	@PutMapping("/api/admin/role") 
	public RoleDTO updateRole(@RequestBody RoleDTO role) {
		return roleService.save(role);	
	}
	
	@DeleteMapping("/api/admin/role") 
	public void deleteRole(@RequestBody Long[] ids) {
		roleService.delete(ids);	
	}
}
