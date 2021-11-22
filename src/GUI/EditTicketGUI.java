package GUI;

import java.awt.Font;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.sql.Connection;
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
import Assets.Ticket;
import Functions.AddMenuItemFunction;
import Functions.CreateTicketFunctions;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.Color;

public class EditTicketGUI {
	/**
	 * @wbp.parser.entryPoint
	 */
	public void EditTicketGUI(ArrayList<FoodItem> itemList, Connection conn, Ticket ticket){
		JPanel contentPane;
		
		JFrame frame = new JFrame();
		frame.setVisible(true);
		
		JFrame testFrame = new JFrame();
		
		
		ArrayList<String> itemTexts = new ArrayList();
		
		for (int i=0; i<itemList.size(); i++) {
			itemTexts.add(itemList.get(i).toStringGUI());//converting the list of fooditems to a String representation
		}
		
		frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		frame.setBounds(100, 100, 450, 300);
		contentPane = new JPanel();
		contentPane.setBackground(new Color(135, 206, 235));
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		frame.setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JList<String> displayList = new JList<>(itemTexts.toArray(new String[0]));//displayable list of our fooditems
		JScrollPane scrollPane = new JScrollPane(displayList);//adding to our scrollpane
		scrollPane.setBounds(8, 0, 275, 229);
		contentPane.add(scrollPane);
		
		JButton btnRemove = new JButton("Remove from Ticket");//removing item button
		
		JButton btnEdit = new JButton("Add to Ticket");//add button for adding to ticket
		btnEdit.setBounds(294, 54, 109, 23);
		contentPane.add(btnEdit);
		btnEdit.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
			int thisId=displayList.getSelectedIndex();
				if (thisId==-1) {
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
					txtYouEnteredAnd.setText("You did not select an item.");
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
				
				}else {
			//	}
				FoodItem thisItem = itemList.get(thisId);
				JPanel removecontentPane;
				JTextField textField;
				
				Boolean isError = false;
				AddMenuItemFunction re = new AddMenuItemFunction(conn);
						//String text = textField.getText();
						//if (text == null || text.isEmpty()) {//if text is empty, assign 9999 which will never be item id. this will then not allow you to edit blank id
						//	text = "9999";
						//}
					
				
							
				if(re.idExists(thisItem.getId(), conn)==true){//tests to see if the id exists in our database
						int theId = thisItem.getId();
						ticket.addItemToTicket(itemList.get(thisId));
						CreateTicketGUI.updateText(ticket);
					
						
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
				//btnSaveButton.setBounds(100, 81, 89, 23);
				//removecontentPane.add(btnNewButton);
			}
				
		});
		
		JButton btnNewButton_1 = new JButton("Save and Exit");//cancel button for main menu
		btnNewButton_1.setBounds(294, 120, 109, 23);
		contentPane.add(btnNewButton_1);
		
		btnNewButton_1.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				AddMenuItemFunction re = new AddMenuItemFunction(conn);
				re.FoodItems.clear();
				frame.dispose();
			}
		});
		
		
		btnRemove.setBounds(294, 86, 109, 23);
		contentPane.add(btnRemove);
		btnRemove.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
			
						int id=9789;
						int saveId=9789;
						
						id = displayList.getSelectedIndex();
						
						if(id==saveId) {}else {//our save id is our error code. if this error code is in place, dont run the remove
						AddMenuItemFunction re = new AddMenuItemFunction(conn);
						if (id==-1) {
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
							txtYouEnteredAnd.setText("You did not select an item.");
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
						
						} else {
						FoodItem thisItem = itemList.get(id);
						System.out.println(id);
						boolean removed = re.RemoveItem(thisItem.getId(), conn);//remove the id
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
								}
							});
							btnNewButton.setBounds(96, 47, 89, 23);
							invalidcontentPane.add(btnNewButton);
						} else {
						frame.dispose();
						AddMenuItemGUI re1 = new AddMenuItemGUI();
						
						JPanel invalidcontentPane;
						
						JFrame invalidFrame = new JFrame();
						invalidFrame.setVisible(true);
						
						invalidFrame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
						invalidFrame.setBounds(100, 100, 298, 114);
						invalidcontentPane = new JPanel();
						invalidcontentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
						invalidFrame.setContentPane(invalidcontentPane);
						invalidcontentPane.setLayout(null);
						
						JLabel lblNewLabel1 = new JLabel("Your item has been removed.");
						lblNewLabel1.setFont(new Font("Tahoma", Font.PLAIN, 13));
						lblNewLabel1.setBounds(20, 11, 262, 14);
						invalidcontentPane.add(lblNewLabel1);
						
						JButton btnNewButton = new JButton("Okay");
						btnNewButton.addMouseListener(new MouseAdapter() {
							@Override
							public void mouseClicked(MouseEvent e) {
								invalidFrame.dispose();
								EditTicketGUI(re.FoodItems, conn, ticket);//do again with the fooditems list		
							}
						});
						btnNewButton.setBounds(96, 47, 89, 23);
						invalidcontentPane.add(btnNewButton);
					}}
				//btnNewButton.setBounds(100, 81, 89, 23);
				//removecontentPane.add(btnNewButton);
			}}
		});
		

		//frame.getRootPane().setDefaultButton(btnNewButton);
		
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
