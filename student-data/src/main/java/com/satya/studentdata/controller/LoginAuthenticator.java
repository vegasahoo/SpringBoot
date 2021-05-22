package com.satya.studentdata.controller;


import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import com.satya.studentdata.service.LoginService;


@RestController
public class LoginAuthenticator {
	
	
	private final LoginService ls =new LoginService();
	
	
	@GetMapping("/authenticate/{userName}/{password}")
	public boolean authenticate(@PathVariable String userName, @PathVariable String password){
		return ls.authenticate(userName, password);
	}

}
