package com.crm.SDET26.practice;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

import org.testng.Reporter;

import com.mysql.cj.jdbc.Driver;

public class SampleJDBCExecuteUpdate {

	public static void main(String[] args) throws SQLException {
		
		Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/sdet26", "root", "root");


		try {

			Driver driverRef = new Driver();
			DriverManager.registerDriver(driverRef);
			Statement stat = con.createStatement();

			int result = stat.executeUpdate("insert into empInfo  ('Batman',12,'Maryland');");
			if(result == 1)
			{
				Reporter.log("data created successfully", true);
			
			}
			else 
			{
				Reporter.log("data not created", true);
			}
			
		}catch(Exception e) {
			
		}
		finally {
			con.close();
			System.out.println("sadad");
		}
		
		
	}
}



