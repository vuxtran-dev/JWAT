package com.example.demo.user;

public class ResponseUser {
	private String token;
	private String username;
    private RoleUser role;



	public ResponseUser(String username, RoleUser role, String token) {
        this.username = username;
        this.role = role;
        this.token = token;
        		
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

    public RoleUser getRole() {
		return role;
	}

	public void setRole(RoleUser role) {
		this.role = role;
	}

	public String getToken() {
		return token;
	}

	public void setToken(String token) {
		this.token = token;
	}
}
