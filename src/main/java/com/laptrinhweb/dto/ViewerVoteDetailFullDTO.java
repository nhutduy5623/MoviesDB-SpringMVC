package com.laptrinhweb.dto;

public class ViewerVoteDetailFullDTO extends AbstractDTO<ViewerVoteDetailFullDTO>{
	private UserDTO user;
	private WorkDTO work;
	private String content;
	private int score;
	
	public UserDTO getUser() {
		return user;
	}
	public void setUser(UserDTO user) {
		this.user = user;
	}
	
	public WorkDTO getWork() {
		return work;
	}
	public void setWork(WorkDTO work) {
		this.work = work;
	}
	public String getContent() {
		return content;
	}
	public void setContent(String content) {
		this.content = content;
	}
	public int getScore() {
		return score;
	}
	public void setScore(int score) {
		this.score = score;
	}
	
}