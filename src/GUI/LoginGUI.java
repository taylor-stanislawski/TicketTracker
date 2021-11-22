package GUI;

import javax.swing.*;

import Assets.Ticket;
import Functions.LoginFunction;
import java.awt.EventQueue;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.sql.Connection;
import java.awt.Font;
import java.awt.Color;

public class LoginGUI {

	private JFrame frame;

	public LoginGUI(Connection conn) {
		initialize(conn);
	}

	/**
	 * Create the application.
     @ -41,39 +31,66 @@ public class loginGUI {
	 * Initialize the contents of the frame.
	 */
	
	
	private void initialize(Connection conn) {
		setFrame(new JFrame());
		getFrame().setBounds(100, 100, 354, 386);
		getFrame().setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		getFrame().getContentPane().setLayout(null);
		
		JLabel ticketLabel = new JLabel("Login");
		ticketLabel.setBackground(new Color(0, 0, 0));
		ticketLabel.setFont(new Font("Tahoma", Font.BOLD, 24));
		ticketLabel.setHorizontalAlignment(SwingConstants.CENTER);
		ticketLabel.setBounds(10, 0, 317, 46);
		frame.getContentPane().add(ticketLabel);
		getFrame().getContentPane().add(ticketLabel);
		
		JLabel loginLabel = new JLabel("");
		loginLabel.setHorizontalAlignment(SwingConstants.CENTER);
		loginLabel.setBounds(76, 47, 186, 18);
		frame.getContentPane().add(loginLabel);
		
		JLabel IDLabel = new JLabel("Employee ID:");
		IDLabel.setBounds(76, 76, 186, 18);
		frame.getContentPane().add(IDLabel);
		getFrame().getContentPane().add(IDLabel);
		
		JTextField IDField = new JTextField();
		IDField.setBounds(76, 93, 186, 26);
		getFrame().getContentPane().add(IDField);
		IDField.setColumns(10);
		
		JLabel passLabel = new JLabel("Password:");
		passLabel.setBounds(76, 134, 186, 18);
		frame.getContentPane().add(passLabel);
		getFrame().getContentPane().add(passLabel);
		
		JTextField passField = new JTextField();
		passField.setBounds(76, 151, 186, 26);
		getFrame().getContentPane().add(passField);
		passField.setColumns(10);
		
		JRadioButton cookButton = new JRadioButton("Cook");
		cookButton.setBackground(new Color(135, 206, 235));
		cookButton.setBounds(76, 184, 186, 31);
		frame.getContentPane().add(cookButton);
		
		JRadioButton waiterButton = new JRadioButton("Waiter");
		waiterButton.setBackground(new Color(135, 206, 235));
		waiterButton.setBounds(76, 218, 186, 32);
		frame.getContentPane().add(waiterButton);
		
		JRadioButton managerButton = new JRadioButton("Manager");
		managerButton.setBackground(new Color(135, 206, 235));
		managerButton.setBounds(76, 253, 186, 31);
		frame.getContentPane().add(managerButton);
		
		JButton loginButton = new JButton("Login");
		loginButton.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent arg0) {
				String ID = IDField.getText();
				String pass = passField.getText();
					if(cookButton.isSelected()) {
						try {
							//open cook window
							if(LoginFunction.login(ID, pass, "Cook", conn) == true) {
								EventQueue.invokeLater(new Runnable() {
									public void run() {
										try {
											SettingsGUI window = new SettingsGUI(conn);
											window.getFrame().setVisible(true);
										} catch (Exception e) {
											e.printStackTrace();
										}
									}
								});
								frame.dispose();
							} else {
								loginLabel.setText("Incorrect Login");
							}
						} catch (Exception e) {
							e.printStackTrace();
						}
					}
					else if(waiterButton.isSelected()) {
						//open Waiter window
						try {
							if(LoginFunction.login(ID, pass, "Waiter", conn) == true) {
								EventQueue.invokeLater(new Runnable() {
									public void run() {
										try {
											Ticket ticket = new Ticket();
											CreateTicketGUI window = new CreateTicketGUI(conn);
											window.getFrame().setVisible(true);
										} catch (Exception e) {
											e.printStackTrace();
										}
									}
								});
								
							} else {
								loginLabel.setText("Incorrect Login");
							}
						} catch (Exception e) {
							e.printStackTrace();
						}
					}
					else if(managerButton.isSelected()) {
						//open Manager window
						try {
							if(LoginFunction.login(ID, pass, "Manager", conn) == true) {
								EventQueue.invokeLater(new Runnable() {
									public void run() {
										try {
											SettingsGUI window = new SettingsGUI(conn);
											window.getFrame().setVisible(true);
										} catch (Exception e) {
											e.printStackTrace();
										}
									}
								});
								frame.dispose();
							} else {
								loginLabel.setText("Incorrect Login");
							}
						} catch (Exception e) {
							e.printStackTrace();
						}
					}
					else {
						loginLabel.setText("Please select a role");
					}
				}

		});
		loginButton.setBounds(98, 301, 141, 26);
		frame.getContentPane().add(loginButton);
		getFrame().getContentPane().add(loginButton);
		
		ButtonGroup group = new ButtonGroup();
		group.add(cookButton);
		group.add(waiterButton);
		group.add(managerButton);
	}

	public JFrame getFrame() {
		return frame;
	}

	public void setFrame(JFrame frame) {
		this.frame = frame;
		frame.getContentPane().setBackground(new Color(135, 206, 235));
	}
}