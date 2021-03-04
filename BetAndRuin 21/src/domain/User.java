package domain;

import java.util.ArrayList;
import java.util.HashMap;

import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
public class User {

	@Id
	private int id;
	private String username;
	private String password;
	private boolean loggedIn = false;
	private HashMap<Integer, ArrayList<Integer>> madeBets;

	/**
	 * Constructor for User
	 * 
	 * @param id
	 * @param usr
	 * @param passwd
	 */
	public User(int id, String usr, String passwd) {

		this.id = id;
		username = usr;
		password = passwd;
		madeBets = new HashMap<Integer,ArrayList<Integer>>();
	}

	public HashMap<Integer, ArrayList<Integer>> getAllBets() {
		return madeBets;
	}
	/**
	 * Return the bet made in the specific question.
	 * @param questionId the question number of the bet want to fetch
	 * @return the made bet(s) in that question
	 */
	public ArrayList<Integer> getQuestionBets(Integer questionId) {
		return madeBets.get(questionId);
	}
	
	/**
	 * Stores bet in the indicated question and bet.
	 * @param questionId
	 * @param amount
	 */
	public void storeBet(Integer questionId, int amount) {
		
		ArrayList<Integer> actualBets = this.getQuestionBets(questionId);
		actualBets.add(amount);
		madeBets.put(questionId, actualBets);

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
	
}
