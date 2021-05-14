package gui;

import java.awt.EventQueue;


import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import businessLogic.BlFacade;
import businessLogic.BlFacadeImplementation;


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
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

public class LoginGUI extends JFrame {

	private static final long serialVersionUID = 1L;

	private JPanel contentPane;
	private JTextField UsnametextField;
	private JPasswordField passwordField;
	private BlFacade businessLogic;
	private MainGUI prevFrame;

	public void setBusinessLogic(BlFacade checker) {
		businessLogic = checker;
	}

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					LoginGUI frame = new LoginGUI();
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
		
		JTextArea outputMessageArea = new JTextArea();
		outputMessageArea.setBackground(SystemColor.control);

		passwordField = new JPasswordField();
		passwordField.addKeyListener(new KeyAdapter() {
			@Override
			public void keyPressed(KeyEvent e) {
				if(e.getKeyCode() == KeyEvent.VK_ENTER) {
					if (!businessLogic.getUserLogged(UsnametextField.getText()).isLoggedIn()) {	//Enter key pressed
						
						// administratzailea baldin bada bertze use case bat ireki
						if (businessLogic.checkCredentialsAdmin(UsnametextField.getText(),
								String.valueOf(passwordField.getPassword()))) {
							setVisible(false);
							prevFrame.setVisible(true);
							prevFrame.getBtnLogin().setVisible(false);
							prevFrame.getBtnRegister().setVisible(false);
							prevFrame.getBifunctionalBtn().setVisible(true);
							prevFrame.getBifunctionalBtn().setText("Create Questions");
							prevFrame.getBtnInsertResults().setVisible(true);
							prevFrame.setUsername(UsnametextField.getText());
						}

						// userra baldin bada use case bat ireki
						else if (businessLogic.checkCredentialsUser(UsnametextField.getText(),
								String.valueOf(passwordField.getPassword()))) {
							setVisible(false);
							prevFrame.setVisible(true);
							prevFrame.getBtnLogin().setVisible(false);
							prevFrame.getBtnRegister().setVisible(false);
							prevFrame.getBifunctionalBtn().setVisible(true);
							prevFrame.getBifunctionalBtn().setText("View Profile");
							prevFrame.getBtnInsertResults().setVisible(false);
							prevFrame.setUsername(UsnametextField.getText());
							businessLogic.markLogin(UsnametextField.getText(),String.valueOf(passwordField.getPassword()));
						}
						
						else {
							outputMessageArea.insert("                                                    ", 0);
							outputMessageArea.insert("Insert correct credentials!!", 0);
						}
						
					}else {
						outputMessageArea.setText("");
						outputMessageArea.setText(UsnametextField.getText()+" already logged!");
					}
				}
				
			}
		});
		passwordField.setBackground(new Color(255, 255, 255));

		JButton btnNewButton = new JButton("LOGIN");
		btnNewButton.setForeground(Color.BLACK);
		btnNewButton.setBackground(SystemColor.controlShadow);
		btnNewButton.addActionListener(new ActionListener() {

			// LOGIN botoia

			public void actionPerformed(ActionEvent e) {
				
				if(!businessLogic.getUserLogged(UsnametextField.getText()).isLoggedIn()) {
					// administratzailea baldin bada bertze use case bat ireki
					if (businessLogic.checkCredentialsAdmin(UsnametextField.getText(),
							String.valueOf(passwordField.getPassword()))) {
						setVisible(false);
						prevFrame.setVisible(true);
						prevFrame.getBtnLogin().setVisible(false);
						prevFrame.getBtnRegister().setVisible(false);
						prevFrame.getBifunctionalBtn().setVisible(true);
						prevFrame.getBifunctionalBtn().setText("Create Questions");
						prevFrame.getBtnInsertResults().setVisible(true);
						prevFrame.setUsername(UsnametextField.getText());
					}

					// userra baldin bada use case bat ireki
					else if (businessLogic.checkCredentialsUser(UsnametextField.getText(),
							String.valueOf(passwordField.getPassword()))) {
						setVisible(false);
						prevFrame.setVisible(true);
						prevFrame.getBtnLogin().setVisible(false);
						prevFrame.getBtnRegister().setVisible(false);
						prevFrame.getBifunctionalBtn().setVisible(true);
						prevFrame.getBifunctionalBtn().setText("View Profile");
						prevFrame.getBtnInsertResults().setVisible(false);
						prevFrame.setUsername(UsnametextField.getText());
						businessLogic.markLogin(UsnametextField.getText(),String.valueOf(passwordField.getPassword()));
					}

					else {
						outputMessageArea.insert("                                                    ", 0);
						outputMessageArea.insert("Insert correct credentials!!", 0);
					}
				}else {
					outputMessageArea.setText("");
					outputMessageArea.setText(UsnametextField.getText()+" already logged!");
				}
					
			}
		});

		JLabel lblNewLabel = new JLabel("SIGN IN");
		lblNewLabel.setFont(new Font("Tahoma", Font.BOLD, 18));
		
		JButton btnClose = new JButton("Close");
		btnClose.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				dispose();
				prevFrame.setVisible(true);
				prevFrame.setUsername(null);
			}
		});

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
				.addGroup(Alignment.LEADING, gl_contentPane.createSequentialGroup()
					.addContainerGap()
					.addComponent(btnClose)
					.addContainerGap(325, Short.MAX_VALUE))
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
					.addGroup(gl_contentPane.createParallelGroup(Alignment.TRAILING)
						.addGroup(gl_contentPane.createSequentialGroup()
							.addGap(32)
							.addComponent(btnNewButton)
							.addPreferredGap(ComponentPlacement.RELATED)
							.addComponent(outputMessageArea, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
							.addContainerGap(24, Short.MAX_VALUE))
						.addGroup(gl_contentPane.createSequentialGroup()
							.addPreferredGap(ComponentPlacement.RELATED)
							.addComponent(btnClose))))
		);
		contentPane.setLayout(gl_contentPane);
	}
	
	/**
	 * Gets the previous frame
	 * @param frame
	 */
	public void previousFrame(MainGUI frame) {
		
		prevFrame = frame;
	}
}