package Functions;

import java.io.*;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class LoginFunction {
	
	public static Statement stmt = null;
	public static ResultSet rs = null;
	
	public static boolean login(String ID, String pass, String role, Connection conn) {
		String verifyUser = null;
		String verifyPass = null;
		
		try {
			if(role.equals("Cook")) {
	            stmt = conn.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE,
                        					ResultSet.CONCUR_READ_ONLY);
	            rs = stmt.executeQuery("select *\r\n " + 
	            					"from ticketTracker.cooks\r\n " + 
	            					"where id = '" + ID + "' and password = '" + pass + "'");
			} else if(role.equals("Waiter")) {
	            stmt = conn.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE,
    					ResultSet.CONCUR_READ_ONLY);
	            rs = stmt.executeQuery("select *\r\n " + 
								   "from ticketTracker.waiters\r\n " + 
								   "where id = '" + ID + "' and password = '" + pass + "'");
			} else {
	            stmt = conn.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE,
    					ResultSet.CONCUR_READ_ONLY);
	            rs = stmt.executeQuery("select *\r\n " + 
								   "from ticketTracker.managers\r\n " + 
								   "where id = '" + ID + "' and password = '" + pass + "'");
			}
		} catch (SQLException e) {
            //print SQL errors
            e.printStackTrace();
        }
				
		try {
			if(rs.next() == false) {
		      } else {
		    	verifyUser = rs.getString(1);
		    	verifyPass = rs.getString(2);
		    }		      
		} catch (Exception e) {
			e.printStackTrace();
		} 

		if(verifyUser != null && verifyPass != null ) {
			return true;
		} else {
			return false;
		}
		

	}

}
