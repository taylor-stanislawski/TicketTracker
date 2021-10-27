package GUI;

import java.awt.EventQueue;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.SwingConstants;
import java.awt.Font;
import javax.swing.JTextField;
import javax.swing.JRadioButton;
import javax.swing.ButtonGroup;
import javax.swing.JButton;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.IOException;

import Functions.RegisterFunction;

public class RegisterGUI {

	private JFrame frame;
	private JTextField idTextfield;
	private JTextField pwdTextfield;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					RegisterGUI window = new RegisterGUI();
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
	public RegisterGUI() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame();
		frame.setBounds(100, 100, 442, 346);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		
		JLabel registerLabel = new JLabel("Register");
		registerLabel.setFont(new Font("Tahoma", Font.BOLD, 24));
		registerLabel.setHorizontalAlignment(SwingConstants.CENTER);
		registerLabel.setBounds(145, 11, 135, 36);
		frame.getContentPane().add(registerLabel);
		
		JRadioButton cookButton = new JRadioButton("Cook");
		cookButton.setBounds(158, 172, 109, 23);
		frame.getContentPane().add(cookButton);
		
		JRadioButton waiterButton = new JRadioButton("Waiter");
		waiterButton.setBounds(158, 198, 109, 23);
		frame.getContentPane().add(waiterButton);
		
		JRadioButton managerButton = new JRadioButton("Manager");
		managerButton.setBounds(158, 224, 109, 23);
		frame.getContentPane().add(managerButton);
		
		ButtonGroup radio = new ButtonGroup();
		radio.add(cookButton);
		radio.add(waiterButton);
		radio.add(managerButton);
		
		JLabel idLabel = new JLabel("ID:");
		idLabel.setBounds(140, 84, 146, 14);
		frame.getContentPane().add(idLabel);
		
		idTextfield = new JTextField();
		idTextfield.setColumns(10);
		idTextfield.setBounds(140, 98, 146, 20);
		frame.getContentPane().add(idTextfield);
		
		pwdTextfield = new JTextField();
		pwdTextfield.setColumns(10);
		pwdTextfield.setBounds(140, 141, 146, 20);
		frame.getContentPane().add(pwdTextfield);
		
		JLabel pwdLabel = new JLabel("Password:");
		pwdLabel.setBounds(140, 127, 146, 14);
		frame.getContentPane().add(pwdLabel);
		
		JLabel messageLabel = new JLabel("");
		messageLabel.setHorizontalAlignment(SwingConstants.CENTER);
		messageLabel.setBounds(10, 50, 406, 23);
		frame.getContentPane().add(messageLabel);
		
		JButton registerButton = new JButton("Register");
		registerButton.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				String registerMessage = "";
				if(cookButton.isSelected()) {
					try {
						registerMessage = RegisterFunction.register(idTextfield.getText(), pwdTextfield.getText(), 0);
					} catch (IOException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
				}
				else if(waiterButton.isSelected()) {
					try {
						registerMessage = RegisterFunction.register(idTextfield.getText(), pwdTextfield.getText(), 1);
					} catch (IOException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
				}
				else {
					try {
						registerMessage = RegisterFunction.register(idTextfield.getText(), pwdTextfield.getText(), 2);
					} catch (IOException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
				}
				messageLabel.setText(registerMessage);
			}
		});
		registerButton.setBounds(152, 254, 122, 23);
		frame.getContentPane().add(registerButton);
		
	}
	
}
