package businessLogic;

import java.util.ArrayList;
import java.util.Date;

import java.util.ResourceBundle;
import java.util.Vector;

import javax.jws.WebMethod;
import javax.jws.WebService;

import configuration.ConfigXML;
import dataAccess.DataAccess;
import domain.Answer;
import domain.Event;
import domain.Question;
import domain.User;
import exceptions.AnswerAlreadyExist;
import exceptions.EventFinished;
import exceptions.QuestionAlreadyExist;


/**
 * Implements the business logic as a web service.
 */
@WebService(endpointInterface = "businessLogic.BlFacade")
public class BlFacadeImplementation implements BlFacade {

	DataAccess dbManager;
	ConfigXML config = ConfigXML.getInstance();

	public BlFacadeImplementation()  {		
		System.out.println("Creating BlFacadeImplementation instance");
		boolean initialize = config.getDataBaseOpenMode().equals("initialize");
		dbManager = new DataAccess(initialize);
		if (initialize)
			dbManager.initializeDB();
		dbManager.close();
	}

	public BlFacadeImplementation(DataAccess dam)  {
		System.out.println("Creating BlFacadeImplementation instance with DataAccess parameter");
		if (config.getDataBaseOpenMode().equals("initialize")) {
			dam.open(true);
			dam.initializeDB();
			dam.close();
		}
		dbManager = dam;		
	}


	/**
	 * This method creates a question for an event, with a question text and the minimum bet
	 * 
	 * @param event to which question is added
	 * @param question text of the question
	 * @param betMinimum minimum quantity of the bet
	 * @return the created question, or null, or an exception
	 * @throws EventFinished if current data is after data of the event
	 * @throws QuestionAlreadyExist if the same question already exists for the event
	 * @throws AnswerAlreadyExist 
	 */
	@Override
	@WebMethod
	public Question createQuestion(Event event, String question, float betMinimum, ArrayList<String> answerList, ArrayList<Integer> rateList, Integer type) 
			throws EventFinished, QuestionAlreadyExist, AnswerAlreadyExist {

		//The minimum bid must be greater than 0
		dbManager.open(false);
		Question qry = null;

		if (new Date().compareTo(event.getEventDate()) > 0)
			throw new EventFinished(ResourceBundle.getBundle("Etiquetas").
					getString("ErrorEventHasFinished"));

		qry = dbManager.createQuestion(event, question, betMinimum,answerList,rateList,type);		
		dbManager.close();
		return qry;
	}

	/**
	 * This method invokes the data access to retrieve the events of a given date 
	 * 
	 * @param date in which events are retrieved
	 * @return collection of events
	 */
	@Override
	@WebMethod	
	public Vector<Event> getEvents(Date date)  {
		dbManager.open(false);
		Vector<Event>  events = dbManager.getEvents(date);
		dbManager.close();
		return events;
	}


	/**
	 * This method invokes the data access to retrieve the dates a month for which there are events
	 * 
	 * @param date of the month for which days with events want to be retrieved 
	 * @return collection of dates
	 */
	@Override
	@WebMethod
	public Vector<Date> getEventsMonth(Date date) {
		dbManager.open(false);
		Vector<Date>  dates = dbManager.getEventsMonth(date);
		dbManager.close();
		return dates;
	}
	
	@WebMethod
	public void close() {
		dbManager.close();
	}

	/**
	 * This method invokes the data access to initialize the database with some events and questions.
	 * It is invoked only when the option "initialize" is declared in the tag dataBaseOpenMode of resources/config.xml file
	 */	
	
	@WebMethod	
	public void initializeBD(){
		dbManager.open(false);
		dbManager.initializeDB();
		dbManager.close();
	}
	@Override
	@WebMethod
	public User getUserLogged(String username){
		//dbManager.open(false);
		User ret = dbManager.getUserByUsername(username);
		//dbManager.close();
		return ret;
		
	}
	

	
	@Override
	@WebMethod 
	public boolean isInt(String str) {
		try {
			Integer.parseInt(str);
			return true;
		}catch(NumberFormatException e1) {
			return false;
		}
	}

	
	/**
	 * Checks credentials for user
	 * 
	 * @param usname
	 * @param passwd
	 * @return true if they match, false if not
	 */
	@Override
	@WebMethod
	public boolean checkCredentialsUser(String usname, String passwd) {
		dbManager.open(false);
		boolean ret = dbManager.checkUser(usname, passwd);
		dbManager.close();
		return ret;
	}

	/**
	 * Checks credentials for administrator
	 * 
	 * @param usname
	 * @param passwd
	 * @return true if they match, false if not
	 */
	@Override
	@WebMethod
	public boolean checkCredentialsAdmin(String usname, String passwd) {

		if (usname.equals("admin") && passwd.equals("admin")) {
			return true;
		} else {
			return false;
		}
	}

	/**
	 * Checks if both passwords in registration match
	 * 
	 * @param pass1
	 * @param pass2
	 * @return true if they match, false if not
	 */
	@Override
	@WebMethod
	public boolean passwdMatches(String pass1, String pass2) {

		if (pass1.equals(pass2) && pass1.length() != 0) {
			return true;
		} else {
			return false;
		}
	}
	
	/**
	 * Stores a newly registered user to the database
	 * 
	 * @param userp
	 * @param passwordp
	 */
	@Override
	@WebMethod
	public int storeUser(String userp, String passwordp, Date birthDate, String name, String surname, String email,
			String bankAccount) {
		dbManager.open(false);
		int ret = dbManager.storeUser(userp, passwordp, birthDate, name, surname, email, bankAccount);
		dbManager.close();
		return ret;
	}



	/**
	 * Updates the given bets of the user, by adding the recent one
	 * 
	 * @param userid
	 * @param questionId
	 * @param amount
	 */
	@Override
	@WebMethod
	public void storeBet(String username,Question question, Answer answer, Event event, Date date, int amount) {

		dbManager.storeBet(username, question, answer, event, date, amount);
	}

	/**
	 * Question getter
	 * 
	 * @param eventNum
	 * @param questionNum
	 * @return
	 */
	@Override
	@WebMethod
	public Question getQuestion(int eventNum, int questionNum) {

		dbManager.open(false);
		Question q = dbManager.getQuestion(eventNum, questionNum);		
		dbManager.close();
		
		return q;
	}

	/**
	 * Answer getter
	 * 
	 * @param eventNum
	 * @param questionNum
	 * @param answerNum
	 * @return
	 */
	@Override
	@WebMethod
	public Answer getAnswer(Integer eventNum, Integer questionNum, Integer answerNum) {

		dbManager.open(false);
		Answer a = dbManager.getAnswer(eventNum, questionNum, answerNum);
		dbManager.close();
		
		return a;
	}

	/**
	 * Event getter
	 * 
	 * @param eventNum
	 * @return
	 */
	@Override
	@WebMethod
	public Event getEvent(Integer eventNum) {

		dbManager.open(false);
		Event e = dbManager.getEvent(eventNum);
		dbManager.close();
		
		return e;
	}

	/**
	 * Method that updates the wallet of the user, incrementing the money by the
	 * given amount
	 * 
	 * @param amount
	 */
	@Override
	@WebMethod
	public void insertMoneyLoggedUser(String username ,int amount) {

		dbManager.insertMoneyLoggedUser(username, amount);
	}
	
	

	
	/**
	 * Updates user data. If the given attribute is empty, remains still
	 * 
	 * @param uName
	 * @param pass
	 * @param bankN
	 */
	@Override
	@WebMethod
	public int updateUserData(String username ,String uName, String pass, String bankN) {
		
		return dbManager.updateUserData(username, uName, pass, bankN);
	}

	/**
	 * Method that removes a bet given its ID
	 * 
	 * @param remBetId
	 * @param amount
	 */
	@Override
	@WebMethod
	public void removeBet(String username, Integer remBetId, int amount) {

		dbManager.removeBet(username, remBetId, amount);
	}

	@Override
	@WebMethod
	public int manageResults(ArrayList<Integer> eventList, ArrayList<Integer> questionType, ArrayList<String> resultList,
			ArrayList<Integer> questionList, ArrayList<Date> dateList) {
		dbManager.open(false);
		int ret = dbManager.manageResults(eventList,questionType,resultList,questionList, dateList);
		dbManager.close();
		return ret;
	}

	@WebMethod
	public Integer createAnswer(Integer eventNum, Integer questionNum, String answerContent, String answerRate) throws AnswerAlreadyExist {
		dbManager.open(false);
		int ret = dbManager.createAnswer(eventNum,questionNum,answerContent,answerRate);
		dbManager.close();
		return ret;
	}

	@Override
	public void resetLogins() {
		dbManager.resetLogins();
		
	}

	@Override
	public void markLogin(String text, String valueOf) {
		dbManager.open(false);
		dbManager.markLogin( text, valueOf);
		dbManager.close();
		
	}

	@Override
	public void logout(String username) {
		dbManager.open(false);
		dbManager.logout( username);
		dbManager.close();
		
	}

}