package com.qa.AuthService.security;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.client.RestTemplate;

import com.qa.AuthService.constants.Constants;

@Service
public class UserDetailsServiceImpl implements UserDetailsService {
	
	public void UserDetailsServiceImpl() {
	};

	@Autowired
	private BCryptPasswordEncoder encoder;
	
	@Autowired
	DataSource dataSource;
	    
//    @Autowired
//    public void configAuthentication(AuthenticationManagerBuilder auth) throws Exception {
//      auth.jdbcAuthentication().dataSource(dataSource).passwordEncoder(encoder)
//     .usersByUsernameQuery("select username,password, enabled from users where username=?")
//     .authoritiesByUsernameQuery("select username, role from users where username=?");
//    } 
	
	@Autowired
	private RestTemplate restTemplate;

	@GetMapping
	public List<Users> getAllUsers() {

		UsersList response= restTemplate.getForObject(Constants.HOST+Constants.PORT+Constants.BASE+Constants.GET_ALL_URL, UsersList.class);
		List<Users> users = response.getAllUsers();
		//ResponseEntity<UsersList> users = restTemplate.getForEntity(Constants.HOST+Constants.PORT+Constants.BASE+Constants.GET_ALL_URL, UsersList.class);
		return users;
	}

	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		
		// hard coding the users. All passwords must be encoded.
	//	final List<Users> users = Arrays.asList(
	//		new Users(false, "omar", encoder.encode("12345"), "TRAINEE"),
	//		new Users(false, "trainer", encoder.encode("12346"), "TRAINER"),
	//		new Users(false, "manager", encoder.encode("12347"), "TRAINING_MANAGER")
	//	);
		

		for(Users Users: getAllUsers()) {
			if(Users.getUsername().equals(username)) {
				
				// Remember that Spring needs roles to be in this format: "ROLE_" + userRole (i.e. "ROLE_ADMIN")
				// So, we need to set it to that format, so we can verify and compare roles (i.e. hasRole("ADMIN")).
				List<GrantedAuthority> grantedAuthorities = AuthorityUtils
		                	.commaSeparatedStringToAuthorityList("ROLE_" + Users.getRole());
				
				// The "User" class is provided by Spring and represents a model class for user to be returned by UserDetailsService
				// And used by auth manager to verify and check user authentication.
				return new User(Users.getUsername(),Users.getPassword(), grantedAuthorities);
			}
		}
		
		// If user not found. Throw this exception.
		throw new UsernameNotFoundException("Username: " + username + " not found");
	}
	
	// A (temporary) class represent the user saved in the database.
	private static class Users {
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
	private static class UsersList {
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

}
	

