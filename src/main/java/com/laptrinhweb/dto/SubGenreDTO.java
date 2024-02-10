package com.laptrinhweb.dto;

import java.util.ArrayList;
import java.util.List;

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
}
