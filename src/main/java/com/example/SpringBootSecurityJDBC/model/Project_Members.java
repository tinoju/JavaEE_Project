package com.example.SpringBootSecurityJDBC.model;

public class Project_Members {
	
	private String user_id;

	public String getUser_id() {
		return user_id;
	}

	public void setUser_id(String user_id) {
		this.user_id = user_id;
	}

	@Override
	public String toString() {
		return "Project_Members [user_id=" + user_id + "]";
	}
    	
}
