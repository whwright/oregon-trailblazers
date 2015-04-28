package edu.gatech.cs2340.oregontb.userinterface;

/**
 * IStorePanel is the interface that is used by the OregonTrailPresenter to interact with the StorePanel.
 * @author Oregon Trailblazers (Michael Sandt, Harrison Wright, Casey Tisdel, Sean Cleary, Sean Gillespie)
 *
 */
public interface IStorePanel {
	
	/**
	 * Sets the Party's current money label to whatever is passed in as a parameter.
	 * @param money
	 */
	public void setCurrentMoney(int money);
	
	/**
	 * Sets the Party's current weight label to whatever is passed in as a parameter.
	 * @param weight
	 */
	public void setCurrentWeight(int weight);
	
	/**
	 * Sets the Oxen's price label to it's desired value, based on the location of the store.
	 */
	public void setOxenPrice();
	
	/**
	 * Sets the Food's price label to it's desired value, based on the location of the store.
	 */
	public void setFoodPrice();
	
	/**
	 * Sets the Clothing's price label to it's desired value, based on the location of the store.
	 */
	public void setClothingPrice();
	
	/**
	 * Sets the Ammo's price label to it's desired value, based on the location of the store.
	 */
	public void setAmmoPrice();
	
	/**
	 * Sets the Wheel's price label to it's desired value, based on the location of the store.
	 */
	public void setWheelPrice();
	
	/**
	 * Sets the Tongue's price label to it's desired value, based on the location of the store.
	 */
	public void setTonguePrice();
	
	/**
	 * Sets the Axle's price label to it's desired value, based on the location of the store.
	 */
	public void setAxlePrice();
	
	/**
	 * Returns the user's input, which represents how many Oxen they wish to purchase. This will return
	 * 0 if there is no user input.
	 * @return int Oxen quantity
	 */
	public int getOxenQuantity();
	
	/**
	 * Returns the user's input, which represents how much Food they wish to purchase. This will return
	 * 0 if there is no user input.
	 * @return int Food quantity
	 */
	public int getFoodQuantity();
	
	/**
	 * Returns the user's input, which represents how much Clothing they wish to purchase. This will return
	 * 0 if there is no user input.
	 * @return int Clothing quantity
	 */
	public int getClothingQuantity();
	
	/**
	 * Returns the user's input, which represents how much Ammo they wish to purchase. This will return
	 * 0 if there is no user input.
	 * @return int Ammo quantity
	 */
	public int getAmmoQuantity();
	
	/**
	 * Returns the user's input, which represents how many Wheels they wish to purchase. This will return
	 * 0 if there is no user input.
	 * @return int Wheel quantity
	 */
	public int getWheelQuantity();
	
	/**
	 * Returns the user's input, which represents how many Tongues they wish to purchase. This will return
	 * 0 if there is no user input.
	 * @return int Tongue quantity
	 */
	public int getTongueQuantity();
	
	/**
	 * Returns the user's input, which represents how many Axles they wish to purchase. This will return
	 * 0 if there is no user input.
	 * @return int Axle quantity
	 */
	public int getAxleQuantity();
	
	/**
	 * Clears the JTextFields for reuse of StorePanel.
	 */
	public void clearFields();

}
