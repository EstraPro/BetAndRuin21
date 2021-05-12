package gui;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import businessLogic.BlFacade;
import businessLogic.BlFacadeImplementation;

import javax.swing.JTextField;
import javax.swing.JLabel;
import javax.swing.SwingConstants;
import java.awt.Font;
import javax.swing.JButton;
import javax.swing.JTextArea;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class InsertResultsGUI extends JFrame {

	private JPanel contentPane;
	private JTextField textField;
	private BlFacade businessLogic;
	private MainGUI prevFrame;
	

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					InsertResultsGUI frame = new InsertResultsGUI();
					frame.setBusinessLogic(new BlFacadeImplementation());
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}
	
	public void setBusinessLogic(BlFacade checker) {
		businessLogic = checker;
	}

	/**
	 * Create the frame.
	 */
	public InsertResultsGUI() {
		
		this.setBusinessLogic(new BlFacadeImplementation());
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		textField = new JTextField();
		textField.setBounds(26, 99, 366, 20);
		contentPane.add(textField);
		textField.setColumns(10);
		
		JLabel lblFilePath = new JLabel("Introduce where the results file is placed in your computer:");
		lblFilePath.setBounds(26, 74, 380, 14);
		contentPane.add(lblFilePath);
		
		JLabel lblTitle = new JLabel("Results");
		lblTitle.setFont(new Font("Microsoft JhengHei UI", Font.PLAIN, 15));
		lblTitle.setHorizontalAlignment(SwingConstants.CENTER);
		lblTitle.setBounds(140, 11, 141, 37);
		contentPane.add(lblTitle);
		
		JButton btnSubmit = new JButton("Submit");
		btnSubmit.setBounds(303, 227, 89, 23);
		contentPane.add(btnSubmit);
		
		JTextArea errorArea = new JTextArea();
		errorArea.setBounds(26, 150, 366, 48);
		contentPane.add(errorArea);
		
		JButton btnClose = new JButton("Close");
		btnClose.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				setVisible(false);
				prevFrame.setVisible(true);
			}
		});
		btnClose.setBounds(26, 227, 89, 23);
		contentPane.add(btnClose);
	}
	
	/**
	 * Gets the previous frame
	 * @param frame
	 */
	public void previousFrame(MainGUI frame) {
		
		prevFrame = frame;
	}
}
