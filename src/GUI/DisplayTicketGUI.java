package GUI;

import java.awt.Color;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.Window;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.sql.Connection;
import java.util.ArrayList;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.Timer;
import javax.swing.border.EmptyBorder;

import Assets.FoodItem;
import Assets.Ticket;
import Functions.AddMenuItemFunction;
import Functions.CreateTicketFunctions;
import javax.swing.JPanel;
import javax.swing.JButton;

public class DisplayTicketGUI {

	private JFrame frame;
	JPanel contentPane;
	public static JTextArea itemList = new JTextArea();
	ArrayList<Ticket> itemTexts = new ArrayList<Ticket>();

	Timer timer = new Timer(0, null);

	/**
	 * Create the application.
	 * @param conn 
	 */
	public DisplayTicketGUI(Connection conn) {
		initialize(conn);
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize(Connection conn) {
		
		timer = new Timer(1000, new ActionListener() {
			boolean stop = false;
			@Override
			public void actionPerformed(ActionEvent evt) {
			//Refresh the panel
				itemTexts = CreateTicketFunctions.retrieveTicket(conn);
				itemList.setText("");
				for(int i=0;i<itemTexts.size();i++) {
					itemList.setText(itemList.getText() + "\nTicket: " + itemTexts.get(i).getId() + "\n " + itemTexts.get(i).getFood().get(i).getName());
					//System.out.println(itemTexts.size());
					//System.out.println(i);
					//System.out.println(itemTexts.get(i).getId());
				}
			    frame.revalidate();	
	        }
		});
		
		frame = new JFrame();
		frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		frame.getContentPane().setBackground(new Color(135, 206, 235));
		frame.setBounds(100, 100, 469, 452);
		contentPane = new JPanel();
		contentPane.setBackground(new Color(135, 206, 235));
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		frame.setContentPane(contentPane);
		contentPane.setLayout(null);
		

		JScrollPane scrollPane = new JScrollPane();//adding to our scrollpane
		scrollPane.setBounds(0, 0, 283, 381);
		contentPane.add(scrollPane);
		
		itemList = new JTextArea();
		scrollPane.setViewportView(itemList);
		itemList.setForeground(Color.BLUE);
		itemList.setFont(new Font("Monospaced", Font.BOLD, 16));
		itemList.setEditable(false);
		
		itemList.setText("test");
		
		JButton btnNewButton = new JButton("Clear Item");
		btnNewButton.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent arg0) {
				try {
					CreateTicketFunctions.setTicketComplete(itemTexts.get(0).getId(), conn);
				} catch (IndexOutOfBoundsException e) {
					//no items to remove, do nothing
				}

			}
		});
		btnNewButton.setBounds(291, 21, 141, 35);
		contentPane.add(btnNewButton);
		
		JButton btnExit = new JButton("Exit");
		btnExit.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent arg0) {
				timer.stop();
				frame.dispose();
				try {
					LoginGUI window = new LoginGUI(conn);
					window.getFrame().setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
		btnExit.setBounds(291, 72, 141, 35);
		contentPane.add(btnExit);
		
		timer.setInitialDelay(0);
		timer.start();

	}

	public JFrame getFrame() {
		return frame;
	}

	public void setFrame(JFrame frame) {
		this.frame = frame;
	}
}
