package gui;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import javax.swing.SwingConstants;
import java.awt.Font;
import java.text.DateFormat;
import java.text.SimpleDateFormat;

import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JButton;
import javax.swing.table.DefaultTableModel;

import businessLogic.BlFacade;
import domain.Bet;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.Color;
import java.awt.SystemColor;
import javax.swing.UIManager;

public class BetHistoryGUI extends JFrame {
	
	private static String Username;
	private JPanel contentPane;
	private JTable tableBets;
	private static BlFacade businessLogic;

	private ViewProfileGUI prevFrame;
	
	
	public void previousFrame(ViewProfileGUI frame) {

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

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					BetHistoryGUI frame = new BetHistoryGUI(businessLogic,Username);
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
	public BetHistoryGUI(BlFacade businessLogic1,String username) {
		Username=username;
		businessLogic= businessLogic1;
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 758, 308);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lblTitle = new JLabel("Record");
		lblTitle.setForeground(UIManager.getColor("PasswordField.selectionBackground"));
		lblTitle.setBounds(10, 8, 722, 21);
		lblTitle.setFont(new Font("Times New Roman", Font.BOLD, 23));
		lblTitle.setHorizontalAlignment(SwingConstants.CENTER);
		contentPane.add(lblTitle);
		
		JScrollPane scrollPaneBets = new JScrollPane();
		scrollPaneBets.setBounds(10, 40, 722, 184);
		contentPane.add(scrollPaneBets);
		
		String pattern = "MM / dd / yyyy";
		DateFormat df = new SimpleDateFormat(pattern);

		
		
		
		DefaultTableModel tableModel = new DefaultTableModel(new Object[][] {}, new String[] { "Bet#","Event", "Question", "Answer", "Amount(€)", "Date", "OutCome" });

		tableBets = new JTable(tableModel);

		Object[] ezaugarriList = new Object[7];
		for (Bet lag : businessLogic.getUserLogged(Username).getAllEvaluatedBets()) {
			ezaugarriList[0] = lag.getId();
			ezaugarriList[1] = lag.getEvent().getDescription();
			ezaugarriList[2] = lag.getQuestion().getQuestion();
			ezaugarriList[3] = lag.getAnswer().getContent();
			ezaugarriList[4] = lag.getAmount();
			ezaugarriList[5] = df.format(lag.getDate());
			if(lag.isHasWon()) {
				ezaugarriList[6] = "+"+lag.getProfit();
			}else {
				ezaugarriList[6] = "-"+lag.getAmount();
			}
			

			tableModel.insertRow(tableModel.getRowCount(), ezaugarriList);
		}
		tableBets.getColumnModel().getColumn(0).setPreferredWidth(10);
		tableBets.getColumnModel().getColumn(1).setPreferredWidth(50);
		tableBets.getColumnModel().getColumn(2).setPreferredWidth(150);
		tableBets.getColumnModel().getColumn(3).setPreferredWidth(70);
		tableBets.getColumnModel().getColumn(4).setPreferredWidth(30);
		tableBets.getColumnModel().getColumn(5).setPreferredWidth(30);
		tableBets.getColumnModel().getColumn(6).setPreferredWidth(10);

		scrollPaneBets.setViewportView(tableBets);
		
		JButton btnBlack = new JButton("Close");
		btnBlack.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				setVisible(false);
				prevFrame.setVisible(true);
			}
		});
		btnBlack.setBounds(331, 235, 89, 23);
		contentPane.add(btnBlack);
	}
}
