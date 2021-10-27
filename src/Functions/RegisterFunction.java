package Functions;

import java.io.*;
import java.util.*;

public class RegisterFunction {
	
	public static String register(String id, String pwd, int type) throws IOException {
		String message = "";
		if(type == 0) {
			File cookFile = new File("Cooks");
			if(checkId(id, cookFile) == false) {
				FileWriter writer = new FileWriter(cookFile, true);
				writer.write("\n" + id + " " + pwd);
				writer.close();
				message = "Successfully added: ID: '" + id + "', Password: '" + pwd + "'";
				System.out.println("Successfully added: ID: '" + id + "', Password: '" + pwd + "'");
			}
			else {
				message = "Id '" + id + "' already exists";
				System.out.println("Id '" + id + "' already exists");
			}
		}
		else if(type == 1) {
			File waiterFile = new File("Waiters");
			if(checkId(id, waiterFile) == false) {
				FileWriter writer = new FileWriter(waiterFile, true);
				writer.write("\n" + id + " " + pwd);
				writer.close();
				message = "Successfully added: ID: '" + id + "', Password: '" + pwd + "'";
				System.out.println("Successfully added: ID: '" + id + "', Password: '" + pwd + "'");
			}
			else {
				message = "Id '" + id + "' already exists";
				System.out.println("Id '" + id + "' already exists");
			}
		}
		else if(type == 2) {
			File managerFile = new File("Managers");
			if(checkId(id, managerFile) == false) {
				FileWriter writer = new FileWriter(managerFile, true);
				writer.write("\n" + id + " " + pwd);
				writer.close();
				message = "Successfully added: ID: '" + id + "', Password: '" + pwd + "'";
				System.out.println("Successfully added: ID: '" + id + "', Password: '" + pwd + "'");
			}
			else {
				message = "Id '" + id + "' already exists";
				System.out.println("Id '" + id + "' already exists");
			}
		}
		else {
			System.out.println("Invalid Employee Type");
		}
		return message;
	}
	
	public static boolean checkId(String id, File file) throws FileNotFoundException {
		boolean exists = false;
		Scanner readFile = new Scanner(file);
		while(readFile.hasNextLine()) {
			if(readFile.next().equals(id)) {
				exists = true;
			}
		}
		readFile.close();
		return exists;
	}
	
	public static void main(String[] args) throws IOException {
		System.out.println(checkId("seven", new File("Cooks")));
		register("eight", "eight", 1);
	}
}
