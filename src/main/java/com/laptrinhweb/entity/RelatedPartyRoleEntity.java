package com.laptrinhweb.entity;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity
@Table(name = "relatedpartyrole")
public class RelatedPartyRoleEntity extends baseEntity{
	
	@Column(name = "code")
	private String code;
	
	@Column(name = "name")
	private String name;
	
	@Column(name = "detail", length = 510)
	private String detail;
	
	@OneToMany(mappedBy = "RProle")
	private List<RelatedPartyEntity> relatedParties = new ArrayList<>();

	
	
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getDetail() {
		return detail;
	}

	public void setDetail(String detail) {
		this.detail = detail;
	}

	public List<RelatedPartyEntity> getRelatedParties() {
		return relatedParties;
	}

	public void setRelatedParties(List<RelatedPartyEntity> relatedParties) {
		this.relatedParties = relatedParties;
	}

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}		
	
}
