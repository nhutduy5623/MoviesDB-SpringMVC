package com.laptrinhweb.dto;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class AbstractDTO<T> {
	private Long id;
	private Date createdDate;
	private Date modifiedDate;
	private String createdBy;
	private String modifiedBy;
	private List<T> listResults = new ArrayList<>();	
	private Integer totalPages=1;
	private Integer nextPage = 1;
	private Integer limit = 4;	
	private String searchValue;
	
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public Date getCreatedDate() {
		return createdDate;
	}
	public void setCreatedDate(Date createdDate) {
		this.createdDate = createdDate;
	}
	public Date getModifiedDate() {
		return modifiedDate;
	}
	public void setModifiedDate(Date modifiedDate) {
		this.modifiedDate = modifiedDate;
	}
	public String getCreatedBy() {
		return createdBy;
	}
	public void setCreatedBy(String createdBy) {
		this.createdBy = createdBy;
	}
	public String getModifiedBy() {
		return modifiedBy;
	}
	public void setModifiedBy(String modifiedBy) {
		this.modifiedBy = modifiedBy;
	}
	public List<T> getListResults() {
		return listResults;
	}
	public void setListResults(List<T> listResults) {
		this.listResults = listResults;
	}
	public Integer getTotalPages() {
		return totalPages;
	}
	public void setTotalPages(Integer totalPages) {
		this.totalPages = totalPages;
	}
	public Integer getNextPage() {
		return nextPage;
	}
	public void setNextPage(Integer nextPage) {
		this.nextPage = nextPage;
	}
	public Integer getLimit() {
		return limit;
	}
	public void setLimit(Integer limit) {
		this.limit = limit;
	}
	public String getSearchValue() {
		return searchValue;
	}
	public void setSearchValue(String searchValue) {
		this.searchValue = searchValue;
	}
		
}
