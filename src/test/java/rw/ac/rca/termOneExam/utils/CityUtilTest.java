package rw.ac.rca.termOneExam.utils;

import org.junit.Test;
import rw.ac.rca.termOneExam.domain.City;

import java.util.ArrayList;
import java.util.List;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.spy;
import static org.mockito.Mockito.when;

public class CityUtilTest {



    
   	@Test
	public void spying() {

		City citySpy =  spy(City.class);
         
		citySpy.setName("mukamira");
		System.out.println(citySpy.getName());

		citySpy.setWeather(122);
		System.out.println(citySpy.getWeather());//3

		when(citySpy.getName()).thenReturn("mukamira");
		System.out.println(citySpy.getName());


		citySpy.setName("muhanga");
		System.out.println(citySpy.getName());
		
		
	}
}
