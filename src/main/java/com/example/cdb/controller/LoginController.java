package com.example.cdb.controller;

import org.springframework.security.core.Authentication;

import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;


import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.view.RedirectView;

import com.example.cdb.exceptionHandler.ErrorResponse;
import com.example.cdb.security.JWTService;

import org.springframework.web.bind.annotation.PostMapping;




@Controller
@CrossOrigin
@RequestMapping("/page")
public class LoginController {
	
	@Autowired
	private AuthenticationProvider authenticationProvider;
	
	@Autowired
	private AuthenticationManager authenticationManager;
	
	@Autowired
	private JWTService jwtService;
	
	@GetMapping("/login")
	public String loginPage() {
		return "Login.html";
	}
	
	
	@PostMapping("/login")
	public ResponseEntity<Map<String, Object>> postMethodName(@RequestParam String email , @RequestParam String password) {
		Map<String, Object> response = new HashMap<>();
		try {
			
//			System.out.println(email);
//			System.out.println(password);
			
			UsernamePasswordAuthenticationToken authenticationToken = new UsernamePasswordAuthenticationToken(email, password);
			
//			SecurityContextHolder.getContext().setAuthentication(authenticationManager.authenticate(authenticationToken));                   
//			Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
			
			Authentication authentication = authenticationManager.authenticate(authenticationToken);
			
			
			if(authentication.isAuthenticated()) {
				System.out.println("Entered");
				String token = jwtService.generateToken(authentication.getName());
				System.out.println(token);
				
				String name = authentication.getName();
				System.out.println("name "+ name);
				System.out.println(authentication.isAuthenticated());
				System.out.println(authentication.getAuthorities());
				System.out.println(authentication.getCredentials());
				
				
		        response.put("token", token);
		        response.put("roles", authentication.getAuthorities());

		        return ResponseEntity.ok(response);
				
				//return ResponseEntity.ok("Login successful. Token: " + token + "ROle: "+ authentication.getAuthorities());
			}else {
				 response.put("error", "Invalid email or password");
                return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(response);
            }

			//return ResponseEntity.ok("Login successful");
			//return new RedirectView("/api/users");
				
		} catch (BadCredentialsException e) {
			System.out.println("BAD CREDEn");
			response.put("error", "Invalid email or password");
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(response);
			
			//return new RedirectView("/page/login?error=true");
        } catch (Exception e) {
        	System.out.println("EXCEPTION "+ e.getMessage());
        	response.put("error", "An error occurred");
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(response);
        	
        	//return new RedirectView("/page/login?error=true");
        }
		
		
	}	
	
}
