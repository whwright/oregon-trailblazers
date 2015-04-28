package edu.gatech.cs2340.oregontb.gamelogic;

import java.util.Random;
import java.util.ArrayList;
import java.util.List;

/**
 * The EventHandler class checks for all the possible random events that can occur
 * during game play and sends them to the GameEngine.
 * @author Oregon Trailblazers
 * @version 1.0
 */
public class EventHandler {
	
	/**
	 * Random class used to generate numbers for the EventHandler.
	 */
	private final Random rand;
	
	/**
	 * The user's party in the game.
	 */
	private final Party party;
	
	/**
	 * Unique identifier for the severeStormCheck event.
	 */
	public static final int STORM_ID = 0;
	
	/**
	 * Unique identifier for the deadPersonCheck event.
	 */
	public static final int DEAD_ID = 1;
	
	/**
	 * Unique identifier for the illPersonCheck event.
	 */
	public static final int ILL_ID = 2;
	
	/**
	 * Unique identifier for the wagonDamageCheck event.
	 */
	public static final int WAGON_DMG_ID = 3;
	
	/**
	 * Unique identifier for the stealFromWagonCheck event.
	 */
	public static final int STEAL_ID = 4;
	
	/**
	 * Unique identifier for the giveToWagonCheck event.
	 */
	public static final int GIVE_ID = 5;
	
	/**
	 * Unique identifier for the deadOxCheck event.
	 */
	public static final int DEAD_OX_ID = 6;
	
	/**
	 * Unique identifier for the tiredOxCheck event.
	 */
	public static final int TIRED_OX_ID = 7;
	
	/**
	 * Unique identifier for the wagonFlipCheck event.
	 */
	public static final int RIVER_ID = 8;
	
	/**
	 * Constructor for EventHandler takes in a party and instantiates the Random class.
	 * @param party
	 */
	public EventHandler(Party party) {
		rand = new Random();
		this.party = party;
	}
	
	/**
	 * This method checks all of the random events and puts them into a list.
	 * @param party
	 * @param locationList
	 * @return List<EventReturnType> of all random events
	 */
	public List<EventReturnType> checkRandomEvents(Party party, LocationList locationList) {
		EventReturnType returnVal = null; // initialize returnVal
		final List<EventReturnType> badStuff = new ArrayList<EventReturnType>(); //allocate a list to return
		// severe storm
		returnVal = severeStormCheck(party, locationList);
		if(returnVal.isBad()) {
			badStuff.add(returnVal);
		}
		
		// dead person
		returnVal = deadPersonCheck(party, locationList);
		if(returnVal.isBad()) {
			badStuff.add(returnVal);
		}
		
		// ill person
		returnVal = illPersonCheck(party, locationList);
		if(returnVal.isBad()) {
			badStuff.add(returnVal);
		}
		
		// wagon damage
		returnVal = wagonDamageCheck(party, locationList);
		if(returnVal.isBad())
		badStuff.add(returnVal);
		
		// steal from wagon
		returnVal = stealFromWagonCheck(party, locationList);
		if(returnVal.isBad()) {
			badStuff.add(returnVal);
		}
		
		// give to wagon
		returnVal = giveToWagonCheck(party, locationList);
		if(returnVal.isBad()) {
			badStuff.add(returnVal);
		}
		
		// dead oxen
		returnVal = deadOxCheck(party, locationList);
		if(returnVal.isBad()) {
			badStuff.add(returnVal);
		}
		
		// tired oxen
		returnVal = tiredOxenCheck(party, locationList);
		if(returnVal.isBad()) {
			badStuff.add(returnVal);
		}
		
		return badStuff; // return
	}
	
	/**
	 * Checks to see if your party gets caught in a storm. If you are, you are forced
	 * to stop and continue to consume food while waiting for it to blow over. There
	 * is also a probability of someone falling ill.
	 * @param party
	 * @param locationList
	 * @return EventReturnType determining if something happens
	 */
	public EventReturnType severeStormCheck(Party party, LocationList locationList) {
		final EventReturnType returnVal = new EventReturnType(STORM_ID); // make returnVal
		if (party.getPace() != Pace.STOPPED) { // makes sure you're not stopped, you can't be forced to stop if you're already stopped
			double gauss = rand.nextGaussian(); 
			gauss = Math.abs(gauss); // gauss is from 0 to positive infinity
			// Because this will be checked every day, the threshold's pretty low. You can amp it up if you want to test it.
			if(gauss > 2.5) { //bad stuff (prob = 0.0278069)
				//consume food based on the rations at the time
				final int days = (Math.abs(rand.nextInt()) % 2) + 1; //1-3 days worth of food, with minimum one.
				final int foodEaten = (party.getRations().getFoodConsumed()* party.getNumberOfAlivePartyMembers())* days; //copy pasta from updateFood()
				// adds the correct amount of food to returnVal
				if (party.getInventory().getFood() < foodEaten) {
					returnVal.addFood(party.getInventory().getFood()); 
				}
				else {
					returnVal.addFood(foodEaten);
				}
				returnVal.setBad(true); // set bad
				returnVal.setDaysLost(days); // indicate to the GameEngine how many days are lost
			}
		}
		return returnVal; // If not bad stuff, nothing is changed.
	}

	/**
	 * Randomly has party members fall ill.
	 * @param party
	 * @param locationList
	 * @return EvenyReturnType determining if anything happens
	 */
	public EventReturnType illPersonCheck(Party party, LocationList locationList) {
		final EventReturnType returnVal = new EventReturnType(ILL_ID); // make returnVal
		double gauss = rand.nextGaussian(); 
		gauss = Math.abs(gauss); // gauss is from 0 to positive infinity
		final double paceFactor = party.getPace().getEventFactor(); 
		final double rationsFactor = party.getRations().getEventFactor();
		double partyFactor = paceFactor + rationsFactor; // create partyFactor based on pace and rations
		if(party.getInventory().getClothing() < party.getNumberOfAlivePartyMembers()) {
			partyFactor += 1;
		}
		if(party.getPace() == Pace.STOPPED)
			partyFactor -= 1;
		gauss += partyFactor;
		if(party.getNumberOfIllOrDeadPartyMembers() < 5 && gauss > 3) { //bad stuff (prob = 0.0644)
			//copy pasta from the river crossing check
			if (party.getNumberOfIllOrDeadPartyMembers() == 4 
					&& party.getParty().get(0).getHealth() != Health.ILL) {
				returnVal.addIllPerson(party.getParty().get(0));
				returnVal.setBad(true);// the leader is only ever at index 0
				return returnVal;
			} // otherwise find a party member
			Person illPerson = null;
			while (illPerson == null || illPerson.isLeader()) { // ensure that the leader is not killed before the others
				illPerson = party.getParty().get(Math.abs(rand.nextInt()) 
						% party.getParty().size());
				if (illPerson.getHealth() == Health.ILL ||
						illPerson.getHealth() == Health.DEAD) {
					illPerson = null;
				}
			}
			returnVal.addIllPerson(illPerson);
			returnVal.setBad(true);
		}
		return returnVal; // no bad stuff, nothing changed.
	}
	
	/**
	 * If someone is ill in the party, randomly see if they die, or randomly see if
	 * they get very ill, which has a larger chance of dying. Will also check if any
	 * of the party members recover from being ill or very ill.
	 * @param party
	 * @param locationList
	 * @return EventReturnType determining if anything happens
	 */
	public EventReturnType deadPersonCheck(Party party, LocationList locationList) {
		final EventReturnType returnVal = new EventReturnType(DEAD_ID);
		double gauss = rand.nextGaussian(); 
		gauss = Math.abs(gauss); // gauss is from 0 to positive infinity
		final double paceFactor = party.getPace().getEventFactor();
		final double rationsFactor = party.getRations().getEventFactor();
		double partyFactor = paceFactor + rationsFactor; 
		if(party.getInventory().getClothing() < party.getNumberOfAlivePartyMembers()) {
			partyFactor += 1;
		}
		//I'm pretty sure this probabilities are wonky, but we can change the numbers.
		/*
		 * This next chunk of code ensures that party members are 
		 * checked in a random order, so party members near the 
		 * top aren't favored against (which would be the case 
		 * if it was sequential).
		 */
		final List<Person> randomSeq = new ArrayList<Person>();
		while(randomSeq.size() < party.getNumberOfAlivePartyMembers()) {
			int random = Math.abs(rand.nextInt()) % (party.getParty().size());
			if(party.getParty().get(random).getHealth() != Health.DEAD) {
				randomSeq.add(party.getParty().get(random));
			}
		}
		for(int i = 0; i < randomSeq.size(); i++) {
			Person member = randomSeq.get(i);
			if(member.getHealth().equals(Health.ILL)) {
				if((gauss + partyFactor) > 2.1) { //death (prob = ???)
					returnVal.addDeadPerson(member);
					returnVal.setBad(true);
				}
				else if((gauss + partyFactor) < 2 && (gauss + partyFactor) >= 1.6) { //very ill (prob = ??)
					returnVal.addVeryIllPerson(member);
					returnVal.setBad(true);
				}
				else if((gauss + partyFactor) < 1.6  && ( + partyFactor) >= 1) { //recovery (prob = ??)
					returnVal.addHealthyPerson(member);
					returnVal.setBad(true);
				}
				//else still ill.
			}
			if(member.getHealth().equals(Health.VERY_ILL)) {
				if((gauss + partyFactor) > 1.8) { //death (prob = ???) 
					returnVal.addDeadPerson(member);
					returnVal.setBad(true);
				}

				else if((gauss+ partyFactor) < 2 && (gauss+ partyFactor) >= 1.6) { //recovery (prob = ??)
					returnVal.addHealthyPerson(member);
					returnVal.setBad(true);
				}
				//else still ill.
			}
			if(returnVal.isBad()) {
				break; //this way, only one person can die OR recover OR fall very ill every step.
			}
		}
		return returnVal;
	}
	
	/**
	 * Randomly breaks a part of the wagon.
	 * @param party
	 * @param locationList
	 * @return ItemType determining which item broke
	 */
	public EventReturnType wagonDamageCheck(Party party, LocationList locationList) {
		final EventReturnType returnVal = new EventReturnType(WAGON_DMG_ID);
		double gauss = rand.nextGaussian();
		gauss = Math.abs(gauss);
		if(party.getProfession() != Profession.CARPENTER) {
			gauss += 0.5;
		}
		if(party.getPace() != Pace.STOPPED && gauss > 2.4) { //arbitrary number here, not sure what the probability of this should be
			//lose only one type of wagon part per event
			final int part = Math.abs(rand.nextInt()) % 3;
			
			if(part == 0 && party.getInventory().getAxles() > 0) {
				returnVal.addAxle(1);
				returnVal.setBad(true);
				return returnVal;
			}
			
			else if(part == 1 && party.getInventory().getTongues() > 0) {
				returnVal.addTongues(1);
				returnVal.setBad(true);
				return returnVal;
			}
			
			else if(part == 2 && party.getInventory().getWheels() > 0) {
				returnVal.addWheel(1);
				returnVal.setBad(true);
				return returnVal;
			}
		}
		return returnVal;
	}
	
	/**
	 * Randomly losses items. The idea is that you encounter a thief on the trail 
	 * who steals items from you.
	 * @param party
	 * @param locationList
	 * @return EventReturnType determining if anything happens
	 */
	public EventReturnType stealFromWagonCheck(Party party, LocationList locationList) {
		final EventReturnType returnVal = new EventReturnType(STEAL_ID);
		double gauss = rand.nextGaussian(); 
		gauss = Math.abs(gauss); // gauss is from 0 to positive infinity
		//Because this will be checked every day, the threshold's pretty low. You can amp it up if you want to test it.
		if(party.getWeight() > 0 && gauss > 2.4) { //bad stuff (prob = 0.0892)
			//lose a random item, copy pasta from river check.
			final int foodLost = Math.abs(rand.nextInt()) % 50; // 50 = max amount of food lost
			final int clothesLost = Math.abs(rand.nextInt()) % 2; // 2 = max clothes lost
			//more forgiving than the river
			if (party.getInventory().getFood() < foodLost) {
				returnVal.addFood(party.getInventory().getFood());
			}
			else {
				returnVal.addFood(foodLost);
			}
			if (party.getInventory().getClothing() < clothesLost) {
				returnVal.addClothing(party.getInventory().getFood());
			}
			else {
				returnVal.addClothing(clothesLost);
			}
			if (party.getInventory().getAxles() != 0) {
				returnVal.addAxle(1);
			}
			returnVal.setBad(true);
		}
		return returnVal; //not bad stuff, nothing is changed.
	}
	
	/**
	 * Will randomly add any type of item except Oxen. Will not occur if capacity is full,
	 * and will not overload the wagon. The idea is that you encounter a native on the 
	 * trail who gives you supplies.
	 * @param party
	 * @param locationList
	 * @return EventReturnType determining if anything happened
	 */
	public EventReturnType giveToWagonCheck(Party party, LocationList locationList) {
		final EventReturnType returnVal = new EventReturnType(GIVE_ID);
		double gauss = rand.nextGaussian(); 
		gauss = Math.abs(gauss); // gauss is from 0 to positive infinity
		if(party.getWeight() < party.getMaxWeight() && gauss > 2.4) { //chance of getting the event
			gauss = rand.nextGaussian(); 
			gauss = Math.abs(gauss); // gauss is from 0 to positive infinity
			
			if(gauss > 1 && gauss < 3) { //chance of getting food
				final int foodGiven = Math.abs(rand.nextInt()) % 25 + 1;
				if(party.getWeight() + (foodGiven * ItemType.FOOD.weight())
						< party.getMaxWeight()) {
					returnVal.addFood(foodGiven);
				}
				else {
					returnVal.addFood((party.getMaxWeight() - party.getWeight())
							% ItemType.FOOD.weight());
				}
			}
			if(gauss > 0.75 && gauss < 1.5) { //chance of getting clothes
				final int clothesGiven = Math.abs(rand.nextInt()) % 5 + 1;
				if(party.getWeight() + (clothesGiven * ItemType.CLOTHING.weight()) 
						< party.getMaxWeight()) {
					returnVal.addClothing(clothesGiven);
				}
				else {
					returnVal.addClothing((party.getMaxWeight() - party.getWeight())
							% ItemType.CLOTHING.weight());
				}
			}
			if(gauss > 1.2 && gauss < 3.1) { //chance of getting ammo 
				final int ammoGiven = Math.abs(rand.nextInt()) % 20 + 1;
				if(party.getWeight() + (ammoGiven * ItemType.AMMO.weight()) 
						< party.getMaxWeight()) {
					returnVal.addAmmo(ammoGiven);
				}
				else {
					returnVal.addAmmo((party.getMaxWeight() - party.getWeight()) 
							% ItemType.AMMO.weight());
				}
			}
			if(gauss < 1.25 || gauss > 3.25) { //chance of getting wagon parts (only ONE kind)
				final int partsGiven = Math.abs(rand.nextInt()) % 3 + 1;
				final int part = Math.abs(rand.nextInt()) % 3;
				
				if(part == 0) {
					if(party.getWeight() + (partsGiven * ItemType.AXLE.weight()) 
							< party.getMaxWeight()) {
						returnVal.addAxle(partsGiven);
					}
					else {
						returnVal.addAxle((party.getMaxWeight() - party.getWeight()) 
								% ItemType.AXLE.weight());
					}
				}
				
				if(part == 1) {
					if(party.getWeight() + (partsGiven * ItemType.TONGUE.weight()) 
							< party.getMaxWeight()) {
						returnVal.addTongues(partsGiven);
					}
					else {
						returnVal.addTongues((party.getMaxWeight() - party.getWeight()) 
								% ItemType.TONGUE.weight());
					}
				}
				
				if(part == 2) {
					if(party.getWeight() + (partsGiven * ItemType.WHEEL.weight()) 
							< party.getMaxWeight()) {
						returnVal.addWheel(partsGiven);
					}
					else {
						returnVal.addWheel((party.getMaxWeight() - party.getWeight()) 
								% ItemType.WHEEL.weight());
					}
				}
			}
			returnVal.setBad(true);
		}
		// flip the sign of all return values to make GameEngine work correctly
		returnVal.setOxen(-returnVal.getOxen());
		returnVal.setFood(-returnVal.getFood());
		returnVal.setClothing(-returnVal.getClothing());
		returnVal.setAmmo(-returnVal.getAmmo());
		returnVal.setWheels(-returnVal.getWheels());
		returnVal.setAxles(-returnVal.getAxles());
		returnVal.setTongues(-returnVal.getTongues());
		return returnVal;
	}
	
	/**
	 * WagonFlipCheck performs a river crossing and determines whether or not
	 *  the user crosses a river successfully.
	 * If the river is crossed successfully, the return value has a boolean flag
	 *  "isBad" set to false. Otherwise, if penalties are applied, isBad is set
	 *  to true and the EventReturnType contains numbers corresponding to how much
	 * of each resources is lost.
	 * @param party The Party associated with the current game instance
	 * @param crossingType The type of crossing to be attempted
	 * @return The EventReturnType indicating the consequences of the crossing
	 */
	public EventReturnType wagonFlipCheck(Party party, String crossingType) {
		final EventReturnType returnVal = new EventReturnType(RIVER_ID);
		if (crossingType.equals("caulk")) {
			double gen = rand.nextGaussian(); // gen is a number from -inf to +inf
			gen = Math.abs(gen); // gen is now a number from 0 to inf
			if (gen < 0.7) { // gen is .7 standard deviation from the mean (51.6%)
				return returnVal; // isBad == false, nothing bad happened
			}
			if (gen >= 0.7 && gen < 1) { // gen is between 0.7 and 1 standard deviations from the mean (16.6%)
				// lose food
				final int foodLost = Math.abs(rand.nextInt()) % 200; // 200 = max amount of food lost
				if (party.getInventory().getFood() < foodLost) {
					returnVal.addFood(party.getInventory().getFood());
				}
				else {
					returnVal.addFood(foodLost);
				}
				returnVal.setBad(true);
				return returnVal;
			}
			if(gen >= 1 && gen < 1.3) { // gen is between 1 and 1.3 standard deviations from the mean (12.3%)
				// lose food and clothes
				final int foodLost = Math.abs(rand.nextInt()) % 200; // 200 = max amount of food lost
				final int clothesLost = Math.abs(rand.nextInt()) % 4; // 4 = max clothes lost
				if (party.getInventory().getFood() < foodLost) {
					returnVal.addFood(party.getInventory().getFood());
				}
				else {
					returnVal.addFood(foodLost);
				}
				if (party.getInventory().getClothing() < clothesLost) {
					returnVal.addClothing(party.getInventory().getFood());
				}
				else {
					returnVal.addClothing(clothesLost);
				}
				returnVal.setBad(true);
				return returnVal;
			}
			if (gen >= 1.3 && gen < 1.7) { // gen is between 1.3 and 1.7 standard deviations from the mean (10.4%)
				// lose food, clothes, axle
				final int foodLost = Math.abs(rand.nextInt()) % 200; // 200 = max amount of food lost
				final int clothesLost = Math.abs(rand.nextInt()) % 4; // 4 = max clothes lost
				if (party.getInventory().getFood() < foodLost) {
					returnVal.addFood(party.getInventory().getFood());
				} 
				else {
					returnVal.addFood(foodLost);
				}
				if (party.getInventory().getClothing() < clothesLost) {
					returnVal.addClothing(party.getInventory().getFood());
				}
				else {
					returnVal.addClothing(clothesLost);
				}
				if (party.getInventory().getAxles() != 0) {
					returnVal.addAxle(1);
				}
				returnVal.setBad(true);
				return returnVal;
			}
			if (gen >= 1.7) { // gen is between 1.7 and +inf standard deviations from the mean (8.9%)
				// someone dies and other stuff is lost
				final int foodLost = Math.abs(rand.nextInt()) % 200; // 200 = max amount of food lost
				final int clothesLost = Math.abs(rand.nextInt()) % 4; // 4 = max clothes lost
				if (party.getInventory().getFood() < foodLost) {
					returnVal.addFood(party.getInventory().getFood());
				}
				else {
					returnVal.addFood(foodLost);
				}
				if (party.getInventory().getClothing() < clothesLost) {
					returnVal.addClothing(party.getInventory().getFood());
				}
				else {
					returnVal.addClothing(clothesLost);
				}
				if (party.getInventory().getAxles() != 0) {
					returnVal.addAxle(1);
				}
				if (party.getNumberOfAlivePartyMembers() == 1) { // only the leader remains
					returnVal.addDeadPerson(party.getParty().get(0)); // the leader is only ever at index 0
					return returnVal;
				} // otherwise find a party member
				Person deadPerson = null;
				while (deadPerson == null || deadPerson.isLeader()) { // ensure that the leader is not killed
					deadPerson = party.getParty().get(Math.abs(rand.nextInt()) 
							% party.getParty().size());
					if (deadPerson.getHealth() == Health.DEAD) {
						deadPerson = null;
					}
				}
				returnVal.addDeadPerson(deadPerson);
				returnVal.setBad(true);
				return returnVal;
			}
			
		}
		else if (crossingType.equals("ford")) {
			double gen = rand.nextGaussian();
			gen = Math.abs(gen); // gen is now a number from 0 to inf
			if (gen < 0.5) { // 38%
				// nothing bad happens
				return returnVal; // isBad == false, nothing bad happened
			}
			if (gen >= 0.5 && gen < 0.9) { // 24.8%
				// lose some food
				final int foodLost = Math.abs(rand.nextInt()) % 200; // 200 = max amount of food lost
				if (party.getInventory().getFood() < foodLost) {
					returnVal.addFood(party.getInventory().getFood());
				}
				else {
					returnVal.addFood(foodLost);
				}
				returnVal.setBad(true);
				return returnVal;
			}
			if(gen >= 0.9 && gen < 1.2) { // 13.7%
				// lose food and clothes
				final int foodLost = Math.abs(rand.nextInt()) % 200; // 200 = max amount of food lost
				final int clothesLost = Math.abs(rand.nextInt()) % 4; // 4 = max clothes lost
				if (party.getInventory().getFood() < foodLost) {
					returnVal.addFood(party.getInventory().getFood());
				}
				else {
					returnVal.addFood(foodLost);
				}
				if (party.getInventory().getClothing() < clothesLost) {
					returnVal.addClothing(party.getInventory().getFood());
				}
				else {
					returnVal.addClothing(clothesLost);
				}
				returnVal.setBad(true);
				return returnVal;
			}
			if (gen >= 1.2 && gen < 1.4) { // 9.6%
				// lose food, clothes, axle
				final int foodLost = Math.abs(rand.nextInt()) % 200; // 200 = max amount of food lost
				final int clothesLost = Math.abs(rand.nextInt()) % 4; // 4 = max clothes lost
				if (party.getInventory().getFood() < foodLost) {
					returnVal.addFood(party.getInventory().getFood());
				}
				else {
					returnVal.addFood(foodLost);
				}
				if (party.getInventory().getClothing() < clothesLost) {
					returnVal.addClothing(party.getInventory().getFood());
				}
				else {
					returnVal.addClothing(clothesLost);
				}
				if (party.getInventory().getAxles() != 0) {
					returnVal.addAxle(1);
				}
				returnVal.setBad(true);
				return returnVal;
			}
			if (gen >= 1.4) { // 16.1%
				// someone dies
				final int foodLost = Math.abs(rand.nextInt()) % 200; // 200 = max amount of food lost
				final int clothesLost = Math.abs(rand.nextInt()) % 4; // 4 = max clothes lost
				if (party.getInventory().getFood() < foodLost) {
					returnVal.addFood(party.getInventory().getFood());
				}
				else {
					returnVal.addFood(foodLost);
				}
				if (party.getInventory().getClothing() < clothesLost) {
					returnVal.addClothing(party.getInventory().getFood());
				}
				else {
					returnVal.addClothing(clothesLost);
				}
				if (party.getInventory().getAxles() != 0) {
					returnVal.addAxle(1);
				}
				if (party.getNumberOfAlivePartyMembers() == 1) { // only the leader remains
					returnVal.addDeadPerson(party.getParty().get(0)); // the leader is only ever at index 0
					return returnVal;
				} // otherwise find a party member
				Person deadPerson = null;
				while (deadPerson == null || deadPerson.isLeader()) { // ensure that the leader is not killed
					deadPerson = party.getParty().get(Math.abs(rand.nextInt()) 
							% party.getParty().size());
					if (deadPerson.getHealth() == Health.DEAD) {
						deadPerson = null;
					}
				}
				returnVal.addDeadPerson(deadPerson);
				returnVal.setBad(true);
				return returnVal;
			}
		}
		return null;
	}
	
	/**
	 * Checks to see if the tired oxen has died. If more oxen are tired,
	 * dead oxen are more likely to occur.
	 * @param party
	 * @param locationList
	 * @return EventReturnType determining if something happened
	 */
	public EventReturnType deadOxCheck(Party party, LocationList locationList) {
		final EventReturnType returnVal = new EventReturnType(DEAD_OX_ID);
		if (party.getInventory().getOxen() > 0) {
			double gauss = rand.nextGaussian(); 
			gauss = Math.abs(gauss); // gauss is from 0 to positive infinity
			double paceFactor = party.getPace().getEventFactor() 
										+ (0.2 + party.getTiredOxen());
			if(party.getProfession() == Profession.FARMER) {
				paceFactor += 1;
			}
			if(party.getInventory().getOxen() > 0){
				if(gauss > (2 + paceFactor)) {
					returnVal.setDeadOxen(1);
					returnVal.setBad(true);
				}
			}
		}
		return returnVal;
	}
	
	/**
	 * Checks to see if oxen become tired or recover. Affected only by the pace at
	 * the moment, but could also be affected by the number of days gone without rest.
	 * @param party
	 * @param locationList
	 * @return EventReturnType determining if something happened
	 */
	public EventReturnType tiredOxenCheck(Party party, LocationList locationList) {
		final EventReturnType returnVal = new EventReturnType(TIRED_OX_ID);
		double gauss = rand.nextGaussian(); 
		gauss = Math.abs(gauss); // gauss is from 0 to positive infinity
		double paceFactor = party.getPace().getEventFactor();
		if(party.getProfession() == Profession.FARMER) {
			paceFactor += 1;
		}
		if(party.getInventory().getOxen() > 0){
			if(gauss > (2 + paceFactor)) {
				returnVal.setTiredOxen(1);
				returnVal.setBad(true);
			}
			if(party.getTiredOxen() > 0	&& gauss > 1.7 + paceFactor) {
				returnVal.setTiredOxen(-1);
				returnVal.setBad(true);
			}
		}
		return returnVal;
	}
	
}
