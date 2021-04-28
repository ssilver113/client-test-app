package com.client.test.model;

import javax.persistence.Id;
import javax.validation.constraints.NotBlank;
import java.io.Serializable;

public class Country implements Serializable {
	@Id
	private Long id;

	@NotBlank
	private String name;

	public Country() {
	}

	public Country(Long id, @NotBlank String name) {
		this.id = id;
		this.name = name;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}
}
