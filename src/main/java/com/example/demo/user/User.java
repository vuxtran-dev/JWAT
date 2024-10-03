package com.example.demo.user;

import jakarta.persistence.Column;
import com.example.demo.taskuser.TaskUser;

//import java.time.LocalDateTime;
import java.util.List;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import jakarta.persistence.*;

@Entity
@Table(name = "users")
public class User {

	@Id
	@Column(name = "user_id")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	@Column(name = "user_name")
	private String username;
	@Column(name = "email")
	private String email;
	@Column(name = "pass_word")
	private String password;
	@Enumerated(EnumType.STRING)
	private RoleUser role;
//	@Column(name = "created_at", nullable = false, updatable = false)
//	private LocalDateTime createdAt;
	@OneToMany(mappedBy = "user", cascade = CascadeType.ALL)
	private List<TaskUser> taskUsers;
	
    public User() {
    }

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public List<TaskUser> getTaskUsers() {
		return taskUsers;
	}

	public void setTaskUsers(List<TaskUser> taskUsers) {
		this.taskUsers = taskUsers;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}


	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public RoleUser getRole() {
		return role;
	}

	public void setRole(RoleUser role) {
		this.role = role;
	}



//	public LocalDateTime getCreatedAt() {
//		return createdAt;
//	}
//
//	public void setCreatedAt(LocalDateTime createdAt) {
//		this.createdAt = createdAt;
//	}



}
