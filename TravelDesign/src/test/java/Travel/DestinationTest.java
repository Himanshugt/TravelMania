package Travel;
import static org.junit.Assert.*;

import java.io.ByteArrayOutputStream;

import org.junit.Before;
import org.junit.Test;
import org.mockito.Mockito;

public class DestinationTest {
	
	private Destination destination;    //Instance of Destination is declared
	
	@Before  //runs before the test
	public void init() 
	{
		destination = new Destination(Mockito.anyString()); //initialized  with dummy values
	}
	

	@Test
	public void testShow() throws Exception
	{
		final ByteArrayOutputStream outputStreamCaptor = new ByteArrayOutputStream();
		String s = Mockito.anyString();
		destination = new Destination(s);
		destination.show();                             //method called
		assertEquals(s, outputStreamCaptor.toString()); //verification
	}

}
