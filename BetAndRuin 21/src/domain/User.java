package domain;

import java.util.ArrayList;

import java.util.Date;
import java.util.HashMap;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;

@Entity
public class User {

	@Id
	private Date birthDate;
	private String Name;
	private String Surname;
	private String Email;
	private int id;
	private String username;
	private String password;
	private boolean loggedIn = false;
	private String BankAccount;
	@OneToMany (fetch = FetchType.EAGER, cascade = CascadeType.PERSIST)
	private ArrayList<Bet> madeBets = new ArrayList<Bet>();
	@OneToOne(fetch = FetchType.EAGER, cascade = CascadeType.PERSIST)
	private Wallet wallet;

	/**
	 * Constructor for Admin
	 * 
	 * @param id
	 * @param usr
	 * @param passwd
	 **/
	public User(int id, String usr, String passwd) {

		this.id = id;
		username = usr;
		password = passwd;
		wallet = new Wallet(id);
	}

	public Wallet getWallet() {
		return wallet;
	}

	public void setWallet(Wallet wallet) {
		this.wallet = wallet;
	}

	/**
	 * Constructor for user
	 * 
	 * @param id
	 * @param username
	 * @param password
	 * @param birthDate
	 * @param name
	 * @param surname
	 * @param email
	 */
	public User(int id, String username, String password, Date birthDate, String name, String surname, String email, String BankAccount) {
		this.birthDate = birthDate;
		Name = name;
		Surname = surname;
		Email = email;
		this.id = id;
		this.username = username;
		this.password = password;
		wallet = new Wallet(id);
		this.BankAccount = BankAccount;
	}

	public ArrayList<Bet> getAllBets() {
		return (ArrayList<Bet>)madeBets;
	}

	/*
	 * Return the bet made in the specific question.
	 * 
	 * @param questionId the question number of the bet want to fetch
	 * 
	 * @return the made bet(s) in that question
	 */
	/*
	 * public ArrayList<Integer> getQuestionBets(Integer questionId) {
	 * 
	 * ArrayList<Integer> list = new ArrayList<Integer>();
	 * 
	 * if(madeBets.get(questionId) != null) list = madeBets.get(questionId);
	 * 
	 * return list; }
	 */
	/**
	 * Stores bet in the indicated question and bet.
	 * 
	 * @param questionId
	 * @param amount
	 */
	public void storeBet(Question question, Answer answer, Event event, Date date, int amount) {
		
		Bet bet = new Bet(madeBets.size()+1, question, answer, event, date, amount, this);
		madeBets.add(bet);
		wallet.removeMoney(amount);
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public boolean isLoggedIn() {

		return loggedIn;
	}

	public void setLoggedIn(boolean loggedIn) {
		this.loggedIn = loggedIn;
	}

	public Date getBirthDate() {
		return birthDate;
	}
	
	public void setBirthDate(Date birthDate) {
		this.birthDate = birthDate;
	}

	public String getName() {
		return Name;
	}

	public void setName(String name) {
		Name = name;
	}

	public String getSurname() {
		return Surname;
	}

	public void setSurname(String surname) {
		Surname = surname;
	}

	public String getEmail() {
		return Email;
	}

	public void setEmail(String email) {
		Email = email;
	}

	public ArrayList<Bet> getMadeBets() {
		return (ArrayList<Bet>)madeBets;
	}

	public void setMadeBets(ArrayList<Bet> madeBets) {
		this.madeBets = madeBets;
	}

	public String getBankAccount() {
		return BankAccount;
	}

	public void setBankAccount(String bankAccount) {
		BankAccount = bankAccount;
	}
	
}
