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

import com.example.SpringBootSecurityJDBC.mapper.CategoryMapper;
import com.example.SpringBootSecurityJDBC.model.Category;


@Repository		//indicates that an annotated class is a repository, which is an abstraction of data access and storage
@Transactional	//giao dich phai duoc thuc hien thanh cong cung nhau,neu 1 hanh dong that bai thi tat ca cac hoat dong se tro ve trang thai ban dau
public class CategoryDao extends JdbcDaoSupport {

	@Autowired 
	DataSource dataSource;
	
	@PostConstruct
	private void initialize(){
		setDataSource(dataSource);
	}
	
//	@Autowired
//	public void CategoryDAO(DataSource dataSource) {
//        this.setDataSource(dataSource);
//    }
	
	//test thoi
	public Category findMenuRole(String menuRole) {
       	
		// Select * from Category c Where c.Role_Name = ?
	   	String sql = CategoryMapper.BASE_SQL + " Where c.Role_Name = ? ";
	
	    Object[] params = new Object[] { menuRole };
	    //arguments to bind to the query
		//may also contain SqlParameterValue objects which indicate not only the argument value
		
	    CategoryMapper mapper = new CategoryMapper();	//a callback that will map one object per row
	    
	    //xem toi uu lai khong xai try / catch
	    	Category categoryInfo = this.getJdbcTemplate().queryForObject(sql, params, mapper);
	        
	        return categoryInfo;
	    
	}
	
	public List<Category> findMenuRoleList(String menuRole) {
       	
		// Select * from Category c Where c.Role_Name = ?
	   	String sql = "Select * From Category c Where c.Role_Name = ? ";
	
	    Object[] params = new Object[] { menuRole };
	    //arguments to bind to the query
		//may also contain SqlParameterValue objects which indicate not only the argument value
		
	    CategoryMapper mapper = new CategoryMapper();	//a callback that will map one object per row
	    
	    //xem toi uu lai khong xai try / catch
	    //test dong code nay
	    List<Category> categoryInfoList_test = this.getJdbcTemplate().query(sql, params, mapper);
	    
	    List<Map<String, Object>> rows = getJdbcTemplate().queryForList(sql, params);
	    List<Category> categoryInfoList = new ArrayList<Category>();
		for(Map<String, Object> row:rows){
			Category emp = new Category();
			emp.setMenuId((Long)row.get("Menu_Id"));
			emp.setMenuName((String)row.get("Menu_Name"));
			emp.setMenuDescription((String)row.get("Menu_Description"));
			emp.setMenuLink((String)row.get("Menu_Link"));
			emp.setRoleName((String)row.get("Role_Name"));
			
			categoryInfoList.add(emp);
		}
		
		return categoryInfoList;
	    
	}

}