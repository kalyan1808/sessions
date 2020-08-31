package com.dxc.student.servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.dcx.studentwebapp.beans.Student;
import com.dxc.student.DAO.StudentjdbcDAO;

/**
 * Servlet implementation class Studentservlet
 */
public class Studentservlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public Studentservlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		response.sendRedirect("Login");
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		HttpSession session = request.getSession(false);
		if(session!=null && session.getAttribute("un")!=null) {
			response.setContentType("text/html");
			PrintWriter out = response.getWriter();
			String username = (String)session.getAttribute("un");
			out.println("<h2>Welcome to "+username+" </h2>");
			
			try {
				List<Student> students = new StudentjdbcDAO().findAll();
				out.println("<table>");
				out.println("<tr>");
				out.println("<th>ID</th>");
				out.println("<th>Name</th>");
				out.println("<th>Date of Birth</th>");
				out.println("<th>Email</th>");
				out.println("<th>Mobile</th>");
				out.println("</tr>");
				for(Student student : students) {
					out.println("<tr>");
					out.println("<td>"+student.getId()+"</td>");
					out.println("<td>"+student.getName()+"</td>");
					
					out.println("<td>"+student.getEmail()+"</td>");
					out.println("<td>"+student.getPhone()+"</td>");
					SimpleDateFormat sdf = new SimpleDateFormat("dd-MM-yyyy");
					out.println("<td>"+sdf.format(student.getdob())+"</td>");
					out.println("</tr>");
				}
				out.println("</table>");
			} catch (ClassNotFoundException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (ParseException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			out.println("<a href=\"logout\">Log Out</a>");
		}
		else {
			response.sendRedirect("Login");
		}
	}

}
