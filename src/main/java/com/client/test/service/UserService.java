package com.client.test.service;

import com.client.test.model.User;
import com.client.test.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserService {

	@Autowired
	private UserRepository userRepository;

	public User getUserByUsername(String username) {
		return userRepository.getUserByUsername((username));
	}
}
