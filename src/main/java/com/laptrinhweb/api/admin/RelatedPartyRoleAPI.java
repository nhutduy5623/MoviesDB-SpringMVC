package com.laptrinhweb.api.admin;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.laptrinhweb.dto.RelatedPartyRoleDTO;
import com.laptrinhweb.service.IRelatedPartyRoleService;

@RestController(value = "RelatedPartyRoleAPI_ADMIN")
public class RelatedPartyRoleAPI {
	
	@Autowired
	IRelatedPartyRoleService relatedPartyRoleService;
	
	@PostMapping("/api/admin/relatedpartyrole") 
	public RelatedPartyRoleDTO createGenre(@RequestBody RelatedPartyRoleDTO relatedPartyRole) {
		return relatedPartyRoleService.save(relatedPartyRole);	
	}
	
	@PutMapping("/api/admin/relatedpartyrole") 
	public RelatedPartyRoleDTO updateGenre(@RequestBody RelatedPartyRoleDTO relatedPartyRole) {
		return relatedPartyRoleService.save(relatedPartyRole);	
	}
	
	@DeleteMapping("/api/admin/relatedpartyrole") 
	public void deleteGenre(@RequestBody Long[] ids) {
		relatedPartyRoleService.delete(ids);	
	}
}
