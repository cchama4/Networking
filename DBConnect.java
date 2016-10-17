package com.uic.ids520;


import java.sql.*;
import javax.swing.*;

public class DBConnect {
	
	 static Connection conn = null;
	 Statement st;
	 ResultSet rs; 
	 static PreparedStatement pst = null;
	 static String userName;
	public static Connection DBConnect() {
		
		try
		{
			Class.forName("com.mysql.jdbc.Driver");
			conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/assignment", "root", "");
			return conn;				
			
		}catch(Exception e)
		{
			JOptionPane.showMessageDialog(null, e);
		}
		
		return null;	
	}
}
