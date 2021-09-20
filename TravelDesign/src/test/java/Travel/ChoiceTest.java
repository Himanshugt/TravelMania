package Travel;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

public class ChoiceTest {
	
	private Choice choice; //Instance of Choice is declared
	
	@Before //runs before the test
	public void init() 
	{
		choice = new Choice(); //object initialized
	}
	

	@Test
	public void testInitializeMap() throws Exception
	{
		choice.initializeMap();             //method called
		assertNotNull(choice.map);          //verification
	}


}
