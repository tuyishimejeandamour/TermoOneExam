package rw.ac.rca.termOneExam.utils;

public class CityUtilTest {




@Test
	public void mocking() {

		//mock does not retain original behaviour of the code
		ArrayList<String> arrayListMock =  mock(ArrayList.class);
		System.out.println(arrayListMock.get(0));//null
		System.out.println(arrayListMock.size());//0
		
		arrayListMock.add("Test");
		arrayListMock.add("Test2");
		System.out.println(arrayListMock.size());//0
		
		when(arrayListMock.size()).thenReturn(5);
		System.out.println(arrayListMock.size());//5
		
	}

   	@Test
	public void spying() {

		List<City> citySpy =  spy(City.class);

		arrayListSpy.save();
		System.out.println(arrayListSpy.get(0));//Test0
		System.out.println(arrayListSpy.size());//1
		
		arrayListSpy.add("Test");
		arrayListSpy.add("Test2");
		System.out.println(arrayListSpy.size());//3
		
		when(arrayListSpy.size()).thenReturn(5);
		System.out.println(arrayListSpy.size());//5
		//now call is lost so 5 will be returned no matter what
	
		arrayListSpy.add("Test3");
		System.out.println(arrayListSpy.size());//5
		
		
	}
}
