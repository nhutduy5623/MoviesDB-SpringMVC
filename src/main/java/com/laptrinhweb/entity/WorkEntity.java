package com.laptrinhweb.entity;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity
@Table(name = "work")
public class WorkEntity extends baseEntity{
	
	@Column(name = "code", unique = true)
	private String code;
	
	@Column(name = "name")
	private String name;
	
	@Column(name = "overview", columnDefinition = "TEXT")
	private String overview;
	
	@Column(name = "thumbnail", length = 510)
	private String thumbnail;
	
	@Column(name = "video", length = 510)
	private String video;
	
	@Column(name = "budget")
	private double budget;
	
	@Column(name = "followercount")
	private Long followerCount;
	
	@Column(name = "viewercount")
	private Long viewerCount;
	
	@Column(name = "score")
	private int score;
	
	@Column(name = "votecount")
	private Long voteCount;
	
	@Column(name = "relateddate")
	private Date relatedDate;
	
	@Column(name = "status")
	private int status;
	
	@Column(name = "background", length = 510)
	private String background;
	
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "genreid")
	private GenreEntity genre;
	
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "serieid")
	private SerieEntity serie;
	
	@ManyToMany(fetch = FetchType.LAZY)
	@JoinTable(name = "subgenre_work", joinColumns = @JoinColumn(name = "workid"), inverseJoinColumns = @JoinColumn(name = "subgenreid"))
	private List<SubGenreEntity> subGenreList = new ArrayList<>();
	
	@OneToMany(mappedBy = "work", fetch = FetchType.LAZY)
	private List<RelatedPartyWorkDetailEntity> RelatedPartyDetailList = new ArrayList<>();

	@OneToMany(mappedBy = "work")
	private List<CommentEntity> commentList = new ArrayList<>();
	
	@OneToMany(mappedBy = "work")
	private List<ViewerVoteDetailEntity> userVoteList = new ArrayList<>();
	
	@OneToMany(mappedBy = "work")
	private List<UserFavoriteDetailEntity> userFavoriteList = new ArrayList<>();

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
	
	public String getVideo() {
		return video;
	}

	public void setVideo(String video) {
		this.video = video;
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


	public GenreEntity getGenre() {
		return genre;
	}

	public void setGenre(GenreEntity genre) {
		this.genre = genre;
	}

	public SerieEntity getSerie() {
		return serie;
	}

	public void setSerie(SerieEntity serie) {
		this.serie = serie;
	}

	public List<SubGenreEntity> getSubGenreList() {
		return subGenreList;
	}

	public void setSubGenreList(List<SubGenreEntity> subGenreList) {
		this.subGenreList = subGenreList;
	}

	public List<CommentEntity> getCommentList() {
		return commentList;
	}

	public void setCommentList(List<CommentEntity> commentList) {
		this.commentList = commentList;
	}

	public List<ViewerVoteDetailEntity> getUserVoteList() {
		return userVoteList;
	}

	public void setUserVoteList(List<ViewerVoteDetailEntity> userVoteList) {
		this.userVoteList = userVoteList;
	}

	public List<UserFavoriteDetailEntity> getUserFavoriteList() {
		return userFavoriteList;
	}

	public void setUserFavoriteList(List<UserFavoriteDetailEntity> userFavoriteList) {
		this.userFavoriteList = userFavoriteList;
	}

	public List<RelatedPartyWorkDetailEntity> getRelatedPartyDetailList() {
		return RelatedPartyDetailList;
	}

	public void setRelatedPartyDetailList(List<RelatedPartyWorkDetailEntity> relatedPartyDetailList) {
		RelatedPartyDetailList = relatedPartyDetailList;
	}
	
	
	
}
