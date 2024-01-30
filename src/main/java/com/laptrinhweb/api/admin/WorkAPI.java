package com.laptrinhweb.api.admin;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.laptrinhweb.dto.WorkDTO;
import com.laptrinhweb.service.IWorkService;

@RestController(value = "WorkAPI_ADMIN")
public class WorkAPI {
	
	@Autowired
	IWorkService workService;
	
	@PostMapping("/api/admin/work") 
	public WorkDTO createGenre(@RequestBody WorkDTO work) {
		return workService.save(work);	
	}
	
	@PutMapping("/api/admin/work") 
	public WorkDTO updateGenre(@RequestBody WorkDTO work) {
		return workService.save(work);	
	}
	
	@DeleteMapping("/api/admin/work") 
	public void deleteGenre(@RequestBody Long[] ids) {
		workService.delete(ids);	
	}
}
