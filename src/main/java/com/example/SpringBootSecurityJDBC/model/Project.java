package com.example.SpringBootSecurityJDBC.model;

import java.sql.Date;

public class Project {
	
	    private Long id;
	    private String title;
	    private String type;
	    private String color;
	    private Double price;
	    
	    private Date launchDate;
	    private String time_completed;
	    private Integer days;
	    private String description;
	    private Integer job_number_es;
	    private String person_creat;
	    private String code_status;
	    private String manager;
	    private Integer members;
		
	    public Long getId() {
			return id;
		}
		public void setId(Long id) {
			this.id = id;
		}
		public String getTitle() {
			return title;
		}
		public void setTitle(String title) {
			this.title = title;
		}
		public String getType() {
			return type;
		}
		public void setType(String type) {
			this.type = type;
		}
		public String getColor() {
			return color;
		}
		public void setColor(String color) {
			this.color = color;
		}
		public Double getPrice() {
			return price;
		}
		public void setPrice(Double price) {
			this.price = price;
		}
		public Date getLaunchDate() {
			return launchDate;
		}
		public void setLaunchDate(Date launchDate) {
			this.launchDate = launchDate;
		}
		public Integer getDays() {
			return days;
		}
		public void setDays(Integer days) {
			this.days = days;
		}
		public String getDescription() {
			return description;
		}
		public void setDescription(String description) {
			this.description = description;
		}
		public String getPerson_creat() {
			return person_creat;
		}
		public void setPerson_creat(String person_creat) {
			this.person_creat = person_creat;
		}
		public String getCode_status() {
			return code_status;
		}
		public void setCode_status(String code_status) {
			this.code_status = code_status;
		}
		public String getManager() {
			return manager;
		}
		public void setManager(String manager) {
			this.manager = manager;
		}
		public Integer getMembers() {
			return members;
		}
		public void setMembers(Integer members) {
			this.members = members;
		}
		public String getTime_completed() {
			return time_completed;
		}
		public void setTime_completed(String time_completed) {
			this.time_completed = time_completed;
		}
		public Integer getJob_number_es() {
			return job_number_es;
		}
		public void setJob_number_es(Integer job_number_es) {
			this.job_number_es = job_number_es;
		}
		
		@Override
		public String toString() {
			return "Project [id=" + id + ", title=" + title + ", type=" + type + ", color=" + color + ", price=" + price
					+ ", launchDate=" + launchDate + ", time_completed=" + time_completed + ", days=" + days
					+ ", description=" + description + ", job_number_es=" + job_number_es + ", person_creat="
					+ person_creat + ", code_status=" + code_status + ", manager=" + manager + ", members=" + members
					+ "]";
		}
		
		
}		
		
//	    private Boolean featured;
	    

//	    public Project() {
//	    }
//
//	    public Project(Long id, String title, String type, String color, String description, Integer days, Double price, Boolean featured, LocalDateTime launchDate) {
//	        this.id = id;
//	        this.title = title;
//	        this.type = type;
//	        this.color = color;
//	        this.description = description;
//	        this.days = days;
//	        this.price = price;
//	        this.featured = featured;
//	        this.launchDate = launchDate;
//	    }

	    
