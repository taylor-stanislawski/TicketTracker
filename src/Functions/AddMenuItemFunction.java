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

public class AddMenuItemFunction {
	
	ArrayList<String> listOfFoodItems = new ArrayList<>();
    ArrayList<String> listOfCategories = new ArrayList<>();//categories if we decide to do that
    public static Statement stmt = null;
	public static ResultSet rs = null;
    
   public static ArrayList<FoodItem> FoodItems = new ArrayList<>();//the fooditems list
	
	public AddMenuItemFunction(Connection conn) {
}

	public void AddSingleItem(int id, String name, float newPrice, Connection conn){
		FoodItem item = new FoodItem(id, name, newPrice);//make the food item
	    FoodItems.add(item);//add it to the list (this does not add to the file)
		FoodItems = sortFoodList(FoodItems);
	}
	
	public boolean RemoveItem(int id, Connection conn) {
		boolean removed = false;
		for (int i=0; i<FoodItems.size(); i++) {
			FoodItem item = FoodItems.get(i);
			if(item.getId()==id) {
				try {
	                stmt = conn.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE,
	                            ResultSet.CONCUR_READ_ONLY);
		            stmt.executeUpdate("delete from ticketTracker.menu\r\n " + 
		            					   "where itemNum = '" + id + "'");
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
	
	public FoodItem GetItem(int id, Connection conn) {
		FoodItem thisItem = null;
		for (int i=0; i<FoodItems.size(); i++) {
			FoodItem item = FoodItems.get(i);
			if(item.getId()==id) {
				thisItem=FoodItems.get(i);
			}
		}
		
		FoodItems = sortFoodList(FoodItems);
		return thisItem;
	}
	
	public boolean idExists(int id, Connection conn) {//tests to see if the ID exists in the database. returns true if so
		boolean doesExist = false;
		for (int i=0; i<FoodItems.size(); i++) {
			FoodItem item = FoodItems.get(i);
			if(item.getId()==id) {
				doesExist = true;
			}
		}
		return doesExist;
	}
	
	public void EditItem(int id, String newid, String newName, String itemPrice, Connection conn) {//editing item in the database
		for (int i=0; i<FoodItems.size(); i++) {
			FoodItem item = FoodItems.get(i);
			if(item.getId()==id) {
				try {
		            stmt = conn.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE,
		                        ResultSet.CONCUR_READ_ONLY);
		            if (newName != null && !newName.isEmpty()) {//if statements for each seperately allow changes to individual id, name or price
						FoodItems.get(i).setName(newName);
						stmt.executeUpdate("UPDATE ticketTracker.menu " + 
          					  "SET itemNum = " + newid + " " +
          					  "where itemNum = " + id);
					}
					if(newid != null && !newid.isEmpty()) {
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
					}

		        } catch (SQLException e) {
		            //print SQL errors
		            e.printStackTrace();
		        }
				
				
			}
		}
		FoodItems = sortFoodList(FoodItems);
	}
	
	
	public void AddSingleItemtoText(int id, String name, float itemPrice, Connection conn){//add to the list and to the file
		FoodItem item = new FoodItem(id, name, itemPrice);
	    FoodItems.add(item);
	    
		FoodItems = sortFoodList(FoodItems);
	    
		try {
            stmt = conn.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE,
                        ResultSet.CONCUR_READ_ONLY);
            stmt.executeUpdate("insert into ticketTracker.menu\r\n " + 
            				   "VALUES(" + id + ", '" + name + "', " + itemPrice + ");");
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
    
    
    //the rest us for printing to the console
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

}
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
