package gui;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import businessLogic.UserManager;
import dataAccess.DataAccess;

import javax.swing.JTextField;
import java.awt.Font;
import javax.swing.JTextArea;
import javax.swing.JLabel;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.util.jar.Attributes.Name;
import java.awt.event.ActionEvent;
import javax.swing.JPasswordField;
import javax.swing.JComboBox;
import com.toedter.calendar.JDateChooser;

public class RegisterGUI extends JFrame {

	private static final long serialVersionUID = 1L;

	private JPanel contentPane;
	private JTextField txtUsername;
	private JPasswordField txtPassword;
	private JPasswordField txtRetypePass;
	MainGUI prevFrame;

	private UserManager businessLogic;
	private JTextField EmailField;
	private JTextField NameField;
	private JTextField SurnameField;

	public void setBusinessLogic(UserManager checker) {
		businessLogic = checker;
	}

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					RegisterGUI frame = new RegisterGUI();
					UserManager bl = new UserManager();
					frame.setBusinessLogic(bl);
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
		UserManager bl = new UserManager();
		this.setBusinessLogic(bl);
		
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 536, 520);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);

		txtUsername = new JTextField();
		txtUsername.setFont(new Font("Tahoma", Font.PLAIN, 15));
		txtUsername.setBounds(188, 263, 235, 28);
		contentPane.add(txtUsername);
		txtUsername.setColumns(10);

		txtPassword = new JPasswordField();
		txtPassword.setFont(new Font("Tahoma", Font.PLAIN, 15));
		txtPassword.setBounds(188, 312, 235, 28);
		contentPane.add(txtPassword);
		txtPassword.setColumns(10);

		txtRetypePass = new JPasswordField();
		txtRetypePass.setFont(new Font("Tahoma", Font.PLAIN, 15));
		txtRetypePass.setColumns(10);
		txtRetypePass.setBounds(188, 359, 235, 28);
		contentPane.add(txtRetypePass);

		JLabel UsernameLabel = new JLabel("Username: ");
		UsernameLabel.setFont(new Font("Tahoma", Font.PLAIN, 15));
		UsernameLabel.setBounds(52, 262, 105, 28);
		contentPane.add(UsernameLabel);

		JLabel lblPassword = new JLabel("Password:");
		lblPassword.setFont(new Font("Tahoma", Font.PLAIN, 15));
		lblPassword.setBounds(59, 311, 79, 28);
		contentPane.add(lblPassword);

		JLabel lblRetypePassword = new JLabel("Retype password:");
		lblRetypePassword.setFont(new Font("Tahoma", Font.PLAIN, 15));
		lblRetypePassword.setBounds(10, 358, 183, 28);
		contentPane.add(lblRetypePassword);

		JLabel RegisterLabel = new JLabel("Register");
		RegisterLabel.setFont(new Font("Segoe UI Semilight", Font.BOLD, 27));
		RegisterLabel.setBounds(200, 11, 154, 50);
		contentPane.add(RegisterLabel);

		JTextArea dispalyTxt = new JTextArea();
		dispalyTxt.setFont(new Font("Monospaced", Font.PLAIN, 15));
		dispalyTxt.setBounds(62, 488, 244, 41);
		contentPane.add(dispalyTxt);

		JButton DoneButton = new JButton("Done!");
		DoneButton.setFont(new Font("Tahoma", Font.PLAIN, 14));
		DoneButton.setBounds(200, 443, 119, 23);
		contentPane.add(DoneButton);
		
		JLabel lblFirstName = new JLabel("Name:");
		lblFirstName.setFont(new Font("Tahoma", Font.PLAIN, 15));
		lblFirstName.setBounds(39, 94, 79, 28);
		contentPane.add(lblFirstName);
		
		JLabel lblSurname = new JLabel("Surname:");
		lblSurname.setFont(new Font("Tahoma", Font.PLAIN, 15));
		lblSurname.setBounds(278, 94, 79, 28);
		contentPane.add(lblSurname);
		
		JLabel lblEmail = new JLabel("Email:");
		lblEmail.setFont(new Font("Tahoma", Font.PLAIN, 15));
		lblEmail.setBounds(84, 207, 54, 28);
		contentPane.add(lblEmail);
		
		JLabel lblDateBirth = new JLabel("Date of Birth:");
		lblDateBirth.setFont(new Font("Tahoma", Font.PLAIN, 15));
		lblDateBirth.setBounds(32, 156, 97, 28);
		contentPane.add(lblDateBirth);
		
		JDateChooser dateChooser = new JDateChooser();
		dateChooser.setBounds(188, 156, 235, 28);
		contentPane.add(dateChooser);
		
		EmailField = new JTextField();
		EmailField.setFont(new Font("Tahoma", Font.PLAIN, 15));
		EmailField.setColumns(10);
		EmailField.setBounds(188, 208, 235, 28);
		contentPane.add(EmailField);
		
		NameField = new JTextField();
		NameField.setFont(new Font("Tahoma", Font.PLAIN, 15));
		NameField.setColumns(10);
		NameField.setBounds(96, 95, 154, 28);
		contentPane.add(NameField);
		
		SurnameField = new JTextField();
		SurnameField.setFont(new Font("Tahoma", Font.PLAIN, 15));
		SurnameField.setColumns(10);
		SurnameField.setBounds(356, 94, 154, 28);
		contentPane.add(SurnameField);
		
		DoneButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				dispalyTxt.setText(null);

				if (businessLogic.passwdMatches(String.valueOf(txtPassword.getPassword()),
						String.valueOf(txtRetypePass.getPassword()))) {
					businessLogic.storeUser(txtUsername.getText(), String.valueOf(txtPassword.getPassword()),dateChooser.getDate(), NameField.getText(), SurnameField.getText(), EmailField.getText());
					dispalyTxt.setText("Registered!");
				
					setVisible(false);
					prevFrame.setVisible(true);
					
				} else {
					dispalyTxt.setText("Password MissMatch($·$)");
				}
			}
		});

	}
	
	/**
	 * Gets the previous frame
	 * @param frame
	 */
	public void previousFrame(MainGUI frame) {
		
		prevFrame = frame;
	}
}
