package Functions;

import java.io.*;
import java.util.ArrayList;
import java.util.Scanner;

public class LoginFunction {
	
	public static Scanner inFile;
	
	public static boolean login(String ID, String pass, String role) {
		String fileName = null;
		short verifyUser = 0;
		short verifyPass = 0;
		ArrayList<String> IDList = new ArrayList<String>();
		ArrayList<String> passList = new ArrayList<String>();
		
		if(role.equals("Cook")) {
			fileName = "Cooks";
		} else if(role.equals("Waiter")) {
			fileName = "Waiters";
		} else {
			fileName = "Managers";
		}
				
		try {
			inFile = new Scanner( new File( fileName ) );
			
			while( inFile.hasNextLine() ) {	      
		         IDList.add( inFile.next() );  //add the employee id to list
		         passList.add( inFile.next() );  //add the employee password to list
		      }
		      
		     inFile.close();
		      
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} 
		
		for (int i = 0; i < IDList.size(); i++) {
			if( ID.equals(IDList.get(i)) && pass.equals(passList.get(i)) ) {
				verifyUser = 1;
				verifyPass = 1;
			}
		}

		if(verifyUser != 0 && verifyPass != 0) {
			return true;
		} else {
			return false;
		}
		

	}

}
