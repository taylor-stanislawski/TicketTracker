package GUI;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.SwingConstants;

import Functions.AddMenuItemFunction;

import java.awt.Font;
import javax.swing.JButton;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.IOException;

public class SettingsGUI {

	private JFrame frame;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					SettingsGUI window = new SettingsGUI();
					window.frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public SettingsGUI() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame();
		frame.setBounds(100, 100, 469, 276);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		
		JLabel lblNewLabel = new JLabel("Settings");
		lblNewLabel.setFont(new Font("Tahoma", Font.BOLD, 24));
		lblNewLabel.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel.setBounds(133, 11, 187, 35);
		frame.getContentPane().add(lblNewLabel);
		
		JButton btnNewButton = new JButton("Edit Menu");
		btnNewButton.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				AddMenuItemFunction re = new AddMenuItemFunction();
				String fileName = "MenuItemList.txt";
				                    try {
										re.AddItemsFromText(fileName);
									} catch (IOException e1) {
										// TODO Auto-generated catch block
										e1.printStackTrace();
									}

				AddMenuItemGUI re1 = new AddMenuItemGUI();
				                    re1.AddMenuItemGUI(re.FoodItems);//run gui with fooditems list
			}
		});
		btnNewButton.setBounds(157, 91, 138, 23);
		frame.getContentPane().add(btnNewButton);
		
		JButton btnEditEmployees = new JButton("Edit Employees");
		btnEditEmployees.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				
			}
		});
		btnEditEmployees.setBounds(157, 119, 138, 23);
		frame.getContentPane().add(btnEditEmployees);
	}
}
