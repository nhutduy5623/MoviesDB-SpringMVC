package com.laptrinhweb.dto;

public class UserFavoriteDetailDTO extends AbstractDTO<UserFavoriteDetailDTO> {
	private String workCode;
	private String userCode;
	private String viewingStatus;
	private int reviewScore = 0;
	private String note;
	
	public String getWorkCode() {
		return workCode;
	}
	public void setWorkCode(String workCode) {
		this.workCode = workCode;
	}
	public String getUserCode() {
		return userCode;
	}
	public void setUserCode(String userCode) {
		this.userCode = userCode;
	}
	public String getViewingStatus() {
		return viewingStatus;
	}
	public void setViewingStatus(String viewingStatus) {
		this.viewingStatus = viewingStatus;
	}
	public int getReviewScore() {
		return reviewScore;
	}
	public void setReviewScore(int reviewScore) {
		this.reviewScore = reviewScore;
	}
	public String getNote() {
		return note;
	}
	public void setNote(String note) {
		this.note = note;
	}	
}
