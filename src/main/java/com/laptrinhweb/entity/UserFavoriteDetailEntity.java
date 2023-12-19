package com.laptrinhweb.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name = "userfavoritedetail")
public class UserFavoriteDetailEntity extends baseEntity {

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "workid")
	private WorkEntity work;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "userid")
	private UserEntity user;

	@Column(name = "reviewScore")
	private int reviewScore = 0;
	
	@Column(name = "viewingstatus")
	private String viewingStatus;

	@Column(name = "note", columnDefinition = "TEXT")
	private String note;

}
