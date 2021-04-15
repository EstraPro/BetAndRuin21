package gui;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.BoxLayout;
import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.JScrollPane;
import javax.swing.JButton;
import java.awt.Font;
import javax.swing.SwingConstants;
import javax.swing.JLabel;
import javax.swing.JList;

public class ViewProfileGUI extends JFrame {

	private JPanel contentPane;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					ViewProfileGUI frame = new ViewProfileGUI();
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
	public ViewProfileGUI() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 472, 360);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JScrollPane showBetscrollPane = new JScrollPane();
		showBetscrollPane.setBounds(10, 138, 446, 128);
		contentPane.add(showBetscrollPane);
		
		JList Betlist = new JList();
		showBetscrollPane.setViewportView(Betlist);
		
		
		JPanel UserInfopanel = new JPanel();
		UserInfopanel.setBounds(10, 11, 297, 116);
		contentPane.add(UserInfopanel);
		UserInfopanel.setLayout(null);
		
		JLabel NameLbl = new JLabel("Name:");
		NameLbl.setFont(new Font("Tahoma", Font.PLAIN, 11));
		NameLbl.setBounds(10, 11, 136, 25);
		UserInfopanel.add(NameLbl);
		
		JLabel lblEmail = new JLabel("E-Mail:");
		lblEmail.setBounds(10, 47, 136, 26);
		UserInfopanel.add(lblEmail);
		
		JLabel lblBankAccount = new JLabel("Bank Account:");
		lblBankAccount.setBounds(10, 84, 277, 21);
		UserInfopanel.add(lblBankAccount);
		
		JLabel lblUsername = new JLabel("Username:");
		lblUsername.setBounds(156, 11, 131, 25);
		UserInfopanel.add(lblUsername);
		
		JLabel lblBirthDate = new JLabel("Birth Date:");
		lblBirthDate.setBounds(156, 47, 131, 26);
		UserInfopanel.add(lblBirthDate);
		
		JButton InsertMoneyButton = new JButton("Insert Money");
		InsertMoneyButton.setFont(new Font("Tahoma", Font.PLAIN, 13));
		InsertMoneyButton.setBounds(327, 93, 107, 35);
		contentPane.add(InsertMoneyButton);
		
		JLabel currentMoneytextLbl = new JLabel("Current Money:");
		currentMoneytextLbl.setFont(new Font("Tahoma", Font.PLAIN, 13));
		currentMoneytextLbl.setBounds(317, 11, 90, 35);
		contentPane.add(currentMoneytextLbl);
		
		JLabel lblMoneyShow = new JLabel("Money");
		lblMoneyShow.setFont(new Font("Yu Gothic UI Semibold", Font.PLAIN, 14));
		lblMoneyShow.setHorizontalAlignment(SwingConstants.CENTER);
		lblMoneyShow.setBounds(327, 47, 107, 35);
		contentPane.add(lblMoneyShow);
		
		JButton btnBack = new JButton("Back");
		btnBack.setFont(new Font("Tahoma", Font.PLAIN, 12));
		btnBack.setBounds(55, 287, 89, 23);
		contentPane.add(btnBack);
		
		JButton DeleteBetbtn = new JButton("Delete Bet");
		DeleteBetbtn.setFont(new Font("Tahoma", Font.PLAIN, 12));
		DeleteBetbtn.setBounds(318, 288, 107, 23);
		contentPane.add(DeleteBetbtn);
	}
}
