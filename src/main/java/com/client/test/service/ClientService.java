package com.client.test.service;

import com.client.test.model.Client;
import com.client.test.repository.ClientRepository;
import org.h2.security.auth.AuthenticationException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;

@Service
public class ClientService {

	@Autowired
	private ClientRepository clientRepository;

	@Autowired
	private PermissionService permissionService;

	public void addClient(Client client) {
		Long userId = permissionService.getUserId();
		client.setUserId(userId);
		clientRepository.addClient(client);
	}

	public Client getClientById(Long clientId) {
		return clientRepository.getClientById(clientId);
	}

	public List<Client> getClientsForCurrentUser() {
		Long userId = permissionService.getUserId();
		return clientRepository.getClientsByUserId(userId);
	}

	public void editClient(Client client) throws AuthenticationException {
		if (hasAccessToClient(client.getId())) {
			clientRepository.editClient(client);
		} else {
			throw new AuthenticationException("Not allowed to edit user!");
		}
	}

	public boolean hasAccessToClient(Long clientId) {
		Long userId = permissionService.getUserId();
		return Objects.equals(userId, getClientById(clientId).getUserId());
	}
}