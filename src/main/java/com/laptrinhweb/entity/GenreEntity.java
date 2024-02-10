package com.laptrinhweb.entity;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity
@Table(name = "genre")
public class GenreEntity extends baseEntity{
	
	@Column(name = "code")
	private String code;
	
	@Column(name = "name")
	private String name;
	
	@Column(name = "thumbnail", length = 510)
	private String thumbnail;
	
	@ManyToMany(mappedBy = "genreList",fetch = FetchType.LAZY)
	private List<SubGenreEntity> subGenreList = new ArrayList<>();
	
	@OneToMany(mappedBy = "genre")
	private List<WorkEntity> workList = new ArrayList<>();	
	
	public GenreEntity() {
	}
	
	public GenreEntity(Long id, String code, String name, String thumbnail) {
		super.setId(id);
		this.code = code;
		this.name = name;
		this.thumbnail = thumbnail;
	}

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

	public List<SubGenreEntity> getSubGenreList() {
		return subGenreList;
	}

	public void setSubGenreList(List<SubGenreEntity> subGenreList) {
		this.subGenreList = subGenreList;
	}

	public List<WorkEntity> getWorkList() {
		return workList;
	}

	public void setWorkList(List<WorkEntity> workList) {
		this.workList = workList;
	}
}
