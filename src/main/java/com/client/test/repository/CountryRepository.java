package com.client.test.repository;

import com.client.test.model.Country;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class CountryRepository {

	@Autowired
	private JdbcTemplate jdbcTemplate;

	public List<Country> getCountries() {
		String sql = "SELECT * FROM country";
		return jdbcTemplate.query(sql, new BeanPropertyRowMapper<>(Country.class));
	}
}
