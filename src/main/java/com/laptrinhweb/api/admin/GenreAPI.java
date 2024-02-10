package com.laptrinhweb.api.admin;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.laptrinhweb.dto.GenreDTO;
import com.laptrinhweb.service.IGenreService;

@RestController(value = "GenreAPI_ADMIN")
public class GenreAPI {
	
	@Autowired
	IGenreService genreService;
	
	@PostMapping("/api/admin/genre") 
	public GenreDTO createGenre(@RequestBody GenreDTO genre) {
		return genreService.save(genre);	
	}
	
	@PutMapping("/api/admin/genre") 
	public GenreDTO updateGenre(@RequestBody GenreDTO genre) {
		return genreService.save(genre);	
	}
	
	@DeleteMapping("/api/admin/genre") 
	public void deleteGenre(@RequestBody Long[] ids) {
		genreService.delete(ids);	
	}
}
