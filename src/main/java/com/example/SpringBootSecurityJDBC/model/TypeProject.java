package com.example.SpringBootSecurityJDBC.model;

public class TypeProject {
	
	private String code_project;
	private String type;
	
	public String getCode_project() {
		return code_project;
	}
	public void setCode_project(String code_project) {
		this.code_project = code_project;
	}
	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
	}
	@Override
	public String toString() {
		return "TypeProject [code_project=" + code_project + ", type=" + type + "]";
	}
	
	
}
