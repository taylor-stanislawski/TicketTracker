package GUI;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.SwingConstants;
import javax.swing.JList;
import java.awt.Font;
import javax.swing.JScrollPane;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

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
		
		JScrollPane ticketScrollPane = new JScrollPane();
		ticketScrollPane.setBounds(169, 56, 274, 346);
		frame.getContentPane().add(ticketScrollPane);
		
		JButton submitTicketButton = new JButton("Submit");
		submitTicketButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				//CookTicketsGUI cookTickets = new CookTicketsGUI(ticket);
				//cookTickets.getFrame().setVisible(true);
			}
		});
		submitTicketButton.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
			}
		});
		submitTicketButton.setBounds(10, 90, 149, 23);
		frame.getContentPane().add(submitTicketButton);
		
		JButton clearTicketButton = new JButton("Clear");
		clearTicketButton.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
			}
		});
		clearTicketButton.setBounds(10, 124, 149, 23);
		frame.getContentPane().add(clearTicketButton);
		
		JButton exitGUIButton = new JButton("Exit");
		exitGUIButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				System.exit(0);
			}
		});
		exitGUIButton.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
			}
		});
		exitGUIButton.setBounds(10, 158, 149, 23);
		frame.getContentPane().add(exitGUIButton);
		
		JButton editTicketButton = new JButton("Edit Ticket");
		editTicketButton.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
			}
		});
		editTicketButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				//EditTicketGUI editTicket = new EditTicketGUI();
				//editTicket.getFrame().setVisible(true);
			}
		});
		editTicketButton.setBounds(10, 56, 149, 23);
		frame.getContentPane().add(editTicketButton);
		
		JList<String> addedItems = new JList<>();
	}
}
