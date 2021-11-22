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
}
