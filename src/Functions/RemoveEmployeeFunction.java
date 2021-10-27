package Functions;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.LinkedList;
import java.util.Scanner;
import Assets.Employee;

public class RemoveEmployeeFunction {
	public static String remove(String id, File file) throws IOException {
		String message = "";
		LinkedList<Employee> empList = new LinkedList<>();
		Scanner readFile = new Scanner(file);
		while(readFile.hasNextLine()) {
			String readId = readFile.next();
			String readPwd = readFile.next();			
			if(!readId.equals(id)) {
				empList.add(new Employee(readId, readPwd));
			}
			else {
				message = "Successfully removed: '" + id + "'";
			}
		}
		FileWriter writer = new FileWriter(file, false);
		for(int i = 0; i < empList.size(); i++) {
			if(i == empList.size() - 1) {
				writer.write(empList.get(i).getId() + " " + empList.get(i).getPwd());
			}
			else {
				writer.write(empList.get(i).getId() + " " + empList.get(i).getPwd() + "\n");
			}
		}
		
		writer.close();
		readFile.close();
		
		return message;
	}
}
