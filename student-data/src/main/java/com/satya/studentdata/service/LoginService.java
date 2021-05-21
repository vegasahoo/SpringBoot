package com.satya.studentdata.service;

import org.springframework.stereotype.Service;

@Service
public class LoginService {
	
	public boolean authenticate(String userName, String password) {
		if(userName.equals("vegasahoo") && password.equals("vega@123")) {
			return true;
		}
		return false;
	}

}
