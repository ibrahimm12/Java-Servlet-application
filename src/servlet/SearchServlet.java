package servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.mysql.jdbc.Statement;

import db.DBConnector;

public class SearchServlet extends HttpServlet {
	List<servlet.Customer> customerList;
	StringBuilder str, str2;
	Statement stmt = null;
	Connection conn = null;
	String dbUsername = "";
	String dbPassword = "";
	String table = "customer";
	String query = "select * from " + table;

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		resp.flushBuffer();
		PrintWriter out = null;
		out= resp.getWriter();
	
		 str = new StringBuilder();
		 //str2 = new StringBuilder();
		out.print("<html><head><style> table, th, td {border: 1px solid black;}</style> </head><body>");
		try
		{
			String command = req.getParameter("Submit");
			if (command!=null)
			{
				if (command.equalsIgnoreCase("Name"))
					showSearchResult(req.getParameter("name"));
				if (command.equals("Room Number"))
					showSearchResult( req.getParameter("room_number"));
				if (command.equals("Stay Duration"))
					showSearchResult( req.getParameter("stay_duration"));
				if (command.equals("Arrival Date"))
					showSearchResult( req.getParameter("arrival_date"));
				if (command.equals("Room Type"))
					showSearchResult( req.getParameter("room_type"));
			}
		}
		catch (Exception e)
		{
			showError(out, "Something went wrong");
		}
		
		out.print("<p>Search Data Form</p><p>Search By</p>");
		
		str.append("<html><head></head><body><form action='search.html' method='GET'><table>"
				+ "<tr><td>Name</td><td><input type='text' name='name' ></td> <td><input type='submit' name='Submit' value='Name'></td></tr>"
				+ "<tr><td>Room Number</td><td><input type='text' name='room_number' ></td><td><input type='submit' name='Submit' value='Room Number'></td></tr>'"
				+ "<tr><td>Stay Duration</td><td><input type='text' name='stay_duration' ></td> <td><input type='submit' name='Submit' value='Saty Duration'></td></tr>"
				+ "<tr><td>Arrival Date</td><td><input type='text' name='arrival_date' ></td> <td><input type='submit' name='Submit' value='Arrival Date'></td></tr>"
				+ "<tr><td>Room Type</td><td><input type='text' name='room_type' ></td> <td><input type='submit' name='Submit' value='Room Type'></td></tr>"
				+ "</table></form><a href='index.html'>Back</a>");
		out.print(str.toString());
		if (str2 != null)
			out.print(str2.toString());
		str2 = null;	
	}
	
	@Override
	public void init() throws ServletException {
		// TODO Auto-generated method stub
		customerList = new ArrayList();
		try
		{
			Class.forName("com.mysql.jdbc.Driver");
			String url="jdbc:mysql://mysql.cc.puv.fi:3306/e1401184_examples";
			conn=DriverManager.getConnection(url, dbUsername, dbPassword);
			
			stmt = (Statement) conn.createStatement();
			ResultSet rs = stmt.executeQuery(query);
			
			// read data from db
			while (rs.next())
			{
				String queryName = rs.getString("name");
				String queryDate = rs.getString("arrival_date");
				int queryDuration = rs.getInt("stay_duration");
				String queryRoomNumber = rs.getString("room_number");
				String queryRoomType = rs.getString("room_type");
				Customer customer = new Customer(queryName, queryRoomNumber,
						queryDate, queryDuration, queryRoomType);
				
				customerList.add(customer);
			}
		}
		catch (SQLException e)
		{
			
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			
			e.printStackTrace();
		}
	}
	
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
	}
	
	void showSearchResult(String value)
	{
		str2 = new StringBuilder();
		str2.append("<table><tr> <td>Name</td> <td>Room Number</td> <td>Stay Duration</td> <td>Arrival Date</td> <td>Room Typer</td> </tr>");
		
		for (Customer entry : customerList)
		{
			if (entry.toString().contains(value))
				//System.out.println(entry.toString());
				str2.append(entry.toString());
		}
		System.out.println(str2);
	}
	
	
	void showError(PrintWriter out, String err)
	{
		out.print("<p>" + err +"</p>");
		out.print("<a href ='search.html'>Back </a>");
	}
}
