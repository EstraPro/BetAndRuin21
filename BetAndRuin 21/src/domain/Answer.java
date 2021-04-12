package domain;

import java.util.ArrayList;
import javax.persistence.*;


@Entity
public class Answer {
	
	@Id
	private Integer answerId;
	private String content;
	private Question question;
	
	/**
	 * Constructor with no questions to add
	 * @param answerId
	 * @param content
	 */
	
	public Answer(Integer answerId, String content, Question question) {
		super();
		this.answerId = answerId;
		this.content = content;
		this.question= question;
	}
	
	/**
	 * Constructor with questions to add
	 * @param answerId
	 * @param content
	 * @param question
	 */
	public Answer(int answerId, String content, Question question) {
		super();
		this.answerId = answerId;
		this.content = content;
		this.question = question;
	}

	/**
	 * Getter for id
	 * @return
	 */
	public Integer getAnswerId() {
		return answerId;
	}

	
	/**
	 * Setter for id
	 * @param answerId
	 */
	public void setAnswerId(Integer answerId) {
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
	public Question getQuestion() {
		return question;
	}
	
	
	/**
	 * Setter for Question
	 * @param question
	 */
	public void setQuestion(Question question) {
		this.question = question;
	}

	@Override
	public String toString() {
		return "Answer [answerId=" + answerId + ", content=" + content + ", question=" + question + "]";
	}
	

}
