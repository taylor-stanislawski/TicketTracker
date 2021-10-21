import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.KeyboardFocusManager;
import java.awt.event.ActionEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.ArrayList;
import java.util.LinkedList;

import javax.swing.AbstractAction;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JLayeredPane;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.border.EmptyBorder;

public class AddMenuItemGUI {
	/**
	 * @wbp.parser.entryPoint
	 */
	public void AddMenuItemGUI(ArrayList<FoodItem> itemList){
		JPanel contentPane;
		JTextField textField;
		JTextField textField_1;
		JTextField textField_2;
		
		JFrame frame = new JFrame();
		frame.setVisible(true);
		
		JFrame testFrame = new JFrame();
		
		ArrayList<String> itemTexts = new ArrayList();
		
		for (int i=0; i<itemList.size(); i++) {
			itemTexts.add(itemList.get(i).toStringGUI());//converting the list of fooditems to a String representation
		}
		
		
		//System.out.println
		
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setBounds(100, 100, 450, 300);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		frame.setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lblNewLabel = new JLabel("ID: "); //id label
		lblNewLabel.setBounds(5, 5, 69, 45);
		contentPane.add(lblNewLabel);
		
		textField = new JTextField();//id text field
		textField.setBounds(28, 17, 86, 20);
		contentPane.add(textField);
		textField.setColumns(10);
		
		JLabel lblNewLabel_1 = new JLabel("Name: ");//name label
		lblNewLabel_1.setBounds(120, 20, 40, 14);
		contentPane.add(lblNewLabel_1);
		
		textField_1 = new JTextField();//name text field
		textField_1.setBounds(163, 17, 86, 20);
		contentPane.add(textField_1);
		textField_1.setColumns(10);
		
		JLabel lblNewLabel_2 = new JLabel("Price: ");//price label
		lblNewLabel_2.setBounds(250, 20, 39, 14);
		contentPane.add(lblNewLabel_2);
		
		textField_2 = new JTextField();//price text field
		textField_2.setBounds(293, 17, 86, 20);
		contentPane.add(textField_2);
		textField_2.setColumns(10);
		
		JList<String> displayList = new JList<>(itemTexts.toArray(new String[0]));//displayable list of our fooditems
		JScrollPane scrollPane = new JScrollPane(displayList);//adding to our scrollpane
		scrollPane.setBounds(10, 97, 414, 153);
		contentPane.add(scrollPane);
		
		JButton btnNewButton = new JButton("Add");//add button with action listener for values in text fields
		btnNewButton.setBounds(69, 64, 89, 23);
		contentPane.add(btnNewButton);
		btnNewButton.addMouseListener(new MouseAdapter() {
			@Override
			
			public void mouseClicked(MouseEvent e) {
			try {
				String id = textField.getText();//get our id
				String name = textField_1.getText();//get our name
				String price = textField_2.getText();//get price
				float fPrice=Float.parseFloat(price);//convert price to float
				int idInt = Integer.parseInt(id);//convert id to integer
				System.out.println(isInteger(name));

				if(isInteger(name)==true) {//name cannot be an integer
					JLayeredPane testcontentPane;//build new frame for error
					testFrame.setVisible(true);
					testFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
					testFrame.setBounds(400, 100, 638, 200);
					testcontentPane = new JLayeredPane();
					testcontentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
					testFrame.setContentPane(testcontentPane);
					testcontentPane.setLayout(null);
					
					JLabel lblNewLabel = new JLabel("Error entering this item. Please ensure you use valid inputs (number, word, number)");
					lblNewLabel.setBounds(10, 11, 617, 67);
					lblNewLabel.setFont(new Font("Tahoma", Font.PLAIN, 16));
					testcontentPane.add(lblNewLabel);
					
					JButton btnNewButton = new JButton("Retry");//retry button the disposes testframe and resets textfields
					btnNewButton.setBounds(250, 86, 103, 35);
					testcontentPane.add(btnNewButton);
					btnNewButton.addMouseListener(new MouseAdapter() {
						@Override
						
						public void mouseClicked(MouseEvent e) {
							textField.setText("");
							textField_1.setText("");
							textField_2.setText("");
							testFrame.dispose();
						}});	
					testFrame.getRootPane().setDefaultButton(btnNewButton);
				}else {//if name isnt a string
						AddMenuItemFunction ne = new AddMenuItemFunction();
				        ne.AddSingleItemtoText(idInt, name, fPrice);//add the item to our text file which also adds to fooditem list
				        
						frame.dispose();
						AddMenuItemGUI(ne.FoodItems);//do again with the fooditems list
						
				}
			}
			catch (NumberFormatException ex) {//if either id or price arent ints, it will throw this exception
				JLayeredPane testcontentPane;//same error comes up as when name is an int
				testFrame.setVisible(true);
				testFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
				testFrame.setBounds(100, 100, 638, 200);
				testcontentPane = new JLayeredPane();
				testcontentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
				testFrame.setContentPane(testcontentPane);
				testcontentPane.setLayout(null);
				
				JLabel lblNewLabel = new JLabel("Error entering this item. Please ensure you use valid inputs (number, word, number)");
				lblNewLabel.setBounds(10, 11, 617, 67);
				lblNewLabel.setFont(new Font("Tahoma", Font.PLAIN, 16));
				testcontentPane.add(lblNewLabel);
				
				JButton btnNewButton = new JButton("Retry");
				btnNewButton.setBounds(250, 86, 103, 35);
				testcontentPane.add(btnNewButton);
				btnNewButton.addMouseListener(new MouseAdapter() {
					@Override
					
					public void mouseClicked(MouseEvent e) {
						textField.setText("");
						textField_1.setText("");
						textField_2.setText("");
						testFrame.dispose();
					}});	
				testFrame.getRootPane().setDefaultButton(btnNewButton);
			}}
		});
		
		JButton btnNewButton_1 = new JButton("Cancel");//cancel button when finished adding items
		btnNewButton_1.setBounds(233, 64, 89, 23);
		contentPane.add(btnNewButton_1);
		btnNewButton_1.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				frame.dispose();
			}
		});

		frame.getRootPane().setDefaultButton(btnNewButton);
		
}
	public static boolean isInteger(String str) {//tests if a string is an integer representation
	    if (str == null) {
	        return false;
	    }
	    int length = str.length();
	    if (length == 0) {
	        return false;
	    }
	    int i = 0;
	    if (str.charAt(0) == '-') {
	        if (length == 1) {
	            return false;
	        }
	        i = 1;
	    }
	    for (; i < length; i++) {
	        char c = str.charAt(i);
	        if (c < '0' || c > '9') {
	            return false;
	        }
	    }
	    return true;
	}
}