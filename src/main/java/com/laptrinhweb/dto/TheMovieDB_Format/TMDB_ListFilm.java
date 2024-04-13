package com.laptrinhweb.dto.TheMovieDB_Format;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown = true)
public class TMDB_ListFilm {
	private resultInListFilm[] results;
	private int page;
	private boolean success = true;

	public resultInListFilm[] getResults() {
		return results;
	}

	public void setResults(resultInListFilm[] results) {
		this.results = results;
	}

	public int getPage() {
		return page;
	}

	public void setPage(int page) {
		this.page = page;
	}

	public boolean isSuccess() {
		return success;
	}

	public void setSuccess(boolean success) {
		this.success = success;
	}
}
