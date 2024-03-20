package com.laptrinhweb.entity;

import java.util.ArrayList;
import java.util.List;

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
@Table(name = "comment")
public class CommentEntity extends baseEntity{
	
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "workid")
	private WorkEntity work;
	
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "userid")
	private UserEntity user;
	
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "parentcommentid")
	private CommentEntity parentComment;	

	@OneToMany(mappedBy = "parentComment")
	private List<CommentEntity> commentList;
	
	@Column(name = "likecount")
	private Long likeCount;
	
	@Column(name = "content", columnDefinition = "TEXT")
	private String content;
	
	@ManyToMany(fetch = FetchType.LAZY)
	@JoinTable(name = "commentlikedetail", joinColumns = @JoinColumn(name = "commentid"), 
									inverseJoinColumns = @JoinColumn(name = "userid"))
	private List<UserEntity> userLikeList = new ArrayList<>();
	
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

	public CommentEntity getParentComment() {
		return parentComment;
	}

	public void setParentComment(CommentEntity parentComment) {
		this.parentComment = parentComment;
	}

	public List<CommentEntity> getCommentList() {
		return commentList;
	}

	public void setCommentList(List<CommentEntity> commentList) {
		this.commentList = commentList;
	}

	public Long getLikeCount() {
		return likeCount;
	}

	public void setLikeCount(Long likeCount) {
		this.likeCount = likeCount;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public List<UserEntity> getUserLikeList() {
		return userLikeList;
	}

	public void setUserLikeList(List<UserEntity> userLikeList) {
		this.userLikeList = userLikeList;
	}

	
}
