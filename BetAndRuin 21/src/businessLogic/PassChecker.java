package businessLogic;

public class PassChecker {
	public boolean checkCredentialsUser(String usname, String passwd) {
		if (usname=="user" && passwd=="user") {
			return true;
		}else {
			return false;
		}
	}
	
	public boolean checkCredentialsAdmin(String usname, String passwd) {
		if (usname=="administrator" && passwd=="administrator") {
			return true;
		}else {
			return false;
		}
	}
}