package domain;

import java.util.Date;

import javax.persistence.*;

@Entity
public class Bet {
	
	@Id
	private Question question;
	private Answer answer;
	private Event event;
	private int amount;
	private Date date;
	
	/**
	 * Constructor for Bet
	 * @param user
	 * @param question
	 * @param answer
	 * @param event
	 * @param amount
	 */
	public Bet(Question question, Answer answer, Event event, Date date, int amount) {
		super();
		this.question = question;
		this.answer = answer;
		this.event = event;
		this.amount = amount;
		this.date = date;
	}

	public Question getQuestion() {
		return question;
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
		return "Bet [question=" + question + ", answer=" + answer + ", event=" + event + ", amount=" + amount
				+ ", date=" + date + "]";
	}
	
	
	

}
