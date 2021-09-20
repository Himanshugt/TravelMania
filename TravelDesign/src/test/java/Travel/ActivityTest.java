package Travel;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;
import org.mockito.Mockito;

public class ActivityTest {

	
	private Activity activity; //Instance of Activity is declared
	
	@Before //runs before every test
	public void init() 
	{
		activity = new Activity("DummyName","DummyDescription",500,5,"DummyDestinationName"); //initialized with dummy values
	}
	
	@Test
	public void testCalculateCostGold() throws Exception
	{
		assertEquals(450, activity.calculateCost("Gold"));     //verification
	}
	
	@Test
	public void testCalculationCostStandard() throws Exception
	{
		assertEquals(500, activity.calculateCost("Standard")); //verification
	}
	
	@Test
	public void testCalculateCostPremium() throws Exception
	{
		assertEquals(0,activity.calculateCost("Premium"));     //verification
	}
	
	
	@Test
	public void testShow() throws Exception
	{
		activity = Mockito.spy(Activity.class);                //using spy to check *real* method
		Mockito.doCallRealMethod().when(activity).show();
		activity.show();                                       //method called
		Mockito.verify(activity,Mockito.times(1)).show();      //verification
	}

	
	@Test
	public void testShowPassengerActivity() throws Exception
	{
		activity = Mockito.spy(Activity.class);                                                //using spy to check *real* method
		Mockito.doCallRealMethod().when(activity).showPassengerActivity(Mockito.anyString());  
		activity.showPassengerActivity(Mockito.anyString());                                   //method called
		Mockito.verify(activity,Mockito.times(1)).showPassengerActivity(Mockito.anyString());  //verification
	}

	
	@Test
	public void testActivityAvailable() throws Exception
	{
		activity = Mockito.spy(Activity.class);                             //using spy to check *real* method
		Mockito.doCallRealMethod().when(activity).activityAvailable();        
		activity.activityAvailable();                                       //method called
		Mockito.verify(activity,Mockito.times(1)).activityAvailable();      //verification
	}

}
