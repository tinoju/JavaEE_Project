package com.example.SpringBootSecurityJDBC.mapper;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;

import com.example.SpringBootSecurityJDBC.model.Category;

public class CategoryMapper implements RowMapper<Category>  {
	//The  CategoryMapper class is used for mapping the columns in the  Category table 
	
		public static final String BASE_SQL = "Select TOP 1 * From Category c ";
		
		@Override
		public Category mapRow(ResultSet rs, int rowNum) throws SQLException {
		
			Long menuId = rs.getLong("Menu_Id");
			String menuName = rs.getString("Menu_Name");
			String menuDescription = rs.getString("Menu_Description");
			String menuLink = rs.getString("Menu_Link");
			String roleName = rs.getString("Role_Name");
		
		return new Category(menuId, menuName, menuDescription, menuLink, roleName);
		}
}
