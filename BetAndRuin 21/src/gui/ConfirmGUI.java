package gui;

import java.awt.BorderLayout;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import businessLogic.UserManager;

import javax.swing.JButton;
import javax.swing.JLabel;
import java.awt.Font;
import java.awt.event.ActionListener;
import java.util.Date;
import java.awt.event.ActionEvent;

public class ConfirmGUI extends JFrame {

	private JPanel contentPane;

	private JFrame prevFrame;

	private Integer id, eventNum, questionNum, answerNum, amount;

	private UserManager businessLogic = new UserManager();

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					ConfirmGUI frame = new ConfirmGUI();
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
	public ConfirmGUI() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);

		JButton btnYes = new JButton("YES");
		btnYes.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				businessLogic.storeBet(businessLogic.getLoggedUserId(), businessLogic.getQuestion(eventNum,questionNum), 
						businessLogic.getAnswer(eventNum,questionNum, answerNum), businessLogic.getEvent(eventNum), new Date(), amount);
				setVisible(false);
				prevFrame.setVisible(true);
			}
		});
		btnYes.setBounds(55, 180, 117, 25);
		contentPane.add(btnYes);

		JButton btnNo = new JButton("NO");
		btnNo.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {

				setVisible(false);
				prevFrame.setVisible(true);
			}
		});
		btnNo.setBounds(273, 180, 117, 25);
		contentPane.add(btnNo);

		JLabel lblAreYouSure = new JLabel("ARE YOU SURE?");
		lblAreYouSure.setFont(new Font("Dialog", Font.BOLD, 16));
		lblAreYouSure.setBounds(152, 59, 265, 66);
		contentPane.add(lblAreYouSure);
	}

	/**
	 * Setter for all values given from BrowseQuestionsGUI
	 * 
	 * @param id
	 * @param eventNum
	 * @param questionNum
	 * @param amount
	 */
	public void setValues(Integer eventNum, Integer questionNum, Integer amount, Integer answerNum) {

		this.eventNum = eventNum;
		this.questionNum = questionNum;
		this.amount = amount;
		this.answerNum = answerNum;
	}

	/**
	 * Gets the previous frame
	 * 
	 * @param frame
	 */
	public void previousFrame(JFrame frame) {

		prevFrame = frame;
	}
}
