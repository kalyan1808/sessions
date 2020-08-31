package com.dxc.student.DAO;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.sql.Connection;
import java.sql.SQLException;

import com.dxc.util.ConnectionManager;

public class JdbcDAO 
{
	Connection con = null;
	public JdbcDAO() throws FileNotFoundException, ClassNotFoundException, IOException, SQLException {
		con = ConnectionManager.getConnection();
		con.setAutoCommit(false);
	}
	
	public void save() throws SQLException {
		con.commit();
		con.close();
	}
	
	public void undo() throws SQLException {
		con.rollback();
		con.close();
	}
}
