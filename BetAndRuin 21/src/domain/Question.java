package domain;

import java.io.*;
import java.util.ArrayList;

import javax.persistence.*;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlID;
import javax.xml.bind.annotation.XmlIDREF;
import javax.xml.bind.annotation.adapters.XmlJavaTypeAdapter;


@XmlAccessorType(XmlAccessType.FIELD)

@Entity
public class Question implements Serializable {

	private static final long serialVersionUID = 1L;
	@XmlID
	@Id 
	@XmlJavaTypeAdapter(IntegerAdapter.class)
	@GeneratedValue
	private Integer questionNumber;
	
	private String question; 
	private float betMinimum;
	private String result;
	private Integer type;
	@OneToMany (fetch = FetchType.EAGER, cascade = CascadeType.PERSIST)
	private ArrayList<Answer> answerList = new ArrayList<Answer>();

	@XmlIDREF
	private Event event;

	public Question(){
		super();
	}

	public Question(Integer queryNumber, String query, float betMinimum, Event event, Integer type) {
		super();
		this.questionNumber = queryNumber;
		this.question = query;
		this.betMinimum=betMinimum;
		this.event = event;
		this.type=type;
	}

	public Question(String query, float betMinimum,  Event event, Integer type) {
		super();
		this.question = query;
		this.betMinimum=betMinimum;
		this.event = event;
		this.type=type;
	}

	/**
	 * Gets the  number of the question
	 * 
	 * @return the question number
	 */
	public Integer getQuestionNumber() {
		return questionNumber;
	}

	/**
	 * Assigns the bet number to a question
	 * 
	 * @param questionNumber to be set
	 */
	public void setQuestionNumber(Integer questionNumber) {
		this.questionNumber = questionNumber;
	}

	/**
	 * Gets the question description of the bet
	 * 
	 * @return the bet question
	 */
	public String getQuestion() {
		return question;
	}


	/**
	 * Sets the question description of the bet
	 * 
	 * @param question to be set
	 */	
	public void setQuestion(String question) {
		this.question = question;
	}



	/**
	 * Gets the minimum amount allowed for a bet
	 * 
	 * @return the minimum bet
	 */
	public float getBetMinimum() {
		return betMinimum;
	}


	/**
	 * Gets the minimum amount allowed for the bet
	 * 
	 * @param  minimum amount to be set
	 */
	public void setBetMinimum(float betMinimum) {
		this.betMinimum = betMinimum;
	}


	/**
	 * Gets the result of the  query
	 * 
	 * @return the the query result
	 */
	public String getResult() {
		return result;
	}

	/**
	 * Sets the correct result of the  query
	 * 
	 * @param correct result of the query
	 */
	public void setResult(String result) {
		this.result = result;
	}

	/**
	 * Gets the event associated with the bet
	 * 
	 * @return the associated event
	 */
	public Event getEvent() {
		return event;
	}

	/**
	 * Sets the event associated with the bet
	 * 
	 * @param event to associate with the bet
	 */
	public void setEvent(Event event) {
		this.event = event;
	}
	
	/**
	 * Gets the question type
	 * 
	 * @return the type
	 */
	public Integer getType() {
		return type;
	}

	/**
	 * Sets the question type
	 * 
	 * @param the type
	 */
	public void setType(Integer type) {
		this.type = type;
	}

	public ArrayList<Answer> getAnswerList() {
		return answerList;
	}

	public void setAnswerList(ArrayList<Answer> answerList) {
		this.answerList = answerList;
	}
	
	public Answer getSpecificAnswer(Integer answerNum)  {
		
		for (Answer q:this.getAnswerList()){
			if (q.getAnswerId().compareTo(answerNum)==0)
				return q;
		}
		return null;
	}
	
	public Answer getSpecificAnswerbyContent(String answerCon)  {
		for (Answer q:this.getAnswerList()){
			if (q.getContent().compareTo(answerCon)==0)
				return q;
		}
		return null;
	}
	public Answer addSpecificAnswer(String content,Integer rate) {
		Answer q=new Answer(content, this, rate);
		answerList.add(q);
		return q;
		
	}
	
	public boolean doesAnswerExist(String answer)  {	
		for (Answer q:this.getAnswerList()){
			if (q.getContent().compareTo(answer)==0)
				return true;
		}
		return false;
	}
	
	@Override
	public String toString(){
		return questionNumber + ";" + question + ";" + Float.toString(betMinimum);
	}	
	
	
}