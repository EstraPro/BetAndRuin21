package domain;

/**
 * Class that represents a virtual wallet
 * 
 * @author gapito
 */
public class Wallet {

	int money;

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
}
