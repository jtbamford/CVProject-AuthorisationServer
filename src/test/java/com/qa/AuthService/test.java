package com.qa.AuthService;

import org.springframework.web.client.RestTemplate;

import com.qa.AuthService.constants.Constants;

public class test {
	private RestTemplate restTemplate;
	
	
	public void restTsest() {
	Object[] users= restTemplate.getForObject(Constants.HOST+Constants.PORT+Constants.BASE+Constants.GET_ALL_URL,Object[].class);
	System.out.println(users);
	}
}
