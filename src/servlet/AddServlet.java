package servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import db.DBConnector;

public class AddServlet extends HttpServlet {
	Connection conn = null;
	PreparedStatement preparedStmt=null;
	String dbUsername = "";
	String dbPassword = "";
	String table = "customer";
	String insertQuery = "INSERT INTO "
			+ table
			+ " (name, arrival_date, room_number, stay_duration, room_type) VALUES (?, ?, ?, ?, ?)";
	
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		// TODO Auto-generated method stub

		PrintWriter out = resp.getWriter();
		StringBuilder str = new StringBuilder();

		String fb = req.getParameter("fb");

		out.print("<p>Add Customer Form</p>");

		if (fb != null)
			out.print("<p style='color:red;'>" + fb + "</p>");

		str.append("<html><head></head><body><form action='add.html' method='POST'><table>"
				+ "<tr><td>CustomerName</td><td><input type='text' name='name' ></td></tr><tr>"
				+ "<td>Date</td><td><input type='text' name='arrival_date' ></td></tr>'"
				+ "<tr><td>Destination</td><td><input type='text' name='room_number' ></td></tr>"
				+ "<tr><td>Origin (number of days)</td><td><input type='text' name='stay_duration' ></td></tr>"
				+ "<tr><td>Time</td><td><<td><input type='text' name='room_number' ></td></tr>"
				+ "<tr><td></td><td><input type='submit' name='Submit' value='Submit'></td></tr>"
				+ "</table></form><a href='index.html'>Back</a> </body></html>");
		out.print(str.toString());
	}

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		// TODO Auto-generated method stub

		PrintWriter out = resp.getWriter();

		String name = req.getParameter("name");
		String date = req.getParameter("arrival_date");
		String roomNumber = req.getParameter("room_number");
		String stayDuration = req.getParameter("stay_duration");
		String roomType = req.getParameter("room_type");
		int duration;
//
//		 if (!checkParameter(name) || !checkParameter(date) ||
//		 !checkParameter(roomNumber) || !checkParameter(roomType)) {
//		 showError(out, "Check parameters" );
//		 }

		duration = Integer.parseInt(stayDuration);

//		DBConnector dbConnector = new DBConnector(db, dbUsername, dbPassword);
		
		try {

			Class.forName("com.mysql.jdbc.Driver");
			String url="jdbc:mysql://mysql.cc.puv.fi:3306/e1401184_examples";
			conn=DriverManager.getConnection(url, dbUsername, dbPassword);
			preparedStmt = conn.prepareStatement(insertQuery);
			preparedStmt.setString(1, name);
			preparedStmt.setString(2, date);
			preparedStmt.setString(3, roomNumber);
			preparedStmt.setInt(4, duration);
			preparedStmt.setString(5, roomType);
			preparedStmt.execute();
			
			resp.sendRedirect("add.html?fb='Customer added'");
		} catch (ClassNotFoundException e1) {
			System.out.println(e1);
			// TODO Auto-generated catch block
			e1.printStackTrace();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (Exception e) {
			System.out.println(e);
			showError(out, "Check your parameters");
		}
	}

	void showError(PrintWriter out, String err) {
		out.print("<p>" + err + "</p>");
		out.print("<a href ='add.html'>Back </a>");
	}

	boolean checkParameter(String para) {
		if (para == null || para.length() == 0)
			return false;
		return true;
	}
}
