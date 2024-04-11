package com.laptrinhweb.dto;

public class ViewerVoteDetailDTO extends AbstractDTO<ViewerVoteDetailDTO>{
	private UserDTO user;
	private String workCode;
	private String content;
	private int score;
	
	public UserDTO getUser() {
		return user;
	}
	public void setUser(UserDTO user) {
		this.user = user;
	}
	
	public String getWorkCode() {
		return workCode;
	}
	public void setWorkCode(String workCode) {
		this.workCode = workCode;
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