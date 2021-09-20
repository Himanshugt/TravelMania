package Travel;

import org.junit.Before;
import org.junit.Test;
import org.mockito.Mockito;

public class PrintItineraryTest {
	
	private PrintItinerary printItinerary; //Instance of PrintItinerary declared
	
	@Before //runs before the test
	public void init() 
	{
		printItinerary = new PrintItinerary(); //object initialized
	}

	@Test
	public void testGenerateItinerary() throws Exception
	{	
		printItinerary = Mockito.spy(PrintItinerary.class);                   //using spy to check *real* method
		Mockito.doCallRealMethod().when(printItinerary).generateItinerary();
		printItinerary.generateItinerary();                                   //method called
		Mockito.verify(printItinerary,Mockito.times(1)).generateItinerary();  //verification
	}

}
