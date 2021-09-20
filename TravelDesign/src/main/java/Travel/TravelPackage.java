package Travel;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

public class TravelPackage {
	
    private String packageName;
    private int packageCapacity;
    private int occupied;
    private List<Destination> dest;
    private List<Passenger> pass;
    
    
    
    public TravelPackage() {} //default constructor
    
    public TravelPackage(String packageName, int packageCapacity, int occupied)  //parameterized constructor
    {
        this.packageName = packageName;
        this.packageCapacity = packageCapacity;
        this.occupied = occupied;
    }
    
    
    public String getPackageName() 
    {
        return packageName;
    }
    public void setPackageName(String packageName) 
    {
        this.packageName = packageName;
    }
    
    public int getPackageCapacity() 
    {
        return packageCapacity;
    }
    public void setPackageCapacity(int packageCapacity)
    {
        this.packageCapacity = packageCapacity;
    }
    
    public int getOccupied()
    {
        return occupied;
    }
    public void setOccupied(int occupied) 
    {
        this.occupied = occupied;
    }
    
    public List<Destination> getDest() 
    {
        return dest;
    }
    public void setDest(List<Destination> dest) 
    {
        this.dest = dest;
    }
    
    public List<Passenger> getPass() 
    {
        return pass;
    }
    public void setPass(List<Passenger> pass) 
    {
        this.pass = pass;
    }
    
    //Show package details
    public void show() 
    {
        System.out.println("\n	"+packageName);
        System.out.println("   Package Capacity: " +packageCapacity);
        System.out.println("   Occupied: "+occupied);
    }
    
    //Show Passengers in each package
    public void showPackages() throws SQLException 
    {
            TravelPackage travelPackage; //Instance of TravelPackage declared
            
            DbQueries dbQuery=new DbQueries(); //Instance of DbQueries created
            
            ResultSet rs1 = dbQuery.getfromDatabase("travel_package"); //fetch all travel packages from database
            while(rs1.next()) 
            {
                travelPackage=new TravelPackage(rs1.getString("Package_Name"),rs1.getInt("Package_Capacity"),rs1.getInt("Occupied")); // travelPackage initialized
                travelPackage.show(); //show travel package details
                
                ResultSet rs2 = dbQuery.getFromDatabase("package_passengers","package_name",travelPackage.getPackageName()); //fetch passengers corresponding to package          
                while(rs2.next()) 
                {
                    int passengerNumber=rs2.getInt("Passenger_Number"); //fetch passenger number from ResultSet

                    ResultSet rs3=dbQuery.getFromDatabase("passenger","passenger_Number",passengerNumber); //fetch passenger name corresponding to passenger number
                    System.out.println();
                    while(rs3.next()) 
                    {
                        System.out.println("     Passenger Number: "+passengerNumber);
                        System.out.println("     Passenger Name: "+rs3.getString("Passenger_Name"));
                    }
                    System.out.println();
                }
                System.out.println();
            }    
    }
    
}