package com.example.SpringBootSecurityJDBC.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.example.SpringBootSecurityJDBC.dao.AppRoleDao;
import com.example.SpringBootSecurityJDBC.dao.AppUserDao;
import com.example.SpringBootSecurityJDBC.model.AppUser;


/**
 * UserDetailsService means a central interface in Spring Security. 
 * It is a service to search "User account and such user's roles". 
 * It is used by the  Spring Security everytime when users log in the system. 
 * Therefore, you need to write a class to implement this interface.
 * Herein, I create the  UserDetailsServiceImpl class which implements the UserDetailsService interface. 
 * The  UserDetailsServiceImpl class is annotated by @Service to tell the  Spring "let's manage it as a Spring BEAN". 
 * **/
@Service
public class UserDetailsServiceImpl implements UserDetailsService {
 
    @Autowired
    private AppUserDao appUserDAO;
 
    @Autowired
    private AppRoleDao appRoleDAO;
    
   
    @Override
    @Transactional
    public UserDetails loadUserByUsername(String userName) throws UsernameNotFoundException {
        
    	//Search User Account in database from "username"(login form)
    	AppUser appUser = this.appUserDAO.findUserAccount(userName);
 
        if (appUser == null) {
            System.out.println("User not found! " + userName);
            throw new UsernameNotFoundException("User " + userName + " was not found in the database");
        }
        
        System.out.println("Found User: " + appUser);		//appUser.getUserName() appUser.getEncrytedPassword()
 
        //test
        
        
        //[ROLE_USER, ROLE_ADMIN,..]getRoleNames
        List<String> roleNames = this.appRoleDAO.getRoleNames(appUser.getUserId());
        
        
        
        //Sau khi login =>Kiem tra data Phan quyen tu database va so sanh voi Web config security de hien thi Web theo phan quyen
        List<GrantedAuthority> grantList = new ArrayList<GrantedAuthority>();
        if (roleNames != null) {
            for (String role : roleNames) {
                // ROLE_USER, ROLE_ADMIN,..
                GrantedAuthority authority = new SimpleGrantedAuthority(role);
                grantList.add(authority);
            }
        }
        //So sanh username, password, authority voi Web config => match ok
        UserDetails userDetails = 
        		(UserDetails) new User(appUser.getUserName(),appUser.getEncrytedPassword(), grantList);
        		
        //test
        //UserDetails userDetails1 = (UserDetails) new User(userName, userName, grantList)
        return userDetails;
    }
    
  
 
}
