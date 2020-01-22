package com.spring5.practice.service;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Stream;

import org.springframework.stereotype.Service;

import com.spring5.practice.exceptions.ResourceAlreadyExistsException;
import com.spring5.practice.exceptions.ResourceNotFoundException;
import com.spring5.practice.model.Country;

@Service
public class CountryService {

	private static List<Country> countries = new ArrayList<Country>();

	private static final String[] COUNTRIES = { "BANGLADESH", "USA", "JAPAN", "NEPAL", "IRELAND", "GERMAN", "CANADA" };

	public CountryService() {
		// TODO Auto-generated constructor stub
//		final AtomicInteger atomicId = new AtomicInteger(0);
//		final AtomicReference<Integer> atomicId = new AtomicReference<Integer>();
//		atomicId.set(0);

//		for (var country : COUNTRIES) {
//			var countryObj = new Country();
//			countryObj.setCountryName(country);
//			countryObj.setCountryCode(country.substring(0, 2));
//			countries.add(countryObj);
//		}

		Stream.of(COUNTRIES).forEach(country -> {
			addCountry(country);
		});
	}

	private void addCountry(String countryName) {
		var countryObj = new Country();
		countryObj.setId(countries.size() + 1);
		countryObj.setCountryName(countryName);
		countryObj.setCountryCode(countryName.substring(0, 3));
		countries.add(countryObj);
	}

	public void addCountry(Country country) {
		checkCountryInList(country);
		country.setId(countries.size() + 1);
		countries.add(country);
	}

	public void checkCountryInList(Country c) {
		if (countries.stream().filter(country -> country.getCountryCode().equals(c.getCountryCode())).findAny()
				.isPresent()) {
			throw new ResourceAlreadyExistsException("Country already exists in list");
		}
	}

	public Country getCountryByCode(String countryCode) {

//		for (var country : countries) {
//			if (country.getCountryCode().equals(countryCode)) {
//				return country;
//			}
//		}

		return countries.stream().filter(country -> country.getCountryCode().equals(countryCode)).findAny()
				.orElseThrow(() -> new ResourceNotFoundException("Country not found with this code"));

	}

	public List<Country> getAll() {
		return countries;
	}
}
