package Travel;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


public class PrintItinerary {
	
	Map<String, Activity> map=new HashMap<>();//Map to hold activity name corresponding to activity instance
    Map<String,Integer> map1=new HashMap<>(); //Map to hold travel package name corresponding to occupied seats
    
    //Show Itinerary of travel packages
    public void generateItinerary() throws SQLException 
    {
        	DbQueries dbQuery=new DbQueries(); //DbQueries instance created
        	
            System.out.println("\n Travel Packages-->\n");
            
            ResultSet rs1 = dbQuery.getfromDatabase("travel_package"); //fetch all travel packages from database
            while(rs1.next()) 
            {
            	//TravelPackage instance created
                TravelPackage travelPackage = new TravelPackage(rs1.getString("Package_Name"),rs1.getInt("Package_Capacity"),rs1.getInt("Occupied"));               
                map1.put(travelPackage.getPackageName(), travelPackage.getOccupied());
                int available=travelPackage.getPackageCapacity()-travelPackage.getOccupied(); //check seat availability in package
                          
                if(available>0) 
                {
                	travelPackage.show(); //show travel package details
                    System.out.println(); 
                    
                    List<Destination> destinationList = new ArrayList<>(); //List to store Destinations
                    
                    ResultSet rs2 =dbQuery.getFromDatabase("package_destinations", "package_name", travelPackage.getPackageName()); //fetch a particular travel package from database          
                    while(rs2.next()) 
                    {
                        Destination destination = new Destination(rs2.getString("Dest_Name"));
                        destinationList.add(destination);    
                    }
                    
                    System.out.println("  Available Destinations are \n");                    
                    for(Destination destination:destinationList)
                    {
                        destination.show(); //show destination details
                        
                        List<Activity> activityList = new ArrayList<>(); // list to store activities
                        
                        ResultSet rs3 = dbQuery.getFromDatabase("activity", "Dest_Name", destination.getDestinationName()); //fetch activities corresponding to a particular destination
                        while(rs3.next()) 
                        {
                            Activity activity = new Activity(rs3.getString("activity_name"),rs3.getString("Description"),
                                                             rs3.getInt("cost"),rs3.getInt("Act_Capacity"),rs3.getString("Dest_name"));  //activity initialized
                            map.put( activity.getActivityName(),activity);
                            activityList.add(activity);
                        }
                        
                        //checks if the passenger booked any activity
                        if(activityList.size()==0)
                        {
                            System.out.println("    (No activities availabe at this destination)");
                        }
                        else 
                        {
                            System.out.println("    Activities at this destination --> ");
                            System.out.println();
                            for(Activity a:activityList) 
                            {
                            	//checks if the activity have any spaces left
                                if(a.getActivityLimit()>0) 
                                {
                                    a.show();   //shows activity details
                                    System.out.println();
                                }
                                else
                                {
                                    System.out.println("    Activity Name: "+a.getActivityName()+" ( Sorry! No seats available) \n");
                                }
                            }
                        }
                        System.out.println();
                    }
                    System.out.println();
                }
                else 
                {
                	System.out.println("	"+travelPackage.getPackageName());
                	System.out.println();
                    System.out.println("   Package seats full\n");
                }
            }
    }

}
