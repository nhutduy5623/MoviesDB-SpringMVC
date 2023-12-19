package com.laptrinhweb.entity;

import java.sql.Date;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity
@Table(name = "user")
public class UserEntity extends baseEntity{
	
	@Column(name = "email")
	private String email;
	
	@Column(name = "password")
	private String passWord;

	@Column(name = "fullname")
	private String fullName;	
	
	@Column(name = "status")
	private int status=1;
	
	@Column(name = "createddate")
	private Date createdDate;
	
	@Column(name = "avatar", length = 510)
	private String avatar;	
	
	@OneToMany(mappedBy = "user")
	private List<ViewerVoteDetailEntity> workVoteList = new ArrayList<>(); 
	
	@ManyToMany(mappedBy = "userList")
	private List<RoleEntity> roleList = new ArrayList<>();

	@ManyToMany(mappedBy = "userLikeList")
	private List<CommentEntity> commentList = new ArrayList<>();

	@OneToMany(mappedBy = "user")
	private List<UserFavoriteDetailEntity> workFavoriteList = new ArrayList<>();

	@ManyToMany(mappedBy = "userLikeList")
	private List<CommentEntity> commentlike = new ArrayList<>();
	
	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getPassWord() {
		return passWord;
	}

	public void setPassWord(String passWord) {
		this.passWord = passWord;
	}

	public String getFullName() {
		return fullName;
	}

	public void setFullName(String fullName) {
		this.fullName = fullName;
	}

	public int getStatus() {
		return status;
	}

	public void setStatus(int status) {
		this.status = status;
	}

	public Date getCreatedDate() {
		return createdDate;
	}

	public void setCreatedDate(Date createdDate) {
		this.createdDate = createdDate;
	}

	public String getAvatar() {
		return avatar;
	}

	public void setAvatar(String avatar) {
		this.avatar = avatar;
	}

	public List<ViewerVoteDetailEntity> getWorkVoteList() {
		return workVoteList;
	}

	public void setWorkVoteList(List<ViewerVoteDetailEntity> workVoteList) {
		this.workVoteList = workVoteList;
	}

	public List<RoleEntity> getRoleList() {
		return roleList;
	}

	public void setRoleList(List<RoleEntity> roleList) {
		this.roleList = roleList;
	}

	public List<CommentEntity> getCommentList() {
		return commentList;
	}

	public void setCommentList(List<CommentEntity> commentList) {
		this.commentList = commentList;
	}

	public List<UserFavoriteDetailEntity> getWorkFavoriteList() {
		return workFavoriteList;
	}

	public void setWorkFavoriteList(List<UserFavoriteDetailEntity> workFavoriteList) {
		this.workFavoriteList = workFavoriteList;
	}

	public List<CommentEntity> getCommentlike() {
		return commentlike;
	}

	public void setCommentlike(List<CommentEntity> commentlike) {
		this.commentlike = commentlike;
	}
	
	
	
	
	
}
