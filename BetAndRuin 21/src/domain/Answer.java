package domain;

import java.util.ArrayList;

import javax.persistence.*;


@Entity
public class Answer {
	
	@Id
	private int answerId;
	private String content;
	private ArrayList<Question> question;
	
	/**
	 * Constructor with no questions to add
	 * @param answerId
	 * @param content
	 */
	
	public Answer(int answerId, String content) {
		super();
		this.answerId = answerId;
		this.content = content;
		this.question= new ArrayList<Question>();
	}
	
	/**
	 * Constructor with questions to add
	 * @param answerId
	 * @param content
	 * @param question
	 */
	public Answer(int answerId, String content, ArrayList<Question> question) {
		super();
		this.answerId = answerId;
		this.content = content;
		this.question = question;
	}

	/**
	 * Getter for id
	 * @return
	 */
	public int getAnswerId() {
		return answerId;
	}

	
	/**
	 * Setter for id
	 * @param answerId
	 */
	public void setAnswerId(int answerId) {
		this.answerId = answerId;
	}

	/**
	 * Getter for content
	 * @return
	 */
	public String getContent() {
		return content;
	}

	/**
	 * Setter for content
	 * @param content
	 */
	public void setContent(String content) {
		this.content = content;
	}

	/**
	 * Getter for Question
	 * @return
	 */
	public ArrayList<Question> getQuestion() {
		return question;
	}
	
	
	/**
	 * Setter for Question
	 * @param question
	 */
	public void setQuestion(ArrayList<Question> question) {
		this.question = question;
	}
	
	/**
	 * Adds a question to the questoin list
	 * @param question
	 */
	public void addQuestiontoAns(Question question) {
		this.question.add(question);
	}
	
	
	
	

}
