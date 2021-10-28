package GUI;

import javax.swing.*;
import Functions.LoginFunction;
import java.awt.EventQueue;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class LoginGUI {

	private JFrame frame;

	public LoginGUI() {
		initialize();
	}

	/**
	 * Create the application.
     @ -41,39 +31,66 @@ public class loginGUI {
	 * Initialize the contents of the frame.
	 */
	
	
	private void initialize() {
		setFrame(new JFrame());
		getFrame().setBounds(100, 100, 353, 442);
		getFrame().setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		getFrame().getContentPane().setLayout(null);
		
		JLabel ticketLabel = new JLabel("Ticket Tracker Login");
		ticketLabel.setHorizontalAlignment(SwingConstants.CENTER);
		ticketLabel.setBounds(55, 0, 213, 26);
		frame.getContentPane().add(ticketLabel);
		getFrame().getContentPane().add(ticketLabel);
		
		JLabel IDLabel = new JLabel("Employee ID:");
		IDLabel.setHorizontalAlignment(SwingConstants.CENTER);
		IDLabel.setBounds(86, 34, 150, 26);
		frame.getContentPane().add(IDLabel);
		getFrame().getContentPane().add(IDLabel);
		
		JTextField IDField = new JTextField();
		IDField.setBounds(68, 61, 186, 32);
		getFrame().getContentPane().add(IDField);
		IDField.setColumns(10);
		
		JLabel passLabel = new JLabel("Password:");
		passLabel.setHorizontalAlignment(SwingConstants.CENTER);
		passLabel.setBounds(101, 107, 121, 26);
		frame.getContentPane().add(passLabel);
		getFrame().getContentPane().add(passLabel);
		
		JTextField passField = new JTextField();
		passField.setBounds(68, 136, 186, 32);
		getFrame().getContentPane().add(passField);
		passField.setColumns(10);
		
		JLabel loginLabel = new JLabel("");
		loginLabel.setHorizontalAlignment(SwingConstants.CENTER);
		loginLabel.setBounds(86, 339, 150, 26);
		frame.getContentPane().add(loginLabel);
		
		JRadioButton cookButton = new JRadioButton("Cook");
		cookButton.setBounds(63, 232, 201, 35);
		frame.getContentPane().add(cookButton);
		
		JRadioButton waiterButton = new JRadioButton("Waiter");
		waiterButton.setBounds(63, 266, 201, 35);
		frame.getContentPane().add(waiterButton);
		
		JRadioButton managerButton = new JRadioButton("Manager");
		managerButton.setBounds(63, 301, 201, 35);
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
							if(LoginFunction.login(ID, pass, "Cook") == true) {
								EventQueue.invokeLater(new Runnable() {
									public void run() {
										try {
											SettingsGUI window = new SettingsGUI();
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
							if(LoginFunction.login(ID, pass, "Waiter") == true) {
								EventQueue.invokeLater(new Runnable() {
									public void run() {
										try {
											SettingsGUI window = new SettingsGUI();
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
							if(LoginFunction.login(ID, pass, "Manager") == true) {
								EventQueue.invokeLater(new Runnable() {
									public void run() {
										try {
											SettingsGUI window = new SettingsGUI();
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
		loginButton.setBounds(91, 180, 141, 35);
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
	}
}