package app.controllers;

import java.util.ArrayList;
import java.util.List;

public class UserInfo {

	private String username;
	private List<String> roles;
	
	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}
	
	public void addRole(String role) {
		this.roles = (this.roles == null) ? new ArrayList<>() : this.roles;
		this.roles.add(role);
	}
	
	public List<String> getRoles() {
		return roles;
	}
	
	public void setRoles(List<String> roles) {
		this.roles = roles;
	}
	
}
