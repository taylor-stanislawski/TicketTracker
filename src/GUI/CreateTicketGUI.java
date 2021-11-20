package GUI;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.SwingConstants;
import java.util.ArrayList;
import java.util.LinkedList;
import Assets.FoodItem;
import Assets.Ticket;
import javax.swing.JList;
import java.awt.Font;
import javax.swing.JScrollPane;
import javax.swing.JPanel;
import java.awt.Color;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.JButton;
import java.awt.event.ActionListener;
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
	public CreateTicketGUI() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame();
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
		editTicketButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				//Open EditTicketGUI
			}
		});
		editTicketButton.setBounds(10, 90, 146, 23);
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
		submitTicketButton.setBounds(10, 124, 146, 23);
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
		exitTicketButton.setBounds(10, 158, 146, 23);
		frame.getContentPane().add(exitTicketButton);
	}
	
	// MOVE THIS
	public static void updateTicket(Ticket ticket) {
		ArrayList<FoodItem> foodStuff = ticket.getFood();
		String result = "";
		for(int i = 0; i < ticket.getFood().size(); i++) {
			if(i == 0) {
				System.out.println("Option 1: " + i);
				result = foodStuff.get(i).getName() + " : " + foodStuff.get(i).getPrice();
				itemList.setText(result);
			}
			else {
				System.out.println("Option 2: " + i);
				result += "\n" + foodStuff.get(i).getName() + " : " + foodStuff.get(i).getPrice();
				itemList.setText(result);
			}
			
		}
	}
	// MOVE THIS
	
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					CreateTicketGUI window = new CreateTicketGUI();
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

	public JFrame getFrame() {
		return frame;
	}

	public void setFrame(JFrame frame) {
		this.frame = frame;
	}
}
