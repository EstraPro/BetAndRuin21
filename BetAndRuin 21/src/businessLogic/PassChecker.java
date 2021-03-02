package businessLogic;

public class PassChecker {
	public boolean checkCredentialsUser(String usname, String passwd) {
		if (usname.equals("user") && passwd.equals("user")) {
			return true;
		}else {
			return false;
		}
	}
	
	public boolean checkCredentialsAdmin(String usname, String passwd) {
		if (usname.equals("administrator") && passwd.equals("administrator")) {
			return true;
		}else {
			return false;
		}
	}
}