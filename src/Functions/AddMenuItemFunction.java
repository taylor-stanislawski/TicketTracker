package Functions;

import java.awt.EventQueue;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.NoSuchElementException;
import java.util.Scanner;

import Assets.FoodItem;
import GUI.AddMenuItemGUI;

public class AddMenuItemFunction {
	
	ArrayList<String> listOfFoodItems = new ArrayList<>();
    ArrayList<String> listOfCategories = new ArrayList<>();//categories if we decide to do that
    
    public static ArrayList<FoodItem> FoodItems = new ArrayList<>();//the fooditems list
	
	public void AddSingleItem(int id, String name, float itemPrice){
		FoodItem item = new FoodItem(id, name, itemPrice);//make the food item
	    FoodItems.add(item);//add it to the list (this does not add to the file)
	}
	
	public void AddSingleItemtoText(int id, String name, float itemPrice){//add to the list and to the file
		FoodItem item = new FoodItem(id, name, itemPrice);
	    FoodItems.add(item);
	    
	    try {//file writer for writing to the file
	        FileWriter myWriter = new FileWriter("MenuItemList.txt", true);
	        myWriter.write("\n" + id + " " + name + " " + itemPrice);
	        myWriter.close();
	        System.out.println("Successfully wrote to the file.");
	      } catch (IOException e) {
	        System.out.println("An error occurred.");
	        e.printStackTrace();
	      }
	}
	
	public void AddItemsFromText(String fileName) throws IOException {//read text file and import fooditems

		Scanner sc = new Scanner(new File(fileName));

    //ArrayList<String> listOfFoodItems = new ArrayList<>();
   // ArrayList<String> listOfCategories = new ArrayList<>();

    String id = sc.next();
    System.out.println(id);
    String name = null;
    String price = null;
    while (id != null) {
    	if (id.contains("/")) {//categories in the text are preceded by a /
    		listOfCategories.add(id);
    		  //System.out.println(id);
    		 id = sc.next();
    		 // System.out.println("next id " + id);
    	} else {//if it isnt a category`
    		//id = sc.next();
    		name = sc.next();//get the name
    		  //System.out.println(name);
    		price = sc.next();//get the price
    		 // System.out.println(price);
    		float f=Float.parseFloat(price);//convert string to float and int
    		int idInt = Integer.parseInt(id);

    		
    		AddSingleItem(idInt, name, f);//add the item to the list
    		
    		if (sc.hasNext()) {
    		id = sc.next(); //test if we have more food items
    		}
    		else {
    			id = null;//if not, this gets us out of the while loop
    		}
    }
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
    
    sc.close();
}
	
}