package com.dxc.student.DAO;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

import com.dcx.studentwebapp.beans.Student;
import com.dxc.util.ConnectionManager;

public class StudentjdbcDAO extends JdbcDAO implements DAO<Student> {

	public StudentjdbcDAO() throws FileNotFoundException, ClassNotFoundException, IOException, SQLException {
		super();
		// TODO Auto-generated constructor stub
	}

	@Override
	public boolean save(Student e) throws SQLException {
		boolean res = false;
		PreparedStatement pstmt = con.prepareStatement("INSERT INTO person VALUES(?,?,?,?,?)");
		pstmt.setInt(1, e.getId());
		pstmt.setString(2, e.getName());
		
		pstmt.setString(3, e.getEmail());
		pstmt.setString(4, e.getPhone());
		pstmt.setDate(5, new Date(e.getdob().getTime()));
		
		if(1== pstmt.executeUpdate()) {
			res = true;
		}
		
		return res;
	}

	@Override
	public boolean edit(Student e) throws SQLException {
		boolean res = false;
		
		PreparedStatement pstmt = con.prepareStatement("UPDATE person SET name=?, dob=?, email=?, mobile=? WHERE id = ?");
		pstmt.setInt(1, e.getId());
		pstmt.setString(2, e.getName());
		
		pstmt.setString(3, e.getEmail());
		pstmt.setString(4, e.getPhone());
		
		
		if(1 == pstmt.executeUpdate()) {
			res = true;
		}
		
		return res;
	}

	@Override
	public boolean delete(int id) throws SQLException {
		boolean res = false;
		
		PreparedStatement pstmt = con.prepareStatement("DELETE FROM person WHERE id = ?");
		pstmt.setInt(1, id);
		if(1== pstmt.executeUpdate()) {
			res = true;
		}
		
		return res;
	}

	@Override
	public Student find(int id) throws SQLException, ParseException, FileNotFoundException, ClassNotFoundException, IOException {
		Student student = null;
		
		try {
			Connection con = ConnectionManager.getConnection();
			con.commit();
			PreparedStatement pstmt = con.prepareStatement("SELECT * FROM person WHERE id = ?");
			
			pstmt.setInt(1, id);
			
			ResultSet rs = pstmt.executeQuery();
			if(rs.next()) {
				String name = rs.getString(2);
				String email = rs.getString(3);
				String mobile = rs.getString("phoneno");
				java.util.Date dd = rs.getDate(5);
				String strDob = new SimpleDateFormat("dd-MM-yyyy").format(dd);
				
				student = new Student(id, name, email, mobile, strDob);
			}
		}
		finally {
			con.close();
		}
		
		return student;
	}

	@Override
	public List<Student> findAll() throws SQLException, ParseException, FileNotFoundException, ClassNotFoundException, IOException {
		ArrayList<Student> students = new ArrayList<>();
		try {
			Connection con = ConnectionManager.getConnection();
			con.commit();
			Statement stmt = con.createStatement();
			ResultSet rs = stmt.executeQuery("SELECT * FROM person");
			while(rs.next()) {
				int id = rs.getInt("id");
				String name = rs.getString("name");
				
				String email = rs.getString("email");
				String mobile = rs.getString("phoneno");
				java.util.Date dd = rs.getDate("dob");
				String strDob = new SimpleDateFormat("dd-MM-yyyy").format(dd);
				Student student = new Student(id, name, email, mobile, strDob);
				students.add(student);
			}
		}
		finally {
			con.close();
		}
		
		
		return students;
	}
	
	
	public String csvAll() throws SQLException, ParseException {
		String fullcsv="";
		
		Statement stmt = con.createStatement();
		ResultSet rs = stmt.executeQuery("SELECT * FROM student");
		while(rs.next()) {
			int id = rs.getInt("id");
			String name = rs.getString("name");
			
			String email = rs.getString("email");
			String mobile = rs.getString("phoneno");
			java.util.Date dd = rs.getDate("dob");
			String strDob = new SimpleDateFormat("dd-MM-yyyy").format(dd);
			
			Student student = new Student(id, name, email, mobile, strDob);
			fullcsv+=student.toCSV()+"\n";
		}
		
		
		return fullcsv;
	}

}
