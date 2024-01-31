package com.laptrinhweb.dto;

import java.util.ArrayList;
import java.util.List;

public class GenreDTO extends AbstractDTO<GenreDTO> {
	private String code;
	private String name;
	private String thumbnail;
	private List<String> subGenreCodeList = new ArrayList<>();
	
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
	public List<String> getSubGenreCodeList() {
		return subGenreCodeList;
	}
	public void setSubGenreCodeList(List<String> subGenreCodeList) {
		this.subGenreCodeList = subGenreCodeList;
	}
}
