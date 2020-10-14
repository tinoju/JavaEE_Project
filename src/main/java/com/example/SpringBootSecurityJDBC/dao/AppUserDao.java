package com.example.SpringBootSecurityJDBC.dao;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.support.JdbcDaoSupport;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.example.SpringBootSecurityJDBC.mapper.AppUserMapper;
import com.example.SpringBootSecurityJDBC.model.AppUser;



/**
 * 
 * DAO (Data Access Object) classes are ones used to access to a database, 
 * for example, Query, Insert, Update, Delete. 
 * The  DAO classes are usually annotated by @Repository to tell the Spring "let's manage them as Spring BEANs".
 * The  AppUserDAO class is used to manipulate with the APP_USER table. 
 * It has a method for finding an user information in the database corresponding to an username(login).
 * 
 * **/

@Repository		//indicates that an annotated class is a repository, which is an abstraction of data access and storage
@Transactional	//giao dich phai duoc thuc hien thanh cong cung nhau,neu 1 hanh dong that bai thi tat ca cac hoat dong se tro ve trang thai ban dau
public class AppUserDao extends JdbcDaoSupport {

	@Autowired
	public void AppUserDAO(DataSource dataSource) {
        this.setDataSource(dataSource);
    }
	
	public AppUser findUserAccount(String userName) {
           	// Select .. from App_User u Where u.User_Name = ?
        	String sql = AppUserMapper.BASE_SQL + " Where u.User_Name = ? ";
 
        	Object[] params = new Object[] { userName };
        	//arguments to bind to the query
        	//may also contain SqlParameterValue objects which indicate not only the argument value
        	
            AppUserMapper mapper = new AppUserMapper();	//a callback that will map one object per row
            
            AppUser userInfo = this.getJdbcTemplate().queryForObject(sql, params, mapper);
            
            return userInfo;
	}
	
}
