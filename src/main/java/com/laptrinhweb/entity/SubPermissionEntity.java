package com.laptrinhweb.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name = "subpermission")
public class SubPermissionEntity extends baseEntity {

	@Column(name = "code", unique = true)
	private String code;

	@Column(name = "name")
	private String name;

	@Column(name = "disription", columnDefinition = "TEXT")
	private String disription;

	@Column(name = "subfunctionurl", columnDefinition = "TEXT")
	private String subfunctionURL;

	@ManyToOne
	@JoinColumn(name = "permissionid")
	private PermissionEntity permission;

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

	public String getSubfunctionURL() {
		return subfunctionURL;
	}

	public void setSubfunctionURL(String subfunctionURL) {
		this.subfunctionURL = subfunctionURL;
	}

	public PermissionEntity getPermission() {
		return permission;
	}

	public void setPermission(PermissionEntity permission) {
		this.permission = permission;
	}

}
