package Functions;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.Scanner;

import Assets.FoodItem;
import Assets.Ticket;

public class EditTicketFunction {
	
	public ArrayList<Ticket> listOfTickets = new ArrayList<>();
    ArrayList<String> listOfFoods = new ArrayList<>();//categories if we decide to do that
    public static Statement stmt = null;
	public static ResultSet rs = null;
    
   public static ArrayList<FoodItem> FoodItems = new ArrayList<>();//the fooditems list
	
	public EditTicketFunction(Connection conn) {
}

	public void AddSingleItem(int id, String name, float newPrice, Connection conn){
		FoodItem item = new FoodItem(id, name, newPrice);//make the food item
	    FoodItems.add(item);//add it to the list (this does not add to the file)
		FoodItems = sortFoodList(FoodItems);
	}
	
	public boolean RemoveTicket(int id, Connection conn) {
		boolean removed = false;
		for (int i=0; i<listOfTickets.size(); i++) {
			Ticket item = listOfTickets.get(i);
			if(item.getId()==id) {
				try {
	                stmt = conn.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE,
	                            ResultSet.CONCUR_READ_ONLY);
		            stmt.executeUpdate("delete from ticketTracker.ticketList\r\n " + 
		            					   "where ticketId = '" + id + "'");
		        } catch (SQLException e) {
		            //print SQL errors
		            e.printStackTrace();
		        }
				removed = true;
			}
		}
		
		FoodItems = sortFoodList(FoodItems);
		return removed;
	}
	
	public Ticket GetTicket(int id, Connection conn) {
		Ticket thisItem = null;
		for (int i=0; i<listOfTickets.size(); i++) {
			Ticket item = listOfTickets.get(i);
			if(item.getId()==id) {
				thisItem=listOfTickets.get(i);
			}
		}
		
	//	listOfTickets = sortFoodList(listOfTickets);
		return thisItem;
	}
	
	public boolean idExists(int id, Connection conn) {//tests to see if the ID exists in the database. returns true if so
		boolean doesExist = false;
		for (int i=0; i<listOfTickets.size(); i++) {
			Ticket item = listOfTickets.get(i);
			if(item.getId()==id) {
				doesExist = true;
			}
		}
		return doesExist;
	}
	
	public void EditItem(int ticketId, int newAmount, int foodId, Connection conn) {//editing item in the database
		newAmount=0;
		for (int i=0; i<listOfTickets.size(); i++) {
			Ticket item = listOfTickets.get(i);
			if(item.getId()==ticketId) {
				try {
		            stmt = conn.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE,
		                        ResultSet.CONCUR_READ_ONLY);
	
		         //   if (newAmount != null && !newAmount.isEmpty()) {//if statements for each seperately allow changes to individual id, name or price
						if (newAmount == 0) {
							stmt.executeUpdate("DELETE from ticketTracker.ticketFoods " + 
		          					  "where ticketTracker.ticketfoods.itemNum = " + foodId);
							System.out.println("DELETING");
						} else {
						stmt.executeUpdate("UPDATE ticketTracker.ticketFoods " + 
          					  "SET amount = " + newAmount + " " +
          					  "where ticketId = " + ticketId + " AND itemNum = " + foodId);
						System.out.println("updating");
					}
		            
			/*		if(newid != null && !newid.isEmpty()) {
						int itemId = Integer.parseInt(newid);
						FoodItems.get(i).setId(itemId);
						stmt.executeUpdate("UPDATE ticketTracker.menu " + 
	          					  "SET item = '" + newName + "' " +
	          					  "where itemNum = " + id);
					}
					if (itemPrice != null && !itemPrice.isEmpty()) {
						float newPrice = Float.parseFloat(itemPrice);
						FoodItems.get(i).setPrice(newPrice);
						stmt.executeUpdate("UPDATE ticketTracker.menu " + 
	          					  "SET price = " + newPrice + " " +
	          					  "where itemNum = " + id);
					}*/

		        } catch (SQLException e) {
		            //print SQL errors
		            e.printStackTrace();
		        }
				
				
				
			}
		}
		FoodItems = sortFoodList(FoodItems);
	}
	
	
	public void AddSingleticket(int ticketId, ArrayList<FoodItem> foods, Connection conn){//add to the list and to the file
		Ticket thisTicket = new Ticket(ticketId, foods);
		listOfTickets.add(thisTicket);
	    
		try {
            stmt = conn.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE,
                        ResultSet.CONCUR_READ_ONLY);
            stmt.executeUpdate("insert into ticketTracker.ticketList\r\n " + 
            				   "VALUES(" + ticketId + ");");
        } catch (SQLException e) {
            //print SQL errors
            e.printStackTrace();
        }
	}
	
	public void AddItemsFromText(String fileName, Connection conn) throws IOException {//read text file and import fooditems

    FoodItems.clear();
    try {
        stmt = conn.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE,
                    ResultSet.CONCUR_READ_ONLY);
        rs = stmt.executeQuery("SELECT * FROM ticketTracker.menu order by itemNum");
	    while(rs.next()) {
	    	AddSingleItem(rs.getInt(1), rs.getString(2), rs.getInt(3), conn);
	    }
	} catch (SQLException e) {
	    //print SQL errors
	    e.printStackTrace();
	}
	}
    
   /* //the rest us for printing to the console
    for(int i=0; i<listOfFoodItems.size(); i++) {
    //System.out.println(listOfFoodItems.get(i));
    String itemText = listOfFoodItems.get(i);
    //FoodItem item = new FoodItem(i, itemText, 10);
    //FoodItems.add(item);
   // System.out.println(item.toString());
    }
    
    for(int i=0; i<listOfCategories.size(); i++) {
        //System.out.println(listOfCategories.get(i));
        }
    for(int i=0; i<FoodItems.size(); i++) {
    	System.out.println(FoodItems.get(i).toString());
    }

}*/
	public ArrayList<FoodItem> sortFoodList(ArrayList<FoodItem> FoodList) {
		Collections.sort(FoodList, new Comparator<FoodItem>(){
		    public int compare(FoodItem s1, FoodItem s2) {
		        return Integer.compare(s1.getId(), s2.getId());
		    }
		});
		for(int i=0; i<FoodList.size(); i++) {
	    	System.out.println(FoodList.get(i).toString());
	    }
		return FoodList;
	}

}
