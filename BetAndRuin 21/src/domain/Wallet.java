package domain;

import javax.persistence.Entity;
import javax.persistence.Id;

/**
 * Class that represents a virtual wallet
 * 
 * @author gapito
 */
@Entity
public class Wallet {
	@Id
	private Integer walletId;
	private int money;

	/**
	 * Constructor of wallet
	 */
	public Wallet(Integer Id) {
		this.walletId = Id;
		money = 10;
	}

	/**
	 * Method that removes the money of the bet made from the virtual wallet
	 * 
	 * @param amountToRemove
	 */
	public void removeMoney(int amountToRemove) {
		money -= amountToRemove;
	}

	/**
	 * Method that add the desired amount of money to the virtual wallet
	 * 
	 * @param amountToInsert
	 */
	public void insertMoney(int amountToInsert) {
		money += amountToInsert;
	}
}
