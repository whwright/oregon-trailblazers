package edu.gatech.cs2340.oregontb.gamelogic;

/**
 * Profession is an enumerated type to represent the Party leader's profession. Each profession
 * knows how much money it starts with, and has a getter method for starting money.
 */
public enum Profession 
{
	BANKER (1600),
	FARMER (400),
	CARPENTER (800);
	
	private int startingMoney;
	
	private Profession(int startingMoney) {
		this.startingMoney = startingMoney;
	}
	
	public int getStartingMoney() {
		return startingMoney;
	}
}
