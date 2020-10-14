package com.example.SpringBootSecurityJDBC;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.authentication.rememberme.JdbcTokenRepositoryImpl;
import org.springframework.security.web.authentication.rememberme.PersistentTokenRepository;

import com.example.SpringBootSecurityJDBC.service.UserDetailsServiceImpl;


@Configuration
@EnableWebSecurity
class WebSecurityConfig extends WebSecurityConfigurerAdapter {
	
	@Autowired
    private UserDetailsServiceImpl userDetailsService;
 
    @Autowired
    private DataSource dataSource;
 
    @Bean
    public BCryptPasswordEncoder passwordEncoder() {
        BCryptPasswordEncoder bCryptPasswordEncoder = new BCryptPasswordEncoder();
        return bCryptPasswordEncoder;
    }
 
    @Autowired
    public void configureGlobal(AuthenticationManagerBuilder auth) throws Exception {
 
        // Setting Service to find User in the database.
        // And Setting PassswordEncoder 
    	//Allows specifying the PasswordEncoder to use with the DaoAuthenticationProvider. The default is to use plain text.
        auth.userDetailsService(userDetailsService).passwordEncoder(passwordEncoder());
        
    }
 
    @Override
    protected void configure(HttpSecurity http) throws Exception {
 
        http.csrf().disable();
 
        // The pages does not require login
        http.authorizeRequests().antMatchers("/", "/login", "/logout").permitAll();
 
        // /userInfo page requires login as ROLE_USER or ROLE_ADMIN.
        // If no login, it will redirect to /login page.
        http.authorizeRequests().antMatchers("/userInfo").access("hasAnyRole('ROLE_USER', 'ROLE_ADMIN')");
 
        // For ADMIN only.
        http.authorizeRequests().antMatchers("/admin").access("hasRole('ROLE_ADMIN')");
 
        // When the user has logged in as XX.
        // But access a page that requires role YY,
        // AccessDeniedException will be thrown.
        http.authorizeRequests().and().exceptionHandling().accessDeniedPage("/403");
 
        // Config for Login Form
        http.authorizeRequests()
        		// Config for Login Page
        		.and()
        		.formLogin()//
                // Submit URL of login page.
                .loginProcessingUrl("/j_spring_security_check") // Submit URL
                .loginPage("/login")//
                .defaultSuccessUrl("/userAccountInfo")//
                .failureUrl("/login?error=true")//
                .usernameParameter("username")//
                .passwordParameter("password")
                
                // Config for Logout Page
                .and()
                .logout()
                .logoutUrl("/logout")
                .logoutSuccessUrl("/logoutSuccessful")
        		
        		//config Remember-me @Anotation
                .and()
        		.rememberMe()	//In annotation configuration, the default http name for “remember me” check box is “remember-me”.
        		//.key(String key) method sets the key to identify tokens created for remember me authentication.
        		//.rememberMeParameter("remember me") // The name of the “check box”. Defaults to ‘_spring_security_remember_me’.
        		//.rememberMeCookieName(String rememberMeCookieName) method sets the name of cookie which stores the token for remember me authentication. Defaults to ‘remember-me’.
        		.tokenRepository(persistentTokenRepository())
        		.tokenValiditySeconds(1 * 24 * 60 * 60); // 24h - method allows specifying how long (in seconds) a token is valid for.
         
    }
    //In Spring Security, there are two approaches to implement “remember me” – Simple Hash-Based Token and Persistent Token Approach.
    // Token stored in Table (Persistent_Logins) //Table “persistent_logins” will be created to store the login token and series.
    @Bean
    public PersistentTokenRepository persistentTokenRepository() {
        JdbcTokenRepositoryImpl db = new JdbcTokenRepositoryImpl();
        db.setDataSource(this.dataSource);
        return db;
    }
    
     //Token stored in Memory (Of Web Server).
//    @Bean
//    public PersistentTokenRepository persistentTokenRepository() {
//        InMemoryTokenRepositoryImpl memory = new InMemoryTokenRepositoryImpl();
//        return memory;
//    }
    
    
    //<!-- If request parameter "targetUrl" is existed, then forward to this url -->
    //<!-- For update login form -->
//    @Bean
//	public SavedRequestAwareAuthenticationSuccessHandler savedRequestAwareAuthenticationSuccessHandler() {
//    	SavedRequestAwareAuthenticationSuccessHandler auth = new SavedRequestAwareAuthenticationSuccessHandler();
//		auth.setTargetUrlParameter("targetUrl");
//		return auth;
//	}
}

/*
 * What is "Remember Me" option?
An user accesses a website and logs in. Then he/she turns off the browser and accesses the website at some time (for example, on the next day), and he/she has to log in again, which causes unnecessary trouble. The " Remember Me" option allows the website to " remember" the user's information to automatically log in when the user visits the website the next time.
When the user logs in an application with the " Remember Me" option, the Spring will save the last login information, and  token. The Token is an encrypted string that contains necessary information  for the  Spring to automatically log in when the user visits the website next time.

There are two common ways for the Spring to save this information: 
1.Memory
2.Database
 * 
 * */
 