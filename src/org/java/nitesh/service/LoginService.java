package org.java.nitesh.service;

import java.util.HashMap;

// dto(data transfer object) is synonymous with model
import org.java.nitesh.dto.User;

public class LoginService {
	
	HashMap<String, String> users = new HashMap<>();
	public LoginService() {
		users.put("jdoe", "John Doe");
		users.put("jausten", "Jane Austen");
	}

	// Here the model is the boolean value
	public boolean authenticate(String id, String password) {
		// Connection to a database can be done here for actual auth
		if (password == null || password.trim() == "")
			return false;
		return true;
	}
	
	// Here the model is User object
	public User getUserDetails(String id) {
		User user = new User();
		user.setUserName(users.get(id));
		user.setUserId(id);
		return user;
	}
}
