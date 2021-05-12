package gui;

import java.awt.Dimension;
import java.awt.Rectangle;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.text.DateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;
import java.util.ResourceBundle;
import java.util.Vector;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

import com.toedter.calendar.JCalendar;

import businessLogic.BlFacade;
import configuration.UtilDate;
import domain.Answer;
import domain.Question;
import java.awt.Font;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.JTextArea;
import javax.swing.DropMode;
import javax.swing.JComboBox;
import java.awt.SystemColor;
import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.LayoutStyle.ComponentPlacement;

public class BrowseQuestionsGUI extends JFrame {

	private static final long serialVersionUID = 1L;

	private JFrame prevFrame;

	private BlFacade businessLogic;

	private final JLabel eventDateLbl = new JLabel(ResourceBundle.getBundle("Etiquetas").getString("EventDate"));
	private final JLabel questionLbl = new JLabel(ResourceBundle.getBundle("Etiquetas").getString("Questions"));
	private final JLabel eventLbl = new JLabel(ResourceBundle.getBundle("Etiquetas").getString("Events"));

	private JButton closeBtn = new JButton(ResourceBundle.getBundle("Etiquetas").getString("Close"));

	// Code for JCalendar
	private JCalendar calendar = new JCalendar();
	private Calendar previousCalendar;
	private Calendar currentCalendar;
	private JScrollPane eventScrollPane = new JScrollPane();
	private JScrollPane questionScrollPane = new JScrollPane();
	private JScrollPane answerScrollPane = new JScrollPane();
	private Vector<Date> datesWithEventsInCurrentMonth = new Vector<Date>();



	private JTable eventTable = new JTable();
	private JTable questionTable = new JTable();
	private JTable answerTable = new JTable();

	private DefaultTableModel eventTableModel;
	private DefaultTableModel questionTableModel;
	private DefaultTableModel answerTableModel;

	private String[] eventColumnNames = new String[] { ResourceBundle.getBundle("Etiquetas").getString("EventN"),
			ResourceBundle.getBundle("Etiquetas").getString("Event"),

	};
	private String[] questionColumnNames = new String[] { ResourceBundle.getBundle("Etiquetas").getString("QuestionN"),
			ResourceBundle.getBundle("Etiquetas").getString("Question"),
			ResourceBundle.getBundle("Etiquetas").getString("MinimumBet") };

	private String[] answerColumnNames = new String[] { ResourceBundle.getBundle("Etiquetas").getString("AnswerN"),
			ResourceBundle.getBundle("Etiquetas").getString("Answer"),
			ResourceBundle.getBundle("Etiquetas").getString("Rate") };

	private JTextField betInp;
	private final JTextArea MessageTextArea = new JTextArea();
	private final JComboBox goals1comboBox = new JComboBox();
	private final JComboBox goals2comboBox = new JComboBox();
	private final JTextArea Team1TextArea = new JTextArea();
	private final JTextArea Team2TextArea = new JTextArea();

	public void setBusinessLogic(BlFacade bl) {
		businessLogic = bl;
	}

	public BrowseQuestionsGUI(BlFacade bl) {
		
		businessLogic = bl;
		try {
			jbInit();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	private void jbInit() throws Exception {
		this.setSize(new Dimension(896, 818));
		this.setTitle(ResourceBundle.getBundle("Etiquetas").getString("BrowseQuestions"));
		
		
		eventScrollPane.setViewportView(eventTable);
		eventTableModel = new DefaultTableModel(null, eventColumnNames);

		eventTable.setModel(eventTableModel);
		eventTable.getColumnModel().getColumn(0).setPreferredWidth(25);
		eventTable.getColumnModel().getColumn(1).setPreferredWidth(268);

		questionScrollPane.setViewportView(questionTable);
		questionTableModel = new DefaultTableModel(null, questionColumnNames);

		questionTable.setModel(questionTableModel);
		questionTable.getColumnModel().getColumn(0).setPreferredWidth(25);
		questionTable.getColumnModel().getColumn(1).setPreferredWidth(243);
		questionTable.getColumnModel().getColumn(2).setPreferredWidth(25);

		answerScrollPane.setViewportView(answerTable);
		answerTableModel = new DefaultTableModel(null, answerColumnNames);

		answerTable.setModel(answerTableModel);
		answerTable.getColumnModel().getColumn(0).setPreferredWidth(25);
		answerTable.getColumnModel().getColumn(1).setPreferredWidth(243);
		answerTable.getColumnModel().getColumn(2).setPreferredWidth(25);

		closeBtn.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {

				jButton2_actionPerformed(e);
			}
		});

		datesWithEventsInCurrentMonth = businessLogic.getEventsMonth(calendar.getDate());
		CreateQuestionGUI.paintDaysWithEvents(calendar, datesWithEventsInCurrentMonth);

		for(int i = 0 ; i<15; i++) {
			goals1comboBox.addItem((Integer)i);
		}
		
		for(int i = 0 ; i<15; i++) {
			goals2comboBox.addItem((Integer)i);
		}
		
		
		goals1comboBox.setVisible(false);
		goals2comboBox.setVisible(false);
		Team1TextArea.setVisible(false);
		Team2TextArea.setVisible(false);
		answerScrollPane.setVisible(false);
		
		
		// Code for JCalendar
		this.calendar.addPropertyChangeListener(new PropertyChangeListener() {

			@Override
			public void propertyChange(PropertyChangeEvent propertyChangeEvent) {

				if (propertyChangeEvent.getPropertyName().equals("locale")) {
					calendar.setLocale((Locale) propertyChangeEvent.getNewValue());
				} else if (propertyChangeEvent.getPropertyName().equals("calendar")) {
					previousCalendar = (Calendar) propertyChangeEvent.getOldValue();
					currentCalendar = (Calendar) propertyChangeEvent.getNewValue();
					DateFormat dateformat1 = DateFormat.getDateInstance(1, calendar.getLocale());
					Date firstDay = UtilDate.trim(new Date(calendar.getCalendar().getTime().getTime()));

					int previousMonth = previousCalendar.get(Calendar.MONTH);
					int currentMonth = currentCalendar.get(Calendar.MONTH);

					if (currentMonth != previousMonth) {
						if (currentMonth == previousMonth + 2) {
							// Si en JCalendar está 30 de enero y se avanza al mes siguiente,
							// devolvería 2 de marzo (se toma como equivalente a 30 de febrero)
							// Con este código se dejará como 1 de febrero en el JCalendar
							currentCalendar.set(Calendar.MONTH, previousMonth + 1);
							currentCalendar.set(Calendar.DAY_OF_MONTH, 1);
						}

						calendar.setCalendar(currentCalendar);
						datesWithEventsInCurrentMonth = businessLogic.getEventsMonth(calendar.getDate());
					}

					CreateQuestionGUI.paintDaysWithEvents(calendar, datesWithEventsInCurrentMonth);

					try {
						eventTableModel.setDataVector(null, eventColumnNames);
						eventTableModel.setColumnCount(3); // another column added to allocate ev objects

						Vector<domain.Event> events = businessLogic.getEvents(firstDay);

						if (events.isEmpty())
							eventLbl.setText(ResourceBundle.getBundle("Etiquetas").getString("NoEvents") + ": "
									+ dateformat1.format(currentCalendar.getTime()));
						else
							eventLbl.setText(ResourceBundle.getBundle("Etiquetas").getString("Events") + ": "
									+ dateformat1.format(currentCalendar.getTime()));
						for (domain.Event ev : events) {
							Vector<Object> row = new Vector<Object>();
							System.out.println("Events " + ev);
							row.add(ev.getEventNumber());
							row.add(ev.getDescription());
							row.add(ev); // ev object added in order to obtain it with
							// tableModelEvents.getValueAt(i,2)
							eventTableModel.addRow(row);
						}
						eventTable.getColumnModel().getColumn(0).setPreferredWidth(25);
						eventTable.getColumnModel().getColumn(1).setPreferredWidth(268);
						eventTable.getColumnModel().removeColumn(eventTable.getColumnModel().getColumn(2)); // not shown
																											// in JTable
					} catch (Exception e1) {
						questionLbl.setText(e1.getMessage());
					}
				}
			}
		});

		JLabel answerLbl = new JLabel(
				ResourceBundle.getBundle("Etiquetas").getString("BrowseQuestionsGUI.answerLbl.text"));
		

		eventTable.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				int i = eventTable.getSelectedRow();
				domain.Event ev = (domain.Event) eventTableModel.getValueAt(i, 2); // obtain ev object

				Vector<Question> questions = ev.getQuestions();

				try {
					questionTableModel.setDataVector(null, questionColumnNames);
					questionTableModel.setColumnCount(4); // another column added to allocate question objects

					if (questions.isEmpty())
						questionLbl.setText(ResourceBundle.getBundle("Etiquetas").getString("NoQuestions") + ": "
								+ ev.getDescription());
					else
						questionLbl.setText(ResourceBundle.getBundle("Etiquetas").getString("SelectedEvent") + " "
								+ ev.getDescription());

					for (domain.Question q : questions) {
						Vector<Object> row = new Vector<Object>();
						row.add(q.getQuestionNumber());
						row.add(q.getQuestion());
						row.add(q.getBetMinimum());
						row.add(q);
						questionTableModel.addRow(row);
					}
					questionTable.getColumnModel().getColumn(0).setPreferredWidth(25);
					questionTable.getColumnModel().getColumn(1).setPreferredWidth(243);
					questionTable.getColumnModel().getColumn(2).setPreferredWidth(25);
					questionTable.getColumnModel().removeColumn(questionTable.getColumnModel().getColumn(3));// ez da
																												// agertuko

				} catch (Exception e2) {
					answerLbl.setText(e2.getMessage());
				}
				// To change the default
				answerLbl.setText("No question selected");
			}
		});

		JFrame thisFrame = this;
		JButton btnBet = new JButton(
				ResourceBundle.getBundle("Etiquetas").getString("BrowseQuestionsGUI.btnNewButton.text")); //$NON-NLS-1$ //$NON-NLS-2$
		btnBet.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				MessageTextArea.setText("                                             ");
				
				if (businessLogic.isAnyUserLogged()) {
					if (answerTable.getSelectedRow() != -1 && eventTable.getSelectedRow() != -1
							&& questionTable.getSelectedRow() != -1) {
						Integer answerNum = (Integer) answerTable.getValueAt(answerTable.getSelectedRow(), 0);
						Integer questNumber = (Integer) questionTable.getValueAt(questionTable.getSelectedRow(), 0);
						Integer eventNumber = (Integer) eventTable.getValueAt(eventTable.getSelectedRow(), 0);

						if (businessLogic.isInt(betInp.getText())) {
							if (Integer.parseInt(betInp.getText()) <= businessLogic.getUserLogged().getWallet()
									.getMoney()
									&& Integer.parseInt(betInp.getText()) >= businessLogic
											.getQuestion(eventNumber, questNumber).getBetMinimum()) {
								setVisible(false);
								ConfirmGUI confirmation = new ConfirmGUI();
								confirmation.setVisible(true);
								confirmation.previousFrame(thisFrame);
								confirmation.setValues(eventNumber, questNumber, Integer.parseInt(betInp.getText()),
										answerNum);
								betInp.setText("");
							} else if (Integer.parseInt(betInp.getText()) > businessLogic.getUserLogged().getWallet()
									.getMoney()) {
								MessageTextArea.setText("You don't have enough money on you wallet!");
							} else if (Integer.parseInt(betInp.getText()) < businessLogic
									.getQuestion(eventNumber, questNumber).getBetMinimum()) {
								MessageTextArea.setText("Please, enter a value higher than the minimum bet!");
							}
						} else {
							MessageTextArea.setText("Please enter a numeric value, not: " + betInp.getText());
						}
					}

				} else
					MessageTextArea
							.setText("You are not able to bet without identification. \n Please register or login.");
			}
		});
		btnBet.setFont(new Font("Tahoma", Font.PLAIN, 15));

		betInp = new JTextField();
		betInp.setColumns(10);
		betInp.setText("");

		JLabel lblEnterAmount = new JLabel(
				ResourceBundle.getBundle("Etiquetas").getString("BrowseQuestionsGUI.lblEnterAmount.text")); //$NON-NLS-1$ //$NON-NLS-2$
		lblEnterAmount.setFont(new Font("Tahoma", Font.PLAIN, 13));
		MessageTextArea.setBackground(SystemColor.menu);
		MessageTextArea.setFont(new Font("Georgia Pro Semibold", Font.PLAIN, 13));
		MessageTextArea
				.setText(ResourceBundle.getBundle("Etiquetas").getString("BrowseQuestionsGUI.MessageTextArea.text"));
		GroupLayout groupLayout = new GroupLayout(getContentPane());
		groupLayout.setHorizontalGroup(
			groupLayout.createParallelGroup(Alignment.LEADING)
				.addGroup(groupLayout.createSequentialGroup()
					.addGap(40)
					.addComponent(eventDateLbl, GroupLayout.PREFERRED_SIZE, 140, GroupLayout.PREFERRED_SIZE)
					.addGap(211)
					.addComponent(eventLbl, GroupLayout.PREFERRED_SIZE, 259, GroupLayout.PREFERRED_SIZE))
				.addGroup(groupLayout.createSequentialGroup()
					.addGap(40)
					.addComponent(calendar, GroupLayout.PREFERRED_SIZE, 225, GroupLayout.PREFERRED_SIZE)
					.addGap(126)
					.addComponent(eventScrollPane, GroupLayout.PREFERRED_SIZE, 346, GroupLayout.PREFERRED_SIZE))
				.addGroup(groupLayout.createSequentialGroup()
					.addGap(305)
					.addComponent(closeBtn, GroupLayout.PREFERRED_SIZE, 130, GroupLayout.PREFERRED_SIZE)
					.addContainerGap(445, Short.MAX_VALUE))
				.addGroup(groupLayout.createSequentialGroup()
					.addGap(238)
					.addGroup(groupLayout.createParallelGroup(Alignment.TRAILING)
						.addComponent(Team1TextArea, Alignment.LEADING, GroupLayout.DEFAULT_SIZE, 90, Short.MAX_VALUE)
						.addComponent(goals1comboBox, Alignment.LEADING, 0, 90, Short.MAX_VALUE))
					.addGap(93)
					.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
						.addGroup(groupLayout.createSequentialGroup()
							.addComponent(goals2comboBox, 0, 89, Short.MAX_VALUE)
							.addGap(370))
						.addGroup(groupLayout.createSequentialGroup()
							.addComponent(Team2TextArea, GroupLayout.PREFERRED_SIZE, 90, GroupLayout.PREFERRED_SIZE)
							.addContainerGap())))
				.addGroup(groupLayout.createSequentialGroup()
					.addGap(171)
					.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
						.addGroup(groupLayout.createSequentialGroup()
							.addComponent(questionLbl, GroupLayout.PREFERRED_SIZE, 406, GroupLayout.PREFERRED_SIZE)
							.addContainerGap())
						.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
							.addGroup(groupLayout.createSequentialGroup()
								.addComponent(MessageTextArea, GroupLayout.PREFERRED_SIZE, 406, GroupLayout.PREFERRED_SIZE)
								.addContainerGap())
							.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
								.addGroup(groupLayout.createSequentialGroup()
									.addComponent(answerScrollPane, GroupLayout.PREFERRED_SIZE, 406, GroupLayout.PREFERRED_SIZE)
									.addContainerGap())
								.addGroup(groupLayout.createSequentialGroup()
									.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
										.addComponent(questionScrollPane, GroupLayout.PREFERRED_SIZE, 406, GroupLayout.PREFERRED_SIZE)
										.addComponent(answerLbl, GroupLayout.PREFERRED_SIZE, 406, GroupLayout.PREFERRED_SIZE))
									.addPreferredGap(ComponentPlacement.RELATED, 145, Short.MAX_VALUE)
									.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
										.addGroup(groupLayout.createParallelGroup(Alignment.TRAILING)
											.addComponent(btnBet, GroupLayout.PREFERRED_SIZE, 89, GroupLayout.PREFERRED_SIZE)
											.addComponent(betInp, GroupLayout.PREFERRED_SIZE, 89, GroupLayout.PREFERRED_SIZE))
										.addComponent(lblEnterAmount, GroupLayout.PREFERRED_SIZE, 116, GroupLayout.PREFERRED_SIZE))
									.addGap(42))))))
		);
		groupLayout.setVerticalGroup(
			groupLayout.createParallelGroup(Alignment.LEADING)
				.addGroup(groupLayout.createSequentialGroup()
					.addGap(15)
					.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
						.addComponent(eventDateLbl, GroupLayout.PREFERRED_SIZE, 25, GroupLayout.PREFERRED_SIZE)
						.addGroup(groupLayout.createSequentialGroup()
							.addGap(4)
							.addComponent(eventLbl, GroupLayout.PREFERRED_SIZE, 16, GroupLayout.PREFERRED_SIZE)))
					.addGap(10)
					.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
						.addComponent(calendar, GroupLayout.PREFERRED_SIZE, 150, GroupLayout.PREFERRED_SIZE)
						.addComponent(eventScrollPane, GroupLayout.PREFERRED_SIZE, 150, GroupLayout.PREFERRED_SIZE))
					.addGap(29)
					.addComponent(questionLbl)
					.addPreferredGap(ComponentPlacement.UNRELATED)
					.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
						.addGroup(groupLayout.createSequentialGroup()
							.addGap(59)
							.addComponent(lblEnterAmount, GroupLayout.PREFERRED_SIZE, 14, GroupLayout.PREFERRED_SIZE)
							.addPreferredGap(ComponentPlacement.RELATED)
							.addComponent(betInp, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
							.addGap(18)
							.addComponent(btnBet, GroupLayout.PREFERRED_SIZE, 43, GroupLayout.PREFERRED_SIZE))
						.addGroup(groupLayout.createSequentialGroup()
							.addPreferredGap(ComponentPlacement.RELATED)
							.addComponent(questionScrollPane, GroupLayout.PREFERRED_SIZE, 116, GroupLayout.PREFERRED_SIZE)
							.addGap(18)
							.addComponent(answerLbl)))
					.addPreferredGap(ComponentPlacement.RELATED)
					.addComponent(answerScrollPane, GroupLayout.PREFERRED_SIZE, 116, GroupLayout.PREFERRED_SIZE)
					.addGap(27)
					.addGroup(groupLayout.createParallelGroup(Alignment.BASELINE)
						.addComponent(Team1TextArea, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
						.addComponent(Team2TextArea, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
					.addPreferredGap(ComponentPlacement.RELATED)
					.addGroup(groupLayout.createParallelGroup(Alignment.BASELINE)
						.addComponent(goals1comboBox, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
						.addComponent(goals2comboBox, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
					.addGap(74)
					.addComponent(MessageTextArea, GroupLayout.PREFERRED_SIZE, 50, GroupLayout.PREFERRED_SIZE)
					.addPreferredGap(ComponentPlacement.RELATED)
					.addComponent(closeBtn, GroupLayout.PREFERRED_SIZE, 30, GroupLayout.PREFERRED_SIZE)
					.addGap(24))
		);
		Team1TextArea.setBackground(SystemColor.menu);
		Team1TextArea.setEditable(false);
		Team2TextArea.setBackground(SystemColor.menu);
		Team2TextArea.setEditable(false);
		getContentPane().setLayout(groupLayout);

		questionTable.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				
				int i = questionTable.getSelectedRow();
				domain.Question question = (domain.Question) questionTableModel.getValueAt(i, 3);// obtain ev object

				Integer qtype = question.getType();
				
				if (qtype.equals(1)) {
				goals1comboBox.setVisible(false);
				goals2comboBox.setVisible(false);
				Team1TextArea.setVisible(false);
				Team2TextArea.setVisible(false);
				answerScrollPane.setVisible(true);
				
				ArrayList<Answer> answers = question.getAnswerList();
				try {
					answerTableModel.setDataVector(null, answerColumnNames);
					answerTableModel.setColumnCount(4); // another column added to allocate answer objects
				
					if (answers.isEmpty())
						answerLbl.setText("No Answers for: " + question.getQuestion());
					else
						answerLbl.setText("Bet on: " + question.getQuestion());

					for (domain.Answer a : answers) {
						Vector<Object> row = new Vector<Object>();
						row.add(a.getAnswerId());
						row.add(a.getContent());
						row.add(a.getRate());
						row.add(a);
						answerTableModel.addRow(row);
					}
					answerTable.getColumnModel().getColumn(0).setPreferredWidth(25);
					answerTable.getColumnModel().getColumn(1).setPreferredWidth(243);
					answerTable.getColumnModel().getColumn(2).setPreferredWidth(25);
					answerTable.getColumnModel().removeColumn(answerTable.getColumnModel().getColumn(3));// ez da
																												// agertuko

				} catch (Exception e3) {
					e3.printStackTrace();
					answerLbl.setText(e3.getMessage());
				}
				}else if (qtype.equals(2)){
					answerScrollPane.setVisible(false);
					goals1comboBox.setVisible(true);
					goals2comboBox.setVisible(true);
					Team1TextArea.setVisible(true);
					Team2TextArea.setVisible(true);
					Team1TextArea.setText("       ");
					Team2TextArea.setText("       ");
					String[] teams = question.getEvent().getDescription().split("-");
					String first = teams[0];
					String second = teams[1];
					Team1TextArea.setText(first);
					Team2TextArea.setText(second);
					
					Integer team1Goals = (Integer) goals1comboBox.getSelectedItem();
					Integer team2Goals = (Integer) goals2comboBox.getSelectedItem();
					
					String answer2 = team1Goals + "-" + team2Goals;
					
				}
			}
		});
		

	}

	private void jButton2_actionPerformed(ActionEvent e) {

		this.dispose();
		prevFrame.setVisible(true);
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