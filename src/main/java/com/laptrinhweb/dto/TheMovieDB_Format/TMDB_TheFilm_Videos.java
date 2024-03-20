package com.laptrinhweb.dto.TheMovieDB_Format;

import java.util.ArrayList;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown = true)
public class TMDB_TheFilm_Videos {
	
	private String id;
	private List<TMDB_VideoDTO> results = new ArrayList<>();

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public List<TMDB_VideoDTO> getResults() {
		return results;
	}

	public void setResults(List<TMDB_VideoDTO> results) {
		this.results = results;
	}

}
