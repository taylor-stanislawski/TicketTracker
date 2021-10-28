package GUI;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.SwingConstants;
import java.awt.Font;
import javax.swing.JTextField;
import javax.swing.JButton;
import javax.swing.JRadioButton;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.File;
import java.io.IOException;

import Functions.RemoveEmployeeFunction;

public class RemoveEmployeeGUI {

	private JFrame frame;
	private JTextField idTextfield;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					RemoveEmployeeGUI window = new RemoveEmployeeGUI();
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
	public RemoveEmployeeGUI() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		setFrame(new JFrame());
		getFrame().setBounds(100, 100, 500, 350);
		getFrame().setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		getFrame().getContentPane().setLayout(null);
		
		JLabel removeEmployeeLabel = new JLabel("Remove Employee");
		removeEmployeeLabel.setFont(new Font("Tahoma", Font.BOLD, 24));
		removeEmployeeLabel.setHorizontalAlignment(SwingConstants.CENTER);
		removeEmployeeLabel.setBounds(87, 11, 310, 47);
		getFrame().getContentPane().add(removeEmployeeLabel);
		
		JLabel messageLabel = new JLabel("message");
		messageLabel.setHorizontalAlignment(SwingConstants.CENTER);
		messageLabel.setBounds(10, 69, 464, 14);
		getFrame().getContentPane().add(messageLabel);
		
		idTextfield = new JTextField();
		idTextfield.setBounds(159, 121, 165, 20);
		getFrame().getContentPane().add(idTextfield);
		idTextfield.setColumns(10);
		
		JLabel idLabel = new JLabel("ID:");
		idLabel.setBounds(159, 106, 165, 14);
		getFrame().getContentPane().add(idLabel);
		
		JRadioButton cookButton = new JRadioButton("Cook");
		cookButton.setSelected(true);
		cookButton.setBounds(187, 166, 109, 23);
		getFrame().getContentPane().add(cookButton);
		
		JRadioButton waiterButton = new JRadioButton("Waiter");
		waiterButton.setBounds(187, 192, 109, 23);
		getFrame().getContentPane().add(waiterButton);
		
		JRadioButton managerButton = new JRadioButton("Manager");
		managerButton.setBounds(187, 218, 109, 23);
		getFrame().getContentPane().add(managerButton);
		
		JButton removeEmployeeButton = new JButton("Remove Employee");
		removeEmployeeButton.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				String message = "";
				if(cookButton.isSelected()) {
					File file = new File("Cooks");
					try {
						message = RemoveEmployeeFunction.remove(idTextfield.getText(), file);
					} catch (IOException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
				}
				else if(cookButton.isSelected()) {
					File file = new File("Waiters");
					try {
						message = RemoveEmployeeFunction.remove(idTextfield.getText(), file);
					} catch (IOException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
				}
				else {
					File file = new File("Managers");
					try {
						message = RemoveEmployeeFunction.remove(idTextfield.getText(), file);
					} catch (IOException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
				}
				messageLabel.setText(message);
			}
		});
		removeEmployeeButton.setBounds(159, 266, 165, 23);
		getFrame().getContentPane().add(removeEmployeeButton);
	}

	public JFrame getFrame() {
		return frame;
	}

	public void setFrame(JFrame frame) {
		this.frame = frame;
	}

}
