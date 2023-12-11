package com.laptrinhweb.entity;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.Table;

@Entity
@Table(name = "permission")
public class permissionEntity extends baseEntity {
	
	@Column(name = "code")
	private String code;
	
	@Column(name = "name")
	private String name;
	
	@Column(name = "disription", columnDefinition = "TEXT")
	private String disription;
	
	@Column(name = "functionurl", columnDefinition = "TEXT")
	private String functionURL;
	
	@ManyToMany(mappedBy = "permissionList")
	private List<roleEntity> roleList = new ArrayList<>();
}
