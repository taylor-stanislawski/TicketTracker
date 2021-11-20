package Assets;

import java.util.ArrayList;
import java.util.LinkedList;

public class Ticket {
	private ArrayList<FoodItem> food = new ArrayList<FoodItem>();
	private int id;
	private int table;
	
	//Constructors
	public Ticket(int id, int table, ArrayList<FoodItem> food) {
		this.setId(id);
		this.setTable(table);
		this.setFood(food);
	}
	
	public Ticket() {
		
	}

	//Start Getters and Setters
	public ArrayList<FoodItem> getFood() {
		return food;
	}

	public void setFood(ArrayList<FoodItem> food) {
		this.food = food;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public int getTable() {
		return table;
	}

	public void setTable(int table) {
		this.table = table;
	}
	//End Getters and Setters
	
	
	public void addItemToTicket(FoodItem item) {
		this.food.add(item);
	}
	
	//Clear all items off of the ticket
	public void clearTicket() {
		this.food.clear();
	}
	
	//Remove an item from a ticket based on its name
	public void removeItemFromTicket(String itemName) {
		int count = 0;
		while(count < food.size()) {
			if(food.get(count).getName().equals(itemName)) {
				food.remove(food.get(count));
			}
		}
	}
	
	//Remove an item from a ticket based on its id
	public void removeItemFromTicket(int id) {
		int count = 0;
		while(count < food.size()) {
			if(food.get(count).getId() == id) {
				food.remove(food.get(count));
			}
		}
	}
	
	public String toString() {
		String result = "";
		result += "Ticket ID: " +  this.id + "\n" +
				"Ticket Table: " + this.table + "\n";
		for(int i = 0; i < this.food.size(); i++) {
			result += this.food.get(i).toString() + "\n" +
					"--------------------------------------------------\n";
		}
		return result;
	}
	
}
