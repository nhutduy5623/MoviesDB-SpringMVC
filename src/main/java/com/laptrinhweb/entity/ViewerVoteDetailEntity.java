package com.laptrinhweb.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name = "viewervotedetail")
public class ViewerVoteDetailEntity extends baseEntity{
	
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "workid")
	private WorkEntity work;
	
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "userid")
	private UserEntity user;
	
	@Column(name = "score")
	private int score = 0;
	
	@Column(name = "content", columnDefinition = "TEXT")
	private String content;

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

	public int getScore() {
		return score;
	}

	public void setScore(int score) {
		this.score = score;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}
	
	
	
}
