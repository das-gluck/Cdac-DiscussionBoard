package com.example.cdb.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import com.example.cdb.service.UserCustomSecurityService;



@Configuration
public class SecurityConfig {

	
	@Autowired
	private UserCustomSecurityService userCustomSecurityService;
	
	@Autowired
	private JWTFilter jwtFilter;
	
	@Bean
	public AuthenticationProvider authenticationProvider() {
		DaoAuthenticationProvider daoAuthenticationProvider = new DaoAuthenticationProvider();
		// this fetch users details from the database on the basis of user enter its email
		daoAuthenticationProvider.setUserDetailsService(userCustomSecurityService);
		
		// it validate the user password (entered in login form) by converting into encoded password
//		daoAuthenticationProvider.setPasswordEncoder(passwordEncoder());
		return daoAuthenticationProvider;
	} 
	
	
	
	@Bean
	public AuthenticationManager authenticationManager(AuthenticationConfiguration authenticationConfiguration) throws Exception {
		return authenticationConfiguration.getAuthenticationManager();
	}
	
	
	
	@Bean
	public PasswordEncoder passwordEncoder() {
		return new BCryptPasswordEncoder(); 
		// return new BCryptPasswordEncoder(12); // 12 indicates the more strong hashing (number should be between 4 to 30 )
	}
	
	@Bean
	public SecurityFilterChain filterChain(HttpSecurity http) throws Exception{
		http.authorizeHttpRequests(configurer -> configurer
//				.requestMatchers(HttpMethod.GET,"/api/users").hasAnyRole("USER","ADMIN")
//				.requestMatchers(HttpMethod.GET,"/api/users/**").hasAnyRole("USER","ADMIN")
				.requestMatchers(HttpMethod.GET,"/api/users/**").permitAll()
				.requestMatchers(HttpMethod.POST,"/api/users").hasAnyRole("ADMIN")
				.requestMatchers(HttpMethod.PUT,"/api/users").hasRole("ADMIN")
				.requestMatchers(HttpMethod.DELETE,"/api/users/**").hasRole("ADMIN")
				
				// Allow authenticated users with USER or ADMIN roles to follow
		        .requestMatchers(HttpMethod.POST, "/api/users/{userId}/follow/{followId}").hasAnyRole("USER", "ADMIN")
		        
		        // Allow authenticated users with USER or ADMIN roles to unfollow
		        .requestMatchers(HttpMethod.POST, "/api/users/{userId}/unfollow/{unfollowId}").hasAnyRole("USER", "ADMIN")
				
				.requestMatchers("/page/login/**").permitAll()
				.requestMatchers("/page/register/**").permitAll()
				.requestMatchers("/api/posts/**").permitAll()
				.requestMatchers("/api/comments/**").permitAll()
				
				)
				.addFilterBefore(jwtFilter, UsernamePasswordAuthenticationFilter.class);
		
		
		http.formLogin(customizer -> customizer.disable()); // this is for browser (it will give form for authentication)   
		http.httpBasic(Customizer.withDefaults()); // this is for postman
		http.csrf(csrf -> csrf.disable());
//		http.cors(Customizer.withDefaults());
		
		
		return http.build();
		
	}
	
	
}
