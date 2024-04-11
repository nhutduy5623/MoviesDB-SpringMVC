package com.laptrinhweb.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name = "userfavoritedetail")
public class UserFavoriteDetailEntity extends baseEntity {

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "workid")
	private WorkEntity work;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "userid")
	private UserEntity user;

	@Column(name = "reviewScore")
	private int reviewScore = 0;
	
	@Column(name = "viewingstatus")
	private String viewingStatus;

	@Column(name = "note", columnDefinition = "TEXT")
	private String note;

	public WorkEntity getWork() {
		return work;
	}

	public void setWork(WorkEntity work) {
		this.work = work;
	}

	public UserEntity getUser() {
		return user;
	}

	public void setUser(UserEntity user) {
		this.user = user;
	}

	public int getReviewScore() {
		return reviewScore;
	}

	public void setReviewScore(int reviewScore) {
		this.reviewScore = reviewScore;
	}

	public String getViewingStatus() {
		return viewingStatus;
	}

	public void setViewingStatus(String viewingStatus) {
		this.viewingStatus = viewingStatus;
	}

	public String getNote() {
		return note;
	}

	public void setNote(String note) {
		this.note = note;
	}
	
	

}
