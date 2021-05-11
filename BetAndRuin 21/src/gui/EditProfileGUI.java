package gui;

import java.awt.BorderLayout;

import java.awt.EventQueue;
import java.awt.Font;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.text.View;

import businessLogic.BlFacade;
import businessLogic.BlFacadeImplementation;

import javax.swing.JLabel;
import javax.swing.SwingConstants;
import javax.swing.JTextField;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.JTextArea;

public class EditProfileGUI extends JFrame {

	private JPanel contentPane;
	private JTextField txtUsername;
	private JTextField txtPassword;
	private JTextField txtBankaccount;
	
	private BlFacade businessLogic;
	private ViewProfileGUI prevFrame;

	public void setBusinessLogic(BlFacade bl) {
		businessLogic = bl;
	}
	
	/**
	 * Gets the previous frame
	 * 
	 * @param frame
	 */
	public void previousFrame(ViewProfileGUI frame) {

		prevFrame = frame;
	}

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					EditProfileGUI frame = new EditProfileGUI();
					frame.setBusinessLogic(new BlFacadeImplementation());
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
	public EditProfileGUI() {
		
		this.setBusinessLogic(new BlFacadeImplementation());

		
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 464, 328);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lblTitlelabel = new JLabel("Edit Profile");
		lblTitlelabel.setBounds(5, 5, 440, 22);
		lblTitlelabel.setFont(new Font("Tahoma", Font.BOLD, 18));
		lblTitlelabel.setHorizontalAlignment(SwingConstants.CENTER);
		contentPane.add(lblTitlelabel);
		
		JLabel lblInfo = new JLabel(String.format("<html><body style=\"text-align: justify;  text-justify: inter-word;\">%s</body></html>", "Write the attributtes you want to modify, if you want to keep it as it is, leave it as it is, blank "));
		lblInfo.setHorizontalAlignment(SwingConstants.CENTER);
		lblInfo.setBounds(52, 39, 366, 54);
		contentPane.add(lblInfo);
		
		txtUsername = new JTextField();
		txtUsername.setBounds(197, 118, 148, 19);
		contentPane.add(txtUsername);
		txtUsername.setColumns(10);
		
		txtPassword = new JTextField();
		txtPassword.setBounds(197, 149, 148, 19);
		contentPane.add(txtPassword);
		txtPassword.setColumns(10);
		
		txtBankaccount = new JTextField();
		txtBankaccount.setBounds(197, 182, 221, 19);
		contentPane.add(txtBankaccount);
		txtBankaccount.setColumns(10);
		
		JLabel lblUname = new JLabel("New Username");
		lblUname.setBounds(52, 120, 128, 15);
		contentPane.add(lblUname);
		
		JLabel lblPass = new JLabel("New Password");
		lblPass.setBounds(52, 153, 128, 15);
		contentPane.add(lblPass);
		
		JLabel lblBank = new JLabel("New Bank Account");
		lblBank.setBounds(52, 184, 148, 15);
		contentPane.add(lblBank);
		
		JTextArea errorArea = new JTextArea();
		errorArea.setBounds(78, 208, 275, 34);
		contentPane.add(errorArea);
		
		JButton btnUpdateInfo = new JButton("Update Info");
		btnUpdateInfo.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				
				String uname, pass, bank;
				
				if(txtUsername.getText().equals(null)) {
					uname = " ";
				} else {
					uname = txtUsername.getText();
				}
						
				if(String.valueOf(txtPassword.getText()).equals(null)) {
					pass = " ";
				}else {
					pass = String.valueOf(txtPassword.getText());
				}
				
				if(txtBankaccount.getText().equals(null)) {
					bank = " ";
				}else {
					bank = txtBankaccount.getText();
				}
				System.out.println(uname + "" + pass + "" + bank);
				if(businessLogic.updateUserData(uname, pass, bank)==0) {
					errorArea.setText("There is already a user with that username,\n please enter a differnt one.");
				}else {
					errorArea.setText("Changes done!");
				}
				
				
			}
		});
		btnUpdateInfo.setBounds(265, 253, 117, 25);
		contentPane.add(btnUpdateInfo);
		
		JButton btnBack = new JButton("Back");
		btnBack.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				setVisible(false);
				prevFrame.setVisible(true);
			}
		});
		btnBack.setBounds(52, 253, 117, 25);
		contentPane.add(btnBack);
		
		
	}
}
