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
import java.sql.Connection;

import Functions.RegisterFunction;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class RegisterGUI {

	private JFrame frame;
	private JTextField idTextfield;
	private JTextField pwdTextfield;

	public RegisterGUI(Connection conn) {
		initialize(conn);
	}

	private void initialize(Connection conn) {
		setFrame(new JFrame());
		getFrame().setBounds(100, 100, 442, 346);
		getFrame().setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		getFrame().getContentPane().setLayout(null);
		
		JLabel registerLabel = new JLabel("Register");
		registerLabel.setFont(new Font("Tahoma", Font.BOLD, 24));
		registerLabel.setHorizontalAlignment(SwingConstants.CENTER);
		registerLabel.setBounds(145, 11, 135, 36);
		getFrame().getContentPane().add(registerLabel);
		
		JRadioButton cookButton = new JRadioButton("Cook");
		cookButton.setBounds(158, 172, 109, 23);
		getFrame().getContentPane().add(cookButton);
		
		JRadioButton waiterButton = new JRadioButton("Waiter");
		waiterButton.setBounds(158, 198, 109, 23);
		getFrame().getContentPane().add(waiterButton);
		
		JRadioButton managerButton = new JRadioButton("Manager");
		managerButton.setBounds(158, 224, 109, 23);
		getFrame().getContentPane().add(managerButton);
		
		ButtonGroup radio = new ButtonGroup();
		radio.add(cookButton);
		radio.add(waiterButton);
		radio.add(managerButton);
		
		JLabel idLabel = new JLabel("ID:");
		idLabel.setBounds(140, 84, 146, 14);
		getFrame().getContentPane().add(idLabel);
		
		idTextfield = new JTextField();
		idTextfield.setColumns(10);
		idTextfield.setBounds(140, 98, 146, 20);
		getFrame().getContentPane().add(idTextfield);
		
		pwdTextfield = new JTextField();
		pwdTextfield.setColumns(10);
		pwdTextfield.setBounds(140, 141, 146, 20);
		getFrame().getContentPane().add(pwdTextfield);
		
		JLabel pwdLabel = new JLabel("Password:");
		pwdLabel.setBounds(140, 127, 146, 14);
		getFrame().getContentPane().add(pwdLabel);
		
		JLabel messageLabel = new JLabel("");
		messageLabel.setHorizontalAlignment(SwingConstants.CENTER);
		messageLabel.setBounds(10, 50, 406, 23);
		getFrame().getContentPane().add(messageLabel);
		
		JButton registerButton = new JButton("Register");
		registerButton.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				String registerMessage = "";
				if(cookButton.isSelected()) {
					try {
						registerMessage = RegisterFunction.register(idTextfield.getText(), pwdTextfield.getText(), 0, conn);
					} catch (IOException e1) {
						e1.printStackTrace();
					}
				}
				else if(waiterButton.isSelected()) {
					try {
						registerMessage = RegisterFunction.register(idTextfield.getText(), pwdTextfield.getText(), 1, conn);
					} catch (IOException e1) {
						e1.printStackTrace();
					}
				}
				else {
					try {
						registerMessage = RegisterFunction.register(idTextfield.getText(), pwdTextfield.getText(), 2, conn);
					} catch (IOException e1) {
						e1.printStackTrace();
					}
				}
				messageLabel.setText(registerMessage);
			}
		});
		registerButton.setBounds(152, 254, 122, 23);
		getFrame().getContentPane().add(registerButton);
		
	}

	public JFrame getFrame() {
		return frame;
	}

	public void setFrame(JFrame frame) {
		this.frame = frame;
	}
	
}
