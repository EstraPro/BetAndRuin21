package dataAccess;


import java.util.Arrays;
import java.util.Calendar;


import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.ResourceBundle;
import java.util.Vector;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.Query;
import javax.persistence.TypedQuery;

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

				a1 = q1.addSpecificAnswer(1, "Athletic ganara");
				a2 = q1.addSpecificAnswer(2, "Atlético ganara");
				a3 = q2.addSpecificAnswer(3, "Athletic metera el primer gol");
				a4 = q2.addSpecificAnswer(4, "Atlético metera el primer gol");
				a5 = q3.addSpecificAnswer(5, "Athletic ganara");
				a6 = q3.addSpecificAnswer(6, "Atlético ganara");
				a7 = q4.addSpecificAnswer(7, "Mas de dos");
				a8 = q4.addSpecificAnswer(8, "Menos de dos");
				a9 = q5.addSpecificAnswer(9, "Málaga ganara");
				a10 = q5.addSpecificAnswer(10, "Valencia ganara");
				a11 = q6.addSpecificAnswer(11, "Si");
				a12 = q6.addSpecificAnswer(12, "No");

			} else if (Locale.getDefault().equals(new Locale("en"))) {

				q1 = ev1.addQuestion("Who will win the match?", 1);
				q2 = ev1.addQuestion("Which team will score first?", 2);
				q3 = ev11.addQuestion("Who will win the match?", 1);
				q4 = ev11.addQuestion("How many goals will be scored?", 2);
				q5 = ev17.addQuestion("Who will win the match?", 1);
				q6 = ev17.addQuestion("Will they score in the first half of the match?", 2);

				a1 = q1.addSpecificAnswer(1, "Athletic will win");
				a2 = q1.addSpecificAnswer(2, "Atlético will win");
				a3 = q2.addSpecificAnswer(3, "Athletic will score first");
				a4 = q2.addSpecificAnswer(4, "Atlético will score first");
				a5 = q3.addSpecificAnswer(5, "Athletic will win");
				a6 = q3.addSpecificAnswer(6, "Atlético will win");
				a7 = q4.addSpecificAnswer(7, "More than two");
				a8 = q4.addSpecificAnswer(8, "Less than two");
				a9 = q5.addSpecificAnswer(9, "Málaga will win");
				a10 = q5.addSpecificAnswer(10, "Valencia will win");
				a11 = q6.addSpecificAnswer(11, "Yes");
				a12 = q6.addSpecificAnswer(12, "No");

			} else {

				q1 = ev1.addQuestion("Zeinek irabaziko du partidua?", 1);
				q2 = ev1.addQuestion("Zeinek sartuko du lehenengo gola?", 2);
				q3 = ev11.addQuestion("Zeinek irabaziko du partidua?", 1);
				q4 = ev11.addQuestion("Zenbat gol sartuko dira?", 2);
				q5 = ev17.addQuestion("Zeinek irabaziko du partidua?", 1);
				q6 = ev17.addQuestion("Golak sartuko dira lehenengo zatian?", 2);

				a1 = q1.addSpecificAnswer(1, "Athletic-ek irabaziko du");
				a2 = q1.addSpecificAnswer(2, "Atlético-k irabaziko du");
				a3 = q2.addSpecificAnswer(3, "Athletic-ek sartuko du lehen gola");
				a4 = q2.addSpecificAnswer(4, "Atlético-k sartuko du lehen gola");
				a5 = q3.addSpecificAnswer(5, "Athletic-ek irabaziko du");
				a6 = q3.addSpecificAnswer(6, "Atlético irabaziko du");
				a7 = q4.addSpecificAnswer(7, "Bi baino gehiago");
				a8 = q4.addSpecificAnswer(8, "Bi baino gutxiago");
				a9 = q5.addSpecificAnswer(9, "Málaga-k irabaziko du");
				a10 = q5.addSpecificAnswer(10, "Valencia-k irabaziko du");
				a11 = q6.addSpecificAnswer(11, "Bai");
				a12 = q6.addSpecificAnswer(12, "Ez");
			}

			db.persist(a1);
			db.persist(a2);
			db.persist(a3);
			db.persist(a4);
			db.persist(a5);
			db.persist(a6);
			db.persist(a7);
			db.persist(a8);
			db.persist(a9);
			db.persist(a10);
			db.persist(a11);
			db.persist(a12);

			db.persist(q1);
			db.persist(q2);
			db.persist(q3);
			db.persist(q4);
			db.persist(q5);
			db.persist(q6);

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

			User admin = new User(0, "admin", "admin");
			User pepita = new User(1, "pepi", "1234", new Date(), "Pepita", "Kaltzaslargas", "motozikleta@gmail.com",
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
	public Question createQuestion(Event event, String question, float betMinimum) throws QuestionAlreadyExist {
		System.out.println(">> DataAccess: createQuestion=> event = " + event + " question = " + question
				+ " minimum bet = " + betMinimum);

		Event ev = db.find(Event.class, event.getEventNumber());

		if (ev.doesQuestionExist(question))
			throw new QuestionAlreadyExist(
					ResourceBundle.getBundle("Etiquetas").getString("ErrorQuestionAlreadyExist"));

		db.getTransaction().begin();
		Question q = ev.addQuestion(question, betMinimum);
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

	public boolean existQuestion(Event event, String question) {
		System.out.println(">> DataAccess: existQuestion => event = " + event + " question = " + question);
		Event ev = db.find(Event.class, event.getEventNumber());
		return ev.doesQuestionExist(question);
	}

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
	public void storeUser(String userp, String passwordp, Date birthDate, String name, String surname, String email,
			String bankAccount) {
		db.getTransaction().begin();

		TypedQuery<Integer> queryMaxId = db.createQuery("SELECT MAX(id) FROM User", Integer.class);

		List<Integer> ids = queryMaxId.getResultList();

		int id = ids.get(0);

		User user = new User(id + 1, userp, passwordp, birthDate, name, surname, email, bankAccount);
		db.persist(user);
		db.getTransaction().commit();
		System.out.println(userp + " Registered!");
		this.close();
	}

	/**
	 * Updates the given bets of the user, by adding the recent one
	 * 
	 * @param userid
	 * @param questionId
	 * @param amount
	 */
	public void storeBet(int userid, Question question, Answer answer, Event event, Date date, int amount) {

		User user = this.getUserById(userid);

		db.getTransaction().begin();

		user.storeBet(question, answer, event, date, amount);

		db.getTransaction().commit();

		System.out.println(user.getUsername() + " has been updated");
		this.close();
	}

	/**
	 * Gets the needed user stored in the database
	 * 
	 * @param userid
	 * @return
	 */
	public User getUserById(int userid) {

		db.getTransaction().begin();
		TypedQuery<User> query = db.createQuery("SELECT u FROM User u WHERE u.id = ?1", User.class);
		query.setParameter(1, userid);
		List<User> user = query.getResultList();

		db.getTransaction().commit();

		// this.close();

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
				"SELECT id FROM User WHERE username.equals(\"" + usname + "\") AND password.equals(\"" + passwd + "\")",
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
		this.close();
	}

	/**
	 * Resets all Users login status
	 */
	public void resetLogins() {

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
	public Integer getLoggedUserId() {

		db.getTransaction().begin();

		TypedQuery<Integer> queryLoggedUsers = db.createQuery("SELECT id FROM User WHERE loggedIn=true", Integer.class);

		List<Integer> ids = queryLoggedUsers.getResultList();

		db.getTransaction().commit();
		// this.close();

		return ids.get(0);
	}

	public Event getEvent(Integer eventNum) {

		db.getTransaction().begin();

		TypedQuery<Event> queryEvent = db.createQuery("SELECT e FROM Event e WHERE e.eventNumber = ?1", Event.class);
		queryEvent.setParameter(1, eventNum);

		Event event = queryEvent.getResultList().get(0);

		db.getTransaction().commit();

		return event;

	}

	public Question getQuestion(Integer eventNum, Integer questionNum) {

		Question question = this.getEvent(eventNum).getSpecificQuestion(questionNum);

		return question;
	}

	public Answer getAnswer(Integer eventNum, Integer questionNum, Integer answerNum) {

		return this.getQuestion(eventNum, questionNum).getSpecificAnswer(answerNum);
	}

	public void insertMoneyLoggedUser(int amount) {

		Integer id = getLoggedUserId();
		db.getTransaction().begin();
		TypedQuery<User> query = db.createQuery("SELECT u FROM User u WHERE u.id = ?1", User.class);
		query.setParameter(1, id);
		List<User> users = query.getResultList();

		users.get(0).getWallet().insertMoney(amount);

		db.getTransaction().commit();

	}

	public void removeBet(Integer remBetId, User user) {
		open(false);

		db.getTransaction().begin();

		user.removeBet(remBetId);

		//Query updateBetquery = db.createQuery(
		//		"UPDATE User u SET madeBets = :bets" + " WHERE u.id.equals(\"" + user.getId() + "\")");

		//updateBetquery.setParameter("bets", Arrays.asList(user.getAllBets()));
		
		//int updateBets = updateBetquery.executeUpdate();
		
		/*Query updateBetquery = db.createQuery(
				"DELETE FROM User u WHERE u.id.equals(\"" + user.getId() + "\")");
		
		int updateBets = updateBetquery.executeUpdate();
		
		db.persist(user);*/

		db.getTransaction().commit();

		close();

	}

}