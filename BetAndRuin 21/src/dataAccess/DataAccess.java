package dataAccess;

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
import javax.persistence.TypedQuery;

import configuration.ConfigXML;
import configuration.UtilDate;
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

			if (Locale.getDefault().equals(new Locale("es"))) {
				q1 = ev1.addQuestion("¿Quién ganará el partido?", 1);
				q2 = ev1.addQuestion("¿Quién meterá el primer gol?", 2);
				q3 = ev11.addQuestion("¿Quién ganará el partido?", 1);
				q4 = ev11.addQuestion("¿Cuántos goles se marcarán?", 2);
				q5 = ev17.addQuestion("¿Quién ganará el partido?", 1);
				q6 = ev17.addQuestion("¿Habrá goles en la primera parte?", 2);
			} else if (Locale.getDefault().equals(new Locale("en"))) {
				q1 = ev1.addQuestion("Athletic will win the match", 1);
				q2 = ev1.addQuestion("Athletico will win the match", 2);
				q3 = ev11.addQuestion("Athletic will win the match", 1);
				q4 = ev11.addQuestion("Athletico will win the match", 2);
				q5 = ev17.addQuestion("Malaga will win the match", 1);
				q6 = ev17.addQuestion("Valencia will win the match", 2);
			} else {
				q1 = ev1.addQuestion("Zeinek irabaziko du partidua?", 1);
				q2 = ev1.addQuestion("Zeinek sartuko du lehenengo gola?", 2);
				q3 = ev11.addQuestion("Zeinek irabaziko du partidua?", 1);
				q4 = ev11.addQuestion("Zenbat gol sartuko dira?", 2);
				q5 = ev17.addQuestion("Zeinek irabaziko du partidua?", 1);
				q6 = ev17.addQuestion("Golak sartuko dira lehenengo zatian?", 2);
			}

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
			db.persist(admin);
			
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
	public void storeUser(String userp, String passwordp, Date birthDate, String name, String surname, String email) {
		db.getTransaction().begin();

		TypedQuery<Integer> queryMaxId = db.createQuery(
				"SELECT MAX(id) FROM User", Integer.class);
		
		List<Integer> ids = queryMaxId.getResultList();
		
		int id = ids.get(0);
		
		User user = new User(id + 1, userp, passwordp, birthDate, name, surname, email);
		db.persist(user);
		db.getTransaction().commit();
		System.out.println(userp + " Registered!");
		this.close();
	}
	
	/**
	 * Updates the given bets of the user, by adding the recent one
	 * @param userid
	 * @param questionId
	 * @param amount
	 */
	public void storeBet(int userid, Integer betKey, int amount) {
		User user = this.getUserById(userid);
		db.getTransaction().begin();
		user.storeBet(betKey, amount);
		db.getTransaction().commit();
		System.out.println(user.getUsername() + " has been updated");
		this.close();
	}
	
	/**
	 * Gets the needed user stored in the database
	 * @param userid
	 * @return
	 */
	public User getUserById(int userid) {
		
		System.out.println("kaicooo " + db.isOpen());
		db.getTransaction().begin();
		TypedQuery<User> query = db.createQuery("SELECT u FROM User u WHERE u.id = ?1",
				User.class);
		query.setParameter(1, userid);
		List<User> user = query.getResultList();
		
		db.getTransaction().commit();
		
		//this.close();
		
		return user.get(0);
	}
	
	/**
	 * Check user credentials
	 * @param usname
	 * @param passwd
	 * @return
	 */
	public boolean checkUser(String usname, String passwd) {
		
		TypedQuery<User> userPassQuery = db.createQuery(
				"SELECT id FROM User WHERE username.equals(\"" + usname + "\") AND password.equals(\"" + passwd + "\")", User.class);
		
		List<User> users = userPassQuery.getResultList();
		
		return users.size() != 0;
	}
	
	/**
	 * Marks loggedIn attribute as true
	 */
	public void markLogin(String user, String passwd) {
		
		db.getTransaction().begin();

		TypedQuery<User> queryUser = db.createQuery(
				"SELECT FROM User WHERE username.equals(\"" + user +"\") AND password.equals(\"" + passwd + "\")", User.class);
		
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

		TypedQuery<User> queryAllUsers = db.createQuery(
				"SELECT FROM User", User.class);
		
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
	 * @return
	 */
	public int getLoggedUserId() {
		
		db.getTransaction().begin();

		TypedQuery<Integer> queryLoggedUsers = db.createQuery(
				"SELECT id FROM User WHERE loggedIn=true", Integer.class);
		
		List<Integer> ids = queryLoggedUsers.getResultList();

		db.getTransaction().commit();
		//this.close();
		
		return ids.get(0);
	}
}