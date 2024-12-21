package com.example.cdb.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

import com.example.cdb.service.UserCustomSecurityService;



@Configuration
public class SecurityConfig {

	
	@Autowired
	private UserCustomSecurityService userCustomSecurityService;
	
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
	public PasswordEncoder passwordEncoder() {
		return new BCryptPasswordEncoder(); 
	}
	
	@Bean
	public SecurityFilterChain filterChain(HttpSecurity http) throws Exception{
		http.authorizeHttpRequests(configurer -> configurer
//				.requestMatchers(HttpMethod.GET,"/api/users").hasAnyRole("USER","ADMIN")
//				.requestMatchers(HttpMethod.GET,"/api/users/**").hasAnyRole("USER","ADMIN")
				.requestMatchers(HttpMethod.GET,"/api/users/**").permitAll()
				.requestMatchers(HttpMethod.POST,"/api/users").hasAnyRole("ADMIN")
				.requestMatchers(HttpMethod.PUT,"/api/users").hasAnyRole("ADMIN")
				.requestMatchers(HttpMethod.DELETE,"/api/users/**").hasRole("ADMIN")
				
				.requestMatchers("/page/login/**").permitAll()
				.requestMatchers("/page/register/**").permitAll()
				);
		
		
		//http.authorizeHttpRequests().requestMatchers("/page").permitAll().anyRequest().authenticated();
		
		http.formLogin().disable();
		
		http.httpBasic(Customizer.withDefaults());
//		http.cors(Customizer.withDefaults());
		http.csrf(csrf -> csrf.disable());
		
		
		return http.build();
		
	}
	
	
}
