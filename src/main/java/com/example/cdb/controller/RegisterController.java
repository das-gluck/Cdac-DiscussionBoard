package com.example.cdb.controller;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.view.RedirectView;

import com.example.cdb.entity.User;
import com.example.cdb.service.UserService;


@Controller
@RequestMapping("/page")
public class RegisterController {
	
	@Autowired
	private UserService userService;
	
	@GetMapping("/register")
	public String registerPage() {
		return "Register.html";
	}
	
	
	@PostMapping("/register")
	public RedirectView registerCredential(@ModelAttribute User user) {
		
		try {
			System.out.println(user);
			userService.insert(user);
			return new RedirectView("/page/register?success=true");
		}catch(Exception e) {
			return new RedirectView("/page/register?error=true");
		}
		
		//return ResponseEntity.ok("Login successful");
		
	}

}
