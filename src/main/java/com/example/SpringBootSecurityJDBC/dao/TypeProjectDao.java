package com.example.SpringBootSecurityJDBC.dao;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.annotation.PostConstruct;
import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.support.JdbcDaoSupport;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.example.SpringBootSecurityJDBC.model.TypeProject;


@Repository		//indicates that an annotated class is a repository, which is an abstraction of data access and storage
@Transactional
public class TypeProjectDao extends JdbcDaoSupport {

	@Autowired 
	DataSource dataSource;
	
	@PostConstruct
	private void initialize(){
		setDataSource(dataSource);
	}
	public List<TypeProject> getAllType() {
		String sql = "SELECT * FROM TYPE_PROJECT";
		List<Map<String, Object>> rows = getJdbcTemplate().queryForList(sql);
		
		List<TypeProject> result = new ArrayList<TypeProject>();
		for(Map<String, Object> row:rows){
			TypeProject type = new TypeProject();
			type.setCode_project((String) row.get("CODE_PROJECT"));
			type.setType((String)row.get("TYPE"));
			result.add(type);
		}
		return result;
	}
}
