package gui;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import businessLogic.UserManager;
import domain.Bet;

import javax.swing.BoxLayout;
import javax.swing.DefaultListModel;
import javax.swing.GroupLayout;
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
import java.awt.event.ActionEvent;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

public class ViewProfileGUI extends JFrame {

	private JPanel contentPane;

	private UserManager businessLogic = new UserManager();

	private JFrame prevFrame;

	private boolean bool = false;
	private JTable tableListBet;

	/**
	 * Gets the previous frame
	 * 
	 * @param frame
	 */
	public void previousFrame(JFrame frame) {

		prevFrame = frame;
	}

	public void setValueBool() {
		bool = true;
	}

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
		setBounds(100, 100, 760, 467);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);

		JScrollPane showBetscrollPane = new JScrollPane();
		showBetscrollPane.setBounds(10, 247, 725, 128);
		contentPane.add(showBetscrollPane);
		
		

		///////////////////////////////////////////////////// Labels of User Info
		JPanel UserInfopanel2 = new JPanel();
		UserInfopanel2.setBounds(10, 80, 226, 134);
		contentPane.add(UserInfopanel2);
		UserInfopanel2.setLayout(null);

		JLabel lblEmail = new JLabel("E-Mail:");
		lblEmail.setBounds(10, 11, 212, 26);
		UserInfopanel2.add(lblEmail);
		lblEmail.setText("E-Mail: " + businessLogic.getUserLogged().getEmail());

		JLabel lblBankAccount = new JLabel("Bank Account:");
		lblBankAccount.setBounds(10, 45, 282, 21);
		UserInfopanel2.add(lblBankAccount);
		lblBankAccount.setText("Bank Account: " + businessLogic.getUserLogged().getBankAccount());

		JLabel lblBirthDate = new JLabel("Birth Date:");
		lblBirthDate.setBounds(10, 77, 223, 26);
		UserInfopanel2.add(lblBirthDate);
		//To get the Date in the format we want
		String pattern = "MM / dd / yyyy";

		// Create an instance of SimpleDateFormat used for formatting 
		// the string representation of date according to the chosen pattern
		DateFormat df = new SimpleDateFormat(pattern);
		
		// Using DateFormat format method we can create a string 
		// representation of a date with the defined format.
		String birthDayAsString = df.format(businessLogic.getUserLogged().getBirthDate());
		//finally set it to
		lblBirthDate.setText("Birth Date: " + birthDayAsString);

		JLabel lblMoneyShow = new JLabel("Money");
		lblMoneyShow.setFont(new Font("Yu Gothic UI Semibold", Font.PLAIN, 18));
		lblMoneyShow.setHorizontalAlignment(SwingConstants.CENTER);
		lblMoneyShow.setBounds(628, 52, 107, 35);
		contentPane.add(lblMoneyShow);
		lblMoneyShow.setText(businessLogic.getUserLogged().getWallet().getMoney() + "€");
		
		////////////////////////////////////////////////////////////////////////ListBet
		
		DefaultTableModel tableModel = new DefaultTableModel(
				new Object[][] {
				},
				new String[] {
					"#Bet", "Event", "Question", "Answer", "Amount(\u20AC)", "Date"
				}
			);
		
		tableListBet = new JTable(new DefaultTableModel(
			new Object[][] {
			},
			new String[] {
				"#Bet", "Event", "Question", "Answer", "Amount(\u20AC)", "Date"
			}
		));
		
		Object[] ezaugarriList = new Object[6];
		for(Bet lag: businessLogic.getUserLogged().getAllBets()) {
			ezaugarriList[0] = lag.getId();
			ezaugarriList[1] = lag.getEvent().getDescription();
			ezaugarriList[2] = lag.getQuestion().getQuestion();
			ezaugarriList[3] = lag.getAnswer().getContent();
			ezaugarriList[4] = lag.getAmount();
			ezaugarriList[5] = df.format(lag.getDate());
			tableModel.insertRow(tableModel.getRowCount(), ezaugarriList);
		}
		
		
		showBetscrollPane.setViewportView(tableListBet);

		////////////////////////////////////////////////////////////////////// Insert
		////////////////////////////////////////////////////////////////////// Money
		////////////////////////////////////////////////////////////////////// Button
		JFrame thisFrame = this;
		JButton InsertMoneyButton = new JButton("Insert Money");
		InsertMoneyButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				InsertMoneyGUI InsertGUI = new InsertMoneyGUI();
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
			}
		});
		btnBack.setFont(new Font("Tahoma", Font.PLAIN, 14));
		btnBack.setBounds(85, 386, 107, 31);
		contentPane.add(btnBack);

		JButton DeleteBetbtn = new JButton("Delete Bet");
		DeleteBetbtn.setFont(new Font("Tahoma", Font.PLAIN, 14));
		DeleteBetbtn.setBounds(552, 386, 120, 31);
		contentPane.add(DeleteBetbtn);

		JPanel NameSurnamepanel = new JPanel();
		NameSurnamepanel.setBounds(10, 11, 226, 62);
		contentPane.add(NameSurnamepanel);
		NameSurnamepanel.setLayout(null);

		JLabel NameLbl = new JLabel("Name:");
		NameLbl.setBounds(10, 11, 266, 14);
		NameSurnamepanel.add(NameLbl);
		NameLbl.setFont(new Font("Tahoma", Font.PLAIN, 13));
		NameLbl.setText(
				"Name: " + businessLogic.getUserLogged().getName() + " " + businessLogic.getUserLogged().getSurname());

		JLabel lblUsername = new JLabel("Username:");
		lblUsername.setFont(new Font("Tahoma", Font.PLAIN, 13));
		lblUsername.setBounds(10, 36, 230, 25);
		NameSurnamepanel.add(lblUsername);
		lblUsername.setText("Username: " + businessLogic.getUserLogged().getUsername());

		JButton btnRefresh = new JButton("Refresh");
		btnRefresh.setFont(new Font("Tahoma", Font.PLAIN, 9));
		btnRefresh.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				int currency = businessLogic.getUserLogged().getWallet().getMoney();
				lblMoneyShow.setText(currency + "€");
			}
		});
		btnRefresh.setBounds(549, 58, 79, 31);
		contentPane.add(btnRefresh);
		
		JLabel lblBetsList = new JLabel("Made Bets:");
		lblBetsList.setFont(new Font("Tahoma", Font.PLAIN, 12));
		lblBetsList.setBounds(10, 222, 79, 14);
		contentPane.add(lblBetsList);

	}
}
