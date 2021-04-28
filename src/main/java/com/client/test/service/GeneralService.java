package com.client.test.service;

import com.client.test.model.Country;
import com.client.test.repository.CountryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class GeneralService {

	@Autowired
	private CountryRepository countryRepository;

	public List<Country> getCountries() {
		return countryRepository.getCountries();
	}
}
