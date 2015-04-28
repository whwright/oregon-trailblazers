package edu.gatech.cs2340.oregontb.gamelogic;

import java.util.List;

import javax.swing.JOptionPane;
import edu.gatech.cs2340.oregontb.userinterface.*;

public class GameEngine {
	private Party party;
	private EventHandler handler;
	private LocationList list;
	private OregonTrailPresenter presenter;
	private int distanceToOregon;
	private boolean hasClothing;
	
	/**
	 * GameEngine constructor assigns parameters to variables, creates a new EventHandler, and makes the LocationList.
	 * @param party assigned to party
	 * @param presenter assigned to presenter
	 */
	public GameEngine(Party party, OregonTrailPresenter presenter) 
	{
		this.party = party;
		this.presenter = presenter;
		this.handler = new EventHandler(party);
		list = new LocationList(party);
		list.init();
		hasClothing = true;
		
		this.distanceToOregon = 1909;
	}
	
	/**
	 * Steps the game one turn
	 */
	public void step() {
		checkWagonCanMove();
	
		// check for random events
		List<EventReturnType> eventsThisStep = handler.checkRandomEvents(party, list); // get all events that occured
		for (EventReturnType event : eventsThisStep) { // for each event in the list of events
			switch(event.getID()) { // determine what kind of event we have and dispatch to private methods to handle each event
				case EventHandler.STORM_ID: handleStormEvent(event); break;
				case EventHandler.DEAD_ID: handleDeadEvent(event); break;
				case EventHandler.ILL_ID: handleIllEvent(event); break;
				case EventHandler.WAGON_DMG_ID: handleWagonDmgEvent(event); break;
				case EventHandler.STEAL_ID: handleStealEvent(event); break;
				case EventHandler.GIVE_ID: handleGiveEvent(event); break;
				case EventHandler.DEAD_OX_ID: handleDeadOxEvent(event); break;
				case EventHandler.TIRED_OX_ID: handleTiredOxEvent(event); break;
				default: break; 
			}
		}
		
		updateFood(); // updates food based on Rations
		updateDistance(); // updates distance based on Pace
		boolean clothingCheck = checkClothing();
		if(!clothingCheck && clothingCheck != hasClothing){
			hasClothing = clothingCheck;
			JOptionPane.showMessageDialog(null, "You don't have enough clothing for all of your party members. Party members are more likely to become ill.");		
		}
		else if(clothingCheck){
			hasClothing = clothingCheck;
		}
			
		// check for location
		if (checkForLocation()) {
			updateLocation();
		}
		
		checkGameOver();
	}

	


	private void updateLocation() {
		int distanceTraveledNow = party.getTotalDistanceTraveled();
		party.setTotalDistanceTraveled(party.getDistanceToNextLocation()+party.getTotalDistanceTraveled()); // set distance traveled to location
		party.setDistanceToNextLocation(0);
		int difference = distanceTraveledNow - party.getTotalDistanceTraveled();
		distanceToOregon += difference;
		int locationNumber = updateLocationNumber(); // update the party's location number
		int distanceToNext = getList().getLocation(locationNumber).getDistance(); // get distance to next location
		party.setDistanceToNextLocation(distanceToNext); 
		
		// if the distanceToNext = 0 (AKA, it's the last location)
		if (distanceToNext == 0) {
			JOptionPane.showMessageDialog(null, "YOU'VE REACHED OREGON!!!! YOU WIN!!");		
			System.exit(0);
		}
		
		// else (AKA, it's not the last location)
		else {			
			// river location
			if (getList().getLocation(locationNumber) instanceof RiverLocation) {
				RiverLocation temp = (RiverLocation) getList().getLocation(locationNumber);
				RiverCrossing(temp.getIsFerry(), temp.getMinDepth(), temp.getMaxDepth(), temp.toString());
			}
			// store location
			else if (getList().getLocation(locationNumber) instanceof StoreLocation) {
				StoreLocation temp = (StoreLocation) getList().getLocation(locationNumber);
				presenter.switchToStore(temp.getPriceScaleFactor(), getList().getLocation(locationNumber).toString());
			}
			// normal location
			else if (getList().getLocation(locationNumber) instanceof NormalLocation) {
				JOptionPane.showMessageDialog(null, "You have reached " + getList().getLocation(locationNumber).toString());
			}
			
		}
	}
	
	public void updateParty() {
		//TODO implement
	}
	
	public void updateUI() {
		//TODO implement
	}

	/**
	 * Gets the GameEngine's party
	 * @return Party
	 */
	public Party getParty() {
		return party;
	}

	/**
	 * Sets the GameEngine's Party
	 * @param party
	 */
	public void setParty(Party party) {
		this.party = party;
	}

	/**
	 * Gets the GameEngine's EventHandler
	 * @return EventHandler
	 */
	public EventHandler getHandler() {
		return handler;
	}

	/**
	 * Sets the GameEngine's EventHandler
	 * @param handler
	 */
	public void setHandler(EventHandler handler) {
		this.handler = handler;
	}

	/**
	 * Gets the GameEngine's LocationList
	 * @return LocationList
	 */
	public LocationList getList() {
		return list;
	}
	
	public LocationNode getLocationAt(int locationNumber) {
		return list.getLocation(locationNumber);
	}

	/**
	 * Sets the GameEngine's LocationList
	 * @param list
	 */
	public void setList(LocationList list) {
		this.list = list;
	}
	
	private void checkGameOver() {
		List<Person> people = party.getParty();
		boolean alive = false;
		for (Person p : people) {
			if (p.getHealth() != Health.DEAD)
				alive = true;
		}
		
		if (alive == false) {
			JOptionPane.showMessageDialog(null, "Everyone in your party has died. You traveled a total of "+party.getTotalDistanceTraveled()+" miles." +
					"\nAnd stopped short "+distanceToOregon+" miles from Oregon.");
		}
		
	}
	
	/**
	 * Updates the Party's food for one step (also subtracts corresponding weight from Party)
	 */
	private void updateFood() {
		int foodToConsume = ((party.getRations().getFoodConsumed() * party.getNumberOfAlivePartyMembers()));
		
		// if Party doesn't have enough food for a step
		if (party.getInventory().getFood() < foodToConsume) {
			// this will subtract the amount of food left from the Party's weight
			party.setWeight(party.getWeight() - party.getInventory().getFood());
			// set food and Rations to none
			party.getInventory().setFood(0);
			party.setRations(Rations.NONE);
			// notify user
			JOptionPane.showMessageDialog(null, "You are out of food, and your rations have been set to none.");
		}
		// if Party has enough for for a step
		else {
			// subtract food
			party.getInventory().setFood(party.getInventory().getFood() - (foodToConsume));
			// subtract weight
			party.setWeight(party.getWeight() - (foodToConsume));
		}		
	}

	/**
	 * Updates the distance traveled for one step
	 */
	private void updateDistance() {
		int distanceTraveledThisStep = party.getPace().getDistanceTraveled();
		distanceToOregon -= distanceTraveledThisStep;
		party.setTotalDistanceTraveled(party.getTotalDistanceTraveled() + distanceTraveledThisStep);
		party.setDistanceToNextLocation(party.getDistanceToNextLocation()-distanceTraveledThisStep);
	}
	
	/**
	 * Returns true if Party has reached a location, and false if they have not
	 * @return boolean
	 */
	private boolean checkForLocation() {
		if (party.getDistanceToNextLocation() <= 0) 
			return true;
		else
			return false;
	}
	
	/**
	 * Increments the location number and returns it
	 * @return locationNumber
	 */
	private int updateLocationNumber() {
		int locationNumber = party.getLocationNumber();
		locationNumber++;
		party.setLocationNumber(locationNumber);
		return locationNumber;
	}
	
	/**
	 * Asks the user what they wish to do when reaching a RiverLocation, and passes the answer to the crossRiver method
	 * @param isFerry
	 * @param minDepth
	 * @param maxDepth
	 * @param name
	 */
	private void RiverCrossing(boolean isFerry, int minDepth, int maxDepth, String name) {
		int choice;
		String reached = "You have reached "+name+" and must cross it. ";
		// can ferry
		if (isFerry) {
			// can ford
			if (minDepth <= 3) {
				// ask user
				Object[] options = {"Caulk the river", "Take the ferry", "Ford the river",  };
				choice = JOptionPane.showOptionDialog(null, reached+"You can safely take the ferry " +
						"for a small fee, take your chances fording the river, or take your chances caulking the river.","You have reached a river!", 
						JOptionPane.YES_NO_OPTION,  JOptionPane.QUESTION_MESSAGE, null, options, options[0]);
				// invalid entry... prompt again
				if (choice == -1) {
					RiverCrossing(isFerry, minDepth, maxDepth, name);
				}
				// get answer and pass to crossRiver
				else {
					String temp = (String) options[choice];
					CrossRiver(temp, isFerry, minDepth, maxDepth, name);
				}
			}
			// cant ford
			else {
				// ask user
				Object[] options = { "Caulk the river", "Take the ferry", };
				choice = JOptionPane.showOptionDialog(null, reached+"You can safely take the ferry " +
						"for a small fee, or take your chances caulking the river.","You have reached a river!", 
						JOptionPane.YES_NO_OPTION,  JOptionPane.QUESTION_MESSAGE, null, options, options[0]);
				// invalid entry... prompt again
				if (choice == -1) {
					RiverCrossing(isFerry, minDepth, maxDepth, name);
				}
				// get answer and pass to crossRiver
				else {
					String temp = (String) options[choice];
					CrossRiver(temp, isFerry, minDepth, maxDepth, name);
				}
			}
			
		}
		// cant ferry
		else {
			// can ford
			if (minDepth <= 3) {
				// ask user
				Object[] options = {"Caulk the river", "Ford the river",  };
				choice = JOptionPane.showOptionDialog(null, reached+"You can take your chances fording " +
						"the river,or take your chances caulking the river.","You have reached a river!", 
						JOptionPane.YES_NO_OPTION,  JOptionPane.QUESTION_MESSAGE, null, options, options[0]);
				// invalid entry... prompt again
				if (choice == -1) {
					RiverCrossing(isFerry, minDepth, maxDepth, name);
				}
				// get answer and pass to crossRiver
				else {
					String temp = (String) options[choice];
					CrossRiver(temp, isFerry, minDepth, maxDepth, name);
				}
			}
			// cant ford
			else {
				// ask user
				Object[] options = {"Caulk the river" };
				choice = JOptionPane.showOptionDialog(null, reached+"You must take your chances caulking " +
						"the river.","You have reached a river!", 
						JOptionPane.YES_NO_OPTION,  JOptionPane.QUESTION_MESSAGE, null, options, options[0]);
				// invalid entry... prompt again
				if (choice == -1) {
					RiverCrossing(isFerry, minDepth, maxDepth, name);
				}
				// get answer and pass to crossRiver
				else {
					String temp = (String) options[choice];
					CrossRiver(temp, isFerry, minDepth, maxDepth, name);
				}
			}
			
		}
	}
	
	
	/**
	 * This method carrys out actions that affect the Party, based on what the user chose to do at a RiverCrossing
	 * @param choice
	 * @param isFerry
	 * @param minDepth
	 * @param maxDepth
	 * @param name
	 */
	private void CrossRiver(String choice, boolean isFerry, int minDepth, int maxDepth, String name) {
		// caulk
		if (choice.equals("Caulk the river")) {
			EventReturnType result = getHandler().wagonFlipCheck(getParty(), "caulk");
			if (!result.isBad()) {
				// nothing bad happened!
				JOptionPane.showMessageDialog(null, "You successfully caulked the river!");
				return;
			}
			else {
				getParty().getInventory().removeFood(result.getFood()); // remove lost food
				getParty().getInventory().removeClothing(result.getClothing()); // remove lost clothing
				getParty().getInventory().removeAxle(result.getAxles()); // remove lost axles
				if (result.getDeceased() != null) {
					List<Person> pList = getParty().getParty();
					pList.get(pList.indexOf(result.getDeceased())).setHealth(Health.DEAD); // mark the dead person as dead
				}
				JOptionPane.showMessageDialog(null, "While attempting to caulk your wagon and float across the river, your wagon tipped! You lost\n" + result.toString());
				return;
			}
			
		}
		// ford
		else if (choice.equals("Ford the river")) {
			EventReturnType result = getHandler().wagonFlipCheck(getParty(), "ford");
			if (!result.isBad()) {
				// nothing bad happened!
				JOptionPane.showMessageDialog(null, "You successfully forded the river!");
				return;
			}
			else {
				getParty().getInventory().removeFood(result.getFood()); // remove lost food
				getParty().getInventory().removeClothing(result.getClothing()); // remove lost clothing
				getParty().getInventory().removeAxle(result.getAxles()); // remove lost axles
				if (result.getDeceased() != null) {
					List<Person> pList = getParty().getParty();
					pList.get(pList.indexOf(result.getDeceased())).setHealth(Health.DEAD); // mark the dead person as dead
				}
				JOptionPane.showMessageDialog(null, "While attempting to ford the river, your wagon tipped! You lost\n" + result.toString());
				return;
			}
		}
		// ferry
		else if (choice.equals("Take the ferry")) { 
			// checks to make sure the Party has $50
			if (getParty().getMoney() < 50) {
				// will notify user and try again if not
				JOptionPane.showMessageDialog(null, "You do not have enough money to take the ferry.");
				RiverCrossing(isFerry, minDepth, maxDepth, name);
			}
			// subtracts 50 and tells the party
			else {
				getParty().setMoney(getParty().getMoney() - 50);
				JOptionPane.showMessageDialog(null, "Congratulations, you have made it across the river safely!");
			}
		}
		
	}
	
	/*
	 * All of these methods are called when the GameEngine has been given an event to deal with throughout the course
	 * of a step. These methods need to indicate to the user what has happened while enforcing any penalties or benefits
	 * according to the event.
	 */
	private void handleTiredOxEvent(EventReturnType event) {
		if(event.getTiredOxen() >= 1)
			JOptionPane.showMessageDialog(null, "One of your oxen is tired.");
		else
			JOptionPane.showMessageDialog(null, "One of your oxen has recovered.");

		party.setTiredOxen(party.getTiredOxen() + event.getTiredOxen()); // set the number of tired oxen
	}

	private void handleDeadOxEvent(EventReturnType event) {
		JOptionPane.showMessageDialog(null, "One of your oxen has died.");
		party.getInventory().addOxen(-event.getDeadOxen()); // remove oxen
	}

	private void handleGiveEvent(EventReturnType event) {
		JOptionPane.showMessageDialog(null, "While traveling, a helpful native approached you and offered to give you some supplies! You gained " + event.toString());
		removeFromInventory(event);
	}

	private void handleStealEvent(EventReturnType event) {
		JOptionPane.showMessageDialog(null, "While traveling, a roving band of bandits stole some supplies from you! You lost " + event.toString());
		removeFromInventory(event);
	}

	private void handleWagonDmgEvent(EventReturnType event) {
		JOptionPane.showMessageDialog(null, "While traveling, a wagon part has broken. You lost "+event.toString());
		removeFromInventory(event);
		}

	private void handleIllEvent(EventReturnType event) {
		JOptionPane.showMessageDialog(null, event.toString());
		event.getIll().setHealth(Health.ILL); // set the person's health to ill
	}

	private void handleDeadEvent(EventReturnType event) {
		JOptionPane.showMessageDialog(null, event.toString());
		if (event.getHealthy() != null)
			event.getHealthy().setHealth(Health.HEALTHY);
		if (event.getIll() != null)
			event.getIll().setHealth(Health.ILL);
		if (event.getVeryIll() != null)
			event.getVeryIll().setHealth(Health.VERY_ILL);
		if (event.getDeceased() != null)
			event.getDeceased().setHealth(Health.DEAD);
	}

	private void handleStormEvent(EventReturnType event) {
		JOptionPane.showMessageDialog(null, "A terrible storm has forced you to stop traveling for " + event.getDaysLost() + " day(s).");
		removeFromInventory(event);
	}
	
	private void removeFromInventory(Inventory inventory) {
		party.getInventory().addAmmo(-inventory.getAmmo()); // this works because the inventory will have negative values
		party.setWeight(party.getWeight()-(inventory.getAmmo()*ItemType.AMMO.weight()));
		
		party.getInventory().addAxle(-inventory.getAxles()); // when an item is to be given to the player
		party.setWeight(party.getWeight()-(inventory.getAxles()*ItemType.AXLE.weight()));
		
		party.getInventory().addFood(-inventory.getFood());
		party.setWeight(party.getWeight()-(inventory.getFood()*ItemType.FOOD.weight()));
		
		party.getInventory().addOxen(-inventory.getOxen());
		party.setWeight(party.getWeight()-(inventory.getOxen()*ItemType.OXEN.weight()));
		
		party.getInventory().addClothing(-inventory.getClothing());
		party.setWeight(party.getWeight()-(inventory.getClothing()*ItemType.CLOTHING.weight()));
		
		party.getInventory().addWheel(-inventory.getWheels());
		party.setWeight(party.getWeight()-(inventory.getWheels()*ItemType.WHEEL.weight()));
		
		party.getInventory().addTongues(-inventory.getTongues());
		party.setWeight(party.getWeight()-(inventory.getTongues()*ItemType.TONGUE.weight()));
	}
	
	/**
	 * This method checks to make sure the wagon has 4 wheels, 2 axles, 1 tongue and 2 oxen.
	 * It will return true if the wagon can move, and false if not.
	 */
	private boolean checkWagonCanMove() {
		boolean wagon = true;
		if (party.getPace() != Pace.STOPPED) {
			boolean wheels = true;
			boolean tongues = true;
			boolean axles = true;
			boolean oxen = true;
			if (party.getInventory().getWheels() < 4) {
				wheels = false;
				wagon = false;
			}
			if (party.getInventory().getTongues() < 1) {
				tongues = false;
				wagon = false;
			}
			if (party.getInventory().getAxles() < 2) {
				axles = false;
				wagon = false;
			}
			if (party.getInventory().getOxen() < 2) {
				oxen = false;
				wagon = false;
			}
			
			if (wheels == false || tongues == false || axles == false || oxen == false) {
				JOptionPane.showMessageDialog(null, "You do not have enough supplies to travel. You need atleast 4 wheels, 2 axles, " +
						" 1 tongue and 2 oxen to travel.\n\nYou have:\n\tWheels: "+party.getInventory().getWheels()+"\n\tAxles: "
						+party.getInventory().getAxles()+"\n\tTongues: "+party.getInventory().getTongues()+"\n\tOxen: "+party.getInventory().getOxen()
						+"\n\nYou must wait until someone comes along the trail with the supplies you need... If they ever do.\n You " +
						" can hunt for food to keep yourself fed.");
				party.setPace(Pace.STOPPED);
			}
			
			
		}
		return wagon;
	}
	/**
	 * This method checks to see if the whole party can be outfitted.
	 * @return true if it can, false if it can't.
	 */
	private boolean checkClothing(){
		return party.getInventory().getClothing() >= party.getNumberOfAlivePartyMembers();
	}
}
