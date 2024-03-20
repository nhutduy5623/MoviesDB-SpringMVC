package com.laptrinhweb.dto.TheMovieDB_Format;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown = true)
public class TMDB_VideoDTO {
	
	private String key;
	private String id;
	private String site;

	public String getKey() {
		return key;
	}

	public void setKey(String key) {
		this.key = key;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getSite() {
		return site;
	}

	public void setSite(String site) {
		this.site = site;
	}
}
