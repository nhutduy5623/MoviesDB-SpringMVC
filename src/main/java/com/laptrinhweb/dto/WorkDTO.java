package com.laptrinhweb.dto;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class WorkDTO extends AbstractDTO{
	private String code;
	private String name;
	private String overview;
	private String thumbnail;
	private double budget;
	private Long followerCount;
	private Long viewerCount;
	private int score;
	private Long voteCount;
	private Date relatedDate;
	private int status;
	private String background;
	private String genreCode;
	private String serieCode;
	private List<String> subGenreCodeList = new ArrayList<>();
	
	//Cần thêm Related
	
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
	public String getOverview() {
		return overview;
	}
	public void setOverview(String overview) {
		this.overview = overview;
	}
	public String getThumbnail() {
		return thumbnail;
	}
	public void setThumbnail(String thumbnail) {
		this.thumbnail = thumbnail;
	}
	public double getBudget() {
		return budget;
	}
	public void setBudget(double budget) {
		this.budget = budget;
	}
	public Long getFollowerCount() {
		return followerCount;
	}
	public void setFollowerCount(Long followerCount) {
		this.followerCount = followerCount;
	}
	public Long getViewerCount() {
		return viewerCount;
	}
	public void setViewerCount(Long viewerCount) {
		this.viewerCount = viewerCount;
	}
	public int getScore() {
		return score;
	}
	public void setScore(int score) {
		this.score = score;
	}
	public Long getVoteCount() {
		return voteCount;
	}
	public void setVoteCount(Long voteCount) {
		this.voteCount = voteCount;
	}
	public Date getRelatedDate() {
		return relatedDate;
	}
	public void setRelatedDate(Date relatedDate) {
		this.relatedDate = relatedDate;
	}
	public int getStatus() {
		return status;
	}
	public void setStatus(int status) {
		this.status = status;
	}
	public String getBackground() {
		return background;
	}
	public void setBackground(String background) {
		this.background = background;
	}
	public String getGenreCode() {
		return genreCode;
	}
	public void setGenreCode(String genreCode) {
		this.genreCode = genreCode;
	}
	public String getSerieCode() {
		return serieCode;
	}
	public void setSerieCode(String serieCode) {
		this.serieCode = serieCode;
	}
	public List<String> getSubGenreCodeList() {
		return subGenreCodeList;
	}
	public void setSubGenreCodeList(List<String> subGenreCodeList) {
		this.subGenreCodeList = subGenreCodeList;
	}	

}
