package Main;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import GUI.LoginGUI;

public class Main {

	static final String JDBC_DRIVER = "com.mysql.jdbc.Driver";
	static final String DB_URL = "jdbc:mysql://localhost/";
	
	//open connection to the database
	public static Connection conn = null;
	public static Statement stmt = null;
	public static ResultSet rs = null;
	
	//launch login GUI
	public static void main(String[] args) {
		
		//input MySQL server params
		String dbuser = args[0];
		String dbpass = args[1];
		
		try {
            //establish connection to database
            System.out.println("Connecting to database");
            conn = DriverManager.getConnection(DB_URL, dbuser, dbpass);
            System.out.println("Connection established: " + conn.isValid(2));

            
        //handle JDBC errors
        } catch (SQLException se) { 
            System.out.println("SQL Exception: " + se.getMessage());
            System.out.println("SQLState Code: " + se.getSQLState());
            System.out.println("Error Code: " + se.getErrorCode());


        } //end try block
		
		try {
			LoginGUI window = new LoginGUI(conn);
			window.getFrame().setVisible(true);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
}
