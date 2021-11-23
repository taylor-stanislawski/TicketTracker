package GUI;

import java.awt.Color;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.SwingConstants;

import Assets.FoodItem;
import Assets.Ticket;
import Functions.AddMenuItemFunction;
import Functions.EditTicketFunction;

import java.awt.Font;
import javax.swing.JButton;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.IOException;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

public class SettingsGUI {

	private JFrame frame;

	public SettingsGUI(Connection conn) {
		initialize(conn);
	}

	private void initialize(Connection conn) {
		setFrame(new JFrame());
		getFrame().setBounds(100, 100, 469, 276);
		frame.getContentPane().setBackground(new Color(135, 206, 235));
		getFrame().setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		getFrame().getContentPane().setLayout(null);
		
		JLabel lblNewLabel = new JLabel("Settings");
		lblNewLabel.setFont(new Font("Tahoma", Font.BOLD, 24));
		lblNewLabel.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel.setBounds(133, 11, 187, 35);
		getFrame().getContentPane().add(lblNewLabel);
		
		JButton editMenuButton = new JButton("Edit Menu");
		editMenuButton.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				AddMenuItemFunction re = new AddMenuItemFunction(conn);
				   try {
					   re.AddItemsFromText(conn);
				   } catch (IOException e1) {
					   e1.printStackTrace();
				   }

				AddMenuItemGUI re1 = new AddMenuItemGUI();
				                    re1.AddMenuItemGUI(re.FoodItems, conn);//run gui with fooditems list
			}
		});
		editMenuButton.setBounds(157, 81, 138, 23);
		getFrame().getContentPane().add(editMenuButton);
		
		JButton editTicketButton = new JButton("Edit Tickets");
		editTicketButton.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				AddTicketGUI re1 = new AddTicketGUI();
				AddMenuItemFunction menu = new AddMenuItemFunction(conn);
				
				try {
					menu.AddItemsFromText(conn);
				} catch (IOException e2) {
					// TODO Auto-generated catch block
					e2.printStackTrace();
				}
				
				EditTicketFunction re = new EditTicketFunction(conn);
				
				ArrayList<Ticket> tickets = new ArrayList<Ticket>();
				
				String query = "SELECT tickettracker.ticketfoods.itemNum, tickettracker.ticketfoods.ticketId, tickettracker.ticketfoods.amount " + 
						"FROM tickettracker.ticketfoods JOIN tickettracker.ticketlist ON tickettracker.ticketfoods.ticketId = tickettracker.ticketList.ticketId where tickettracker.ticketList.ticketId = tickettracker.ticketfoods.ticketId";
				
				Statement stmt;
				
				System.out.println("yo before here");
				
				try {
					stmt = conn.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE,
        					ResultSet.CONCUR_READ_ONLY);
					ResultSet rs = stmt.executeQuery(query);
					
					while(rs.next()) {
						System.out.println("yo here");
						int itemNum = rs.getInt("itemNum");
						int ticketId = rs.getInt("ticketId");
						int amount = rs.getInt("amount");
						
						System.out.println("WHAT IS HAPPENING" + itemNum + ticketId);
						
						Ticket thisTicket = new Ticket();
						//thisTicket.setFood(re.FoodItems);
					//	ArrayList<FoodItem> foods = thisTicket.getFood();
						FoodItem thisItem = menu.GetItem(itemNum, conn);
						thisTicket.setId(ticketId);
						thisTicket.addItemToTicket(thisItem);
					//	thisTicket.setAmounts(amount, ticketId);
						tickets.add(thisTicket);
					}
				} catch (SQLException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
				
				re.listOfTickets=tickets;
				
				//System.out.println(re.listOfTickets.toString());
				
				re1.AddTicketGUI(re.listOfTickets, conn);//run gui with fooditems list
			}
		});
		editTicketButton.setBounds(157, 54, 138, 23);
		getFrame().getContentPane().add(editTicketButton);
		
		JButton addEmployeeButton = new JButton("Add Employee");
		addEmployeeButton.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				RegisterGUI register = new RegisterGUI(conn);
				register.getFrame().setVisible(true);
			}
		});
		addEmployeeButton.setBounds(157, 107, 138, 23);
		frame.getContentPane().add(addEmployeeButton);
		
		JButton removeEmployeeButton = new JButton("Remove Employee");
		removeEmployeeButton.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				RemoveEmployeeGUI remove = new RemoveEmployeeGUI(conn);
				remove.getFrame().setVisible(true);
			}
		});
		removeEmployeeButton.setBounds(157, 134, 138, 23);
		frame.getContentPane().add(removeEmployeeButton);
		
		JButton btnLogout = new JButton("Logout");
		btnLogout.addMouseListener(new MouseAdapter() {
			public void mouseClicked(MouseEvent arg0) {
				EventQueue.invokeLater(new Runnable() {
					public void run() {
						try {
							LoginGUI window = new LoginGUI(conn);
							window.getFrame().setVisible(true);
						} catch (Exception e) {
							e.printStackTrace();
						}
					}
				});
				frame.dispose();
			}
		});
		btnLogout.setBounds(157, 161, 138, 23);
		frame.getContentPane().add(btnLogout);
		
	}

	public JFrame getFrame() {
		return frame;
	}

	public void setFrame(JFrame frame) {
		this.frame = frame;
	}
}
