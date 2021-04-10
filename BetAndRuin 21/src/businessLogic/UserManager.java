package businessLogic;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;

import dataAccess.DataAccess;

/**
 * Class that checks credentials
 *
 */
public class UserManager {

	DataAccess db;
	
	public UserManager() {
		
		db = new DataAccess();
	}
	
	/**
	 * Checks credentials for user
	 * @param usname
	 * @param passwd
	 * @return true if they match, false if not
	 */
	public boolean checkCredentialsUser(String usname, String passwd) {

		return db.checkUser(usname, passwd);
	}

	/**
	 * Checks credentials for administrator
	 * @param usname
	 * @param passwd
	 * @return true if they match, false if not
	 */
	public boolean checkCredentialsAdmin(String usname, String passwd) {
		if (usname.equals("admin") && passwd.equals("admin")) {
			return true;
		} else {
			return false;
		}
	}

	/**
	 * Checks if both passwords in registration match
	 * @param pass1
	 * @param pass2
	 * @return true if they match, false if not
	 */
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
	public void storeUser(String userp, String passwordp, Date birthDate, String name, String surname, String email) {
		
		db.storeUser(userp, passwordp,birthDate, name, surname, email);
	}
	
	/**
	 * Marks loggedIn attribute as true
	 */
	public void markLogin(String user, String passwd) {
		
		db.markLogin(user, passwd);
	}
	
	/**
	 * Resets all Users login status
	 */
	public void resetLogins() {
		
		db.resetLogins();
	}
	
	/**
	 * Return id of logged user
	 * @return
	 */
	public int getLoggedUserId() {
		
		return db.getLoggedUserId();
	}
	
	/**
	 * Updates the given bets of the user, by adding the recent one
	 * @param userid
	 * @param questionId
	 * @param amount
	 */
	public void storeBet(int userid, Integer betKey, int amount) {
		
		db.storeBet(userid, betKey, amount);
	}
}