package com.laptrinhweb.entity;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity
@Table(name = "permission")
public class PermissionEntity extends baseEntity {
	
	@Column(name = "code", unique = true)
	private String code;
	
	@Column(name = "name")
	private String name;
	
	@Column(name = "disription", columnDefinition = "TEXT")
	private String disription;
	
	@Column(name = "functionurl", columnDefinition = "TEXT")
	private String functionURL;
	
	@ManyToMany(mappedBy = "permissionList")
	private List<RoleEntity> roleList = new ArrayList<>();
	
	@OneToMany(mappedBy = "permission")
	private List<SubPermissionEntity> subPermissionList = new ArrayList<>();

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

	public String getDisription() {
		return disription;
	}

	public void setDisription(String disription) {
		this.disription = disription;
	}

	public String getFunctionURL() {
		return functionURL;
	}

	public void setFunctionURL(String functionURL) {
		this.functionURL = functionURL;
	}

	public List<RoleEntity> getRoleList() {
		return roleList;
	}

	public void setRoleList(List<RoleEntity> roleList) {
		this.roleList = roleList;
	}

	public List<SubPermissionEntity> getSubPermissionList() {
		return subPermissionList;
	}

	public void setSubPermissionList(List<SubPermissionEntity> subPermissionList) {
		this.subPermissionList = subPermissionList;
	}
	
	
	
	
}
