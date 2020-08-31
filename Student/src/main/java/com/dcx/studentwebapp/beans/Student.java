package com.dcx.studentwebapp.beans;

import java.io.Serializable;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;


public class Student implements Serializable, Comparable<Student>
{
	int id;
	String name;
	String email;
	String phoneno;
	Date dob;
	public Student() {
		super();
	}
	public Student(int id, String name, String email, String phoneno, String dob) throws ParseException {
		super();
		this.id = id;
		this.name = name;
		this.email=email;
		this.phoneno=phoneno;
		this.dob=(Date) new SimpleDateFormat("dd-MM-yyyy").parse(dob);
		
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getPhone() {
		return phoneno;
	}
	public void setPhone(String phoneno) {
		this.phoneno = phoneno;
	}
	public Date getdob()
	{
		return dob;
	}
	public void setdob(Date dob)
	{
		this.dob=dob;
	}
	
	@Override
	public String toString() {
		String dd=new SimpleDateFormat("dd-MM-YYYY").format(dob);
		return "Student [id=" + id + ", name=" + name + ", email=" + email + ", phoneno=" + phoneno + ", date of birth=" +dd+ "]";
	}
	public String toCSV() {
		String dd=new SimpleDateFormat("dd-MM-YYYY").format(dob);
		return id+","+name+","+email+","+phoneno+","+dd;
	}
	public static Student parseStudent(String csvStudent) throws ParseException {
		
		String[] values = csvStudent.split(",");
		int id = Integer.parseInt(values[0].trim());
		String name = values[1].trim();
		String strDob = values[2].trim();
		String email = values[3].trim();
		String mobile = values[4].trim();
		
		Student student = new Student(id, name, strDob, email, mobile);
		
		return student;
	}
	@Override
	public int compareTo(Student o) {
		// TODO Auto-generated method stub
		return this.id - o.getId();
	}
}