package Assets;

public class FoodItem {
	private int id;
	private String name;
	private float price;
	
	//Constructor
	public FoodItem(int id, String name, float price) {
		this.setId(id);
		this.setName(name);
		this.setPrice(price);
	}

	//Getters and Setters
	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public float getPrice() {
		return price;
	}

	public void setPrice(float price) {
		this.price = price;
	}
	//End Getters and Setters
	
	//Print out the contents of a food item
	public String toString() {
		String result = "";
		result += "Item ID: " +  this.id + "\n" +
				"Item Name: " + this.name + "\n" +
				"Item Price: $" + this.price;
		return result;
	}
	
}
