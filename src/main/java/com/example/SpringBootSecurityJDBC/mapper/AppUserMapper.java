package com.example.SpringBootSecurityJDBC.mapper;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;

import com.example.SpringBootSecurityJDBC.model.AppUser;

public class AppUserMapper implements RowMapper<AppUser> {	
//The  AppUserMapper class is used for mapping the columns in the  APP_USER table 
	
	public static final String BASE_SQL = "Select TOP 1 u.User_Id, u.User_Name, u.Encryted_Password From App_User u ";

	@Override
	public AppUser mapRow(ResultSet rs, int rowNum) throws SQLException {
	
		Long userId = rs.getLong("User_Id");
		String userName = rs.getString("User_Name");
		String encrytedPassword = rs.getString("Encryted_Password");
	
	return new AppUser(userId, userName, encrytedPassword);
	}
}
