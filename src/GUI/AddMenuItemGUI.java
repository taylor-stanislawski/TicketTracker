package GUI;

import java.awt.Dimension;
import java.awt.Font;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.ArrayList;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JLayeredPane;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;

import Assets.FoodItem;
import Functions.AddMenuItemFunction;

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
		
		frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
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
		btnNewButton.setBounds(15, 64, 89, 23);
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
				
				AddMenuItemFunction re = new AddMenuItemFunction();
				if(re.idExists(idInt)==false) {

				if(isInteger(name)==true) {//name cannot be an integer
					JLayeredPane testcontentPane;//build new frame for error
					testFrame.setVisible(true);
					testFrame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
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
			} else {//if this id already exists
				textField.setText("");
				JPanel invalidcontentPane;
				
				JFrame invalidFrame = new JFrame();
				invalidFrame.setVisible(true);
				
				invalidFrame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
				invalidFrame.setBounds(100, 100, 298, 114);
				invalidcontentPane = new JPanel();
				invalidcontentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
				invalidFrame.setContentPane(invalidcontentPane);
				invalidcontentPane.setLayout(null);
				
				JLabel lblNewLabel = new JLabel("You entered an ID that is already in use");
				lblNewLabel.setFont(new Font("Tahoma", Font.PLAIN, 13));
				lblNewLabel.setBounds(20, 11, 262, 14);
				invalidcontentPane.add(lblNewLabel);
				
				JButton btnNewButton = new JButton("Okay");
				btnNewButton.addMouseListener(new MouseAdapter() {
					@Override
					public void mouseClicked(MouseEvent e) {
						invalidFrame.dispose();
					}
				});
				btnNewButton.setBounds(96, 47, 89, 23);
				invalidcontentPane.add(btnNewButton);
				}
			}
			catch (NumberFormatException ex) {//if either id or price arent ints, it will throw this exception
				JLayeredPane testcontentPane;//same error comes up as when name is an int
				testFrame.setVisible(true);
				testFrame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
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
		
		JButton btnNewButton_1 = new JButton("Cancel");
		btnNewButton_1.setBounds(335, 64, 89, 23);
		contentPane.add(btnNewButton_1);
		
		btnNewButton_1.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				AddMenuItemFunction re = new AddMenuItemFunction();
				re.FoodItems.clear();
				frame.dispose();
			}
		});
		
		JButton btnEdit = new JButton("Edit");//edit button for editting items
		btnEdit.setBounds(120, 64, 89, 23);
		contentPane.add(btnEdit);
		btnEdit.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				JPanel removecontentPane;
				JTextField textField;
				JFrame removeframe = new JFrame();
				removeframe.setVisible(true);
				removeframe.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
				removeframe.setBounds(100, 100, 319, 152);
				removecontentPane = new JPanel();
				removecontentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
				removeframe.setContentPane(removecontentPane);
				removecontentPane.setLayout(null);
				
				JLabel lblNewLabel = new JLabel("Enter the ID of the item you would like to edit.");
				lblNewLabel.setFont(new Font("Tahoma", Font.PLAIN, 12));
				lblNewLabel.setBounds(10, 11, 283, 28);
				removecontentPane.add(lblNewLabel);
				
				textField = new JTextField();
				textField.setBounds(100, 50, 86, 20);
				removecontentPane.add(textField);
				textField.setColumns(10);
				
				JLabel lblNewLabel_1 = new JLabel("ID:");
				lblNewLabel_1.setBounds(74, 50, 15, 14);
				removecontentPane.add(lblNewLabel_1);
				
				JButton btnNewButton = new JButton("Edit");
				btnNewButton.addMouseListener(new MouseAdapter() {
					@Override
					public void mouseClicked(MouseEvent e) {
						Boolean isError = false;
						AddMenuItemFunction re = new AddMenuItemFunction();
						String text = textField.getText();
						if (text == null || text.isEmpty()) {//if text is empty, assign 9999 which will never be item id. this will then not allow you to edit blank id
							text = "9999";
						}
						int thisId = 0;
						try {
						thisId = Integer.parseInt(text);}
						catch (NumberFormatException ex) {
							isError=true;
							textField.setText("");
							JPanel invalidcontentPane;
							
							JFrame invalidFrame = new JFrame();
							invalidFrame.setVisible(true);
							
							invalidFrame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
							invalidFrame.setBounds(100, 100, 298, 114);
							invalidcontentPane = new JPanel();
							invalidcontentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
							invalidFrame.setContentPane(invalidcontentPane);
							invalidcontentPane.setLayout(null);
							
							JLabel lblNewLabel = new JLabel("You entered an invalid ID");
							lblNewLabel.setFont(new Font("Tahoma", Font.PLAIN, 13));
							lblNewLabel.setBounds(20, 11, 262, 14);
							invalidcontentPane.add(lblNewLabel);
							
							JButton btnNewButton = new JButton("Okay");
							btnNewButton.addMouseListener(new MouseAdapter() {
								@Override
								public void mouseClicked(MouseEvent e) {
									invalidFrame.dispose();
								}
							});
							btnNewButton.setBounds(96, 47, 89, 23);
							invalidcontentPane.add(btnNewButton);
						}
						if (isError == false) {
							
						if(re.idExists(thisId)==true){//tests to see if the id exists in our database
						String id = textField.getText();
						int theId = Integer.parseInt(id);
						JPanel removecontentPane;
						JTextField edittextField;
						JTextField edittextField_1;
						JTextField edittextField_2;
						
						JFrame removeFrame = new JFrame();
						removeFrame.setVisible(true);
						
						removeFrame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
						removeFrame.setBounds(100, 100, 450, 150);
						removecontentPane = new JPanel();
						contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
						removeFrame.setContentPane(removecontentPane);
						removecontentPane.setLayout(null);
						
						FoodItem thisItem = re.GetItem(theId);
						
						JLabel lblNewLabel = new JLabel("Enter the new info for " + thisItem.getName() + " (ID= " + theId + ", Price= " + thisItem.getPrice() + ")");
						lblNewLabel.setFont(new Font("Tahoma", Font.PLAIN, 15));
						lblNewLabel.setBounds(5, 11, 414, 26);
						removecontentPane.add(lblNewLabel);
						
						JLabel lblNewLabel_1 = new JLabel("ID:");
						lblNewLabel_1.setBounds(20, 52, 15, 14);
						removecontentPane.add(lblNewLabel_1);
						
						edittextField = new JTextField();
						edittextField.setBounds(40, 52, 86, 20);
						removecontentPane.add(edittextField);
						edittextField.setColumns(10);
						
						JLabel lblNewLabel_2 = new JLabel("Name:");
						lblNewLabel_2.setBounds(130, 52, 46, 14);
						removecontentPane.add(lblNewLabel_2);
						
						edittextField_1 = new JTextField();
						edittextField_1.setBounds(170, 52, 86, 20);
						removecontentPane.add(edittextField_1);
						edittextField_1.setColumns(10);
						
						JLabel lblNewLabel_3 = new JLabel("Price:");
						lblNewLabel_3.setBounds(260, 52, 33, 14);
						removecontentPane.add(lblNewLabel_3);
						
						edittextField_2 = new JTextField();
						edittextField_2.setBounds(300, 52, 86, 20);
						removecontentPane.add(edittextField_2);
						edittextField_2.setColumns(10);
						
						JButton btnNewButton = new JButton("Save");
						btnNewButton.addMouseListener(new MouseAdapter() {
							@Override
							public void mouseClicked(MouseEvent e) {
								boolean emptyIns = false;
								String idString = edittextField.getText();
								String newName = edittextField_1.getText();
								String newPrice = edittextField_2.getText();
								int editId=9789;
								
								try {
								editId = Integer.parseInt(idString);}
								catch(NumberFormatException ex) {
									removeFrame.dispose();
									emptyIns=true;
								}
								
								if(re.idExists(editId)==false||editId==9789) {
								
								re.EditItem(theId, idString, newName, newPrice);//run the changes to the function
								removeFrame.dispose();
								frame.dispose();
								AddMenuItemGUI re1 = new AddMenuItemGUI();
								re1.AddMenuItemGUI(re.FoodItems);//run our gui again with our new changes
								} else {
									if (editId==theId) {
										re.EditItem(theId, idString, newName, newPrice);//run the changes to the function
										removeFrame.dispose();
										frame.dispose();
										AddMenuItemGUI re1 = new AddMenuItemGUI();
										re1.AddMenuItemGUI(re.FoodItems);//run our gui again with our new changes
									}
									else if (emptyIns==false) {
									textField.setText("");
									JPanel invalidcontentPane;
									
									JFrame invalidFrame = new JFrame();
									invalidFrame.setVisible(true);
									
									invalidFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
									invalidFrame.setBounds(100, 100, 298, 114);
									invalidcontentPane = new JPanel();
									invalidcontentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
									invalidFrame.setContentPane(invalidcontentPane);
									invalidcontentPane.setLayout(null);
									
									JLabel lblNewLabel = new JLabel("You entered an ID that is already in use");
									lblNewLabel.setFont(new Font("Tahoma", Font.PLAIN, 13));
									lblNewLabel.setBounds(20, 11, 262, 14);
									invalidcontentPane.add(lblNewLabel);
									
									JButton btnNewButton = new JButton("Okay");
									btnNewButton.addMouseListener(new MouseAdapter() {
										@Override
										public void mouseClicked(MouseEvent e) {
											invalidFrame.dispose();
										}
									});
									btnNewButton.setBounds(96, 47, 89, 23);
									invalidcontentPane.add(btnNewButton);
								}}
							}
						});
						btnNewButton.setBounds(150, 80, 89, 23);
						removecontentPane.add(btnNewButton);
					}else{//if id didnt exist
						JPanel invalidcontentPane;
						JLabel txtYouEnteredAnd;
						
						JFrame invalidFrame = new JFrame();
						invalidFrame.setVisible(true);
						
						invalidFrame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
						invalidFrame.setBounds(100, 100, 298, 114);
						invalidcontentPane = new JPanel();
						invalidcontentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
						invalidFrame.setContentPane(invalidcontentPane);
						invalidcontentPane.setLayout(null);
						
						txtYouEnteredAnd = new JLabel();
						txtYouEnteredAnd.setBounds(49, 11, 182, 25);
						txtYouEnteredAnd.setFont(new Font("Tahoma", Font.PLAIN, 15));
						txtYouEnteredAnd.setText("You entered and invalid ID");
						invalidcontentPane.add(txtYouEnteredAnd);
						txtYouEnteredAnd.setBounds(20, 11, 262, 14);
						
						JButton btnNewButton = new JButton("Okay");
						btnNewButton.addMouseListener(new MouseAdapter() {
							@Override
							public void mouseClicked(MouseEvent e) {
								invalidFrame.dispose();
							}
						});
						btnNewButton.setBounds(96, 47, 89, 23);
						invalidcontentPane.add(btnNewButton);
						}}
						if (isError==false) {
						removeframe.dispose();}
					}
				});
				btnNewButton.setBounds(100, 81, 89, 23);
				removecontentPane.add(btnNewButton);
			}
				
		});
		
		JButton btnRemove = new JButton("Remove");//removing an item
		btnRemove.setBounds(229, 64, 89, 23);
		contentPane.add(btnRemove);
		btnRemove.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				JPanel removecontentPane;
				JTextField textField;
				JFrame removeframe = new JFrame();
				removeframe.setVisible(true);
				removeframe.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
				removeframe.setBounds(100, 100, 319, 152);
				removecontentPane = new JPanel();
				removecontentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
				removeframe.setContentPane(removecontentPane);
				removecontentPane.setLayout(null);
				
				JLabel lblNewLabel = new JLabel("Enter the ID of the item you would like to remove.");
				lblNewLabel.setFont(new Font("Tahoma", Font.PLAIN, 12));
				lblNewLabel.setBounds(10, 11, 283, 28);
				removecontentPane.add(lblNewLabel);
				
				textField = new JTextField();
				textField.setBounds(100, 50, 86, 20);
				removecontentPane.add(textField);
				textField.setColumns(10);
				
				JLabel lblNewLabel_1 = new JLabel("ID:");
				lblNewLabel_1.setBounds(74, 50, 15, 14);
				removecontentPane.add(lblNewLabel_1);
				
				JButton btnNewButton = new JButton("Remove");
				btnNewButton.addMouseListener(new MouseAdapter() {
					@Override
					public void mouseClicked(MouseEvent e) {
						String textFieldString = textField.getText();
						System.out.println(textField);
						int id=9789;
						int saveId=9789;
						try {
						id = Integer.parseInt(textFieldString);}
						catch (NumberFormatException ex){
							JPanel invalidcontentPane;
							JLabel txtYouEnteredAnd;
							
							JFrame invalidFrame = new JFrame();
							invalidFrame.setVisible(true);
							
							invalidFrame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
							invalidFrame.setBounds(100, 100, 298, 114);
							invalidcontentPane = new JPanel();
							invalidcontentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
							invalidFrame.setContentPane(invalidcontentPane);
							invalidcontentPane.setLayout(null);
							
							txtYouEnteredAnd = new JLabel();
							txtYouEnteredAnd.setBounds(49, 11, 182, 25);
							txtYouEnteredAnd.setFont(new Font("Tahoma", Font.PLAIN, 15));
							txtYouEnteredAnd.setText("You entered an invalid ID");
							invalidcontentPane.add(txtYouEnteredAnd);
							txtYouEnteredAnd.setBounds(10, 11, 283, 28);
							
							JButton btnNewButton = new JButton("Okay");
							btnNewButton.addMouseListener(new MouseAdapter() {
								@Override
								public void mouseClicked(MouseEvent e) {
									invalidFrame.dispose();
								}
							});
							btnNewButton.setBounds(96, 47, 89, 23);
							invalidcontentPane.add(btnNewButton);
						}
						if(id==saveId) {}else {//our save id is our error code. if this error code is in place, dont run the remove
						AddMenuItemFunction re = new AddMenuItemFunction();
						boolean removed = re.RemoveItem(id);//remove the id
						if (removed==false) {
							JPanel invalidcontentPane;
							JLabel txtYouEnteredAnd;
							
							JFrame invalidFrame = new JFrame();
							invalidFrame.setVisible(true);
							
							invalidFrame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
							invalidFrame.setBounds(100, 100, 298, 114);
							invalidcontentPane = new JPanel();
							invalidcontentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
							invalidFrame.setContentPane(invalidcontentPane);
							invalidcontentPane.setLayout(null);
							
							txtYouEnteredAnd = new JLabel();
							txtYouEnteredAnd.setBounds(49, 11, 182, 25);
							txtYouEnteredAnd.setFont(new Font("Tahoma", Font.PLAIN, 15));
							txtYouEnteredAnd.setText("You entered and ID that doesnt exist");
							invalidcontentPane.add(txtYouEnteredAnd);
							txtYouEnteredAnd.setBounds(10, 11, 283, 28);
							
							JButton btnNewButton = new JButton("Okay");
							btnNewButton.addMouseListener(new MouseAdapter() {
								@Override
								public void mouseClicked(MouseEvent e) {
									invalidFrame.dispose();
									removeframe.dispose();
								}
							});
							btnNewButton.setBounds(96, 47, 89, 23);
							invalidcontentPane.add(btnNewButton);
						} else {
						removeframe.dispose();
						frame.dispose();
						AddMenuItemGUI re1 = new AddMenuItemGUI();
						re1.AddMenuItemGUI(re.FoodItems);
					}}}
				});
				btnNewButton.setBounds(100, 81, 89, 23);
				removecontentPane.add(btnNewButton);
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
