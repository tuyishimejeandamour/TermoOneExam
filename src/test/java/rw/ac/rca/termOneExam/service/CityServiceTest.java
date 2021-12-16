package rw.ac.rca.termOneExam.service;

import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;
import rw.ac.rca.termOneExam.domain.City;
import rw.ac.rca.termOneExam.dto.CreateCityDTO;
import rw.ac.rca.termOneExam.repository.ICityRepository;

import java.util.Arrays;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class CityServiceTest {


    @Mock
	private ICityRepository cityRepositoryMock;
	
	@InjectMocks
	private CityService cityService;

    @Test
		public void getAll() {

		when(cityRepositoryMock.findAll()).thenReturn(Arrays.asList(new City("musanze",15), new City("nyabihu",13)));
		assertEquals(15,cityService.getAll().get(0).getWeather());
	}
    @Test
	public void getById() {

		when(cityRepositoryMock.getById(101L)).thenReturn(new City("musanze",15));
		assertEquals("musanze",cityService.getAll().get(0).getName());
	}
	
	@Test
	public void save() {
		CreateCityDTO dto = new CreateCityDTO("kirehe", 27);
		City city = new City("kirehe",27);
		when(cityRepositoryMock.existsByName(dto.getName())).thenReturn(true);
		when(cityRepositoryMock.save(city)).thenReturn(city);
		
		City insertdata = cityService.save(dto);
		assertEquals("kirehe",insertdata.getName());
		
	}
	
	
}
