package com.example.jdbc;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import java.util.Scanner;

public class JdbcExample_02_Insert {
	// Url, UserName and Password to connect MySql Database. Make it CONSTANT
	private static final String URL = "jdbc:mysql://localhost:3306/jdbcdb";

	private static final String USERNAME = "root";
	private static final String PASSWORD = "oracle";

	public static void main(String[] args) {
		Connection con = null;
		PreparedStatement pst = null;
		ResultSet rs = null;

		String sqlQuery = "INSERT INTO person VALUES(?,?,?)";

		try {
			// Load and Register MySQL Driver (optional for JDBC 4+)
			Class.forName("com.mysql.cj.jdbc.Driver");

			// Establish Connection
			con = DriverManager.getConnection(URL, USERNAME, PASSWORD);
			pst = con.prepareStatement(sqlQuery);

			Scanner sc = new Scanner(System.in);
			System.out.println("Enter the Person ID");
			int pId = sc.nextInt();
			
			sc.nextLine();//This is needed because Int after String will not work.
			System.out.println("Enter the Person Name");
			String pName = sc.nextLine();
			
			System.out.println("Enter the Person Location");
			String pLoc = sc.nextLine();
			
			pst.setInt(1, pId);
			pst.setString(2, pName);
			pst.setString(3, pLoc);

			int rows = pst.executeUpdate();

			System.out.println("---------------------------------------------------------------------");
			System.out.println("JDBC Insert Example");
			System.out.println("---------------------------------------------------------------------");

			System.out.println("No of Rows Updated       : " + rows);
			System.out.println("---------------------------------------------------------------------");
		} catch (ClassNotFoundException e) {
			System.out.println("MySQL JDBC Driver not found: " + e.getMessage());
		} catch (SQLException e) {
			System.out.println("Database connection failed: " + e.getMessage());
		} finally {
			// Closing resources in reverse order
			try {
				if (rs != null)
					rs.close();
			} catch (SQLException e) {
				System.out.println("Failed to close ResultSet: " + e.getMessage());
			}
			try {
				if (pst != null)
					pst.close();
			} catch (SQLException e) {
				System.out.println("Failed to close Statement: " + e.getMessage());
			}
			try {
				if (con != null)
					con.close();
			} catch (SQLException e) {
				System.out.println("Failed to close Connection: " + e.getMessage());
			}
		}
	}
}
