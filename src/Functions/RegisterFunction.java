package Functions;

import java.io.*;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.*;

public class RegisterFunction {
	
	public static Statement stmt = null;
	public static ResultSet rs = null;
	
	public static String register(String id, String pwd, int type, Connection conn) throws IOException {
		String message = "";
		
		try {
			if(type == 0) {
				if(checkId(id, 0, conn) == true) {
					stmt = conn.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE,
        					ResultSet.CONCUR_READ_ONLY);
					stmt.executeUpdate("insert into ticketTracker.cooks\r\n " + 
									   "VALUES('" + id  + "','" + pwd + "')");
					
					message = "Successfully added: ID: '" + id + "', Password: '" + pwd + "'";
					System.out.println("Successfully added: ID: '" + id + "', Password: '" + pwd + "'");
				}
				else {
					message = "Id '" + id + "' already exists";
					System.out.println("Id '" + id + "' already exists");
				}
			}
			else if(type == 1) {
				if(checkId(id, 1, conn) == true) {
					stmt = conn.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE,
        					ResultSet.CONCUR_READ_ONLY);
					stmt.executeUpdate("insert into ticketTracker.waiters\r\n " + 
									   "VALUES('" + id  + "','" + pwd + "')");
					message = "Successfully added: ID: '" + id + "', Password: '" + pwd + "'";
					System.out.println("Successfully added: ID: '" + id + "', Password: '" + pwd + "'");
				}
				else {
					message = "Id '" + id + "' already exists";
					System.out.println("Id '" + id + "' already exists");
				}
			}
			else if(type == 2) {
				if(checkId(id, 2, conn) == true) {
					stmt = conn.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE,
        					ResultSet.CONCUR_READ_ONLY);
					stmt.executeUpdate("insert into ticketTracker.managers\r\n " + 
									   "VALUES('" + id  + "','" + pwd + "')");
					message = "Successfully added: ID: '" + id + "', Password: '" + pwd + "'";
					System.out.println("Successfully added: ID: '" + id + "', Password: '" + pwd + "'");
				}
				else {
					message = "Id '" + id + "' already exists";
					System.out.println("Id '" + id + "' already exists");
				}
			}
			else {
				System.out.println("Invalid Employee Type");
			}
		} catch (SQLException e) {
            //print SQL errors
            e.printStackTrace();
        }
		return message;
	}
	
	public static boolean checkId(String id, int role, Connection conn) {
		boolean flag = false;
		try {
			if(role == 0) {
	            stmt = conn.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE,
                        					ResultSet.CONCUR_READ_ONLY);
	            rs = stmt.executeQuery("select *\r\n " + 
	            					"from ticketTracker.cooks\r\n " + 
	            					"where id = '" + id + "'");
			} else if(role == 1) {
	            stmt = conn.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE,
    					ResultSet.CONCUR_READ_ONLY);
	            rs = stmt.executeQuery("select *\r\n " + 
								   "from ticketTracker.waiters\r\n " + 
								   "where id = '" + id + "'");
			} else {
	            stmt = conn.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE,
    					ResultSet.CONCUR_READ_ONLY);
	            rs = stmt.executeQuery("select *\r\n " + 
								   "from ticketTracker.managers\r\n " + 
								   "where id = '" + id + "'");
			}
		} catch (SQLException e) {
            //print SQL errors
            e.printStackTrace();
        }
		
		try {
			if(rs.next() == false) {
				flag = true;
		      } else {
		    	flag = false;
		    }		      
		} catch (Exception e) {
			e.printStackTrace();
		}
		return flag;

	}

}
