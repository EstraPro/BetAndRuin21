package exceptions;

public class AnswerAlreadyExist extends Exception {
	private static final long serialVersionUID = 1L;

	public AnswerAlreadyExist() {
		super();
	}

	/**This exception is triggered if the question already exists 
	 *@param s String of the exception
	 */
	public AnswerAlreadyExist(String s) {
		super(s);
	}
}