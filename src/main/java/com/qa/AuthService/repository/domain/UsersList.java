package com.qa.AuthService.repository.domain;

import java.util.ArrayList;
import java.util.List;

public class UsersList {

	private List<Users> allUsers;
		
	public List<Users> getAllUsers() {
			return allUsers;
	}

	public void setAllUsers(List<Users> allUsers) {
			this.allUsers = allUsers;
	}
		
	public void UsersList() {			
	}

	public UsersList() {
		allUsers = new ArrayList<>(); 
	}
		
}
