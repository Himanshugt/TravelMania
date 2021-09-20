package Travel;


import java.sql.ResultSet;
import java.sql.SQLException;

import java.util.Scanner;


public class Passenger {
	
	private String passengerName;
	private int passengerNumber;
	private String status;
	private int balance;
	
	public Passenger(){} //default constructor
	
	public Passenger(String passengerName, int passengerNumber, String status, int balance) //parameterized constructor
	{
		this.passengerName = passengerName;
		this.passengerNumber = passengerNumber;
		this.status = status;
		this.balance = balance;
	}
	
	public String getPassengerName() 
	{
		return passengerName;
	}
	public void setPassengerName(String passengerName) 
	{
		this.passengerName = passengerName;
	}
	
	public int getPassengerNumber()
	{
		return passengerNumber;
	}
	public void setPassengerNumber(int passengerNumber) 
	{
		this.passengerNumber = passengerNumber;
	}
	
	public String getStatus() 
	{
		return status;
	}
	public void setStatus(String status)
	{
		this.status = status;
	}
	
	public int getBalance() 
	{
		return balance;
	}
	public void setBalance(int balance) 
	{
		this.balance = balance;
	}
	
	//show passenger particulars
	public void show() 
	{
		System.out.println("   Passenger Number: "+passengerNumber);
		System.out.println("   Passenger Name: " +passengerName);
		System.out.println("   Passenger status: "+status);
		if(!status.equals("Premium"))
		{
			System.out.println("   Passenger balance: "+balance);
		}
	}
	
	//method to add new passenger
	public void enterNewPassenger() throws SQLException
	{
        	Scanner sc= new Scanner(System.in);
        	
        	DbQueries dbQuery=new DbQueries(); //Instance of DbQueries created
        	
            System.out.println("\n Enter Passenger details\n");
            
            Passenger passenger = enterPassengerDetails(); //Get passenger details
            
            PrintItinerary printItinerary = new PrintItinerary();
        	printItinerary.generateItinerary();
			
			System.out.print("Choose travel package name: ");
            String chosenPackage=sc.next();
			
            TravelPackage travelPackage = new TravelPackage();
            travelPackage.setOccupied(printItinerary.map1.get(chosenPackage)+1); //increase occupancy of chosen travel package
            
            //update occupancy in database
            dbQuery.updateDatabase("travel_Package","Occupied",travelPackage.getOccupied(),"Package_Name",chosenPackage); 
			
			ResultSet rs4 =dbQuery.getLastPassengerFromDatabase(); //fetch current passenger number from database
			
			while(rs4.next())
			{
				passenger.setPassengerNumber(rs4.getInt("passenger_Number"));
				
				//insert passenger number corresponding to chosen package in database
				dbQuery.insertIntoDatabase("package_passengers", "Package_name", "passenger_number", chosenPackage, passenger.getPassengerNumber());
			}
			
            System.out.print("\nChoose the number of activities: ");           
            int numberOfActivity=sc.nextInt();
            
            //allows passenger to select activities
            while(numberOfActivity>0)
            {
	            System.out.print("\nChoose activity: ");
	            String actName=sc.next();
	            
	            Activity activity=printItinerary.map.get(actName); //gets activity instance corresponding to chosen activity
	            
	            //checks seat availability
	            if(activity.getActivityLimit()<1)
	            {
	            	System.out.println("   Sorry! No seats available");
	            }
	            else 
	            {
		            int activityCost=activity.calculateCost(passenger.getStatus()); //gets cost of the chosen activity		            
		            if(activityCost>passenger.getBalance()) 
		            {
		            	System.out.println("   Insufficient balance");
		            }
		            else 
		            {
		            	activity.setActivityLimit(activity.getActivityLimit()-1); //update activity availability
		            	passenger.setBalance(passenger.getBalance()-activityCost);
		            	System.out.println("\n   Your remaining balance is "+passenger.getBalance());
		            	
		            	//insert activity corresponding to passenger number into database
		            	dbQuery.insertIntoDatabase("passenger_activities","Activity_name","Passenger_number",actName,passenger.getPassengerNumber());
		              
		            	//update activity availability in database
		                dbQuery.updateDatabase("activity", "Act_Capacity",activity.getActivityLimit(), "Activity_Name", actName);
		            }
	            }
	            --numberOfActivity;
            }
            
            //update balance corresponding to the current passenger into database 
            dbQuery.updateDatabase("passenger", "Balance", passenger.getBalance(), "Passenger_Number", passenger.getPassengerNumber());
    }
	
	//method to enter passenger particulars
	private Passenger enterPassengerDetails() throws SQLException {
    	Scanner sc= new Scanner(System.in);
		
		Passenger passenger=new Passenger();
		
		DbQueries dbQuery=new DbQueries(); //Instance of DbQuery created
		
        System.out.print("Enter Passenger Name: ");
        passenger.setPassengerName(sc.next());
        System.out.print("\nEnter status (Standar/Gold/Premium): ");
        passenger.setStatus(sc.next());
        System.out.print("\nEnter balance: ");
        passenger.setBalance(sc.nextInt());
        
        //insert passenger details into database
        dbQuery.insertIntoDatabase("passenger", "passenger_Name", "status", "balance", passenger.getPassengerName(),  passenger.getStatus(), passenger.getBalance());
                
		return passenger;
		
	}
	
	//method to show entire passenger details
	public void showPasseger() throws SQLException 
	{
	    	DbQueries dbQuery=new DbQueries(); //Instance of DbQueries created
			
			System.out.println("\n	Passenger Details\n");
			
			ResultSet rs1 = dbQuery.getfromDatabase("passenger"); //fetch all passengers from database
			while(rs1.next()) 
			{
				Passenger passenger=new Passenger(rs1.getString("Passenger_name"),rs1.getInt("Passenger_Number"),
												  rs1.getString("Status"),rs1.getInt("Balance")); //instance of passenger created
				passenger.show(); //show passenger particulars
				
				int numberOfActivities=0;
				
				ResultSet rs2 = dbQuery.getPassengerActivityCount(passenger.getPassengerNumber()); //fetch count of activities enrolled by a particular passenger
				while(rs2.next()) 
				{
					numberOfActivities=rs2.getInt("count(passenger_number)");
				}
				
				//show activities enrolled by the passenger
				if(numberOfActivities==0)
				{
					System.out.println("     No activities selected by passenger\n");
				}
				else
				{
					int  i = 1;
					
					//fetch activities corresponding to passenger number from database
					ResultSet rs3 = dbQuery.getFromDatabase("passenger_activities","passenger_number",passenger.getPassengerNumber());
					while(rs3.next()) 
					{
						String actName=rs3.getString("activity_name");
	    				ResultSet rs4 = dbQuery.getFromDatabase("activity", "Activity_Name", actName);  //fetch activity record corresponding to activity name
	    				Activity act=new Activity(); //Instance of activity created
	    				while(rs4.next()) 
	    				{
	    					act = new Activity(rs4.getString("activity_name"),rs4.getString("Description"),
			                                   rs4.getInt("cost"),rs4.getInt("Act_Capacity"),rs4.getString("Dest_name"));
	    				}
	    				System.out.print("     "+(i++) +".");
	    				act.showPassengerActivity(passenger.getStatus()); //show activity details according to passenger activities
					}
				}
			}
	}
}
