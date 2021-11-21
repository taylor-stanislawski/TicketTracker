package Functions;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.LinkedList;
import java.util.Scanner;
import Assets.Employee;

public class RemoveEmployeeFunction {
	
	public static Statement stmt = null;
	public static ResultSet rs = null;
	
	public static String remove(String id, int type, Connection conn) throws IOException {
		String message = "";
		try {
			if(type == 0) {
				if(RegisterFunction.checkId(id, 0, conn) == false) {
					stmt = conn.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE,
        										ResultSet.CONCUR_READ_ONLY);
					stmt.executeUpdate("DELETE FROM ticketTracker.cooks\r\n " + 
							   "WHERE id = '" + id + "'");
					message = "Successfully removed: ID: '" + id;
					System.out.println("Successfully removed: ID: '" + id);
				}
				else {
					message = "Id '" + id + "' already exists";
					System.out.println("Id '" + id + "' already exists");
				}
			}
			else if(type == 1) {
				if(RegisterFunction.checkId(id, 1, conn) == false) {
					stmt = conn.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE,
        										ResultSet.CONCUR_READ_ONLY);
					stmt.executeUpdate("DELETE FROM ticketTracker.waiters\r\n " + 
									   "WHERE id = '" + id + "'");
					message = "Successfully removed: ID: '" + id;
					System.out.println("Successfully removed: ID: '" + id);
				}
				else {
					message = "Id '" + id + "' already exists";
					System.out.println("Id '" + id + "' already exists");
				}
			}
			else if(type == 2) {
				if(RegisterFunction.checkId(id, 2, conn) == false) {
					stmt = conn.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE,
        										ResultSet.CONCUR_READ_ONLY);
					stmt.executeUpdate("DELETE FROM ticketTracker.managers\r\n " + 
							   		   "WHERE id = '" + id + "'");
					message = "Successfully removed: ID: '" + id;
					System.out.println("Successfully removed: ID: '" + id);
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
}
