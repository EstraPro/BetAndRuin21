package gui;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JTextArea;
import javax.swing.JButton;
import javax.swing.JTextField;
import javax.swing.JLabel;
import java.awt.Font;
import javax.swing.SwingConstants;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class InsertMoneyGUI extends JFrame {

	private JPanel contentPane;
	private JTextField InsertAmountField;
	
	private JFrame prevFrame;

	/**
	 * Gets the previous frame
	 * 
	 * @param frame
	 */
	public void previousFrame(JFrame frame) {

		prevFrame = frame;
	}

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					InsertMoneyGUI frame = new InsertMoneyGUI();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public InsertMoneyGUI() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JTextArea AnswertextArea = new JTextArea();
		AnswertextArea.setBounds(94, 154, 243, 70);
		contentPane.add(AnswertextArea);
		
		JButton btnClose = new JButton("Close");
		btnClose.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				prevFrame.setVisible(true);
				setVisible(false);
			}
		});
		btnClose.setBounds(52, 235, 89, 23);
		contentPane.add(btnClose);
		
		JButton btnInsert = new JButton("Insert");
		btnInsert.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				AnswertextArea.setText("<html> Done! <br> </html>"+ InsertAmountField.getText() +"€ added to your system account.");
			}
		});
		btnInsert.setBounds(292, 235, 89, 23);
		contentPane.add(btnInsert);
		
		InsertAmountField = new JTextField();
		InsertAmountField.setBounds(181, 108, 40, 23);
		contentPane.add(InsertAmountField);
		InsertAmountField.setColumns(10);
		
		JLabel lblTitle = new JLabel("Currency");
		lblTitle.setHorizontalAlignment(SwingConstants.CENTER);
		lblTitle.setFont(new Font("Tahoma", Font.PLAIN, 24));
		lblTitle.setBounds(57, 21, 324, 35);
		contentPane.add(lblTitle);
		
		JLabel lblExplanation = new JLabel("<html>Enter the desired amount of money to <br> the system:</html>");
		lblExplanation.setFont(new Font("Tahoma", Font.PLAIN, 15));
		lblExplanation.setBounds(94, 88, 280, 41);
		contentPane.add(lblExplanation);
	}
}
