package com.example.SpringBootSecurityJDBC.model;

import java.sql.Date;

public class Job {
	
	private Long id;
    private String title;
    private Integer score;
    private Integer member_job;
    private Integer days;
    private String person_create;
    
    private Date launchDate;
    private String person_updated;
    private Date launchDate_updated;
    private String code_status;
    private Boolean home_flag;
    private String time_completed;
	
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
	public Integer getScore() {
		return score;
	}
	public void setScore(Integer score) {
		this.score = score;
	}
	public Integer getMember_job() {
		return member_job;
	}
	public void setMember_job(Integer member_job) {
		this.member_job = member_job;
	}
	public Integer getDays() {
		return days;
	}
	public void setDays(Integer days) {
		this.days = days;
	}
	public String getPerson_create() {
		return person_create;
	}
	public void setPerson_create(String person_create) {
		this.person_create = person_create;
	}
	public Date getLaunchDate() {
		return launchDate;
	}
	public void setLaunchDate(Date launchDate) {
		this.launchDate = launchDate;
	}
	public String getPerson_updated() {
		return person_updated;
	}
	public void setPerson_updated(String person_updated) {
		this.person_updated = person_updated;
	}
	public Date getLaunchDate_updated() {
		return launchDate_updated;
	}
	public void setLaunchDate_updated(Date launchDate_updated) {
		this.launchDate_updated = launchDate_updated;
	}
	public String getCode_status() {
		return code_status;
	}
	public void setCode_status(String code_status) {
		this.code_status = code_status;
	}
	public Boolean getHome_flag() {
		return home_flag;
	}
	public void setHome_flag(Boolean home_flag) {
		this.home_flag = home_flag;
	}
	public String getTime_completed() {
		return time_completed;
	}
	public void setTime_completed(String time_completed) {
		this.time_completed = time_completed;
	}
	@Override
	public String toString() {
		return "Job [id=" + id + ", title=" + title + ", score=" + score + ", member_job=" + member_job + ", days="
				+ days + ", person_create=" + person_create + ", launchDate=" + launchDate + ", person_updated="
				+ person_updated + ", launchDate_updated=" + launchDate_updated + ", code_status=" + code_status
				+ ", home_flag=" + home_flag + ", time_completed=" + time_completed + "]";
	}
    
    
	
}
