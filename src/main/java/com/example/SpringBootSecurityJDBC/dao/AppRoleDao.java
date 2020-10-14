package com.example.SpringBootSecurityJDBC.dao;

import java.util.List;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.support.JdbcDaoSupport;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;


@Repository
@Transactional
public class AppRoleDao extends JdbcDaoSupport {
	
	    @Autowired
	    public void AppRoleDAO(DataSource dataSource) {
	        this.setDataSource(dataSource);
	    }
	 
	    public List<String> getRoleNames(Long userId) {
	    	String sql = "Select r.Role_Name " //
	                + " from User_Role ur, App_Role r " //
	                + " where ur.Role_Id = r.Role_Id and ur.User_Id = ? ";
	 
	        Object[] params = new Object[] { userId };
	 
	        List<String> roles = this.getJdbcTemplate().queryForList(sql, params, String.class);
	 
	        return roles;
	    }
}


//SELECT R1.ROLE_NAME
//FROM APP_ROLE R1, APP_USER U, USER_ROLE R2
//WHERE
//R1.ROLE_ID = R2.ROLE_ID
//AND R2.USER_ID = U.USER_ID
//AND R2.USER_ID = '1'
