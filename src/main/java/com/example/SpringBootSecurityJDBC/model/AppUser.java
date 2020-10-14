package com.example.SpringBootSecurityJDBC.model;


public class AppUser {	//The AppUser class represents for a record in the  APP_USER table of database.
	
	private Long userId;
    private String userName;
    private String encrytedPassword;
    private boolean enabled;

    public AppUser() {
    	 
    }
 
    public AppUser(Long userId, String userName, String encrytedPassword) {
        this.userId = userId;
        this.userName = userName;
        this.encrytedPassword = encrytedPassword;
    }
    
	public Long getUserId() {
		return userId;
	}

	public void setUserId(Long userId) {
		this.userId = userId;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String getEncrytedPassword() {
		return encrytedPassword;
	}

	public void setEncrytedPassword(String encrytedPassword) {
		this.encrytedPassword = encrytedPassword;
	}

	public boolean isEnabled() {
		return enabled;
	}

	public void setEnabled(boolean enabled) {
		this.enabled = enabled;
	}

	@Override
	public String toString() {
		return "AppUser [userName=" + userName + ", encrytedPassword=" + encrytedPassword + "]";
	}
    
    
}
