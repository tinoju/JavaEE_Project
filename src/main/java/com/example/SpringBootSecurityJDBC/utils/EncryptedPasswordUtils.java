package com.example.SpringBootSecurityJDBC.utils;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

public class EncryptedPasswordUtils {
	
	// Encryte Password with BCryptPasswordEncoder
    public static String encrytePassword(String password) {
        BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
        return encoder.encode(password);
    }
 
    public static void main(String[] args) {
        String password_user = "123";
        String encrytedPassword_user = encrytePassword(password_user);
 
        System.out.println("Encryted Password User: " + encrytedPassword_user);
        
        String password_admin = "111";
        String encrytedPassword_admin = encrytePassword(password_admin);
 
        System.out.println("Encryted Password Admin: " + encrytedPassword_admin);
    }
}
