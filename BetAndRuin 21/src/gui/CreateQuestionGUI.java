package gui;

import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.Rectangle;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.text.DateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;
import java.util.ResourceBundle;
import java.util.Vector;

import javax.swing.DefaultComboBoxModel;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JScrollPane;
import javax.swing.JTextField;

import com.toedter.calendar.JCalendar;

import businessLogic.BlFacade;
import configuration.UtilDate;
import domain.Event;
import exceptions.EventFinished;
import exceptions.QuestionAlreadyExist;

public class CreateQuestionGUI extends JFrame {

	private static final long serialVersionUID = 1L;

	private BlFacade businessLogic;
	
	private String Username;
	
	private JComboBox<Event> eventComboBox = new JComboBox<Event>();
	DefaultComboBoxModel<Event> eventModel = new DefaultComboBoxModel<Event>();

	private JLabel listOfEventsLbl = new JLabel(ResourceBundle.getBundle("Etiquetas").getString("ListEvents"));
	private JLabel queryLbl = new JLabel(ResourceBundle.getBundle("Etiquetas").getString("Question"));
	private JLabel minBetLbl = new JLabel(ResourceBundle.getBundle("Etiquetas").getString("MinimumBetPrice"));
	private JLabel eventDateLbl = new JLabel(ResourceBundle.getBundle("Etiquetas").getString("EventDate"));

	private JTextField queryText = new JTextField();
	private JTextField priceText = new JTextField();
	private JCalendar calendar = new JCalendar();
	private Calendar previousCalendar = null;
	private Calendar currentCalendar = null;

	private JScrollPane eventScrollPane = new JScrollPane();

	private JButton createBtn = new JButton(ResourceBundle.getBundle("Etiquetas").getString("CreateQuestion"));
	private JButton closeBtn = new JButton(ResourceBundle.getBundle("Etiquetas").getString("Close"));
	private JLabel msgLbl = new JLabel();
	private JLabel errorLbl = new JLabel();

	private Vector<Date> datesWithEventsInCurrentMonth = new Vector<Date>();

	private MainGUI prevFrame;
	private JTextField answersText;
	private JTextField answers1Text;
	private JTextField answers2Text;
	private final JTextField answers3Text = new JTextField();
	
	JLabel lblAnswers;
	
	private JComboBox<Integer> ratecomboBox1;
	private JComboBox<Integer> ratecomboBox2;
	private JComboBox<Integer> ratecomboBox3;
	private JComboBox<Integer> ratecomboBox4;
	
	private JComboBox<Integer> questionTypeComboBox;
	
	private final JButton btnLogout = new JButton(ResourceBundle.getBundle("Etiquetas").getString("Logout")); //$NON-NLS-1$ //$NON-NLS-2$

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

	public void setBusinessLogic(BlFacade bl) {
		businessLogic = bl;
	}

	public CreateQuestionGUI(BlFacade bl, Vector<domain.Event> v) {
		businessLogic = bl;
		Username = null;
		try {
			jbInit(v);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	private void jbInit(Vector<domain.Event> v) throws Exception {

		this.getContentPane().setLayout(null);
		this.setSize(new Dimension(652, 595));
		this.setTitle(ResourceBundle.getBundle("Etiquetas").getString("CreateQuestion"));

		eventComboBox.setModel(eventModel);
		eventComboBox.setBounds(new Rectangle(282, 106, 250, 20));
		listOfEventsLbl.setBounds(new Rectangle(297, 77, 277, 20));
		queryLbl.setBounds(new Rectangle(32, 270, 75, 20));
		queryText.setBounds(new Rectangle(107, 270, 429, 20));
		minBetLbl.setBounds(new Rectangle(32, 301, 75, 20));
		priceText.setBounds(new Rectangle(107, 301, 60, 20));

		calendar.setBounds(new Rectangle(47, 109, 225, 150));
		eventScrollPane.setBounds(new Rectangle(25, 44, 346, 116));

		createBtn.setBounds(new Rectangle(85, 503, 149, 30));
		createBtn.setEnabled(false);

		createBtn.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				jButtonCreate_actionPerformed(e);
			}
		});
		closeBtn.setBounds(new Rectangle(423, 503, 130, 30));
		closeBtn.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				jButtonClose_actionPerformed(e);
			}
		});

		msgLbl.setBounds(new Rectangle(285, 233, 305, 20));
		msgLbl.setForeground(Color.red);
		// jLabelMsg.setSize(new Dimension(305, 20));

		errorLbl.setBounds(new Rectangle(182, 299, 305, 20));
		errorLbl.setForeground(Color.red);

		this.getContentPane().add(msgLbl, null);
		this.getContentPane().add(errorLbl, null);

		this.getContentPane().add(closeBtn, null);
		this.getContentPane().add(createBtn, null);
		this.getContentPane().add(queryText, null);
		this.getContentPane().add(queryLbl, null);
		this.getContentPane().add(priceText, null);

		this.getContentPane().add(minBetLbl, null);
		this.getContentPane().add(listOfEventsLbl, null);
		this.getContentPane().add(eventComboBox, null);

		this.getContentPane().add(calendar, null);

		datesWithEventsInCurrentMonth = businessLogic.getEventsMonth(calendar.getDate());
		paintDaysWithEvents(calendar, datesWithEventsInCurrentMonth);

		eventDateLbl.setBounds(new Rectangle(40, 15, 140, 25));
		eventDateLbl.setBounds(47, 59, 140, 25);
		getContentPane().add(eventDateLbl);
		
		lblAnswers = new JLabel(ResourceBundle.getBundle("Etiquetas").getString("CreateQuestionGUI.lblNewLabel.text")); //$NON-NLS-1$ //$NON-NLS-2$
		lblAnswers.setBounds(32, 382, 455, 14);
		getContentPane().add(lblAnswers);
		
		answersText = new JTextField();
		answersText.setText(ResourceBundle.getBundle("Etiquetas").getString("CreateQuestionGUI.textField.text")); //$NON-NLS-1$ //$NON-NLS-2$
		answersText.setBounds(107, 379, 429, 20);
		getContentPane().add(answersText);
		answersText.setColumns(10);
		
		answers1Text = new JTextField();
		answers1Text.setText(" ");
		answers1Text.setColumns(10);
		answers1Text.setBounds(107, 410, 429, 20);
		getContentPane().add(answers1Text);
		
		answers2Text = new JTextField();
		answers2Text.setText(" ");
		answers2Text.setColumns(10);
		answers2Text.setBounds(107, 441, 429, 20);
		getContentPane().add(answers2Text);
		answers3Text.setText(" ");
		answers3Text.setColumns(10);
		answers3Text.setBounds(107, 472, 429, 20);
		
		getContentPane().add(answers3Text);
		btnLogout.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				setVisible(false);
				prevFrame.setVisible(true);
				prevFrame.getBtnLogin().setVisible(true);
				prevFrame.getBtnRegister().setVisible(true);
				prevFrame.getBifunctionalBtn().setVisible(false);
				prevFrame.getBtnInsertResults().setVisible(false);
				prevFrame.setUsername(null);
				businessLogic.logout(Username);
			}
		});
		btnLogout.setBounds(47, 25, 89, 23);
		
		getContentPane().add(btnLogout);
		
		JLabel lblRates = new JLabel(ResourceBundle.getBundle("Etiquetas").getString("CreateQuestionGUI.lblRates.text")); //$NON-NLS-1$ //$NON-NLS-2$
		lblRates.setBounds(535, 354, 75, 14);
		getContentPane().add(lblRates);
		
		ratecomboBox1 = new JComboBox<Integer>();
		ratecomboBox1.setBounds(571, 378, 39, 22);
		
		ratecomboBox2 = new JComboBox<Integer>();
		ratecomboBox2.setBounds(571, 409, 39, 22);
		
		ratecomboBox3 = new JComboBox<Integer>();
		ratecomboBox3.setBounds(571, 440, 39, 22);
		
		ratecomboBox4 = new JComboBox<Integer>();
		ratecomboBox4.setBounds(571, 471, 39, 22);
		
		for(int i = 1 ; i<101; i++) {
			ratecomboBox1.addItem((Integer)i);
			ratecomboBox2.addItem((Integer)i);
			ratecomboBox3.addItem((Integer)i);
			ratecomboBox4.addItem((Integer)i);
		}
		
		getContentPane().add(ratecomboBox1);
		getContentPane().add(ratecomboBox2);
		getContentPane().add(ratecomboBox3);
		getContentPane().add(ratecomboBox4);
		
		JLabel questType = new JLabel(ResourceBundle.getBundle("Etiquetas").getString("CreateQuestionGUI.questType.text")); //$NON-NLS-1$ //$NON-NLS-2$
		questType.setBounds(new Rectangle(32, 301, 75, 20));
		questType.setBounds(10, 332, 97, 20);
		getContentPane().add(questType);
		
		questionTypeComboBox = new JComboBox<Integer>();
		questionTypeComboBox.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(questionTypeComboBox.getSelectedIndex()!=-1){
					if((int)questionTypeComboBox.getSelectedItem()==1) {
						answersText.setVisible(true);
						answers1Text.setVisible(true);
						answers2Text.setVisible(true);
						answers3Text.setVisible(true);
						ratecomboBox1.setVisible(true);
						ratecomboBox2.setVisible(true);
						ratecomboBox3.setVisible(true);
						ratecomboBox4.setVisible(true);
						lblRates.setVisible(true);
						lblAnswers.setText(ResourceBundle.getBundle("Etiquetas").getString("CreateQuestionGUI.lblNewLabel.text"));
						
					}
					if((int)questionTypeComboBox.getSelectedItem()==2) {
						answersText.setVisible(false);
						answers1Text.setVisible(false);
						answers2Text.setVisible(false);
						answers3Text.setVisible(false);
						ratecomboBox1.setVisible(false);
						ratecomboBox2.setVisible(false);
						ratecomboBox3.setVisible(false);
						ratecomboBox4.setVisible(false);
						lblRates.setVisible(false);
						lblAnswers.setText("No need to enter answers for this type of questions.");
						
					}
				}
			}
		});
		
		questionTypeComboBox.setBounds(107, 331, 80, 22);
		for(int i = 1 ; i<3; i++) {
			questionTypeComboBox.addItem((Integer)i);
		}
		getContentPane().add(questionTypeComboBox);

		// Code for JCalendar
		this.calendar.addPropertyChangeListener(new PropertyChangeListener() {
			@Override
			public void propertyChange(PropertyChangeEvent propertychangeevent) {
				if (propertychangeevent.getPropertyName().equals("locale")) {
					calendar.setLocale((Locale) propertychangeevent.getNewValue());
				} else if (propertychangeevent.getPropertyName().equals("calendar")) {
					currentCalendar = (Calendar) propertychangeevent.getOldValue();
					previousCalendar = (Calendar) propertychangeevent.getNewValue();
					System.out.println("calendarAnt: " + currentCalendar.getTime());
					System.out.println("calendarAct: " + previousCalendar.getTime());
					DateFormat dateformat1 = DateFormat.getDateInstance(1, calendar.getLocale());

					int monthAnt = currentCalendar.get(Calendar.MONTH);
					int monthAct = previousCalendar.get(Calendar.MONTH);
					if (monthAct != monthAnt) {
						if (monthAct == monthAnt + 2) {
							// Si en JCalendar estÃ¡ 30 de enero y se avanza al mes siguiente,
							// devolverá 2 de marzo (se toma como equivalente a 30 de febrero)
							// Con este código se dejará como 1 de febrero en el JCalendar
							previousCalendar.set(Calendar.MONTH, monthAnt + 1);
							previousCalendar.set(Calendar.DAY_OF_MONTH, 1);
						}

						calendar.setCalendar(previousCalendar);

						datesWithEventsInCurrentMonth = businessLogic.getEventsMonth(calendar.getDate());
					}

					paintDaysWithEvents(calendar, datesWithEventsInCurrentMonth);

					Date firstDay = UtilDate.trim(previousCalendar.getTime());

					try {
						Vector<domain.Event> events = businessLogic.getEvents(firstDay);

						if (events.isEmpty())
							listOfEventsLbl.setText(ResourceBundle.getBundle("Etiquetas").getString("NoEvents") + ": "
									+ dateformat1.format(previousCalendar.getTime()));
						else
							listOfEventsLbl.setText(ResourceBundle.getBundle("Etiquetas").getString("Events") + " : "
									+ dateformat1.format(previousCalendar.getTime()));
						eventComboBox.removeAllItems();
						System.out.println("Events " + events);

						for (domain.Event ev : events)
							eventModel.addElement(ev);
						eventComboBox.repaint();

						if (events.size() == 0)
							createBtn.setEnabled(false);
						else
							createBtn.setEnabled(true);

					} catch (Exception e1) {

						errorLbl.setText(e1.getMessage());
					}
				}
			}
		});
	}

	public static void paintDaysWithEvents(JCalendar jCalendar, Vector<Date> datesWithEventsCurrentMonth) {
		// For each day with events in current month, the background color for that day
		// is changed.

		Calendar calendar = jCalendar.getCalendar();

		int month = calendar.get(Calendar.MONTH);
		int today = calendar.get(Calendar.DAY_OF_MONTH);
		int year = calendar.get(Calendar.YEAR);

		calendar.set(Calendar.DAY_OF_MONTH, 1);
		int offset = calendar.get(Calendar.DAY_OF_WEEK);

		if (Locale.getDefault().equals(new Locale("es")))
			offset += 4;
		else
			offset += 5;

		for (Date d : datesWithEventsCurrentMonth) {
			calendar.setTime(d);
			System.out.println(d);

			// Obtain the component of the day in the panel of the DayChooser of the
			// JCalendar.
			// The component is located after the decorator buttons of "Sun", "Mon",... or
			// "Lun", "Mar"...,
			// the empty days before day 1 of month, and all the days previous to each day.
			// That number of components is calculated with "offset" and is different in
			// English and Spanish

			Component o = jCalendar.getDayChooser().getDayPanel()
					.getComponent(calendar.get(Calendar.DAY_OF_MONTH) + offset);
			o.setBackground(Color.CYAN);
		}

		calendar.set(Calendar.DAY_OF_MONTH, today);
		calendar.set(Calendar.MONTH, month);
		calendar.set(Calendar.YEAR, year);
	}

	private void jButtonCreate_actionPerformed(ActionEvent e) {
		domain.Event event = ((domain.Event) eventComboBox.getSelectedItem());

		try {
			errorLbl.setText("");
			msgLbl.setText("");

			// Displays an exception if the query field is empty
			String inputQuestion = queryText.getText();
			
			Integer inputType = (Integer) questionTypeComboBox.getSelectedItem();
			
			
			
			
			
			
			
			
			
			
			
			
			if (inputQuestion.length() > 0) {

				// It could be to trigger an exception if the introduced string is not a number
				float inputPrice = Float.parseFloat(priceText.getText());

				if (inputPrice <= 0)
					errorLbl.setText(ResourceBundle.getBundle("Etiquetas").getString("ErrorNumber"));
				else {
					if(questionTypeComboBox.getSelectedIndex()!=-1) {
						if((int)questionTypeComboBox.getSelectedItem()==1) {
							
							//Inserts the Answers in the input depending on how many answers the admin enters.
							ArrayList<String> inputAnswerTotal = new ArrayList<String>();
							ArrayList<Integer> inputRateTotal = new ArrayList<Integer>();
							
							String inputAnswer = answersText.getText();
							String inputAnswer1 = answers1Text.getText();
							String inputAnswer2 = answers2Text.getText();
							String inputAnswer3 = answers3Text.getText();
							
							
							if(inputAnswer.length() > 1 && ratecomboBox1.getSelectedIndex()!=-1 ) {
								inputRateTotal.add((Integer) ratecomboBox1.getSelectedItem());
								inputAnswerTotal.add(inputAnswer);
							}
							if(inputAnswer1.length() > 1 && ratecomboBox2.getSelectedIndex()!=-1 ) {
								inputRateTotal.add((Integer) ratecomboBox2.getSelectedItem());
								inputAnswerTotal.add(inputAnswer1);
							}
							if(inputAnswer2.length() > 1 && ratecomboBox3.getSelectedIndex()!=-1 ) {
								inputRateTotal.add((Integer) ratecomboBox3.getSelectedItem());
								inputAnswerTotal.add(inputAnswer2);
							}
							if(inputAnswer3.length() > 1 && ratecomboBox4.getSelectedIndex()!=-1 ) {
								inputRateTotal.add((Integer) ratecomboBox4.getSelectedItem());
								inputAnswerTotal.add(inputAnswer3);
							}
							
							if(inputAnswerTotal.size() >= 1 && inputRateTotal.size() >= 1) {
								
								businessLogic.createQuestion(event, inputQuestion, inputPrice, inputAnswerTotal,inputRateTotal,inputType);
								
							}else {
								msgLbl.setText(ResourceBundle.getBundle("Etiquetas").getString("ErrorAnswer"));
							}
						}
						if((int)questionTypeComboBox.getSelectedItem()==2){
							businessLogic.createQuestion(event, inputQuestion, inputPrice, null ,null ,inputType);
						}
					}
					msgLbl.setText(ResourceBundle.getBundle("Etiquetas").getString("QuestionCreated"));
				}
			} else
				msgLbl.setText(ResourceBundle.getBundle("Etiquetas").getString("ErrorQuestion"));
		} catch (EventFinished e1) {
			msgLbl.setText(ResourceBundle.getBundle("Etiquetas").getString("ErrorEventHasFinished") + " : "
					+ event.getDescription());
		} catch (QuestionAlreadyExist e1) {
			msgLbl.setText(ResourceBundle.getBundle("Etiquetas").getString("ErrorQuestionAlreadyExist"));
		} catch (java.lang.NumberFormatException e1) {
			errorLbl.setText(ResourceBundle.getBundle("Etiquetas").getString("ErrorNumber"));
		} catch (Exception e1) {
			e1.printStackTrace();
		}
	}

	private void jButtonClose_actionPerformed(ActionEvent e) {
		dispose();
		prevFrame.setVisible(true);
	}
}