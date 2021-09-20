package Travel;

import org.junit.Before;
import org.junit.Test;
import org.mockito.Mockito;

public class TravelPackageTest {

	private TravelPackage travelPackage; //Instance of TravelPackage declared
	
	@Before //runs before every test
	public void init() 
	{
		travelPackage = new TravelPackage(); //object initialized
	}
	
	@Test
	public void testShow() throws Exception
	{	
		travelPackage = Mockito.spy(TravelPackage.class);      //using spy to check *real* method
		travelPackage.setPackageName(Mockito.anyString());     //sets field of the object upon which the test is to be performed
		travelPackage.setPackageCapacity(Mockito.anyInt());    //sets field of the object upon which the test is to be performed
		travelPackage.setOccupied(Mockito.anyInt());           //sets field of the object upon which the test is to be performed
		
		Mockito.doCallRealMethod().when(travelPackage).show();
		travelPackage.show();                                  //method called
		Mockito.verify(travelPackage,Mockito.times(1)).show(); //verification
	}

	@Test
	public void testShowPackages() throws Exception
	{
		travelPackage = Mockito.spy(TravelPackage.class);               //using spy to check *real* method
		travelPackage.setPackageName(Mockito.anyString());              //sets field of the object upon which the test is to be performed
		travelPackage.setPackageCapacity(Mockito.anyInt());             //sets field of the object upon which the test is to be performed
		travelPackage.setOccupied(Mockito.anyInt());                    //sets field of the object upon which the test is to be performed
		
		Mockito.doCallRealMethod().when(travelPackage).showPackages();
		travelPackage.showPackages();                                   //method called
		Mockito.verify(travelPackage,Mockito.times(1)).showPackages();  //verification
	}

}
