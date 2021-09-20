package Travel;

import org.junit.Before;
import org.junit.Test;
import org.mockito.Mockito;

public class PassengerTest {
	
	private Passenger passenger; //Instance of Passenger is declared
	
	@Before //runs before every test
	public void init() 
	{
		passenger = new Passenger(); //passenger object is initialized
	}
	

	@Test
	public void testShow() throws Exception
	{
		passenger = Mockito.spy(Passenger.class);            //using spy to check *real* method
		passenger.setStatus(Mockito.anyString());            //sets field of the object upon which the test is to be performed
		Mockito.doCallRealMethod().when(passenger).show();   
		passenger.show();                                    //method called
		Mockito.verify(passenger,Mockito.times(1)).show();	 //verification
	}

	@Test
	public void testEnterNewPassenger() throws Exception
	{
		passenger = Mockito.mock(Passenger.class);                      //using mock instead of spy to avoid database distortion
		Mockito.doNothing().when(passenger).enterNewPassenger(); 
		passenger.enterNewPassenger();                                  //method called
		Mockito.verify(passenger,Mockito.times(1)).enterNewPassenger(); //verification
	}

	@Test
	public void testShowPasseger() throws Exception
	{
		passenger = Mockito.spy(Passenger.class);                       //using spy to check *real* method
		Mockito.doCallRealMethod().when(passenger).showPasseger();      
		passenger.showPasseger();                                       //method called
		Mockito.verify(passenger,Mockito.times(1)).showPasseger();      //verification
	}

}
