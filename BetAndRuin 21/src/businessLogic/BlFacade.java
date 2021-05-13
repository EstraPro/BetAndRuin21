package businessLogic;

import java.util.ArrayList;
import java.util.Date;
import java.util.Vector;

import javax.jws.WebMethod;
import javax.jws.WebService;

import domain.Answer;
import domain.Event;
import domain.Question;
import domain.User;
import exceptions.AnswerAlreadyExist;
import exceptions.EventFinished;
import exceptions.QuestionAlreadyExist;

/**
 * Interface that specifies the business logic.
 */
@WebService
public interface BlFacade  {

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
	@WebMethod
	Question createQuestion(Event event, String question, float betMinimum, ArrayList<String> answerList, ArrayList<Integer> rateList, Integer type) 
			throws EventFinished, QuestionAlreadyExist, AnswerAlreadyExist;
		
	/**
	 * This method retrieves all the events of a given date 
	 * 
	 * @param date in which events are retrieved
	 * @return collection of events
	 */
	@WebMethod public Vector<Event> getEvents(Date date);
	
	/**
	 * This method retrieves from the database the dates in a month for which there are events
	 * 
	 * @param date of the month for which days with events want to be retrieved 
	 * @return collection of dates
	 */
	@WebMethod public Vector<Date> getEventsMonth(Date date);
		
	
	@WebMethod public User getUserLogged();
	
	@WebMethod public String getLoggedUserUserName();
	
	@WebMethod public Question getQuestion(int eventNum, int questionNum);
	
	@WebMethod public boolean isInt(String str);

	@WebMethod public boolean isAnyUserLogged();

	@WebMethod public void resetLogins();
	
	@WebMethod public boolean passwdMatches(String pass1, String pass2);
	
	@WebMethod public int storeUser(String userp, String passwordp, Date birthDate, String name, String surname, String email,
			String bankAccount) ;
	
	@WebMethod public boolean checkCredentialsAdmin(String usname, String passwd);
	
	@WebMethod public boolean checkCredentialsUser(String usname, String passwd);
	
	@WebMethod public void markLogin(String user, String passwd);

	@WebMethod public void removeBet(Integer remBetId, int valueAt);
	
	@WebMethod public void insertMoneyLoggedUser(int amount);
	
	@WebMethod public int updateUserData(String uName, String pass, String bankN);
	
	@WebMethod public Answer getAnswer(Integer eventNum, Integer questionNum, Integer answerNum);
	
	@WebMethod public Event getEvent(Integer eventNum);
	
	@WebMethod public void storeBet(Question question, Answer answer, Event event, Date date, int amount);

	@WebMethod int manageResults(ArrayList<String> eventList, ArrayList<Integer> questionType, ArrayList<String> resultList,
			ArrayList<Date> dateList);

	@WebMethod Integer createAnswer(Integer eventNum, Integer questionNum, String string, String string2) throws AnswerAlreadyExist;
	
	
	
}