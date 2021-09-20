package Travel;

 import org.junit.Before;
import org.junit.Test;
import org.mockito.Mockito;

public class MenuTest {

	private Menu menu; //Instance of Menu is declared
	
	@Before //runs before the test
	public void init() 
	{
		menu = new Menu(); //object initialized
	}
	
	@Test
	public void testGenerateMenu() throws Exception
	{	
		menu = Mockito.mock(Menu.class);                          //using mock instead of spy to avoid database distortion
		Mockito.doNothing().when(menu).generateMenu();
		menu.generateMenu();                                      //method called
		Mockito.verify(menu,Mockito.times(1)).generateMenu();     //method verified
	}

}
