package db;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DBConnector {

	Connection connection = null;
	String db, username, password;

	public DBConnector(String db, String username, String password) {
		this.db = db;
		this.username = username;
		this.password = password;
	}

	public Object connect() {

		try {
			// Here we load the database driver
			// Class.forName("oracle.jdbc.driver.OracleDriver");
			Class.forName("com.mysql.jdbc.Driver");
			// Here we set the JDBC URL for the Oracle database
			// String url = "jdbc:oracle:thin:@db.cc.puv.fi:1521:ora817";
			
			// Here we set the JDBC URL for mySQL database
//			String url = "jdbc:mysql://mysql.cc.puv.fi:3306/" + db;
			String url = "jdbc:mysql://localhost:3306/" + db;
			// Here we create a connection to the database
			// conn = DriverManager.getConnection(url, "scott", "tiger");
			connection = DriverManager.getConnection(url, username, password);

			return connection;

		} catch (SQLException sqlExc) {
			return sqlExc;
		} catch (ClassNotFoundException e) {
			return e;
		}
	}

	public Object closeConnection() {

		try {
			if (connection != null)
				connection.close();
		} catch (SQLException sqlexc) {
			return sqlexc;
		}

		return new Object();
	}

}