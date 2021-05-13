package gui;


import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import businessLogic.BlFacade;
import businessLogic.BlFacadeImplementation;
import exceptions.AnswerAlreadyExist;

import javax.swing.JButton;
import javax.swing.JLabel;
import java.awt.Font;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.Date;
import java.awt.event.ActionEvent;

public class ConfirmGUI extends JFrame {

	

	private JPanel contentPane;

	private JFrame prevFrame;

	private Integer eventNum, questionNum, amount;
	
	private ArrayList<String> infoList;

	private BlFacade businessLogic;
	
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
			
				if(Integer.parseInt(infoList.get(0))==1) {
				businessLogic.storeBet(businessLogic.getQuestion(eventNum,questionNum), 
						businessLogic.getAnswer(eventNum,questionNum, Integer.parseInt(infoList.get(1))), businessLogic.getEvent(eventNum), new Date(), amount);
				}else if(Integer.parseInt(infoList.get(0))==2) {
				
					Integer newAnserNum=null;
					try {
						newAnserNum = businessLogic.createAnswer(eventNum,questionNum, infoList.get(1), infoList.get(2));
					} catch (AnswerAlreadyExist e) {
						e.printStackTrace();
					};
					businessLogic.storeBet(businessLogic.getQuestion(eventNum,questionNum), 
							businessLogic.getAnswer(eventNum,questionNum, newAnserNum), businessLogic.getEvent(eventNum), new Date(), amount);
				}
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
	 * @param eventNum
	 * @param questionNum
	 * @param amount
	 */
	public void setValues(Integer eventNum, Integer questionNum, Integer amount, ArrayList<String> infoList) {

		this.eventNum = eventNum;
		this.questionNum = questionNum;
		this.amount = amount;
		this.infoList = infoList;
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
