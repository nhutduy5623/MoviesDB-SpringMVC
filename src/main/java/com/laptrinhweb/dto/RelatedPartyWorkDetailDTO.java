package com.laptrinhweb.dto;

public class RelatedPartyWorkDetailDTO extends AbstractDTO<RelatedPartyWorkDetailDTO>{
	private String workCode;
	private String relatedPartyCode;
	private String role;
	
	public RelatedPartyWorkDetailDTO() {
	}
	public RelatedPartyWorkDetailDTO(String relatedPartyCode, String role) {
		this.relatedPartyCode = relatedPartyCode;
		this.role = role;
	}	
	public RelatedPartyWorkDetailDTO(String workCode, String relatedPartyCode, String role) {
		super();
		this.workCode = workCode;
		this.relatedPartyCode = relatedPartyCode;
		this.role = role;
	}
	public String getWorkCode() {
		return workCode;
	}
	public void setWorkCode(String workCode) {
		this.workCode = workCode;
	}
	public String getRelatedPartyCode() {
		return relatedPartyCode;
	}
	public void setRelatedPartyCode(String relatedPartyCode) {
		this.relatedPartyCode = relatedPartyCode;
	}
	public String getRole() {
		return role;
	}
	public void setRole(String role) {
		this.role = role;
	}
	@Override
	public String toString() {
		return "RelatedPartyWorkDetailDTO [workCode=" + workCode + ", relatedPartyCode=" + relatedPartyCode + ", role="
				+ role + "]";
	}	
	
	
}
