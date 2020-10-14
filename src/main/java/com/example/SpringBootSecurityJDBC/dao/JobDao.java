package com.example.SpringBootSecurityJDBC.dao;

import java.sql.Date;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.annotation.PostConstruct;
import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.support.JdbcDaoSupport;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.example.SpringBootSecurityJDBC.model.Job;


@Repository		//indicates that an annotated class is a repository, which is an abstraction of data access and storage
@Transactional
public class JobDao extends JdbcDaoSupport {
	
	@Autowired 
	DataSource dataSource;
	
	@PostConstruct
	private void initialize(){
		setDataSource(dataSource);
	}
	
	public Boolean insertJob(Job job) {
		String sql = "INSERT INTO JOB " +
				"(ID, TITLE, SCORE, MEMBER_JOB, DAYS,PERSON_CREATE, LAUNCHDATE, PERSON_UPDATED, LAUNCHDATE_UPDATED, CODE_STATUS, TIME_COMPLETED, HOME_FLAG) "
				+ "VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)" ;
		getJdbcTemplate().update(sql, new Object[]{ 
				job.getId(), job.getTitle(), job.getScore(), job.getMember_job(), job.getDays(), job.getPerson_create(),
				job.getLaunchDate(), job.getPerson_updated(), job.getLaunchDate_updated(), job.getCode_status(),
				job.getTime_completed(), job.getHome_flag()
				
		});
		return true;
	}
	
	public Job getJobInfo(Long id) {
		String sql = "SELECT * FROM JOB WHERE ID = ?";
		return (Job)getJdbcTemplate().queryForObject(sql, new Object[]{id}, new RowMapper<Job>(){
			@Override
			public Job mapRow(ResultSet rs, int rowNumber) throws SQLException {	//JobMapper class
				Job emp = new Job();
				emp.setId(rs.getLong("ID"));
				emp.setTitle(rs.getString("TITLE"));
				emp.setScore(rs.getInt("SCORE"));
				emp.setMember_job(rs.getInt("MEMBER_JOB"));
				emp.setDays(rs.getInt("DAYS"));
				emp.setPerson_create(rs.getString("PERSON_CREATE"));
				emp.setLaunchDate(rs.getDate("LAUNCHDATE"));
				emp.setCode_status(rs.getString("CODE_STATUS"));
				emp.setTime_completed(rs.getString("TIME_COMPLETED"));
				emp.setHome_flag(rs.getBoolean("HOME_FLAG"));
				
				return emp;
			}
		});
	}
	
	public List<Job> getAllJobs(){
		String sql = "SELECT * FROM JOB ORDER BY ID ASC";
		
		List<Map<String, Object>> rows = getJdbcTemplate().queryForList(sql);
		
		List<Job> result = new ArrayList<Job>();
		for(Map<String, Object> row:rows){
			Job emp = new Job();
			
			emp.setId((Long)row.get("ID"));	//column name /OBJECT KEY
			emp.setTitle((String)row.get("TITLE"));
			emp.setScore((Integer)row.get("SCORE"));
			emp.setMember_job((Integer)row.get("MEMBER_JOB"));
			emp.setDays((Integer)row.get("DAYS"));
			emp.setPerson_create((String)row.get("PERSON_CREATE"));
			emp.setLaunchDate((Date)row.get("LAUNCHDATE"));
			emp.setPerson_updated((String)row.get("PERSON_UPDATED"));
			emp.setLaunchDate_updated((Date)row.get("LAUNCHDATE_UPDATED"));
			emp.setCode_status((String)row.get("CODE_STATUS"));
			emp.setTime_completed((String)row.get("TIME_COMPLETED"));
			emp.setHome_flag((Boolean)row.get("HOME_FLAG"));
			
			result.add(emp);
		}
		
		return result;
	}
	
	public String codeStatus (String codeStatus) {

		String sql = "SELECT STATUS FROM CODE_GROUP WHERE CODE_STATUS = ?";
		String codeStatus_job = getJdbcTemplate().queryForObject(sql, new Object[] {codeStatus}, String.class);
		    
		return codeStatus_job;
	}
	
	public List<String> codeStatusList (String codeStatus) {		//not use

		String sql = "SELECT STATUS FROM CODE_GROUP WHERE CODE_STATUS = ?";
		List<String> codeStatus_jobs = getJdbcTemplate().queryForList(sql, new Object[] {codeStatus}, String.class);
		    
		return codeStatus_jobs;
	}
}
