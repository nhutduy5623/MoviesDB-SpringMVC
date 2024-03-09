package com.laptrinhweb.api.admin;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.laptrinhweb.dto.SubGenreDTO;
import com.laptrinhweb.service.ISubGenreService;

@RestController(value = "SubGenreAPI_ADMIN")
public class SubGenreAPI {
	
	@Autowired
	ISubGenreService subGenreService;
	
	@PostMapping("/api/admin/subgenre") 
	public SubGenreDTO createSubGenre(@RequestBody SubGenreDTO subGenre) {
		return subGenreService.save(subGenre);	
	}
	
	@PutMapping("/api/admin/subgenre") 
	public SubGenreDTO updateSubGenre(@RequestBody SubGenreDTO subGenre) {
		return subGenreService.save(subGenre);	
	}
	
	@DeleteMapping("/api/admin/subgenre") 
	public void deleteSubGenre(@RequestBody Long[] ids) {
		subGenreService.delete(ids);	
	}
	
	@PostMapping("/api/subgenre/getbygenrecode") 
	public List<SubGenreDTO> getSubGenreByGenreCode(@RequestBody String genreCode) {
		System.out.println("GenreCode: "+ genreCode);
		return subGenreService.findByGenreList_Code(genreCode);	
	}
}
