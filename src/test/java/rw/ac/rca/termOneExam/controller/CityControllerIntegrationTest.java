package rw.ac.rca.termOneExam.controller;

import static org.junit.Assert.assertTrue;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;

import org.json.JSONException;
import org.junit.Test;
import org.skyscreamer.jsonassert.JSONAssert;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.http.ResponseEntity;

import rw.ac.rca.termOneExam.domain.City;
import rw.ac.rca.termOneExam.utils.APICustomResponse;


public class CityControllerIntegrationTest {
	
	@Autowired
	private TestRestTemplate restTemplate;
		
	@Test
	public void getAll_success() throws JSONException {
		String response = this.restTemplate.getForObject("/api/cities/all", String.class);
		
		//JSONAssert.assertEquals("[]", response, false);
		JSONAssert.assertEquals("[{id:101},{id:102},{id:103},{id:104}]", response, false);
	}
	
	@Test
	public void getById_successObject() {
		City city = this.restTemplate.getForObject("/api/cities/id/101", City.class);
		
		assertEquals("Item1", city.getName());
		assertEquals(1000, city.getWeather());
	}
	@Test
	public void getById_success() {
		ResponseEntity<City> city = this.restTemplate.getForEntity("/api/cities/id/101", City.class);
		
		assertTrue(city.getStatusCode().is2xxSuccessful());
		assertEquals("Item1", city.getBody().getName());
		assertEquals(1000, city.getBody().getWeather());
	}
	
	@Test
	public void getById_404() {
		ResponseEntity<APICustomResponse> item = 
				this.restTemplate.getForEntity("/api/cities/id/101", APICustomResponse.class);
	
		assertTrue(item.getStatusCodeValue()==404);
		assertFalse(item.getBody().isStatus());
		assertEquals("Item not found", item.getBody().getMessage());
	}
	

}
