package com.laptrinhweb.dto.TheMovieDB_Format;

import java.util.ArrayList;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown = true)
public class ListSubGenresTMDB {
	List<TMDB_subGenreDTO> genres = new ArrayList<>();

	public List<TMDB_subGenreDTO> getGenres() {
		return genres;
	}

	public void setGenres(List<TMDB_subGenreDTO> genres) {
		this.genres = genres;
	}
	
	
}	
