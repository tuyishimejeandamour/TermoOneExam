package rw.ac.rca.termOneExam.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import rw.ac.rca.termOneExam.domain.City;
import rw.ac.rca.termOneExam.dto.CreateCityDTO;
import rw.ac.rca.termOneExam.repository.ICityRepository;

@Service
public class CityService {

	@Autowired
	private ICityRepository cityRepository;
	
	public Optional<City> getById(long id) {
		  Optional<City> city = cityRepository.findById(id);
		      city.ifPresent(city1 -> city1.setFahrenheit(this.calculateFahrenheit(city1.getWeather())));
			  return  city;
	}

	public List<City> getAll() {
		 List<City> allCity = cityRepository.findAll();
		   allCity.forEach(city -> {
			   city.setFahrenheit(this.calculateFahrenheit(city.getWeather()));
		   });
		return allCity;
	}

	public boolean existsByName(String name) {

		return cityRepository.existsByName(name);
	}

	public City save(CreateCityDTO dto) {
		City city =  new City(dto.getName(), dto.getWeather());
		City city1 = cityRepository.save(city);
		 city1.setFahrenheit(this.calculateFahrenheit(city.getWeather()));
		return city1 ;
	}

	private double calculateFahrenheit(double weather){
		return (9/5 * weather) + 32;
	}
	

}
