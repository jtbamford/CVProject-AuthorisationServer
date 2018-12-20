package com.qa.AuthService.repository.domain;

public class Users {
	
		private Boolean enabled;
	    private String username, password;
	    private String role;
	    	
	    public Users( ) {		
	    }
	    
		public Users(Boolean enabled, String username, String password, String role) {
	    	this.enabled = enabled;
	    	this.username = username;
	   		this.password = password;
	   		this.role = role;
	   	}

		
		public Boolean getEnabled() {
			return enabled;
		}

		public void setEnabled(Boolean enabled) {
			this.enabled = enabled;
		}


		public String getUsername() {
			return username;
		}

		public void setUsername(String username) {
			this.username = username;
		}

		public String getPassword() {
			return password;
		}

		public void setPassword(String password) {
			this.password = password;
		}

		public String getRole() {
			return role;
		}

		public void setRole(String role) {
			this.role = role;
		}

}
