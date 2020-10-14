package com.example.SpringBootSecurityJDBC.dao;

import java.sql.ResultSet;
import java.sql.SQLException;

import javax.annotation.PostConstruct;
import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.support.JdbcDaoSupport;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.example.SpringBootSecurityJDBC.model.Project;


@Repository		//indicates that an annotated class is a repository, which is an abstraction of data access and storage
@Transactional
public class ProjectDao extends JdbcDaoSupport {
	
	@Autowired 
	DataSource dataSource;
	
	@PostConstruct
	private void initialize(){
		setDataSource(dataSource);
	}
	
	public Boolean insertProject(Project project) {
		String sql = "INSERT INTO PROJECT " +
				"(ID, TITLE, TYPE, COLOR, PRICE, LAUNCHDATE, TIME_COMPLETED, DAYS, DESCRIPTION, JOB_NUMBER_ES, PERSON_CREATE, CODE_STATUS, MANAGER, MEMBERS) "
				+ "VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)" ;
		getJdbcTemplate().update(sql, new Object[]{ 
				project.getId(), project.getTitle(), project.getType(), project.getColor(), project.getPrice(), project.getLaunchDate(),
				project.getTime_completed(), project.getDays(), project.getDescription(), project.getJob_number_es(),
				project.getPerson_creat(), project.getCode_status(), project.getManager(), project.getMembers()
				
		});
		return true;
	}
	
	
	public Project getProjectInfo(Long id) {
		String sql = "SELECT * FROM PROJECT WHERE ID = ?";
		return (Project)getJdbcTemplate().queryForObject(sql, new Object[]{id}, new RowMapper<Project>(){
			@Override
			public Project mapRow(ResultSet rs, int rowNumber) throws SQLException {	//ProjectMapper class
				Project emp = new Project();
				emp.setId(rs.getLong("ID"));
				emp.setTitle(rs.getString("TITLE"));
				emp.setType(rs.getString("COLOR"));
				emp.setPrice(rs.getDouble("PRICE"));
				emp.setLaunchDate(rs.getDate("LAUNCHDATE"));
				emp.setTime_completed(rs.getString("TIME_COMPLETED"));
				emp.setDays(rs.getInt("DAYS"));
				emp.setDescription(rs.getString("DESCRIPTION"));
				emp.setJob_number_es(rs.getInt("JOB_NUMBER_ES"));
				emp.setPerson_creat(rs.getString("PERSON_CREATE"));
				emp.setCode_status(rs.getString("CODE_STATUS"));
				emp.setManager(rs.getString("MANAGER"));
				emp.setMembers(rs.getInt("MEMBERS"));
				return emp;
			}
		});
	}
	
	public Boolean updateProject_job_number_es_(int job_number_es_more, Long id) {
		String sql = "UPDATE PROJECT SET JOB_NUMBER_ES = ? WHERE ID = ? ";
		getJdbcTemplate().update(sql, new Object[]{job_number_es_more , id});
		
//		getJdbcTemplate().batchUpdate(sql, new BatchPreparedStatementSetter() {
//			
//			public void setValues(PreparedStatement ps, int i) throws SQLException {
//				
//				ps.setInt(1, job_number_es_more);
//				ps.setLong(2, id);
//			}
//
//			@Override
//			public int getBatchSize() {
//				// TODO Auto-generated method stub
//				return 0;
//			}
//			
//			
//		});
		return true;
	}
	
	public String codeStatus (String codeStatus) {
		String codeStatus_project = null;
		String sql = "SELECT STATUS FROM CODE_GROUP WHERE CODE_STATUS = ?";
		codeStatus_project = getJdbcTemplate().queryForObject(sql, new Object[] {codeStatus}, String.class);
		return codeStatus_project;
	}
	

}
