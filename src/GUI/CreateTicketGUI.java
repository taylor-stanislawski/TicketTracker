package GUI;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.SwingConstants;
import java.util.ArrayList;
import java.util.LinkedList;
import Assets.FoodItem;
import Assets.Ticket;
import Functions.AddMenuItemFunction;
import Functions.CreateTicketFunctions;

import javax.swing.JList;
import java.awt.Font;
import javax.swing.JScrollPane;
import javax.swing.JPanel;
import java.awt.Color;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.IOException;
import java.sql.Connection;
import java.awt.event.ActionEvent;
import java.awt.Window;

public class CreateTicketGUI {

	private JFrame frame;
	public static JTextArea itemList = new JTextArea();

	/**
	 * Launch the application.
	 */
	

	/**
	 * Create the application.
	 */
	public CreateTicketGUI(Connection conn, Ticket ticket) {
		initialize(conn, ticket);
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize(Connection conn, Ticket ticket) {
		frame = new JFrame();
		frame.getContentPane().setBackground(new Color(135, 206, 235));
		frame.setBounds(100, 100, 469, 452);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		
		JLabel createTicketLabel = new JLabel("Create Ticket");
		createTicketLabel.setBackground(new Color(135, 206, 235));
		createTicketLabel.setFont(new Font("Tahoma", Font.BOLD, 24));
		createTicketLabel.setHorizontalAlignment(SwingConstants.CENTER);
		createTicketLabel.setBounds(10, 11, 433, 63);
		frame.getContentPane().add(createTicketLabel);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(166, 85, 277, 317);
		frame.getContentPane().add(scrollPane);
		
		itemList = new JTextArea();
		itemList.setForeground(Color.BLUE);
		itemList.setFont(new Font("Monospaced", Font.BOLD, 16));
		itemList.setEditable(false);
		scrollPane.setViewportView(itemList);
		
		JButton editTicketButton = new JButton("Edit Ticket");
		editTicketButton.setBackground(new Color(211, 211, 211));
		editTicketButton.setForeground(new Color(0, 0, 205));
		editTicketButton.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				AddMenuItemFunction re = new AddMenuItemFunction(conn);
				   try {
					   re.AddItemsFromText(conn);
				   } catch (IOException e1) {
					   e1.printStackTrace();
				   }
				
				Ticket myTicket = new Ticket();
				EditTicketGUI re1 = new EditTicketGUI();
				                    re1.EditTicketGUI(re.FoodItems, conn, myTicket);//run gui with fooditems list
				CreateTicketFunctions.updateTicket(myTicket);
				itemList.setText(ticket.toString());


			}
		});
		editTicketButton.setBounds(10, 86, 146, 23);
		frame.getContentPane().add(editTicketButton);
		
		JButton submitTicketButton = new JButton("Submit");
		submitTicketButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				//Send Ticket to Ticket Display GUI
				//Clear current Ticket
			}
		});
		submitTicketButton.setBackground(new Color(211, 211, 211));
		submitTicketButton.setForeground(new Color(0, 128, 0));
		submitTicketButton.setBounds(10, 130, 146, 23);
		frame.getContentPane().add(submitTicketButton);
		
		JButton exitTicketButton = new JButton("Exit");
		exitTicketButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				//Clear current Ticket
				frame.dispose();
			}
		});
		exitTicketButton.setBackground(new Color(211, 211, 211));
		exitTicketButton.setForeground(new Color(165, 42, 42));
		exitTicketButton.setBounds(10, 177, 146, 23);
		frame.getContentPane().add(exitTicketButton);
	}
	
/*	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					CreateTicketGUI window = new CreateTicketGUI(conn);
					window.frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
		FoodItem cabbage = new FoodItem(1, "cabbage", (float) 3.75);
		FoodItem squash = new FoodItem(1, "squash", (float) 4.75);
		FoodItem carrots = new FoodItem(1, "carrots", (float) 5.75);
		Ticket ticket = new Ticket();
		ticket.addItemToTicket(cabbage);
		ticket.addItemToTicket(squash);
		ticket.addItemToTicket(carrots);
		updateTicket(ticket);
	}
*/
	
	public JFrame getFrame() {
		return frame;
	}

	public void setFrame(JFrame frame) {
		this.frame = frame;
	}
	
	public static void updateText(Ticket ticket) {
		CreateTicketFunctions.updateTicket(ticket);
		itemList.setText(ticket.toString());
	}
}
