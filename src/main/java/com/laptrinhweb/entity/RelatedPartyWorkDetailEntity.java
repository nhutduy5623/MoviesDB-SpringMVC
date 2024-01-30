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
	
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "workid")
	private WorkEntity work;
	
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "relatedpartyid")
	private RelatedPartyEntity relatedParty;	
	
	@Column(name = "role")
	private String role;

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

	
}
