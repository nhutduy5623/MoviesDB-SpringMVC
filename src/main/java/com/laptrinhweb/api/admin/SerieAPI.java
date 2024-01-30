package com.laptrinhweb.api.admin;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.laptrinhweb.dto.SerieDTO;
import com.laptrinhweb.service.ISerieService;

@RestController(value = "SerieAPI_ADMIN")
public class SerieAPI {
	
	@Autowired
	ISerieService serieService;
	
	@PostMapping("/api/admin/serie") 
	public SerieDTO createGenre(@RequestBody SerieDTO serie) {
		return serieService.save(serie);	
	}
	
	@PutMapping("/api/admin/serie") 
	public SerieDTO updateGenre(@RequestBody SerieDTO serie) {
		return serieService.save(serie);	
	}
	
	@DeleteMapping("/api/admin/serie") 
	public void deleteGenre(@RequestBody Long[] ids) {
		serieService.delete(ids);	
	}
}
