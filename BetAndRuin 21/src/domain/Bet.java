package domain;

import java.util.Date;

import javax.persistence.*;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlID;
import javax.xml.bind.annotation.XmlIDREF;
import javax.xml.bind.annotation.adapters.XmlJavaTypeAdapter;

@XmlAccessorType(XmlAccessType.FIELD)
@Entity
public class Bet {
	@XmlID
	@XmlJavaTypeAdapter(IntegerAdapter.class)
	@Id
	private Integer id;
	private Question question;
	private Answer answer;
	private Event event;
	private int amount;
	private Date date;
	private boolean evaluated;
	
	@XmlIDREF
	private User user;
	
	/**
	 * Constructor for Bet
	 * @param user
	 * @param question
	 * @param answer
	 * @param event
	 * @param amount
	 */
	public Bet(int id, Question question, Answer answer, Event event, Date date, int amount, User user) {
		super();
		this.id=id;
		this.question = question;
		this.answer = answer;
		this.event = event;
		this.amount = amount;
		this.date = date;
		this.user= user;
		this.evaluated=false;
		
	}
	

	public Bet() {
		super();
	}


	public Question getQuestion() {
		return question;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public void setQuestion(Question question) {
		this.question = question;
	}

	public Answer getAnswer() {
		return answer;
	}

	public void setAnswer(Answer answer) {
		this.answer = answer;
	}

	public Event getEvent() {
		return event;
	}

	public void setEvent(Event event) {
		this.event = event;
	}

	public int getAmount() {
		return amount;
	}

	public void setAmount(int amount) {
		this.amount = amount;
	}

	public Date getDate() {
		return date;
	}

	public void setDate(Date date) {
		this.date = date;
	}
	
	@Override
	public String toString() {
		return "Question: " + question.getQuestion() + "\n" +"Answer: " + answer.getContent() + "\n" + "Event: " + event.getDescription() + ", Amount of Betting: " + amount
				+ ", Date: " + date.toString();
	}
	
	public double getProfit() {
		double profit=((this.answer.getRate() * 0.01)+1)*this.amount;
		return profit;
	}
	
    public boolean isEvaluated() {
		return evaluated;
	}

	public void setEvaluated(boolean avaluated) {
		this.evaluated = avaluated;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}
	

}
