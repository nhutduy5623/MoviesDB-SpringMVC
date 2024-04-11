package com.laptrinhweb.dto;

public class UserFavoriteDetailFULLDTO extends AbstractDTO<UserFavoriteDetailFULLDTO> {
	
	private WorkDTO workDTO;
	private UserDTO userDTO;
	private String viewingStatus;
	private int reviewScore = 0;
	private String note;
	
	public WorkDTO getWorkDTO() {
		return workDTO;
	}
	public void setWorkDTO(WorkDTO workDTO) {
		this.workDTO = workDTO;
	}
	public UserDTO getUserDTO() {
		return userDTO;
	}
	public void setUserDTO(UserDTO userDTO) {
		this.userDTO = userDTO;
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
