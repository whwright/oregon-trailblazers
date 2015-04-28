package edu.gatech.cs2340.oregontb.gamelogic;

/**
 * ItemType is an enumerated type that represents each individual item. Each item has a base price and a weight,
 * and a getter for both.
 */
public enum ItemType 
{
	// ITEMS:
	FOOD (1, 5),
	OXEN (40, 0), //NOTE: for 2 oxen, weight is 0 because they are outside the wagon
	CLOTHING (10, 2),
	AMMO (2, 3), // NOTE: for 20 round box
	WHEEL (10, 75),
	AXLE (10, 175),
	TONGUE (10, 100),
	NULL (0, 0);
	
	private int basePrice;
	private int weight;
	
	private ItemType(int basePrice, int weight) {
		this.basePrice = basePrice;
		this.weight = weight;
	}
	
	public int basePrice() {
		return basePrice;
	}
	
	public int weight() {
		return weight;
	}
}
