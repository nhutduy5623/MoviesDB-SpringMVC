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
@Table(name = "relatedparty")
public class RelatedPartyEntity extends baseEntity{
	
	@Column(name = "code", unique = true)
	private String code;
	
	@Column(name = "name")
	private String name;
	
	@Column(name = "avatar", length = 510)
	private String avatar;
	
	@Column(name = "followercount")
	private Long followerCount;
	
	@Column(name = "overview", length = 510)
	private String overview;
	
	@Column(name = "website")
	private String website;
	
	@Column(name = "status")
	private int status;
	
	@OneToMany(mappedBy = "relatedParty")
	private List<RelatedPartyWorkDetailEntity> workRelatedDetailList = new ArrayList<>();
	
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "rproleid")
	private RelatedPartyRoleEntity RProle;
	
	@ManyToMany(fetch = FetchType.LAZY)
	@JoinTable(name = "relatedparty_work", joinColumns = @JoinColumn(name = "relatedpartyid"), 
	inverseJoinColumns = @JoinColumn(name = "workid"))
	private List<WorkEntity> workList = new ArrayList<>();

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getAvatar() {
		return avatar;
	}

	public void setAvatar(String avatar) {
		this.avatar = avatar;
	}

	public Long getFollowerCount() {
		return followerCount;
	}

	public void setFollowerCount(Long followerCount) {
		this.followerCount = followerCount;
	}

	public String getOverview() {
		return overview;
	}

	public void setOverview(String overview) {
		this.overview = overview;
	}

	public String getWebsite() {
		return website;
	}

	public void setWebsite(String website) {
		this.website = website;
	}

	public int getStatus() {
		return status;
	}

	public void setStatus(int status) {
		this.status = status;
	}

	public RelatedPartyRoleEntity getRProle() {
		return RProle;
	}

	public void setRProle(RelatedPartyRoleEntity rProle) {
		RProle = rProle;
	}

	public List<WorkEntity> getWorkList() {
		return workList;
	}

	public void setWorkList(List<WorkEntity> workList) {
		this.workList = workList;
	}

	public List<RelatedPartyWorkDetailEntity> getWorkRelatedDetailList() {
		return workRelatedDetailList;
	}

	public void setWorkRelatedDetailList(List<RelatedPartyWorkDetailEntity> workRelatedDetailList) {
		this.workRelatedDetailList = workRelatedDetailList;
	}

	@Override
	public String toString() {
		return "RelatedPartyEntity [code=" + code + ", name=" + name + "]";
	}
	
	
	
	
	
}
