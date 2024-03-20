package com.laptrinhweb.dto;

import java.util.ArrayList;
import java.util.List;

import com.laptrinhweb.dto.TheMovieDB_Format.TMDB_subGenreDTO;

public class SubGenreDTO extends AbstractDTO<SubGenreDTO>{
	
	private String code;
	private String name;
	private String thumbnail;
	private String shortDescription;
	private List<String> genreCodeList = new ArrayList<>();
	
	public String getCode() {
		return code;
	}
	public void setCode(String code) {
		this.code = code;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getThumbnail() {
		return thumbnail;
	}
	public void setThumbnail(String thumbnail) {
		this.thumbnail = thumbnail;
	}
	public List<String> getGenreCodeList() {
		return genreCodeList;
	}
	public void setGenreCodeList(List<String> genreCodeList) {
		this.genreCodeList = genreCodeList;
	}
	public String getShortDescription() {
		return shortDescription;
	}
	public void setShortDescription(String shortDescription) {
		this.shortDescription = shortDescription;
	}
	public SubGenreDTO(TMDB_subGenreDTO subGenreTMDB, String genreCode) {
		super();
		this.code = subGenreTMDB.getId();
		this.name = subGenreTMDB.getName();
		this.thumbnail = "https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcToMTawtOjFuq65Z3kohu8O5sKGYd-qIqydSnk5MgLGUHLH4ZhQurHoVjeUAVEmIKvBbZ0&usqp=CAU";
		this.shortDescription = "";
		this.genreCodeList.add(genreCode);
	}
	public SubGenreDTO() {
		super();
	} 	
	
	
}
