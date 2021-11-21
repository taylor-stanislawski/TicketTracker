package Functions;

import Assets.*;

import java.util.ArrayList;

import javax.swing.JLabel;

public class CreateTicketFunctions {
	public Ticket newTicket;
	
	public static void updateTicket(Ticket ticket) {
		ArrayList<FoodItem> foodStuff = ticket.getFood();
		String result = "";
		for(int i = 0; i < ticket.getFood().size(); i++) {
			if(i == 0) {
				System.out.println("Option 1: " + i);
				result = foodStuff.get(i).getName() + " : " + foodStuff.get(i).getPrice();
				//itemList.setText(result);
			}
			else {
				System.out.println("Option 2: " + i);
				result += "\n" + foodStuff.get(i).getName() + " : " + foodStuff.get(i).getPrice();
				//itemList.setText(result);
			}
			
		}
	}
}
