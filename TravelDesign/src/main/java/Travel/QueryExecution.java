package Travel;

import java.sql.Connection;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class QueryExecution {
	
    private String url="jdbc:mysql://localhost:3306/travel"; //url to connect MySQL to JDBC driver
    private String user = "root"; //MySQL server name
    private String password = "0116"; 

    public String getUrl() 
    {
		return url;
	}
	public void setUrl(String url) 
	{
		this.url = url;
	}

	public String getUser()
	{
		return user;
	}
	public void setUser(String user) 
	{
		this.user = user;
	}

	public String getPassword()
	{
		return password;
	}
	public void setPassword(String password) 
	{
		this.password = password;
	}

	PreparedStatement processIUDQuery(Connection connection, String query) throws SQLException  //Process modification queries and returns statement
	{
        return connection.prepareStatement(query);
    }

    void executeIUDStatement(PreparedStatement statement) throws SQLException //Execute modification queries and display status
    {
        int rows = statement.executeUpdate();
        if(rows==0) // record updated in database
        {
            System.out.println("\n  *Database Updated*");
        }
        else if(rows>0)  // record inserted into database
        {
            System.out.println("\n  *Database Updated*");
        }
        else {
        	System.out.println("\n  *Error!*");
        }
    }

    ResultSet executeSelectionQuery(Connection connection, String query) throws SQLException //Process selection query and fetch data
    {
        Statement selectStatement = connection.createStatement();
        return selectStatement.executeQuery(query);
    }

}