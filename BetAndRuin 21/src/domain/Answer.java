package domain;


import javax.persistence.*;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlIDREF;
import javax.xml.bind.annotation.adapters.XmlJavaTypeAdapter;

@XmlAccessorType(XmlAccessType.FIELD)
@Entity
public class Answer {
	
	@Id
	@XmlJavaTypeAdapter(IntegerAdapter.class)
	@GeneratedValue
	private Integer answerId;
	private String content;
	private Integer rate;
	
	@XmlIDREF
	private Question question;
	
	/**
	 * Constructor with no questions to add
	 * @param answerId
	 * @param content
	 */
	
	public Answer(String content, Integer rate) {
		super();
		this.content = content;
		this.rate= rate;
	}
	
	public Answer() {
		super();
	}

	/**
	 * Constructor with questions to add
	 * @param content
	 * @param question
	 */
	public Answer(String content, Question question,Integer rate) {
		super();
		this.content = content;
		this.question = question;
		this.rate= rate;
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
	 * Getter for the rate
	 * @return
	 */
	public Integer getRate() {
		return rate;
	}

	/**
	 * Setter for the rate
	 * @param rate
	 */
	public void setRate(Integer rate) {
		this.rate = rate;
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
