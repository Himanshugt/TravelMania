package Travel;


import java.util.Scanner;


public class Menu {
	
	void generateMenu() 
	{
		Scanner sc=new Scanner(System.in);
	    System.out.println("       Menu");
	    System.out.println("1.Add new passenger");
	    System.out.println("2.Print Itinerary of travel packages");
	    System.out.println("3.Show Passengers in each package");
	    System.out.println("4.Show Passenger Details");
	    System.out.println("5.Show current activities available \n");
	
	    System.out.print("Enter your choice number: ");
	    int option=sc.nextInt();
	    
	    //option is selected using the concept of polymorphism instead of if-else statements
	    Choice choice = new Choice();
	    choice.initializeMap();
	    choice.map.get(option).makeChoice();
	    sc.close();
	}
	 
}
