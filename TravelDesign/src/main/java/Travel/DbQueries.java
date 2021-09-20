package Travel;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class DbQueries {
	
    static QueryExecution sql = new QueryExecution();
	static Connection connection=null;
	static {
		try  
		{
			connection = DriverManager.getConnection(sql.getUrl(), sql.getUser(), sql.getPassword()); //Database connection established
			
		} catch (SQLException e) 
		{
			e.printStackTrace();
		}
	}
	
	//query to insert into database
	public void insertIntoDatabase(String tablename, String attribute1, String attribute2, String value1, int value2) {
		String query;
		PreparedStatement iUDStatement; //statement to execute parameterized query such as insertion or modification
		query = "Insert into "+tablename+"("+attribute1+","+attribute2+")"+" values(?,?)";
    	try 
    	{
			iUDStatement =sql.processIUDQuery(connection,query);
			iUDStatement.setString(1, value1); //inserts the passed parameter into the corresponding attribute
	    	iUDStatement.setInt(2, value2); 
	    	sql.executeIUDStatement(iUDStatement); //returns successful execution of query 
	    	
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
		
	//query to insert into database (Method Overloaded)
	public void insertIntoDatabase(String tablename, String attribute1, String attribute2,String attribute3, String value1, String value2,int value3) {
		String query;
		PreparedStatement iUDStatement; //statement to execute parameterized query such as insertion or modification
		query = "Insert into "+tablename+"("+attribute1+","+attribute2+","+attribute3+")"+" values(?,?,?)";
    	try 
    	{
			iUDStatement =sql.processIUDQuery(connection,query);
			iUDStatement.setString(1, value1); //inserts the passed parameter into the corresponding attribute
	    	iUDStatement.setString(2, value2);
	    	iUDStatement.setInt(3, value3);
	    	sql.executeIUDStatement(iUDStatement); //returns successful execution of query 
	    	
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	//query to fetch all records from a particular table
	public ResultSet getfromDatabase(String tablename) {
		String query;
		query= "select * from "+tablename;
        try 
        {
			return sql.executeSelectionQuery(connection,query);
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
        return null;
		
	}

	//query to fetch records from a particular table according to the condition
	public ResultSet getFromDatabase(String tablename,String conditionAttribute,String conditionValue) {
		String query;
		query = "select * from "+tablename+" where "+conditionAttribute+"='"+conditionValue+"'";
        try 
        {
			return sql.executeSelectionQuery(connection,query);
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
        return null;
	}
	
	//query to fetch records from a particular table according to the condition (Method Overloaded)
	public ResultSet getFromDatabase(String tablename,String conditionAttribute,int conditionValue) {
		String query;
		query = "select * from "+tablename+" where "+conditionAttribute+"="+conditionValue+"";
        try 
        {
			return sql.executeSelectionQuery(connection,query);
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
        return null;
	}
	
	//query to fetch last inserted passenger number
	public ResultSet getLastPassengerFromDatabase() {
		String query;
		query = "SELECT Passenger_Number FROM passenger ORDER BY Passenger_Number DESC LIMIT 1";
        try 
        {
			return sql.executeSelectionQuery(connection,query);
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
        return null;
	}
	
	//query to fetch count of activities enrolled by a particular passenger
	public ResultSet getPassengerActivityCount(int passengerNumber) {
		String query;
		query= "select count(passenger_number) from passenger_activities where passenger_number="+passengerNumber;
        try 
        {
			return sql.executeSelectionQuery(connection,query);
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
        return null;
	}
	
	//query to fetch activities from database that still have spaces available
	public ResultSet getActivitySpaces(){
		String query;
        query= "select * from activity where act_capacity>0";
        try 
        {
			return sql.executeSelectionQuery(connection,query);
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
        return null;
	}

	//query to update database
	public void updateDatabase(String tablename, String setAttribute, int setValue, String conditionAttribute, String conditionValue) {
		String query;
		query="UPDATE "+tablename+ " SET "+setAttribute+" = " + setValue +" where "+conditionAttribute+" = '" + conditionValue+"'";
		PreparedStatement iUDDStatement; //statement to execute parameterized query such as insertion or modification
		try 
		{
			iUDDStatement = sql.processIUDQuery(connection,query);
	        sql.executeIUDStatement(iUDDStatement); //returns successful execution of query 
	        
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	//query to update database (Method Overloaded)
	public void updateDatabase(String tablename, String setAttribute, int setValue, String conditionAttribute, int conditionValue) {
		String query;
		query="UPDATE "+tablename+ " SET "+setAttribute+" = " + setValue +" where "+conditionAttribute+" = " + conditionValue+"";
		PreparedStatement iUDDStatement; //statement to execute parameterized query such as insertion or modification
		try 
		{
			iUDDStatement = sql.processIUDQuery(connection,query);
	        sql.executeIUDStatement(iUDDStatement); //returns successful execution of query 
	        
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

}
