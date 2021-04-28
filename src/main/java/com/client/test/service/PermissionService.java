package com.client.test.service;

import com.client.test.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

@Service
public class PermissionService {

	@Autowired
	private UserRepository userRepository;

	/* Spring security automatically clears password after authentication so for now username is enough */
	public Long getUserId() {
		Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
		return userRepository.getUserByUsername(authentication.getName()).getId();
	}
}
