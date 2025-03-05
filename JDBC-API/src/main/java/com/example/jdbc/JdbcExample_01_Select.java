package com.example.jdbc;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class JdbcExample_01_Select
{
	// Url, UserName and Password to connect MySql Database. Make it CONSTANT
	private static final String URL = "jdbc:mysql://localhost:3306/jdbcdb";
	private static final String USERNAME = "root";
	private static final String PASSWORD = "oracle";
	
	public static void main(String[] args)
	{
        Connection con = null;
        Statement st = null;
        ResultSet rs = null;
        
        String sqlQuery = "SELECT * FROM person";
        
		try
		{
            // Load and Register MySQL Driver (optional for JDBC 4+)
            Class.forName("com.mysql.cj.jdbc.Driver");

            // Establish Connection
            con = DriverManager.getConnection(URL, USERNAME, PASSWORD);
            st = con.createStatement();
            rs = st.executeQuery(sqlQuery);

            System.out.println("---------------------------------------------------------------------");
            System.out.println("JDBC Select Example");
            System.out.println("---------------------------------------------------------------------");

            while (rs.next())
            {
                System.out.println("Person Id       : " + rs.getInt("pid"));
                System.out.println("Person Name     : " + rs.getString("pname"));
                System.out.println("Person Location : " + rs.getString("plocation"));
                System.out.println("---------------------------------------------------------------------");
            }
        } 
		catch (ClassNotFoundException e)
		{
            System.out.println("MySQL JDBC Driver not found: " + e.getMessage());
        } 
		catch (SQLException e) {
            System.out.println("Database connection failed: " + e.getMessage());
        } 
		finally
		{
            // Closing resources in reverse order
            try {
                if (rs != null) rs.close();
            } catch (SQLException e) {
                System.out.println("Failed to close ResultSet: " + e.getMessage());
            }
            try {
                if (st != null) st.close();
            } catch (SQLException e) {
                System.out.println("Failed to close Statement: " + e.getMessage());
            }
            try {
                if (con != null) con.close();
            } catch (SQLException e) {
                System.out.println("Failed to close Connection: " + e.getMessage());
            }
        }
    }
}
