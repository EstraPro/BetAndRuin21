package gui;

import java.awt.BorderLayout;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import businessLogic.BlFacade;
import businessLogic.BlFacadeImplementation;
import domain.Bet;

import javax.swing.BoxLayout;
import javax.swing.DefaultListModel;
import javax.swing.GroupLayout;
import javax.swing.ImageIcon;
import javax.swing.GroupLayout.Alignment;
import javax.swing.JScrollPane;
import javax.swing.JButton;
import java.awt.Font;
import javax.swing.SwingConstants;
import javax.swing.JLabel;
import javax.swing.JList;
import java.awt.event.ActionListener;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.awt.event.ActionEvent;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;
import java.awt.GridBagLayout;
import java.awt.GridBagConstraints;
import java.awt.Insets;
import javax.swing.JTextArea;

public class ViewProfileGUI extends JFrame {
	
	private static String Username;

	private JPanel contentPane;

	private BlFacade businessLogic;

	private MainGUI prevFrame;

	private JTextArea ErrorMessageArea;

	private boolean bool = false;
	private JTable tableListBet;

	/**
	 * Gets the previous frame
	 * 
	 * @param frame
	 */
	public void previousFrame(MainGUI frame) {

		prevFrame = frame;
	}
	
	public String getUsername() {
		return Username;
	}

	public void setUsername(String username) {
		Username = username;
	}

	/*
	 * 
	 */
	public void setBusinessLogic(BlFacade checker) {
		businessLogic = checker;
	}

	public void setValueBool() {
		bool = true;
	}

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		System.out.println(Username);
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					ViewProfileGUI frame = new ViewProfileGUI(Username);
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
	public ViewProfileGUI(String User) {
		Username = User;
		this.setBusinessLogic(new BlFacadeImplementation());
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 853, 467);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);

		JScrollPane showBetscrollPane = new JScrollPane();
		showBetscrollPane.setBounds(10, 247, 817, 128);
		contentPane.add(showBetscrollPane);

		///////////////////////////////////////////////////// Labels of User Info
		JPanel UserInfopanel2 = new JPanel();
		UserInfopanel2.setBounds(10, 107, 267, 107);
		contentPane.add(UserInfopanel2);
		UserInfopanel2.setLayout(null);

		JLabel lblEmail = new JLabel("E-Mail:");
		lblEmail.setBounds(10, 11, 212, 26);
		UserInfopanel2.add(lblEmail);
		lblEmail.setText("E-Mail: " + businessLogic.getUserLogged(Username).getEmail());

		JLabel lblBankAccount = new JLabel("Bank Account:");
		lblBankAccount.setBounds(10, 45, 282, 21);
		UserInfopanel2.add(lblBankAccount);
		lblBankAccount.setText("Bank Account: " + businessLogic.getUserLogged(Username).getBankAccount());

		JLabel lblBirthDate = new JLabel("Birth Date:");
		lblBirthDate.setBounds(10, 77, 223, 26);
		UserInfopanel2.add(lblBirthDate);
		// To get the Date in the format we want
		String pattern = "MM / dd / yyyy";

		// Create an instance of SimpleDateFormat used for formatting
		// the string representation of date according to the chosen pattern
		DateFormat df = new SimpleDateFormat(pattern);

		// Using DateFormat format method we can create a string
		// representation of a date with the defined format.
		String birthDayAsString = df.format(businessLogic.getUserLogged(Username).getBirthDate());
		// finally set it to
		lblBirthDate.setText("Birth Date: " + birthDayAsString);

		JLabel lblMoneyShow = new JLabel("Money");
		lblMoneyShow.setFont(new Font("Yu Gothic UI Semibold", Font.PLAIN, 18));
		lblMoneyShow.setHorizontalAlignment(SwingConstants.CENTER);
		lblMoneyShow.setBounds(628, 8, 107, 35);
		contentPane.add(lblMoneyShow);
		lblMoneyShow.setText(businessLogic.getUserLogged(Username).getWallet().getMoney() + "€");

		//////////////////////////////////////////////////////////////////////// ListBet

		DefaultTableModel tableModel = new DefaultTableModel(new Object[][] {}, new String[] { "#Bet", "Event",
				"Question", "Answer", "Amount(\u20AC)", "Date", "Expected Profit", "Ezkutaketa" });

		tableListBet = new JTable(tableModel);

		Object[] ezaugarriList = new Object[8];
		for (Bet lag : businessLogic.getUserLogged(Username).getAllOngoingBets()) {
			ezaugarriList[0] = lag.getId();
			ezaugarriList[1] = lag.getEvent().getDescription();
			ezaugarriList[2] = lag.getQuestion().getQuestion();
			ezaugarriList[3] = lag.getAnswer().getContent();
			ezaugarriList[4] = lag.getAmount();
			ezaugarriList[5] = df.format(lag.getDate());
			ezaugarriList[6] = lag.getProfit();
			ezaugarriList[7] = lag.getEvent();
			tableModel.insertRow(tableModel.getRowCount(), ezaugarriList);
		}
		tableListBet.getColumnModel().getColumn(0).setPreferredWidth(25);
		tableListBet.getColumnModel().getColumn(1).setPreferredWidth(60);
		tableListBet.getColumnModel().getColumn(2).setPreferredWidth(120);
		tableListBet.getColumnModel().getColumn(3).setPreferredWidth(70);
		tableListBet.getColumnModel().getColumn(4).setPreferredWidth(30);
		tableListBet.getColumnModel().getColumn(5).setPreferredWidth(50);
		tableListBet.getColumnModel().getColumn(6).setPreferredWidth(50);
		tableListBet.getColumnModel().removeColumn(tableListBet.getColumnModel().getColumn(7));
		showBetscrollPane.setViewportView(tableListBet);

		////////////////////////////////////////////////////////////////////// Insert
		////////////////////////////////////////////////////////////////////// Money
		////////////////////////////////////////////////////////////////////// Button
		JFrame thisFrame = this;
		JButton InsertMoneyButton = new JButton("Insert Money");
		InsertMoneyButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				InsertMoneyGUI InsertGUI = new InsertMoneyGUI();
				InsertGUI.setUsername(Username);
				InsertGUI.setBusinessLogic(businessLogic);
				InsertGUI.previousFrame(thisFrame);
				setVisible(false);
				InsertGUI.setVisible(true);
			}
		});
		InsertMoneyButton.setFont(new Font("Tahoma", Font.PLAIN, 15));
		InsertMoneyButton.setBounds(552, 105, 163, 52);
		contentPane.add(InsertMoneyButton);

		JLabel currentMoneytextLbl = new JLabel("Current Money:");
		currentMoneytextLbl.setFont(new Font("Tahoma", Font.PLAIN, 13));
		currentMoneytextLbl.setBounds(547, 11, 178, 35);
		contentPane.add(currentMoneytextLbl);

		JButton btnBack = new JButton("Close");
		btnBack.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				setVisible(false);
				prevFrame.setVisible(true);
				prevFrame.setUsername(Username);
			}
		});
		btnBack.setFont(new Font("Tahoma", Font.PLAIN, 14));
		btnBack.setBounds(85, 386, 107, 31);
		contentPane.add(btnBack);

		JButton DeleteBetbtn = new JButton("Delete Bet");
		DeleteBetbtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {

				/*
				 * if(tableListBet.getSelectedRow()!=-1) { Integer remBetId = (Integer)
				 * tableModel.getValueAt(tableListBet.getSelectedRow(),0);
				 * businessLogic.removeBet(remBetId, (int)
				 * tableModel.getValueAt(tableListBet.getSelectedRow(), 4));
				 * tableModel.removeRow(tableListBet.getSelectedRow()); }
				 */

				if (tableListBet.getSelectedRow() != -1) {

					Integer remBetId = (Integer) tableModel.getValueAt(tableListBet.getSelectedRow(), 0);

					domain.Event remBetEvent = (domain.Event) tableModel.getValueAt(tableListBet.getSelectedRow(), 7);
					Date remBetDate = (Date) remBetEvent.getEventDate();
					Date currentDate = new Date();
					String remBetDateString = df.format(remBetDate);
					String currentDateString = df.format(currentDate);

					ErrorMessageArea.setText("");

				/*	if (currentDateString.compareTo(remBetDateString) <= 0) {
						ErrorMessageArea.setText("You can not delete that bet it \n has already expired!");
					} else*/ if (Math.abs(((String) tableModel.getValueAt(tableListBet.getSelectedRow(), 5))
							.compareTo(currentDateString)) >= 10) {
						ErrorMessageArea.setText("More than 10 days have passed since you made the bet!");
					} else {
						businessLogic.removeBet(Username ,remBetId,
								(int) tableModel.getValueAt(tableListBet.getSelectedRow(), 4));
						tableModel.removeRow(tableListBet.getSelectedRow());
					}
				}
			}
		});
		DeleteBetbtn.setFont(new Font("Tahoma", Font.PLAIN, 14));
		DeleteBetbtn.setBounds(615, 386, 120, 31);
		contentPane.add(DeleteBetbtn);

		JPanel NameSurnamepanel = new JPanel();
		NameSurnamepanel.setBounds(10, 38, 226, 62);
		contentPane.add(NameSurnamepanel);
		NameSurnamepanel.setLayout(null);

		JLabel NameLbl = new JLabel("Name:");
		NameLbl.setBounds(10, 11, 266, 14);
		NameSurnamepanel.add(NameLbl);
		NameLbl.setFont(new Font("Tahoma", Font.PLAIN, 13));
		NameLbl.setText(
				"Name: " + businessLogic.getUserLogged(Username).getName() + " " + businessLogic.getUserLogged(Username).getSurname());

		JLabel lblUsername = new JLabel("Username:");
		lblUsername.setFont(new Font("Tahoma", Font.PLAIN, 13));
		lblUsername.setBounds(10, 36, 230, 25);
		NameSurnamepanel.add(lblUsername);
		lblUsername.setText("Username: " + businessLogic.getUserLogged(Username).getUsername());

		JButton btnRefresh = new JButton("Refresh");
		btnRefresh.setFont(new Font("Tahoma", Font.PLAIN, 9));
		btnRefresh.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				int currency = businessLogic.getUserLogged(Username).getWallet().getMoney();
				lblMoneyShow.setText(currency + "€");
				String accountNum = businessLogic.getUserLogged(Username).getBankAccount();
				lblBankAccount.setText("Bank Account: " + accountNum);
				String newUserName = businessLogic.getUserLogged(Username).getUsername();
				lblUsername.setText("Username: " + newUserName);

			}
		});
		btnRefresh.setBounds(549, 58, 79, 31);
		contentPane.add(btnRefresh);

		JLabel lblBetsList = new JLabel("Made Bets:");
		lblBetsList.setFont(new Font("Tahoma", Font.PLAIN, 12));
		lblBetsList.setBounds(10, 225, 79, 14);
		contentPane.add(lblBetsList);

		JPanel photoPanel = new JPanel();
		photoPanel.setBounds(287, 46, 195, 168);
		contentPane.add(photoPanel);
		photoPanel.setLayout(null);

		JLabel LogoLabel = new JLabel(" ");
		LogoLabel.setBounds(0, 0, 160, 168);
		photoPanel.add(LogoLabel);

		LogoLabel.setIcon(new ImageIcon("./Image/myke.jpg"));

		JButton btnLogout = new JButton("Logout");
		btnLogout.setBounds(10, 8, 89, 23);
		contentPane.add(btnLogout);

		JLabel lblProfilePhotoTxt = new JLabel("Profile Photo: ");
		lblProfilePhotoTxt.setBounds(287, 29, 195, 14);
		contentPane.add(lblProfilePhotoTxt);

		JButton btnEditprofile = new JButton("Edit Profile");
		ViewProfileGUI frame = this;
		btnEditprofile.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {

				setVisible(false);
				EditProfileGUI newg = new EditProfileGUI();
				newg.setUsername(Username);
				newg.setBusinessLogic(businessLogic);
				newg.setVisible(true);
				newg.previousFrame(frame);
			}
		});
		btnEditprofile.setBounds(111, 8, 111, 23);
		contentPane.add(btnEditprofile);

		ErrorMessageArea = new JTextArea();
		ErrorMessageArea.setBounds(507, 181, 308, 52);
		contentPane.add(ErrorMessageArea);
		btnLogout.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				setVisible(false);
				prevFrame.setVisible(true);
				prevFrame.getBtnLogin().setVisible(true);
				prevFrame.getBtnRegister().setVisible(true);
				prevFrame.getBifunctionalBtn().setVisible(false);
				prevFrame.setUsername(null);
			}
		});

	}
}
