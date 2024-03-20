package com.laptrinhweb.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name = "relatedparty_workdetail")
public class RelatedPartyWorkDetailEntity extends baseEntity{
	
	@ManyToOne
	@JoinColumn(name = "workid")
	private WorkEntity work;
	
	@ManyToOne
	@JoinColumn(name = "relatedpartyid")
	private RelatedPartyEntity relatedParty;	
	
	@Column(name = "role")
	private String role;

	
	
	public RelatedPartyWorkDetailEntity() {
		super();
	}
	
	

	public RelatedPartyWorkDetailEntity(WorkEntity work, RelatedPartyEntity relatedParty, String role) {
		this.work = work;
		this.relatedParty = relatedParty;
		this.role = role;
	}



	public String getRole() {
		return role;
	}

	public void setRole(String role) {
		this.role = role;
	}

	
	public WorkEntity getWork() {
		return work;
	}

	public void setWork(WorkEntity work) {
		this.work = work;
	}

	public RelatedPartyEntity getRelatedParty() {
		return relatedParty;
	}

	public void setRelatedParty(RelatedPartyEntity relatedParty) {
		this.relatedParty = relatedParty;
	}



	@Override
	public String toString() {
		return "RelatedPartyWorkDetailEntity [work=" + work + ", relatedParty=" + relatedParty + ", role=" + role + "]";
	}
	
	

	
}
