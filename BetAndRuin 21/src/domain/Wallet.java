package domain;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.xml.bind.annotation.adapters.XmlJavaTypeAdapter;

/**
 * Class that represents a virtual wallet
 * 
 * @author gapito
 */
@Entity
public class Wallet {
	@Id
	@XmlJavaTypeAdapter(IntegerAdapter.class)
	@GeneratedValue
	private Integer walletId;
	private int money;

	/**
	 * Constructor of wallet
	 */
	public Wallet() {
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

	public Integer getWalletId() {
		return walletId;
	}

	public void setWalletId(Integer walletId) {
		this.walletId = walletId;
	}

	public int getMoney() {
		return money;
	}

	public void setMoney(int money) {
		this.money = money;
	}
	
	
}
