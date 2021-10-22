import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

public class AddMenuItemFunction {
	
	ArrayList<String> listOfFoodItems = new ArrayList<>();
    ArrayList<String> listOfCategories = new ArrayList<>();//categories if we decide to do that
    
   static ArrayList<FoodItem> FoodItems = new ArrayList<>();//the fooditems list
	
	public void AddSingleItem(int id, String name, float newPrice){
		FoodItem item = new FoodItem(id, name, newPrice);//make the food item
	    FoodItems.add(item);//add it to the list (this does not add to the file)
	}
	
	public void RemoveItem(int id) {
		for (int i=0; i<FoodItems.size(); i++) {
			FoodItem item = FoodItems.get(i);
			if(item.getId()==id) {
				FoodItems.remove(i);
				try {
					ItemListToText(FoodItems);
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		}
	}
	
	public boolean idExists(int id) {//tests to see if the ID exists in the database. returns true if so
		boolean doesExist = false;
		for (int i=0; i<FoodItems.size(); i++) {
			FoodItem item = FoodItems.get(i);
			if(item.getId()==id) {
				doesExist = true;
			}
		}
		return doesExist;
	}
	
	public void EditItem(int id, String newid, String newName, String itemPrice) {//edditing item in the database
		for (int i=0; i<FoodItems.size(); i++) {
			FoodItem item = FoodItems.get(i);
			if(item.getId()==id) {
				if (newName != null && !newName.isEmpty()) {//if statements for each seperatly allow changes to individual id, name or price
					FoodItems.get(i).setName(newName);
				}
				if(newid != null && !newid.isEmpty()) {
					int itemId = Integer.parseInt(newid);
					FoodItems.get(i).setId(itemId);
				}
				if (itemPrice != null && !itemPrice.isEmpty()) {
					float newPrice = Float.parseFloat(itemPrice);
					FoodItems.get(i).setPrice(newPrice);
				}
				
				try {
					ItemListToText(FoodItems);//export to text with our updated list
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		}
	}
	
	public void ItemListToText(ArrayList<FoodItem> FoodItems) throws IOException {
		FileWriter myWriter;	
			myWriter = new FileWriter("MenuItemList.txt");
		for (int i=0; i<FoodItems.size(); i++) {
			
	        try {
				myWriter.write("\n" + FoodItems.get(i).getId() + " " + FoodItems.get(i).getName() + " " + FoodItems.get(i).getPrice());
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

		}
        myWriter.close();
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