package Travel;

import java.sql.SQLException;
import java.util.HashMap;

public class Choice {
	
	//Map to hold different makeChoice implementation corresponding to chosen option
    HashMap<Integer,Choices> map=new HashMap<>(); 
    
    public void initializeMap()
    {
        map.put(1,new Choice1());
        map.put(2,new Choice2());
        map.put(3,new Choice3());
        map.put(4,new Choice4());
        map.put(5,new Choice5());
    }	
}

interface Choices
{
    public void makeChoice();
}

class Choice1 implements Choices //Choice class to add new passenger
{
    @Override
    public void makeChoice() 
    {
        Passenger passenger=new Passenger();
        try 
        {
			passenger.enterNewPassenger();
		} catch (SQLException e) {
			e.printStackTrace();
		}
    }
}

class Choice2 implements Choices //Choice class to show Itinerary of travel packages
{
    @Override
    public void makeChoice() 
    {
        PrintItinerary printItinerary = new PrintItinerary();
        try 
        {
			printItinerary.generateItinerary();
		} catch (SQLException e) {
			e.printStackTrace();
		}
    }
}

class Choice3  implements Choices //Choice class to show Passengers in each package
{
    @Override
    public void makeChoice() 
    {
        TravelPackage travelPackage=new TravelPackage();
        try 
        {
			travelPackage.showPackages();
		} catch (SQLException e) {
			e.printStackTrace();
		}
    }
}

class Choice4 implements Choices  //Choice class to show Passenger Details
{
    @Override
    public void makeChoice() 
    {
        Passenger passenger=new Passenger();
        try 
        {
			passenger.showPasseger();
		} catch (SQLException e) {
			e.printStackTrace();
		}
    }
}

class Choice5 implements Choices  //Choice class to show current activities available 
{
    @Override
    public void makeChoice()
    {
        Activity activity=new Activity();
        try 
        {
			activity.activityAvailable();
		} catch (SQLException e) {
			e.printStackTrace();
		}
    }
}