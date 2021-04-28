package com.client.test.repository;

import com.client.test.model.Client;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.support.DataAccessUtils;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;
import org.springframework.util.StringUtils;

import java.sql.Types;
import java.util.List;

@Repository
public class ClientRepository {

	@Autowired
	private JdbcTemplate jdbcTemplate;

	public Client getClientById(Long clientId) {
		String sql = "SELECT * FROM client WHERE id = ?";
		List<Client> results =  jdbcTemplate.query(sql, new Object[]{clientId}, new BeanPropertyRowMapper<>(Client.class));
		return DataAccessUtils.singleResult(results);
	}

	public List<Client> getClientsByUserId(Long userId) {
		String sql = "SELECT * FROM client WHERE user_id = ?";
		return jdbcTemplate.query(sql, new Object[]{userId}, new BeanPropertyRowMapper<>(Client.class));
	}

	/* Note: For proper restful web services, I think it is good practice to return saved object */
	public void addClient(Client client) {
		String sql = "INSERT INTO client (user_id, first_name, last_name, username, email, address, country_id) " +
				"VALUES (?, ?, ?, ?, ?, ?, ?)";

		Object[] clientParams = new Object[] {
				client.getUserId(),
				client.getFirstName(),
				client.getLastName(),
				client.getUsername(),
				StringUtils.isEmpty(client.getEmail()) ? null :  client.getEmail(),
				client.getAddress(),
				client.getCountryId()
		};

		int[] clientTypes = new int[] { Types.INTEGER, Types.VARCHAR, Types.VARCHAR,
				Types.VARCHAR, Types.VARCHAR, Types.VARCHAR, Types.INTEGER};

		jdbcTemplate.update(sql, clientParams, clientTypes);
	}

	public void editClient(Client client) {
		String sql = "UPDATE client SET first_name = ?, last_name = ?, userName = ?, email = ?, address = ?, country_id = ? WHERE id = ?";

		Object[] clientParams = new Object[] {
				client.getFirstName(),
				client.getLastName(),
				client.getUsername(),
				StringUtils.isEmpty(client.getEmail()) ? null :  client.getEmail(),
				client.getAddress(),
				client.getCountryId(),
				client.getId()
		};

		jdbcTemplate.update(sql, clientParams);
	}
}