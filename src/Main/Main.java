package Main;

import GUI.LoginGUI;

public class Main {

	//launch login GUI
	public static void main(String[] args) {
		try {
			LoginGUI window = new LoginGUI();
			window.getFrame().setVisible(true);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
}
