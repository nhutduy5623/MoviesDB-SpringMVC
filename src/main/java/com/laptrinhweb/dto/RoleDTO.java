package com.laptrinhweb.dto;

import java.util.ArrayList;
import java.util.List;

public class RoleDTO extends AbstractDTO<RoleDTO> {
	private String code;
	private String name;
	private String disription;
	private List<String> userCodeList = new ArrayList<>();
	private List<String> permissionCodeList = new ArrayList<>();
	
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
	public List<String> getUserCodeList() {
		return userCodeList;
	}
	public void setUserCodeList(List<String> userCodeList) {
		this.userCodeList = userCodeList;
	}
	public List<String> getPermissionCodeList() {
		return permissionCodeList;
	}
	public void setPermissionCodeList(List<String> permissionCodeList) {
		this.permissionCodeList = permissionCodeList;
	}
	
}
