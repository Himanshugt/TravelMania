package Travel;
import java.sql.ResultSet;
import java.sql.SQLException;


public class Activity 
{
	private String activityName;
    private String description;
    private int cost;
    private int activityLimit;
    private String destinationName;
    
    //Default constructor
    public Activity(){} 

    //Parameterized Constructor
    public Activity(String activityName, String description, int cost, int activityLimit, String destinationName) 
    {
        this.activityName = activityName;
        this.description = description;
        this.cost = cost;
        this.activityLimit = activityLimit;
        this.destinationName = destinationName;
    }
    
    
    public String getActivityName()
    {
        return activityName;
    } 
    public void setActivityName(String activityName) 
    {
        this.activityName = activityName;
    }
  
    public String getDescription() 
    {
        return description;
    }
    public void setDescription(String description)
    {
        this.description = description;
    }
    
    public int getCost()
    {
        return cost;
    }
    public void setCost(int cost) 
    {
        this.cost = cost;
    }
    
    public int getActivityLimit() 
    {
        return activityLimit;
    }
    public void setActivityLimit(int activityLimit)
    {
        this.activityLimit = activityLimit;
    }
    
    
    public String getDestinationName() 
    {
        return destinationName;
    }
    public void setDestinationName(String destinationName)
    {
        this.destinationName = destinationName;
    }
    
    
    //Calculate activity cost according to status of the passenger
    public int calculateCost(String str)
    {
        int discountedCost=cost;
        if(str.equals("Gold"))
            discountedCost=90*discountedCost/100;
        else if(str.equals("Premium")) {
            discountedCost=0;
        }
        return discountedCost;
    }
    
    //Display activity details
    public void show()
    {
        System.out.println("    Activity Name: "+activityName);
        System.out.println("    Cost: "+cost);
        System.out.println("    "+description);
        System.out.println("    Seats available: "+activityLimit);
        System.out.println("    Destination: "+destinationName);
    }
    
    //display amount paid by a passenger for activity
    public void showPassengerActivity(String str)
    {
        System.out.println("Activity Name: "+activityName);
        System.out.println("       Destination: "+destinationName);
        System.out.println("       Amount Paid: "+calculateCost(str)+"\n");
    }
    
    //Display activities that still have spaces available
    public void activityAvailable() throws SQLException 
    {
    	DbQueries dbQuery=new DbQueries();
    	ResultSet rs=dbQuery.getActivitySpaces(); //gets available activities from database
    	
        System.out.println("List of Activities that still have spaces available\n");
        while(rs.next())
        { 
        	//creating an instance according to the data received from database
            Activity a = new Activity(rs.getString("activity_name"),rs.getString("Description"),
                                      rs.getInt("cost"),rs.getInt("Act_Capacity"),rs.getString("Dest_name")); 
            System.out.println();
            a.show();
        }
    }
	
}
