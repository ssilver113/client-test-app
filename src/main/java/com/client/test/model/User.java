package com.client.test.model;

import javax.persistence.Id;
import javax.validation.constraints.NotBlank;
import java.io.Serializable;

public class User implements Serializable {

	@Id
	private Long id;

	@NotBlank
	private String username;

	@NotBlank
	private String password;

	public User() {
	}

	public User(Long id, String username, String password) {
		this.id = id;
		this.username = username;
		this.password = password;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
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
}
