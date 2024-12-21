package com.example.cdb.controller;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.view.RedirectView;

import com.example.cdb.entity.User;
import com.example.cdb.service.UserService;


@Controller
@CrossOrigin
@RequestMapping("/page")
public class RegisterController {
	
	@Autowired
	private UserService userService;
	
	@GetMapping("/register")
	public String registerPage() {
		return "Register.html";
	}
	
	
	@PostMapping("/register")
	public ResponseEntity<String> registerCredential(@ModelAttribute User user) {
		
		try {
			System.out.println(user); 
			userService.insert(user);
			//return new RedirectView("/page/register?success=true");
			return ResponseEntity.ok("Registeration successful"); 
		}catch(Exception e) {
			//return new RedirectView("/page/register?error=true");
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("An error occurred");
		}
		
		//return ResponseEntity.ok("Login successful");
		
	}

}
