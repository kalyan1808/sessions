package com.dxc.util;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class ConnectionManager 
{
	protected ConnectionManager()
	{
		
	}
	public static Connection getConnection() throws ClassNotFoundException, SQLException 
	{
		Connection con = null;
		
		String dri = "com.mysql.jdbc.Driver";
		String url = "jdbc:mysql://localhost:3306/queries?" + "useSSL=false";
		String un = "root";
		String pw = "root";
		
		Class.forName(dri);
		con = DriverManager.getConnection(url, un, pw);
		con.setAutoCommit(false);
		return con;
	}

}