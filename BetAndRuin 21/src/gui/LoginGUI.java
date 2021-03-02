package gui;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import businessLogic.PassChecker;

import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.LayoutStyle.ComponentPlacement;
import javax.swing.JPasswordField;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.Color;
import javax.swing.JTextArea;
import java.awt.SystemColor;
import java.awt.Font;

public class LoginGUI extends JFrame {

	private JPanel contentPane;
	private JTextField UsnametextField;
	private JPasswordField passwordField;
	private PassChecker businessLogic;
	public void setBusinessLogic(PassChecker checker) {
	businessLogic = checker; }

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					LoginGUI frame = new LoginGUI();
					frame.setVisible(true);
					PassChecker bl = new PassChecker();
					frame.setBusinessLogic(bl);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public LoginGUI() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		
		JLabel usernameLabel = new JLabel("Username:");
		
		JLabel passwordLabel = new JLabel("Password:");
		
		UsnametextField = new JTextField();
		UsnametextField.setColumns(10);
		
		passwordField = new JPasswordField();
		passwordField.setBackground(new Color(255, 255, 255));
		
		JTextArea outputMessageArea = new JTextArea();
		outputMessageArea.setBackground(SystemColor.control);
		
		JButton btnNewButton = new JButton("LOGIN");
		btnNewButton.setForeground(Color.BLACK);
		btnNewButton.setBackground(SystemColor.controlShadow);
		btnNewButton.addActionListener(new ActionListener() {
			
			//LOGIN botoia
			
			public void actionPerformed(ActionEvent e) {
				//userra baldin bada use case bat ireki
				if (businessLogic.checkCredentialsUser(UsnametextField.getText(),String.valueOf(passwordField.getPassword()))) {
					MainGUI Mainwindow = new MainGUI();
					Mainwindow.setVisible(true);
				}
				
				//administratzailea baldin bada bertze use case bat ireki
				else if (businessLogic.checkCredentialsAdmin(UsnametextField.getText(),String.valueOf(passwordField.getPassword()))) {
					MainGUI Mainwindow = new MainGUI();
					Mainwindow.setVisible(true);
				}
				
				else{
					outputMessageArea.insert("                                                    ", 0);
					outputMessageArea.insert("Insert correct credentials!!", 0);
					}
				}
		});
		
		JLabel lblNewLabel = new JLabel("SIGN IN");
		lblNewLabel.setFont(new Font("Tahoma", Font.BOLD, 18));
		
		
		GroupLayout gl_contentPane = new GroupLayout(contentPane);
		gl_contentPane.setHorizontalGroup(
			gl_contentPane.createParallelGroup(Alignment.TRAILING)
				.addGroup(gl_contentPane.createSequentialGroup()
					.addGap(35)
					.addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING)
						.addGroup(gl_contentPane.createSequentialGroup()
							.addGap(28)
							.addComponent(passwordLabel, GroupLayout.PREFERRED_SIZE, 95, GroupLayout.PREFERRED_SIZE)
							.addPreferredGap(ComponentPlacement.RELATED)
							.addComponent(passwordField, 145, 145, 145))
						.addGroup(gl_contentPane.createSequentialGroup()
							.addGap(27)
							.addComponent(usernameLabel, GroupLayout.PREFERRED_SIZE, 95, GroupLayout.PREFERRED_SIZE)
							.addPreferredGap(ComponentPlacement.RELATED)
							.addComponent(UsnametextField, GroupLayout.DEFAULT_SIZE, 146, Short.MAX_VALUE)))
					.addContainerGap(117, GroupLayout.PREFERRED_SIZE))
				.addGroup(gl_contentPane.createSequentialGroup()
					.addContainerGap(186, Short.MAX_VALUE)
					.addComponent(btnNewButton)
					.addGap(175))
				.addGroup(gl_contentPane.createSequentialGroup()
					.addContainerGap(134, Short.MAX_VALUE)
					.addComponent(outputMessageArea, GroupLayout.PREFERRED_SIZE, 253, GroupLayout.PREFERRED_SIZE)
					.addGap(37))
				.addGroup(gl_contentPane.createSequentialGroup()
					.addContainerGap(185, Short.MAX_VALUE)
					.addComponent(lblNewLabel, GroupLayout.PREFERRED_SIZE, 221, GroupLayout.PREFERRED_SIZE)
					.addGap(18))
		);
		gl_contentPane.setVerticalGroup(
			gl_contentPane.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_contentPane.createSequentialGroup()
					.addContainerGap()
					.addComponent(lblNewLabel, GroupLayout.PREFERRED_SIZE, 36, GroupLayout.PREFERRED_SIZE)
					.addGap(18)
					.addGroup(gl_contentPane.createParallelGroup(Alignment.BASELINE)
						.addComponent(UsnametextField, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
						.addComponent(usernameLabel, GroupLayout.PREFERRED_SIZE, 22, GroupLayout.PREFERRED_SIZE))
					.addGap(35)
					.addGroup(gl_contentPane.createParallelGroup(Alignment.BASELINE)
						.addComponent(passwordField, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
						.addComponent(passwordLabel, GroupLayout.PREFERRED_SIZE, 22, GroupLayout.PREFERRED_SIZE))
					.addGap(32)
					.addComponent(btnNewButton)
					.addPreferredGap(ComponentPlacement.RELATED)
					.addComponent(outputMessageArea, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
					.addContainerGap(24, Short.MAX_VALUE))
		);
		contentPane.setLayout(gl_contentPane);
	}
}