package com.example.cdb.controller;

import org.springframework.security.core.Authentication;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
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

import org.springframework.web.bind.annotation.PostMapping;




@Controller
@CrossOrigin
@RequestMapping("/page")
public class LoginController {
	
	@Autowired
	private AuthenticationProvider authenticationProvider;
	
	@GetMapping("/login")
	public String loginPage() {
		return "Login.html";
	}
	
	
	@PostMapping("/login")
	public ResponseEntity<String> postMethodName(@RequestParam String email , @RequestParam String password) {
		
		try {
			
//			System.out.println(email);
//			System.out.println(password);
			
			UsernamePasswordAuthenticationToken authenticationToken = new UsernamePasswordAuthenticationToken(email, password);
			SecurityContextHolder.getContext().setAuthentication(authenticationProvider.authenticate(authenticationToken));
			
			//org.springframework.security.core.Authentication a = authenticationProvider.authenticate(authenticationToken);
			Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
//			
			String name = authentication.getName();
			System.out.println("name "+ name);
			System.out.println(authentication.isAuthenticated());
			System.out.println(authentication.getAuthorities());
			System.out.println(authentication.getCredentials());
			
			
			return ResponseEntity.ok("Login successful"); 
			//return new RedirectView("/api/users");
			
			 
			
		} catch (BadCredentialsException e) {
			System.out.println("BAD CREDEn");
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Invalid email or password");
			
			//return new RedirectView("/page/login?error=true");
        } catch (Exception e) {
        	System.out.println("EXCEPTION");
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("An error occurred");
        	
        	//return new RedirectView("/page/login?error=true");
        }
		
		
	}	
	
}
