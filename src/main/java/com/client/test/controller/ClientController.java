package com.client.test.controller;

import com.client.test.model.Client;
import com.client.test.service.ClientService;
import com.client.test.service.GeneralService;
import org.h2.security.auth.AuthenticationException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import javax.validation.Valid;

@Controller
public class ClientController {

	@Autowired
	private ClientService clientService;

	@Autowired
	private GeneralService generalService;

	@GetMapping("/")
	public String getClients(Model model) {
		model.addAttribute("clients", clientService.getClientsForCurrentUser());
		return "index";
	}

	@GetMapping("/add-client")
	public String showAddClientForm(Client client, Model model) {
		model.addAttribute("countries", generalService.getCountries());
		return "add-client";
	}

	@PostMapping("/client")
	public String addClient(@Valid Client client, BindingResult result, Model model) {
		if (result.hasErrors()) {
			model.addAttribute("countries", generalService.getCountries());
			return "add-client";
		}

		clientService.addClient(client);
		model.addAttribute("clients", clientService.getClientsForCurrentUser());
		return "redirect:/";
	}

	@GetMapping("/edit-client/{id}")
	public String showEditClientForm(@PathVariable("id") long id, Model model) {
		Client client = clientService.getClientById(id);

		if (client == null || !clientService.hasAccessToClient(client.getId())) {
			return "redirect:/";
		}

		model.addAttribute("client", client);
		model.addAttribute("countries", generalService.getCountries());
		return "edit-client";
	}

	/* Note: Normally we would use PUT for object updating, but this is a HTML form post (the standard supports only GET and POST) */
	@PostMapping("/client/{id}")
	public String editClient(@PathVariable("id") long id, @Valid Client client, BindingResult result, Model model) throws AuthenticationException {
		if (result.hasErrors()) {
			client.setId(id);
			model.addAttribute("countries", generalService.getCountries());
			return "edit-client";
		}

		clientService.editClient(client);
		model.addAttribute("clients", clientService.getClientsForCurrentUser());
		return "redirect:/";
	}
}
