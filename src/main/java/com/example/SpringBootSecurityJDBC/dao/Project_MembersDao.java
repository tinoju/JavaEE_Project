package com.example.SpringBootSecurityJDBC.dao;

import javax.annotation.PostConstruct;
import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.support.JdbcDaoSupport;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

@Repository		//indicates that an annotated class is a repository, which is an abstraction of data access and storage
@Transactional
public class Project_MembersDao extends JdbcDaoSupport  {
	
	@Autowired 
	DataSource dataSource;
	
	@PostConstruct
	private void initialize(){
		setDataSource(dataSource);
	}
	
	public boolean insertMembersList (Long id_project,String[] membersList) {
		String sql = "INSERT INTO PROJECT_MEMBERS " +
				"(PROJECT_ID,USER_NAME) "
				+ "VALUES (?,?)" ;
		for(String member : membersList) {
			getJdbcTemplate().update(sql, new Object[] {id_project,member});
		}
		
		
		return true;
	}
	
}
