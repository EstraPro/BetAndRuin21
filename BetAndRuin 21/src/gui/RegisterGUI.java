package gui;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import dataAccess.DataAccess;

import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.JTextField;
import java.awt.Font;
import javax.swing.JTextArea;
import javax.swing.JTextPane;
import javax.swing.JLabel;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.JPasswordField;

public class RegisterGUI extends JFrame {

	private JPanel contentPane;
	private JTextField txtUsername;
	private JPasswordField txtPassword;
	private JPasswordField txtRetypePass;
	private DataAccess db = new DataAccess();

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					RegisterGUI frame = new RegisterGUI();
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
	public RegisterGUI() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 433, 396);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		txtUsername = new JTextField();
		txtUsername.setFont(new Font("Tahoma", Font.PLAIN, 15));
		txtUsername.setBounds(160, 126, 187, 28);
		contentPane.add(txtUsername);
		txtUsername.setColumns(10);
		
		txtPassword = new JPasswordField();
		txtPassword.setFont(new Font("Tahoma", Font.PLAIN, 15));
		txtPassword.setBounds(160, 175, 187, 28);
		contentPane.add(txtPassword);
		txtPassword.setColumns(10);
		
		txtRetypePass = new JPasswordField();
		txtRetypePass.setFont(new Font("Tahoma", Font.PLAIN, 15));
		txtRetypePass.setColumns(10);
		txtRetypePass.setBounds(160, 233, 187, 28);
		contentPane.add(txtRetypePass);
		
		JLabel UsernameLabel = new JLabel("Username: ");
		UsernameLabel.setFont(new Font("Tahoma", Font.PLAIN, 15));
		UsernameLabel.setBounds(63, 125, 105, 28);
		contentPane.add(UsernameLabel);
		
		JLabel lblPassword = new JLabel("Password:");
		lblPassword.setFont(new Font("Tahoma", Font.PLAIN, 15));
		lblPassword.setBounds(70, 174, 98, 28);
		contentPane.add(lblPassword);
		
		JLabel lblRetypePassword = new JLabel("Retype password:");
		lblRetypePassword.setFont(new Font("Tahoma", Font.PLAIN, 15));
		lblRetypePassword.setBounds(20, 232, 148, 28);
		contentPane.add(lblRetypePassword);
		
		JLabel RegisterLabel = new JLabel("Register");
		RegisterLabel.setFont(new Font("Segoe UI Semilight", Font.BOLD, 27));
		RegisterLabel.setBounds(143, 24, 154, 50);
		contentPane.add(RegisterLabel);
		
		JTextArea dispalyTxt = new JTextArea();
		dispalyTxt.setFont(new Font("Monospaced", Font.PLAIN, 15));
		dispalyTxt.setBounds(70, 319, 244, 41);
		contentPane.add(dispalyTxt);
		
		JButton DoneButton = new JButton("Done!");
		DoneButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				dispalyTxt.setText(null);
				if(String.valueOf(txtPassword.getPassword()).equals(String.valueOf(txtRetypePass.getPassword()))) {
					db.storeUser(txtUsername.getText(), String.valueOf(txtPassword.getPassword()));
					dispalyTxt.setText("Registered!");
			}else {
				dispalyTxt.setText("Password MissMatch($·$)");
			}
		}
		});
		DoneButton.setFont(new Font("Tahoma", Font.PLAIN, 14));
		DoneButton.setBounds(132, 289, 119, 23);
		contentPane.add(DoneButton);
		
		
	}
}

