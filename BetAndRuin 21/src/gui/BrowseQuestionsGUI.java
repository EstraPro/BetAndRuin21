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
import domain.Question;
import java.awt.Font;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.JTextArea;
import javax.swing.DropMode;

public class BrowseQuestionsGUI extends JFrame {

	private static final long serialVersionUID = 1L;
	
	private JFrame prevFrame;

	private BlFacade businessLogic;

	private final JLabel eventDateLbl = new JLabel(ResourceBundle.getBundle("Etiquetas").
			getString("EventDate"));
	private final JLabel questionLbl = new JLabel(ResourceBundle.getBundle("Etiquetas").
			getString("Questions")); 
	private final JLabel eventLbl = new JLabel(ResourceBundle.getBundle("Etiquetas").
			getString("Events")); 

	private JButton closeBtn = new JButton(ResourceBundle.getBundle("Etiquetas").
			getString("Close"));

	// Code for JCalendar
	private JCalendar calendar = new JCalendar();
	private Calendar previousCalendar;
	private Calendar currentCalendar;
	private JScrollPane eventScrollPane = new JScrollPane();
	private JScrollPane questionScrollPane = new JScrollPane();

	private Vector<Date> datesWithEventsInCurrentMonth = new Vector<Date>();

	private JTable eventTable= new JTable();
	private JTable questionTable = new JTable();

	private DefaultTableModel eventTableModel;
	private DefaultTableModel questionTableModel;
	private DefaultTableModel answerTableModel;

	private String[] eventColumnNames = new String[] {
			ResourceBundle.getBundle("Etiquetas").getString("EventN"), 
			ResourceBundle.getBundle("Etiquetas").getString("Event"), 

	};
	private String[] questionColumnNames = new String[] {
			ResourceBundle.getBundle("Etiquetas").getString("QuestionN"), 
			ResourceBundle.getBundle("Etiquetas").getString("Question")
	};
	private String[] answerColumnNames = new String[] {
			ResourceBundle.getBundle("Etiquetas").getString("AnswerN"), 
			ResourceBundle.getBundle("Etiquetas").getString("Answer")
	};
	
	private JTextField betInp;
	private JTable answerTable;


	public void setBusinessLogic(BlFacade bl) {
		businessLogic = bl;
	}

	public BrowseQuestionsGUI(BlFacade bl) {
		businessLogic = bl;
		try {
			jbInit();
		}
		catch(Exception e) {
			e.printStackTrace();
		}
	}


	private void jbInit() throws Exception {

		this.getContentPane().setLayout(null);
		this.setSize(new Dimension(774, 706));
		this.setTitle(ResourceBundle.getBundle("Etiquetas").getString("BrowseQuestions"));

		eventDateLbl.setBounds(new Rectangle(40, 15, 140, 25));
		questionLbl.setBounds(138, 248, 406, 14);
		eventLbl.setBounds(295, 19, 259, 16);

		this.getContentPane().add(eventDateLbl, null);
		this.getContentPane().add(questionLbl);
		this.getContentPane().add(eventLbl);

		closeBtn.setBounds(new Rectangle(276, 595, 130, 30));

		closeBtn.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				
				jButton2_actionPerformed(e);
			}
		});

		this.getContentPane().add(closeBtn, null);

		calendar.setBounds(new Rectangle(40, 50, 225, 150));

		datesWithEventsInCurrentMonth = businessLogic.getEventsMonth(calendar.getDate());
		CreateQuestionGUI.paintDaysWithEvents(calendar, datesWithEventsInCurrentMonth);

		// Code for JCalendar
		this.calendar.addPropertyChangeListener(new PropertyChangeListener() {

			@Override
			public void propertyChange(PropertyChangeEvent propertyChangeEvent) {

				if (propertyChangeEvent.getPropertyName().equals("locale")) {
					calendar.setLocale((Locale) propertyChangeEvent.getNewValue());
				}
				else if (propertyChangeEvent.getPropertyName().equals("calendar")) {
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
						datesWithEventsInCurrentMonth = businessLogic.getEventsMonth(calendar.
								getDate());
					}

					CreateQuestionGUI.paintDaysWithEvents(calendar,datesWithEventsInCurrentMonth);

					try {
						eventTableModel.setDataVector(null, eventColumnNames);
						eventTableModel.setColumnCount(3); // another column added to allocate ev objects

						Vector<domain.Event> events = businessLogic.getEvents(firstDay);

						if (events.isEmpty() ) eventLbl.setText(ResourceBundle.getBundle("Etiquetas").
								getString("NoEvents") + ": " + dateformat1.format(currentCalendar.
										getTime()));
						else eventLbl.setText(ResourceBundle.getBundle("Etiquetas").
								getString("Events") + ": " + dateformat1.format(currentCalendar.
										getTime()));
						for (domain.Event ev : events){
							Vector<Object> row = new Vector<Object>();
							System.out.println("Events " + ev);
							row.add(ev.getEventNumber());
							row.add(ev.getDescription());
							row.add(ev); 	// ev object added in order to obtain it with 
							// tableModelEvents.getValueAt(i,2)
							eventTableModel.addRow(row);		
						}
						eventTable.getColumnModel().getColumn(0).setPreferredWidth(25);
						eventTable.getColumnModel().getColumn(1).setPreferredWidth(268);
						eventTable.getColumnModel().removeColumn(eventTable.getColumnModel().
								getColumn(2)); // not shown in JTable
					}
					catch (Exception e1) {
						questionLbl.setText(e1.getMessage());
					}
				}
			} 
		});

		this.getContentPane().add(calendar, null);

		eventScrollPane.setBounds(new Rectangle(292, 50, 346, 150));
		questionScrollPane.setBounds(new Rectangle(138, 274, 406, 116));

		eventTable.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				int i = eventTable.getSelectedRow();
				domain.Event ev = (domain.Event)eventTableModel.getValueAt(i,2); // obtain ev object
				Vector<Question> queries = ev.getQuestions();

				questionTableModel.setDataVector(null, questionColumnNames);

				if (queries.isEmpty())
					questionLbl.setText(ResourceBundle.getBundle("Etiquetas").
							getString("NoQuestions") + ": " + ev.getDescription());
				else 
					questionLbl.setText(ResourceBundle.getBundle("Etiquetas").
							getString("SelectedEvent") + " " + ev.getDescription());

				for (domain.Question q : queries) {
					Vector<Object> row = new Vector<Object>();
					row.add(q.getQuestionNumber());
					row.add(q.getQuestion());
					questionTableModel.addRow(row);	
				}
				questionTable.getColumnModel().getColumn(0).setPreferredWidth(25);
				questionTable.getColumnModel().getColumn(1).setPreferredWidth(268);
			}
		});

		eventScrollPane.setViewportView(eventTable);
		eventTableModel = new DefaultTableModel(null, eventColumnNames);

		eventTable.setModel(eventTableModel);
		eventTable.getColumnModel().getColumn(0).setPreferredWidth(25);
		eventTable.getColumnModel().getColumn(1).setPreferredWidth(268);
		
		
		questionScrollPane.setViewportView(questionTable);
		questionTableModel = new DefaultTableModel(null, questionColumnNames);

		questionTable.setModel(questionTableModel);
		questionTable.getColumnModel().getColumn(0).setPreferredWidth(25);
		questionTable.getColumnModel().getColumn(1).setPreferredWidth(268);

		this.getContentPane().add(eventScrollPane, null);
		this.getContentPane().add(questionScrollPane, null);

		
		JFrame thisFrame = this;
		JButton btnBet = new JButton(ResourceBundle.getBundle("Etiquetas").getString("BrowseQuestionsGUI.btnNewButton.text")); //$NON-NLS-1$ //$NON-NLS-2$
		btnBet.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(questionTable.getSelectedRow()!=-1 && eventTable.getSelectedRow()!=-1 && answerTable.getSelectedRow()!=-1) {
					Integer questNumber = (Integer) questionTable.getValueAt(questionTable.getSelectedRow(), 0);
					Integer eventNumber = (Integer) eventTable.getValueAt(eventTable.getSelectedRow(), 0);
					Integer answerNum = (Integer) answerTable.getValueAt(eventTable.getSelectedRow(), 0);
					setVisible(false);
					ConfirmGUI confirmation = new ConfirmGUI();
					confirmation.setVisible(true);
					confirmation.previousFrame(thisFrame);
					confirmation.setValues(eventNumber, questNumber, answerNum, Integer.parseInt(betInp.getText()));
				}
			}
		});
		btnBet.setFont(new Font("Tahoma", Font.PLAIN, 15));
		btnBet.setBounds(589, 347, 89, 43);
		getContentPane().add(btnBet);
		
		betInp = new JTextField();
		betInp.setColumns(10);
		betInp.setText("");
		betInp.setBounds(589, 315, 89, 20);
		getContentPane().add(betInp);
		
		JScrollPane answerScrollPane = new JScrollPane();
		answerScrollPane.setBounds(new Rectangle(138, 274, 406, 116));
		answerScrollPane.setBounds(138, 441, 406, 116);
		getContentPane().add(answerScrollPane);
		
		answerTable = new JTable();
		answerTable.setModel(new DefaultTableModel(
			new Object[][] {
			},
			new String[] {
				"#Answer", "Answer"
			}
		));
		answerTable.getColumnModel().getColumn(0).setPreferredWidth(25);
		answerTable.getColumnModel().getColumn(0).setMinWidth(6);
		answerTable.getColumnModel().getColumn(1).setPreferredWidth(277);
		answerScrollPane.setViewportView(answerTable);
		
		JLabel answerLbl = new JLabel(ResourceBundle.getBundle("Etiquetas").getString("BrowseQuestionsGUI.answerLbl.text")); //$NON-NLS-1$ //$NON-NLS-2$
		answerLbl.setBounds(138, 416, 46, 14);
		getContentPane().add(answerLbl);
		
		JLabel lblEnterAmount = new JLabel(ResourceBundle.getBundle("Etiquetas").getString("BrowseQuestionsGUI.lblEnterAmount.text")); //$NON-NLS-1$ //$NON-NLS-2$
		lblEnterAmount.setFont(new Font("Tahoma", Font.PLAIN, 13));
		lblEnterAmount.setBounds(577, 295, 116, 14);
		getContentPane().add(lblEnterAmount);
		
		questionTable.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				int i = questionTable.getSelectedRow();
				domain.Question question = (domain.Question)questionTableModel.getValueAt(i,2); // obtain question object
				ArrayList<domain.Answer> answers = question.getAnswerList();

				answerTableModel.setDataVector(null, answerColumnNames);
				

				if (answers.isEmpty())
					answerLbl.setText(ResourceBundle.getBundle("Etiquetas").
							getString("NoAnswers") + ": " + question.getQuestion());
				else 
					answerLbl.setText(ResourceBundle.getBundle("Etiquetas").
							getString("SelectedQuestion") + " " + question.getQuestion());

				for (domain.Answer a : answers) {
					Vector<Object> row = new Vector<Object>();
					row.add(a.getAnswerId());
					row.add(a.getContent());
					answerTableModel.addRow(row);	
				}
				answerTable.getColumnModel().getColumn(0).setPreferredWidth(25);
				answerTable.getColumnModel().getColumn(1).setPreferredWidth(268);
				
			}
		});

	}

	private void jButton2_actionPerformed(ActionEvent e) {
		
		this.dispose();
		prevFrame.setVisible(true);
	}
	
	/**
	 * Gets the previous frame
	 * @param frame
	 */
	public void previousFrame(JFrame frame) {
		
		prevFrame = frame;
	}
}