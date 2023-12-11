package com.laptrinhweb.entity;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.Table;

@Entity
@Table(name = "role")
public class roleEntity extends baseEntity {

	@Column(name = "code")
	private String code;

	@Column(name = "name")
	private String name;

	@Column(name = "disription", columnDefinition = "TEXT")
	private String disription;

	@ManyToMany()
	@JoinTable(name = "role_user", joinColumns = @JoinColumn(name = "roleid"), 
									inverseJoinColumns = @JoinColumn(name = "userid"))
	private List<userEntity> userList = new ArrayList<>();
	
	@ManyToMany()
	@JoinTable(name = "role_permission", joinColumns = @JoinColumn(name = "roleid"), 
									inverseJoinColumns = @JoinColumn(name = "permissionid"))
	private List<userEntity> permissionList  = new ArrayList<>();

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

	public List<userEntity> getUserList() {
		return userList;
	}

	public void setUserList(List<userEntity> userList) {
		this.userList = userList;
	}

	public List<userEntity> getPermissionList() {
		return permissionList;
	}

	public void setPermissionList(List<userEntity> permissionList) {
		this.permissionList = permissionList;
	}
	
	

}
