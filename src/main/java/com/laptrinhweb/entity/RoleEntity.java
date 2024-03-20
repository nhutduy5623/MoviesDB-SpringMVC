package com.laptrinhweb.entity;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.Table;

@Entity
@Table(name = "role")
public class RoleEntity extends baseEntity {

	@Column(name = "code", unique = true)
	private String code;

	@Column(name = "name")
	private String name;

	@Column(name = "disription", columnDefinition = "TEXT")
	private String disription;

	@ManyToMany(fetch = FetchType.LAZY)
	@JoinTable(name = "role_user", joinColumns = @JoinColumn(name = "roleid"), 
									inverseJoinColumns = @JoinColumn(name = "userid"))
	private List<UserEntity> userList = new ArrayList<>();
	
	@ManyToMany(fetch = FetchType.LAZY)
	@JoinTable(name = "role_permission", joinColumns = @JoinColumn(name = "roleid"), 
									inverseJoinColumns = @JoinColumn(name = "permissionid"))
	private List<PermissionEntity> permissionList  = new ArrayList<>();

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

	public List<UserEntity> getUserList() {
		return userList;
	}

	public void setUserList(List<UserEntity> userList) {
		this.userList = userList;
	}

	public List<PermissionEntity> getPermissionList() {
		return permissionList;
	}

	public void setPermissionList(List<PermissionEntity> permissionList) {
		this.permissionList = permissionList;
	}
	
	

}
