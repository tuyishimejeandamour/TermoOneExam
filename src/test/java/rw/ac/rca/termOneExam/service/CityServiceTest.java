package rw.ac.rca.termOneExam.service;

public class CityServiceTest {

@Mock
	private CityRepository cityRepositoryMock;
	
	@InjectMocks
	private CityService cityService;

    @Test
		public void getAll() {

		when(itemRepositoryMock.findAll()).thenReturn(Arrays.asList(new City(101,"musanze",15),
				new City(2,"nyabihu",13)));
		assertEquals(15,cityService.getAll().get(0).getWeather());
	}
    @Test
	public void getById() {

		when(itemRepositoryMock.getById(101)).thenReturn(new City(101,"musanze",15));
		assertEquals("musanze",cityService.getAll().get(0).getName());
	}
	
	@Test
	public void save() {
		CreateCityDTO dto = new CreateCityDTO(101,"kirehe", 27);
		City city = new City(101,"kirehe",27,);
		when(itemRepositoryMock.existsByName(dto.getName())).thenReturn(true);
		when(itemRepositoryMock.save(item)).thenReturn(city);
		
		City insertdata = cityService.save(dto);
		assertEquals("kirehe",insertdata.getName());
		
	}
	
	
}
