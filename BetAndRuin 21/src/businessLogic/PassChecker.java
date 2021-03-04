package businessLogic;

import dataAccess.DataAccess;

/**
 * Class that checks credentials
 *
 */
public class PassChecker {

	DataAccess db = new DataAccess();
	
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
}