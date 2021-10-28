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
					window.getFrame().setVisible(true);
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
		setFrame(new JFrame());
		getFrame().setBounds(100, 100, 469, 276);
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
		editMenuButton.setBounds(157, 81, 138, 23);
		getFrame().getContentPane().add(editMenuButton);
		
		JButton addEmployeeButton = new JButton("Add Employee");
		addEmployeeButton.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				RegisterGUI register = new RegisterGUI();
				register.getFrame().setVisible(true);
			}
		});
		addEmployeeButton.setBounds(157, 107, 138, 23);
		frame.getContentPane().add(addEmployeeButton);
		
		JButton removeEmployeeButton = new JButton("Remove Employee");
		removeEmployeeButton.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				RemoveEmployeeGUI remove = new RemoveEmployeeGUI();
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
							LoginGUI window = new LoginGUI();
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
