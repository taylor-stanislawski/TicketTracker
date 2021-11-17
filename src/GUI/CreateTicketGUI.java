package GUI;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.SwingConstants;
import javax.swing.JList;
import java.awt.Font;

public class CreateTicketGUI {

	private JFrame frame;

	/**
	 * Launch the application.
	 */
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
	}

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
		createTicketLabel.setFont(new Font("Tahoma", Font.BOLD, 24));
		createTicketLabel.setHorizontalAlignment(SwingConstants.CENTER);
		createTicketLabel.setBounds(10, 11, 433, 34);
		frame.getContentPane().add(createTicketLabel);
		
		JList<String> addedItems = new JList<>()
	}
}
