package com.laptrinhweb.dto;

import java.util.ArrayList;
import java.util.List;

public class PermissionDTO extends AbstractDTO<PermissionDTO>{
	private String code;
	private String name;
	private String disription;
	private String functionURL;
	private List<String> roleCodeList = new ArrayList<>();
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
	public List<String> getRoleCodeList() {
		return roleCodeList;
	}
	public void setRoleCodeList(List<String> roleCodeList) {
		this.roleCodeList = roleCodeList;
	}
	
	
}
