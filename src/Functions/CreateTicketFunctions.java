package Functions;

import Assets.*;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import javax.swing.JLabel;

public class CreateTicketFunctions {
	
	public static Statement stmt = null;
	public static ResultSet rs = null;
	public Ticket newTicket;
	
	public static void updateTicket(Ticket ticket) {
		ArrayList<FoodItem> foodStuff = ticket.getFood();
		String result = "";
		for(int i = 0; i < ticket.getFood().size(); i++) {
			if(i == 0) {
				result = foodStuff.get(i).getName() + " : " + foodStuff.get(i).getPrice();
				//itemList.setText(result);
			}
			else {
				result += "\n" + foodStuff.get(i).getName() + " : " + foodStuff.get(i).getPrice();
				//itemList.setText(result);
			}
			
		}
	}
	
	public static FoodItem retrieveItem(int id, Connection conn) {
		int itemID = 0;
		String itemName = null;
		float itemPrice = 0;
		try {
			stmt = conn.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE,
					ResultSet.CONCUR_READ_ONLY);
			rs = stmt.executeQuery("select *\r\n " + 
					"from ticketTracker.menu\r\n " + 
					"where itemNum = '" + id + "'");
			while(rs.next()) {
				itemID = rs.getInt(1);
				itemName = rs.getString(2);
				itemPrice = rs.getFloat(3);
			}
		
		} catch (SQLException e) {
			e.printStackTrace();
		}
		FoodItem newItem = new FoodItem(itemID,itemName,itemPrice);
		
		return newItem;
	}
	
	public static void insertTicket(Ticket ticket, Connection conn) {
		int maxNum = 0;
		String orderData = "";
		try {
			stmt = conn.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE,
					ResultSet.CONCUR_READ_ONLY);
			rs = stmt.executeQuery("SELECT MAX(number) + 1 AS Number FROM ticketTracker.ticket;");
			while(rs.next()) {
				maxNum = rs.getInt(1);
			}
			
			for(int i=0;i<ticket.getFood().size();i++) {
				orderData = orderData + ticket.getFood().get(i).getName() + "\n ";
			}
			
			stmt.executeUpdate("insert into ticketTracker.ticket\r\n " + 
							  "VALUES(" + maxNum + ",\r\n " + 
							  "		  '" +  orderData  +"', false, CURRENT_TIMESTAMP);");
		
		} catch (SQLException e) {
			e.printStackTrace();
		}
	
	}
	
	public static ArrayList<Ticket> retrieveTicket(Connection conn) {
		int ticketNum = 0;
		String orderData = null;
		ArrayList<Ticket> ticketList = new ArrayList<Ticket>();
		ArrayList<FoodItem> foodList = new ArrayList<FoodItem>();
		try {
			stmt = conn.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE,
					ResultSet.CONCUR_READ_ONLY);
			rs = stmt.executeQuery("Select number,data from ticketTracker.ticket\r\n " + 
								   "WHERE state = 0 order by number asc;");
			while(rs.next()) {
				Ticket ticket = new Ticket();
				ticketNum = rs.getInt(1);
				orderData = rs.getString(2);
					ticket.setId(ticketNum);
					foodList.add(new FoodItem(0, orderData, 0));
					ticket.setFood(foodList);
					ticketList.add(ticket);
					System.out.println(ticket.getFood().get(0).getName());
				orderData = null;
			}
		
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return ticketList;
	}
	
	public static void setTicketComplete(int id, Connection conn) {
		try {
			stmt = conn.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE,
					ResultSet.CONCUR_READ_ONLY);
			stmt.executeUpdate("UPDATE ticketTracker.ticket\r\n " + 
									"SET state = 1\r\n " + 
									"WHERE number = " + id);
		} catch (SQLException e) {
			e.printStackTrace();
		}

	}
}
