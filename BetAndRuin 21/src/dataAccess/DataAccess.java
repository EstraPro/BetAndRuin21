package dataAccess;

import java.util.ArrayList;

import java.util.Arrays;
import java.util.Calendar;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.ResourceBundle;
import java.util.Vector;

import javax.persistence.EntityExistsException;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.NoResultException;
import javax.persistence.Persistence;
import javax.persistence.Query;
import javax.persistence.TypedQuery;

import com.objectdb.o.UserException;

import configuration.ConfigXML;
import configuration.UtilDate;
import domain.Answer;
import domain.Bet;
import domain.Event;
import domain.Question;
import domain.User;
import exceptions.QuestionAlreadyExist;

/**
 * Implements the Data Access utility to the objectDb database
 */
public class DataAccess {

	protected EntityManager db;
	protected EntityManagerFactory emf;

	ConfigXML config = ConfigXML.getInstance();

	public DataAccess(boolean initializeMode) {
		System.out.println("Creating DataAccess instance => isDatabaseLocal: " + config.isDataAccessLocal()
				+ " getDatabBaseOpenMode: " + config.getDataBaseOpenMode());
		open(initializeMode);
	}

	public DataAccess() {
		this(false);
	}

	/**
	 * This method initializes the database with some trial events and questions. It
	 * is invoked by the business logic when the option "initialize" is used in the
	 * tag dataBaseOpenMode of resources/config.xml file
	 */
	public void initializeDB() {

		db.getTransaction().begin();

		try {

			Calendar today = Calendar.getInstance();

			int month = today.get(Calendar.MONTH);
			month += 1;
			int year = today.get(Calendar.YEAR);
			if (month == 12) {
				month = 0;
				year += 1;
			}

			Event ev1 = new Event(1, "Atlético-Athletic", UtilDate.newDate(year, month, 17));
			Event ev2 = new Event(2, "Eibar-Barcelona", UtilDate.newDate(year, month, 17));
			Event ev3 = new Event(3, "Getafe-Celta", UtilDate.newDate(year, month, 17));
			Event ev4 = new Event(4, "Alavés-Deportivo", UtilDate.newDate(year, month, 17));
			Event ev5 = new Event(5, "Español-Villareal", UtilDate.newDate(year, month, 17));
			Event ev6 = new Event(6, "Las Palmas-Sevilla", UtilDate.newDate(year, month, 17));
			Event ev7 = new Event(7, "Malaga-Valencia", UtilDate.newDate(year, month, 17));
			Event ev8 = new Event(8, "Girona-Leganés", UtilDate.newDate(year, month, 17));
			Event ev9 = new Event(9, "Real Sociedad-Levante", UtilDate.newDate(year, month, 17));
			Event ev10 = new Event(10, "Betis-Real Madrid", UtilDate.newDate(year, month, 17));

			Event ev11 = new Event(11, "Atletico-Athletic", UtilDate.newDate(year, month, 1));
			Event ev12 = new Event(12, "Eibar-Barcelona", UtilDate.newDate(year, month, 1));
			Event ev13 = new Event(13, "Getafe-Celta", UtilDate.newDate(year, month, 1));
			Event ev14 = new Event(14, "Alavés-Deportivo", UtilDate.newDate(year, month, 1));
			Event ev15 = new Event(15, "Español-Villareal", UtilDate.newDate(year, month, 1));
			Event ev16 = new Event(16, "Las Palmas-Sevilla", UtilDate.newDate(year, month, 1));

			Event ev17 = new Event(17, "Málaga-Valencia", UtilDate.newDate(year, month + 1, 28));
			Event ev18 = new Event(18, "Girona-Leganés", UtilDate.newDate(year, month + 1, 28));
			Event ev19 = new Event(19, "Real Sociedad-Levante", UtilDate.newDate(year, month + 1, 28));
			Event ev20 = new Event(20, "Betis-Real Madrid", UtilDate.newDate(year, month + 1, 28));

			Question q1;
			Question q2;
			Question q3;
			Question q4;
			Question q5;
			Question q6;

			Answer a1;
			Answer a2;
			Answer a3;
			Answer a4;
			Answer a5;
			Answer a6;
			Answer a7;
			Answer a8;
			Answer a9;
			Answer a10;
			Answer a11;
			Answer a12;

			if (Locale.getDefault().equals(new Locale("es"))) {

				q1 = ev1.addQuestion("¿Quién ganará el partido?", 1);
				q2 = ev1.addQuestion("¿Quién meterá el primer gol?", 2);
				q3 = ev11.addQuestion("¿Quién ganará el partido?", 1);
				q4 = ev11.addQuestion("¿Cuántos goles se marcarán?", 2);
				q5 = ev17.addQuestion("¿Quién ganará el partido?", 1);
				q6 = ev17.addQuestion("¿Habrá goles en la primera parte?", 2);

				a1 = q1.addSpecificAnswer("Athletic ganara");
				a2 = q1.addSpecificAnswer("Atlético ganara");
				a3 = q2.addSpecificAnswer("Athletic metera el primer gol");
				a4 = q2.addSpecificAnswer("Atlético metera el primer gol");
				a5 = q3.addSpecificAnswer("Athletic ganara");
				a6 = q3.addSpecificAnswer("Atlético ganara");
				a7 = q4.addSpecificAnswer("Mas de dos");
				a8 = q4.addSpecificAnswer("Menos de dos");
				a9 = q5.addSpecificAnswer("Málaga ganara");
				a10 = q5.addSpecificAnswer("Valencia ganara");
				a11 = q6.addSpecificAnswer("Si");
				a12 = q6.addSpecificAnswer("No");

			} else if (Locale.getDefault().equals(new Locale("en"))) {

				q1 = ev1.addQuestion("Who will win the match?", 1);
				q2 = ev1.addQuestion("Which team will score first?", 2);
				q3 = ev11.addQuestion("Who will win the match?", 1);
				q4 = ev11.addQuestion("How many goals will be scored?", 2);
				q5 = ev17.addQuestion("Who will win the match?", 1);
				q6 = ev17.addQuestion("Will they score in the first half of the match?", 2);

				a1 = q1.addSpecificAnswer("Athletic will win");
				a2 = q1.addSpecificAnswer("Atlético will win");
				a3 = q2.addSpecificAnswer("Athletic will score first");
				a4 = q2.addSpecificAnswer("Atlético will score first");
				a5 = q3.addSpecificAnswer("Athletic will win");
				a6 = q3.addSpecificAnswer("Atlético will win");
				a7 = q4.addSpecificAnswer("More than two");
				a8 = q4.addSpecificAnswer("Less than two");
				a9 = q5.addSpecificAnswer("Málaga will win");
				a10 = q5.addSpecificAnswer("Valencia will win");
				a11 = q6.addSpecificAnswer("Yes");
				a12 = q6.addSpecificAnswer("No");

			} else {

				q1 = ev1.addQuestion("Zeinek irabaziko du partidua?", 1);
				q2 = ev1.addQuestion("Zeinek sartuko du lehenengo gola?", 2);
				q3 = ev11.addQuestion("Zeinek irabaziko du partidua?", 1);
				q4 = ev11.addQuestion("Zenbat gol sartuko dira?", 2);
				q5 = ev17.addQuestion("Zeinek irabaziko du partidua?", 1);
				q6 = ev17.addQuestion("Golak sartuko dira lehenengo zatian?", 2);

				a1 = q1.addSpecificAnswer("Athletic-ek irabaziko du");
				a2 = q1.addSpecificAnswer("Atlético-k irabaziko du");
				a3 = q2.addSpecificAnswer("Athletic-ek sartuko du lehen gola");
				a4 = q2.addSpecificAnswer("Atlético-k sartuko du lehen gola");
				a5 = q3.addSpecificAnswer("Athletic-ek irabaziko du");
				a6 = q3.addSpecificAnswer("Atlético irabaziko du");
				a7 = q4.addSpecificAnswer("Bi baino gehiago");
				a8 = q4.addSpecificAnswer("Bi baino gutxiago");
				a9 = q5.addSpecificAnswer("Málaga-k irabaziko du");
				a10 = q5.addSpecificAnswer("Valencia-k irabaziko du");
				a11 = q6.addSpecificAnswer("Bai");
				a12 = q6.addSpecificAnswer("Ez");
			}
			/*
			 * db.persist(a1); db.persist(a2); db.persist(a3); db.persist(a4);
			 * db.persist(a5); db.persist(a6); db.persist(a7); db.persist(a8);
			 * db.persist(a9); db.persist(a10); db.persist(a11); db.persist(a12);
			 * 
			 * db.persist(q1); db.persist(q2); db.persist(q3); db.persist(q4);
			 * db.persist(q5); db.persist(q6);
			 */
			db.persist(ev1);
			db.persist(ev2);
			db.persist(ev3);
			db.persist(ev4);
			db.persist(ev5);
			db.persist(ev6);
			db.persist(ev7);
			db.persist(ev8);
			db.persist(ev9);
			db.persist(ev10);
			db.persist(ev11);
			db.persist(ev12);
			db.persist(ev13);
			db.persist(ev14);
			db.persist(ev15);
			db.persist(ev16);
			db.persist(ev17);
			db.persist(ev18);
			db.persist(ev19);
			db.persist(ev20);

			User admin = new User("admin", "admin");
			User pepita = new User("pepi", "1234", new Date(), "Pepita", "Kaltzaslargas", "motozikleta@gmail.com",
					"ES 2345 4937 4345 8678");
			db.persist(admin);
			db.persist(pepita);

			db.getTransaction().commit();
			System.out.println("The database has been initialized");
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * This method creates a question for an event, with a question text and the
	 * minimum bet
	 * 
	 * @param event      to which question is added
	 * @param question   text of the question
	 * @param betMinimum minimum quantity of the bet
	 * @return the created question, or null, or an exception
	 * @throws QuestionAlreadyExist if the same question already exists for the
	 *                              event
	 */
	public Question createQuestion(Event event, String question, float betMinimum, ArrayList<String> answerList)
			throws QuestionAlreadyExist {
		System.out.println(">> DataAccess: createQuestion=> event = " + event + " question = " + question
				+ " minimum bet = " + betMinimum + "Possible Answers" + answerList.toString());

		Event ev = db.find(Event.class, event.getEventNumber());

		if (ev.doesQuestionExist(question))
			throw new QuestionAlreadyExist(
					ResourceBundle.getBundle("Etiquetas").getString("ErrorQuestionAlreadyExist"));

		db.getTransaction().begin();
		Question q = ev.addQuestion(question, betMinimum);
		for (String lag : answerList) {
			q.addSpecificAnswer(lag);
		}
		// db.persist(q);
		db.persist(ev); // db.persist(q) not required when CascadeType.PERSIST is added
		// in questions property of Event class
		// @OneToMany(fetch=FetchType.EAGER, cascade=CascadeType.PERSIST)
		db.getTransaction().commit();
		return q;
	}

	/**
	 * This method retrieves from the database the events of a given date
	 * 
	 * @param date in which events are retrieved
	 * @return collection of events
	 */
	public Vector<Event> getEvents(Date date) {
		System.out.println(">> DataAccess: getEvents");
		Vector<Event> res = new Vector<Event>();
		TypedQuery<Event> query = db.createQuery("SELECT ev FROM Event ev WHERE ev.eventDate=?1", Event.class);
		query.setParameter(1, date);
		List<Event> events = query.getResultList();
		for (Event ev : events) {
			System.out.println(ev.toString());
			res.add(ev);
		}
		return res;
	}

	/**
	 * This method retrieves from the database the dates in a month for which there
	 * are events
	 * 
	 * @param date of the month for which days with events want to be retrieved
	 * @return collection of dates
	 */
	public Vector<Date> getEventsMonth(Date date) {
		System.out.println(">> DataAccess: getEventsMonth");
		Vector<Date> res = new Vector<Date>();

		Date firstDayMonthDate = UtilDate.firstDayMonth(date);
		Date lastDayMonthDate = UtilDate.lastDayMonth(date);

		TypedQuery<Date> query = db.createQuery(
				"SELECT DISTINCT ev.eventDate FROM Event ev " + "WHERE ev.eventDate BETWEEN ?1 and ?2", Date.class);
		query.setParameter(1, firstDayMonthDate);
		query.setParameter(2, lastDayMonthDate);
		List<Date> dates = query.getResultList();
		for (Date d : dates) {
			System.out.println(d.toString());
			res.add(d);
		}
		return res;
	}

	/**
	 * Method that opens the DB
	 * 
	 * @param initializeMode
	 */
	public void open(boolean initializeMode) {

		System.out.println("Opening DataAccess instance => isDatabaseLocal: " + config.isDataAccessLocal()
				+ " getDatabBaseOpenMode: " + config.getDataBaseOpenMode());

		String fileName = config.getDataBaseFilename();
		if (initializeMode) {
			fileName = fileName + ";drop";
			System.out.println("Deleting the DataBase");
		}

		if (config.isDataAccessLocal()) {
			emf = Persistence.createEntityManagerFactory("objectdb:" + fileName);
			db = emf.createEntityManager();
		} else {
			Map<String, String> properties = new HashMap<String, String>();
			properties.put("javax.persistence.jdbc.user", config.getDataBaseUser());
			properties.put("javax.persistence.jdbc.password", config.getDataBasePassword());

			emf = Persistence.createEntityManagerFactory(
					"objectdb://" + config.getDataAccessNode() + ":" + config.getDataAccessPort() + "/" + fileName,
					properties);

			db = emf.createEntityManager();
		}
	}

	/**
	 * To check if a question exists
	 * 
	 * @param event
	 * @param question
	 * @return
	 */
	public boolean existQuestion(Event event, String question) {
		System.out.println(">> DataAccess: existQuestion => event = " + event + " question = " + question);
		Event ev = db.find(Event.class, event.getEventNumber());
		return ev.doesQuestionExist(question);
	}

	/**
	 * Method that closes the DB
	 */
	public void close() {
		db.close();
		System.out.println("DataBase is closed");
	}

	/**
	 * Stores a newly registered user to the database
	 * 
	 * @param userp
	 * @param passwordp
	 */
	public int storeUser(String userp, String passwordp, Date birthDate, String name, String surname, String email,
			String bankAccount) {
		db.getTransaction().begin();
		User user = null;
		int ret = 0;
		try {

			user = new User(userp, passwordp, birthDate, name, surname, email, bankAccount);
			db.persist(user);
			db.getTransaction().commit();
			
			System.out.println(userp + " Registered!");
			ret = 1;

		} catch (Exception e) {
			System.out.println("Duplicated Username");
			ret = 0;
		}

		
		return ret;
	}

	/**
	 * Updates the given bets of the user, by adding the recent one
	 * 
	 * @param userid
	 * @param questionId
	 * @param amount
	 */
	public void storeBet(Question question, Answer answer, Event event, Date date, int amount) {

		String username = getLoggedUserUserName();

		this.open(false);

		db.getTransaction().begin();

		TypedQuery<User> query = db.createQuery("SELECT u FROM User u WHERE u.username = ?1", User.class);
		query.setParameter(1, username);
		List<User> users = query.getResultList();

		users.get(0).storeBet(question, answer, event, date, amount);

		db.getTransaction().commit();

		this.close();
	}

	/**
	 * Gets the needed user stored in the database
	 * 
	 * @param userid
	 * @return
	 */
	public User getUserByUsername(String username) {

		this.open(false);

		db.getTransaction().begin();
		TypedQuery<User> query = db.createQuery("SELECT u FROM User u WHERE u.username = ?1", User.class);
		query.setParameter(1, username);
		List<User> user = query.getResultList();

		db.getTransaction().commit();

		this.close();

		return user.get(0);
	}

	/**
	 * Check user credentials
	 * 
	 * @param usname
	 * @param passwd
	 * @return
	 */
	public boolean checkUser(String usname, String passwd) {

		TypedQuery<User> userPassQuery = db.createQuery(
				"SELECT FROM User WHERE username.equals(\"" + usname + "\") AND password.equals(\"" + passwd + "\")",
				User.class);

		List<User> users = userPassQuery.getResultList();

		return users.size() != 0;
	}

	/**
	 * Marks loggedIn attribute as true
	 */
	public void markLogin(String user, String passwd) {

		db.getTransaction().begin();

		TypedQuery<User> queryUser = db.createQuery(
				"SELECT FROM User WHERE username.equals(\"" + user + "\") AND password.equals(\"" + passwd + "\")",
				User.class);

		List<User> users = queryUser.getResultList();

		User usr = users.get(0);
		usr.setLoggedIn(true);
		db.persist(usr);
		db.getTransaction().commit();
		System.out.println(usr.getUsername() + " Logged!");
	}

	/**
	 * Resets all Users login status
	 */
	public void resetLogins() {

		this.open(false);

		db.getTransaction().begin();

		TypedQuery<User> queryAllUsers = db.createQuery("SELECT FROM User", User.class);

		List<User> users = queryAllUsers.getResultList();

		for (User usr : users) {

			usr.setLoggedIn(false);
			db.persist(usr);
		}
		db.getTransaction().commit();
		this.close();
	}

	/**
	 * Return id of logged user
	 * 
	 * @return
	 */
	public String getLoggedUserUserName() {

		this.open(false);

		db.getTransaction().begin();

		TypedQuery<String> queryLoggedUsers = db.createQuery("SELECT username FROM User WHERE loggedIn=true",
				String.class);

		List<String> usernames = queryLoggedUsers.getResultList();

		db.getTransaction().commit();
		this.close();

		return usernames.get(0);
	}

	/**
	 * Event getter
	 * 
	 * @param eventNum
	 * @return
	 */
	public Event getEvent(Integer eventNum) {

		this.open(false);

		db.getTransaction().begin();

		TypedQuery<Event> queryEvent = db.createQuery("SELECT e FROM Event e WHERE e.eventNumber = ?1", Event.class);
		queryEvent.setParameter(1, eventNum);

		Event event = queryEvent.getResultList().get(0);

		db.getTransaction().commit();

		this.close();

		return event;

	}

	/**
	 * Question getter
	 * 
	 * @param eventNum
	 * @param questionNum
	 * @return
	 */
	public Question getQuestion(Integer eventNum, Integer questionNum) {

		Question question = this.getEvent(eventNum).getSpecificQuestion(questionNum);

		return question;
	}

	/**
	 * Answer getter
	 * 
	 * @param eventNum
	 * @param questionNum
	 * @param answerNum
	 * @return
	 */
	public Answer getAnswer(Integer eventNum, Integer questionNum, Integer answerNum) {

		return this.getQuestion(eventNum, questionNum).getSpecificAnswer(answerNum);
	}

	/**
	 * Method that inserts given amount of money to logged user
	 * 
	 * @param amount
	 */
	public void insertMoneyLoggedUser(int amount) {

		String username = getLoggedUserUserName();

		this.open(false);

		db.getTransaction().begin();
		TypedQuery<User> query = db.createQuery("SELECT u FROM User u WHERE u.username = ?1", User.class);
		query.setParameter(1, username);
		List<User> users = query.getResultList();

		users.get(0).getWallet().insertMoney(amount);

		db.getTransaction().commit();

		this.close();
	}

	/**
	 * Updates user data. If the given attribute is empty, no changes are made
	 * 
	 * @param uName
	 * @param pass
	 * @param bankN
	 */
	public int updateUserData(String uName, String pass, String bankN) {
		int ret = 0;
		String username = getLoggedUserUserName();

		this.open(false);

		db.getTransaction().begin();
		TypedQuery<User> query = db.createQuery("SELECT u FROM User u WHERE u.username = ?1", User.class);
		query.setParameter(1, username);
		List<User> users = query.getResultList();
		User modifiedUser = null;
		
		try {
			if (!uName.isEmpty()) {
				modifiedUser = this.copyUser(users.get(0),uName);
				if (!pass.isEmpty())
					modifiedUser.updatePassword(pass);
				if (!bankN.isEmpty())
					modifiedUser.updateBankNumber(bankN);
				
				db.persist(modifiedUser);
				db.getTransaction().commit();
				this.deleteUser(users.get(0));
				
			}else {
				if (!pass.isEmpty())
					users.get(0).updatePassword(pass);
				if (!bankN.isEmpty())
					users.get(0).updateBankNumber(bankN);
			}
				
			ret = 1;
		}catch (Exception e) {
			System.out.println("UserName Already Exist");
			ret = 0;
		}
		this.close();
		return ret;
	}
	
	/**
	 * 
	 * @param user
	 * @return
	 */
	public User copyUser(User user, String newUsername) {
		User NewUser = new User(newUsername, user.getPassword(), user.getBirthDate(),user.getName(), user.getSurname(), user.getEmail() ,user.getBankAccount());
		NewUser.setLoggedIn(true);
		return NewUser;
	}

	/**
	 * 
	 * @param username
	 */
	public void deleteUser(User username) {
		
		db.getTransaction().begin();
		
		User conFlightRes = null;
		try {
			 conFlightRes = db.find(User.class, username.getUsername());
		}catch(IllegalArgumentException e) {
			db.merge(username);
		}
		db.remove(conFlightRes);
		
		db.getTransaction().commit();
	}

	/**
	 * Method that removes a bet given its id, and updates the money in wallet
	 * 
	 * @param remBetId
	 * @param amount
	 */
	public void removeBet(Integer remBetId, int amount) {

		String username = getLoggedUserUserName();

		this.open(false);

		db.getTransaction().begin();

		TypedQuery<User> query = db.createQuery("SELECT u FROM User u WHERE u.username = ?1", User.class);
		query.setParameter(1, username);
		List<User> users = query.getResultList();

		users.get(0).removeBet(remBetId);
		users.get(0).getWallet().insertMoney((int) (amount / 2));

		db.getTransaction().commit();

		this.close();
	}

	/**
	 * Checks if any user is logged in
	 * 
	 * @return
	 */
	public boolean isAnyUserLogged() {

		boolean b = false;

		db.getTransaction().begin();

		TypedQuery<User> queryAllUsers = db.createQuery("SELECT FROM User", User.class);
		List<User> users = queryAllUsers.getResultList();

		db.getTransaction().commit();

		for (User usr : users) {
			if (usr.isLoggedIn())
				b = true;
		}

		return b;
	}
}