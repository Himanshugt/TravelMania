package Travel;

public class Destination {
	
	private String destinationName;
	
	public Destination(String destinationName) //parameterized constructor
	{
	    this.destinationName = destinationName;
	}
	
	
	public String getDestinationName() //Getter method
	{
	    return destinationName;
	}
	public void setDestinationName(String destinationName) //Setter method
	{
	    this.destinationName = destinationName;
	}
	
	//Display Destination 
	public void show() 
	{
	      System.out.println("   "+destinationName);
	}
}
