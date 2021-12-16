package rw.ac.rca.termOneExam.controller;

import static org.junit.Assert.assertTrue;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;

import java.util.Optional;

import org.json.JSONException;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.skyscreamer.jsonassert.JSONAssert;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.json.JsonParseException;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;

import org.springframework.test.context.junit4.SpringRunner;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;

import rw.ac.rca.termOneExam.domain.City;
import rw.ac.rca.termOneExam.utils.APICustomResponse;


@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment= SpringBootTest.WebEnvironment.RANDOM_PORT)
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
	
	@Test
	public void post_201() throws Exception {
		City city = new City( "muhanga", 23);


		HttpHeaders headers = new HttpHeaders(null);
		headers.setContentType(MediaType.APPLICATION_JSON);
		HttpEntity<City> request = new HttpEntity<City>(city);

		ResponseEntity<City> responseEntity = this.restTemplate
				.postForEntity("/api/cities/add", request, City.class);

		
		assertEquals(HttpStatus.CREATED, responseEntity.getStatusCode());
		assertEquals("muhanga", responseEntity.getBody().getName());
		

		
	}
	
	@Test
	public void post_400_BadRequest() {

		ResponseEntity<String> result = null;

		City city = new City( "muhanga", 23);


	
		ObjectMapper mapper = new ObjectMapper();
		JsonNode jsonNode = null;
		// Our post consumes JSON format
		HttpHeaders headers = new HttpHeaders();
		headers.setContentType(MediaType.APPLICATION_JSON);

		try {
			String vehicleJSONString = mapper.writeValueAsString(city);

			HttpEntity<String> request = new HttpEntity<String>(vehicleJSONString, headers);
			result = this.restTemplate.postForEntity("/api/cities/add", request, String.class);
			
			jsonNode = mapper.readTree(result.getBody()).get("errorMessage");

		} catch (JsonParseException e) {
			e.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();
		}

		assertEquals(HttpStatus.BAD_REQUEST, result.getStatusCode());
		// Assert the expected error message
		assertTrue(jsonNode.asText().contains(" length is invalid for the declared year"));
	}

}
