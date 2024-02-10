package com.laptrinhweb.entity;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.Table;

@Entity
@Table(name = "subgenre")
public class SubGenreEntity extends baseEntity {

	@Column(name = "code")
	private String code;

	@Column(name = "name")
	private String name;

	@Column(name = "thumbnail", length = 510)
	private String thumbnail;

	@Column(name = "shortdescription", columnDefinition = "TEXT")
	private String shortDescription;

	@ManyToMany(fetch = FetchType.LAZY)
	@JoinTable(name = "subgenre_genre", 
				joinColumns = @JoinColumn(name = "subgenreid"), 
				inverseJoinColumns = @JoinColumn(name = "genreid"))
	private List<GenreEntity> genreList = new ArrayList<>();

	@ManyToMany(mappedBy = "subGenreList")
	private List<WorkEntity> workList = new ArrayList<>();

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

	public String getShortDescription() {
		return shortDescription;
	}

	public void setShortDescription(String shortDescription) {
		this.shortDescription = shortDescription;
	}

	public List<GenreEntity> getGenreList() {
		return genreList;
	}

	public void setGenreList(List<GenreEntity> genreList) {
		this.genreList = genreList;
	}

	public List<WorkEntity> getWorkList() {
		return workList;
	}

	public void setWorkList(List<WorkEntity> workList) {
		this.workList = workList;
	}
}
