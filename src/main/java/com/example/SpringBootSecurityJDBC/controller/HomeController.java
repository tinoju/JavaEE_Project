package com.example.SpringBootSecurityJDBC.controller;

import java.security.Principal;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import com.example.SpringBootSecurityJDBC.dao.CategoryDao;
import com.example.SpringBootSecurityJDBC.dao.JobDao;
import com.example.SpringBootSecurityJDBC.dao.ProjectDao;
import com.example.SpringBootSecurityJDBC.dao.Project_MembersDao;
import com.example.SpringBootSecurityJDBC.dao.TypeProjectDao;
import com.example.SpringBootSecurityJDBC.model.Category;
import com.example.SpringBootSecurityJDBC.model.Job;
import com.example.SpringBootSecurityJDBC.model.Project_Members;
import com.example.SpringBootSecurityJDBC.model.Project;
import com.example.SpringBootSecurityJDBC.model.TypeProject;
import com.example.SpringBootSecurityJDBC.utils.WebUtils;


@Controller
public class HomeController {
	
	@Autowired
	ProjectDao projectDao;
	@Autowired
	TypeProjectDao typeProjectDao;
	@Autowired
	JobDao jobDao;
	@Autowired
	Project_MembersDao project_MembersDao;
	
	long id_project = 0;
	
	@RequestMapping(value = { "/", "/welcome" }, method = RequestMethod.GET)
    public String welcomePage(Model model) {
        model.addAttribute("title", "Welcome");
        model.addAttribute("message", "This is welcome page!");
        return "welcomePage";
    }
 
	
	@RequestMapping(value = "login", method = RequestMethod.GET)
    public ModelAndView loginPage(Model model) {
    	ModelAndView modelAndView = new ModelAndView("loginPage");
    	return modelAndView;
    }
	
	@Autowired
	CategoryDao categoryDao;
	@RequestMapping(value = "/userAccountInfo", method = RequestMethod.GET)
    public String loginSuccessfulPage(Model model, Principal principal,HttpServletRequest request) {
        model.addAttribute("title", "Login Success");
        
        // After user login successfully.
    	// Sau khi user login thanh cong se co principal
        String userName = principal.getName();
        //output: User Name: dbuser1
        
        //lay cac thong tin User Account sau khi login success (.getPrincipal)
        User loginedUser = (User) ((Authentication) principal).getPrincipal();
        
        System.out.println(loginedUser.getAuthorities());
        
        //PA3 LAY THONG TIN ROLE
        //confirm authority of username (after login succeess)
        String menuRole = null;
        
        Collection<GrantedAuthority> authorities = loginedUser.getAuthorities();
        
        for(GrantedAuthority role: authorities) {
        	if (role.getAuthority().contains("ROLE_ADMIN")) {	//uu tien phan quyen cao nhat
        		menuRole = "ROLE_ADMIN";
        		System.out.println("PA3 Role admin");
        		break;
        	} else  
        	if (role.getAuthority().equals("ROLE_USER")) {
        		menuRole = "ROLE_USER";
        		System.out.println("PA3 Role user");
        		break;
        	}
        }
        
        //PA1 LAY THONG TIN ROLE
//        boolean authorized_menu_admin = authorities.contains(new SimpleGrantedAuthority("ROLE_ADMIN"));
//        
//        boolean authorized_menu_user = authorities.contains(new SimpleGrantedAuthority("ROLE_USER"));
        
                
	    
	    //PA2 LAY THONG TIN ROLE, SU DUNG HttpServletRequest request o puplic.  . .
//	    if(request.isUserInRole("ROLE_ADMIN")) {
//	    	System.out.println("test login role_admin");
//	    }
//	    if(request.isUserInRole("ROLE_USER")) {
//	    	System.out.println("test login role_user");
//	    }
	            
	    List<Category> categoryInfoList = categoryDao.findMenuRoleList(menuRole);
        
        model.addAttribute("categoryInfoList", categoryInfoList);
               
        return "loginSuccessfulPage";	//VIEW MENU theo ROLE_NAME = "ROLE_USER" OR "ROLE_ADMIN"
    }
    
    
    @RequestMapping(value = "/userInfo", method = RequestMethod.GET)
    public String userInfo(Model model, Principal principal) {
 
        // After user login successfully.
    	// Sau khi user login thanh cong se co principal
        String userName = principal.getName();
                
        //lay cac thong tin User Account sau khi login success (.getPrincipal)
        User loginedUser = (User) ((Authentication) principal).getPrincipal();
 
        String userInfo = WebUtils.toString(loginedUser);	//noi String, xai cac ham ben duoi => view
        
        model.addAttribute("userInfo", userInfo);
 
        //detail TEST
        System.out.println("test");
        System.out.println(loginedUser.getAuthorities());		//if username=dbadmin1 => [ROLE_ADMIN, ROLE_USER] , if username=dbuser1 =>[ROLE_USER] 
        System.out.println(loginedUser.getUsername());			//if username=dbadmin1 or //if username=dbuser1
        System.out.println(loginedUser.getPassword());			//null
        System.out.println(loginedUser.isAccountNonExpired());	//true	
        System.out.println(loginedUser.isAccountNonLocked());	//true
        System.out.println(loginedUser.isEnabled());			//true
        System.out.println(loginedUser.toString());				//username,password,enable,AccountNonExpired,AccountNonLocked,Granted Authorities
        
        return "userInfoPage";
    }
 
    @RequestMapping(value = "/admin", method = RequestMethod.GET)
    public String adminPage(Model model, Principal principal) {
        
    	// After user login successfully.
    	// Sau khi user login thanh cong se co principal
        User loginedUser = (User) ((Authentication) principal).getPrincipal();
 
        String userInfo = WebUtils.toString(loginedUser);
        model.addAttribute("userInfo", userInfo);
        
   //test MENU VIEW
        //PA3 LAY THONG TIN ROLE
        //confirm authority of username (after login succeess)
        String menuRole = null;
        
        Collection<GrantedAuthority> authorities = loginedUser.getAuthorities();
        
        for(GrantedAuthority role: authorities) {
        	if (role.getAuthority().contains("ROLE_ADMIN")) {	//uu tien phan quyen cao nhat
        		menuRole = "ROLE_ADMIN";
        		System.out.println("PA3 Role admin");
        		break;
        	} else  
        	if (role.getAuthority().equals("ROLE_USER")) {
        		menuRole = "ROLE_USER";
        		System.out.println("PA3 Role user");
        		break;
        	}
        }
        
	      
	    List<Category> categoryInfoList = categoryDao.findMenuRoleList(menuRole);
        
        model.addAttribute("categoryInfoList", categoryInfoList);
         
        return "adminPage";
    }
 
    @RequestMapping(value = "/logoutSuccessful", method = RequestMethod.GET)
    public String logoutSuccessfulPage(Model model) {
        model.addAttribute("title", "Logout");
        return "logoutSuccessfulPage";
    }
    
/**
 * If users log in the system, but access a unauthorized site (not their role), 
 * the system will display the content of the /403 page to inform that the access is denied.
 * **/    
    @RequestMapping(value = "/403", method = RequestMethod.GET)
    public String accessDenied(Model model, Principal principal) {
 
        if (principal != null) {
            User loginedUser = (User) ((Authentication) principal).getPrincipal();
 
            String userInfo = WebUtils.toString(loginedUser);
            
            //test
            System.out.println(userInfo);
            String message = null;
                       
            if(principal.getName().equals("dbuser1")) {
            	
            	message = "Hi " + principal.getName() //
                + "<br> You do not have permission to access this page!";
            	model.addAttribute("message", message);
            } else
            model.addAttribute("message", message);
        }
        
        return "403Page";
    }
    
    @RequestMapping(value = "/creat-project", method = RequestMethod.GET)
    public ModelAndView creatProject(Model model) {
    	
    	ModelAndView modelAndView = new ModelAndView("creatProject","project",new Project());
    	
    	List<TypeProject> typeList = typeProjectDao.getAllType();
        model.addAttribute("typeList", typeList);
    	return modelAndView;
    }
    
String typeProject = null;
    @RequestMapping("/save-project")
    public ModelAndView saveProjectSubmission(@ModelAttribute Project project, Model model) {
    	
    	ModelAndView modelAndView = new ModelAndView("saveProject");
    	
    	//get View Type Project Name
    	List<TypeProject> typeList = typeProjectDao.getAllType();
    	for (int i = 0; i< typeList.size();i++) {
    		if(typeList.get(i).getCode_project().equals(project.getType())) {
    			model.addAttribute("typeProject", typeList.get(i).getType());
    			typeProject = project.getType();
    		}
    	}
    	
    	//get View Project Information
    	boolean insertProject_status = projectDao.insertProject(project);
    	if (insertProject_status = true) {
    		System.out.println("Project insert successfull");
//    		model.addAttribute("insertProject_status", insertProject_status);
    	}
    	
    	
    	Project projectDetail = projectDao.getProjectInfo(project.getId());
        model.addAttribute("projectDetail", projectDetail);
id_project = project.getId();
        //View get Job Number ES
        int numberJobs = projectDetail.getJob_number_es();
        int memberJobs = projectDetail.getMembers();
        model.addAttribute("numberJobs", numberJobs);
        model.addAttribute("memberJobs", memberJobs);
        

        return modelAndView;
    }
    
long job_count = 0;	//view SETUP JOB DONE
    
    @RequestMapping(value = "/setup-jobs", method = RequestMethod.GET)
    public ModelAndView setupJobs(Model model) {
    	ModelAndView modelAndView = new ModelAndView("setupJobs");
    	
    	Project projectDetail = projectDao.getProjectInfo(id_project);
        model.addAttribute("projectDetail", projectDetail);
      
        //View get Job Number ES
        int numberJobs = projectDetail.getJob_number_es();
        int memberJobs = projectDetail.getMembers();
        model.addAttribute("numberJobs", numberJobs);
        model.addAttribute("memberJobs", memberJobs);
        
    	
    	return modelAndView;
    }
    
    @RequestMapping(value = "/create-job", method = RequestMethod.GET)		//creat more job 
    public ModelAndView creatJob(@ModelAttribute Job job, Model model) {
    	ModelAndView modelAndView = new ModelAndView("createJob","job",new Job());
    	//here 1
    	Project projectDetail = projectDao.getProjectInfo(id_project);
    	if(job_count == projectDetail.getJob_number_es()) {
    		ModelAndView modelAndView_more = new ModelAndView("setupJobs");
    		int job_number_es_more = projectDetail.getJob_number_es() + 1;
    		boolean job_number_es_more_update_status = projectDao.updateProject_job_number_es_(job_number_es_more, projectDetail.getId());
    		System.out.println("update jobnumberes_more success: " + job_number_es_more_update_status );
    		//update Job_number_es after more job
    		projectDetail = projectDao.getProjectInfo(id_project);
    		int numberJobs = projectDetail.getJob_number_es();
    		model.addAttribute("numberJobs", numberJobs);
    		return modelAndView_more;
    	} else {
    		return modelAndView;
    	}
     }
    
    
    @RequestMapping(value = "/save-job")
    public ModelAndView setupJobsFinish(@ModelAttribute Job job, Model model) {
    	
    	ModelAndView modelAndView = new ModelAndView("setupJobs");
    	
    	Project projectDetail = projectDao.getProjectInfo(id_project);
        model.addAttribute("projectDetail", projectDetail);
    	if (job_count<=projectDetail.getJob_number_es()) {		//< job es , we could insert new job
    		boolean insertJob_status = jobDao.insertJob(job);	
        	if (insertJob_status == true) {
        		model.addAttribute("insertJob_status", insertJob_status);
        		job_count++;
        		
        	}
    	} 
        
    	//get Job.id for view Setup Job done
    	Job jobInfo = jobDao.getJobInfo(job_count);
    	long job_count_view = jobInfo.getId();					//id of job
    	model.addAttribute("job_count_view", job_count_view);
    	
    	//View get Job Number ES
        int numberJobs = projectDetail.getJob_number_es();
        int memberJobs = projectDetail.getMembers();
        model.addAttribute("numberJobs", numberJobs);
        model.addAttribute("memberJobs", memberJobs);
        	
    	return modelAndView;
    }
    
    @RequestMapping(value="create-job-finish", method = RequestMethod.GET)
    public ModelAndView projectDetail(Model model) {
    	ModelAndView modelAndView = new ModelAndView("projectDetail");
        // get Project information
    	Project projectDetail = projectDao.getProjectInfo(id_project);
        model.addAttribute("projectDetail", projectDetail);
        //get View Type Project Name
    	List<TypeProject> typeList = typeProjectDao.getAllType();
    	for (int i = 0; i< typeList.size();i++) {
    		if(typeList.get(i).getCode_project().equals(typeProject)) {
    			model.addAttribute("typeProject", typeList.get(i).getType());
    		}
    	}
    	//get CodeStatus Project
    	String code_status_project = projectDao.codeStatus(projectDetail.getCode_status());
    	model.addAttribute("code_status_project", code_status_project);
        //get Job information - List Jobs
        List<Job> jobDetailList = jobDao.getAllJobs();
        model.addAttribute("jobDetailList", jobDetailList);
        //get CodeStatus Job List
        List<String> code_status_jobList = new ArrayList<String>();
        for (int i=0;i<jobDetailList.size();i++) {
        	String code_status_job = jobDetailList.get(i).getCode_status();
        	code_status_jobList.add(code_status_job);
        }
        //get View CodeStatus Job list
        List<String> code_status_job_view_list = new ArrayList<String>();
        for(String code_status_job : code_status_jobList) {
        	String code_status = jobDao.codeStatus(code_status_job);
        	code_status_job_view_list.add(code_status);
        }
        model.addAttribute("code_status_job_view_list", code_status_job_view_list);

        return modelAndView;
    }
    
    @RequestMapping(value = "/setup-members", method = RequestMethod.GET)
    public ModelAndView insertMembers(@ModelAttribute Project_Members project_Members, Model model) {
    	ModelAndView modelAndView = new ModelAndView("setup_members","project_Members",new Project_Members());
        // TODO: save project in DB here
    	Project projectDetail = projectDao.getProjectInfo(id_project);
        model.addAttribute("projectDetail", projectDetail);
        
        //View get Job Number ES
        int memberJobs = projectDetail.getMembers();
        model.addAttribute("memberJobs", memberJobs);
    	
    	
        return modelAndView;
    }
    
    @RequestMapping("/save-members")
    public ModelAndView saveMembers(@ModelAttribute Project_Members members, Model model) {
    	ModelAndView modelAndView = new ModelAndView("task_management");

    	System.out.println(members.getUser_id());
    	
    	//split string input for get user_id list
    	String membersList_input = members.getUser_id();
    	
    	String[] membersList = membersList_input.split(",");	//
    	
    	for(String member : membersList) {
    		System.out.println(member);
    	}
    	//create function /insert String[] membersList to Database 
    	boolean insertMemberList_status = project_MembersDao.insertMembersList(id_project, membersList);
    	System.out.println("Insert project_MembersDao successfull: " + insertMemberList_status);
    	model.addAttribute("insertMemberList_status", insertMemberList_status);
    	
        return modelAndView;
    }
    
    @RequestMapping("/process")
    public ModelAndView saveJobSubmission(Model model) {
    	ModelAndView modelAndView = new ModelAndView("task_management");
        // TODO: save project in DB here

        return modelAndView;
    }
    
    
}
