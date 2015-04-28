package edu.gatech.cs2340.oregontb.testing;

import static org.junit.Assert.*;
import org.junit.Test;
import edu.gatech.cs2340.oregontb.gamelogic.*;
import edu.gatech.cs2340.oregontb.userinterface.*;
import java.lang.reflect.*;
import java.util.ArrayList;


public class JUnitTests {

	
	/**
	 * Testing the Store class's buy method.
	 * by Sean Gillespie
	 */
	@Test
	public void buyTest() {
		Party party = new Party();
		party.setMoney(2000);
		Store store = new Store(1.0, party);
		Inventory inventory = new Inventory();
		inventory.addFood(200);
		inventory.addClothing(20);
		
		boolean bought = store.buy(inventory, 400, 1040);
		assertTrue(bought); // make sure it bought successfully
		assertEquals(party.getInventory().getFood(), 200);
		assertEquals(party.getInventory().getClothing(), 20);
		
		Party party2 = new Party();
		Store store2 = new Store(1.0, party2);
		Inventory inventory2 = new Inventory();
		inventory2.addFood(20000);
		boolean bought2 = store2.buy(inventory, 20000, 100000);
		assertFalse(bought2);
		assertEquals(party2.getInventory().getFood(), 0);
	}
	/**
	 *  Testing EventHandler's illPersonCheck method on full party, all HEALTHY
	 *  by Michael Sandt
	 */
	@Test
	public void illPersonCheckFull(){
		//set up party
		Party party = new Party();
		party.setPace(Pace.STEADY);
		party.setRations(Rations.NORMAL);
		//set up ArrayList of people
		ArrayList<Person> people = new ArrayList<Person>();
		Person person1 = new Person("Person1",true);
		people.add(person1);
		Person person2 = new Person("Person2",false);
		people.add(person2);
		Person person3 = new Person("Person3",false);
		people.add(person3);
		Person person4 = new Person("Person4",false);
		people.add(person4);
		Person person5 = new Person("Person5",false);
		people.add(person5);
		//set party to healthy
		for(int i=0; i<people.size(); i++){
			people.get(i).setHealth(Health.HEALTHY);
		}
		//add people to party
		party.setParty(people);
		
		//create event handler
		EventHandler handler = new EventHandler(party);
		EventReturnType retVal = null;
		//keep running check until something bad happens
		do{
			retVal = handler.illPersonCheck(party, null);
		}
		while(!retVal.isBad());
		
		int numIll = 0;
		for(int i=0; i<people.size(); i++)
		{
			if(people.get(i).equals(retVal.getIll()))
				numIll++;
		}
		//check to make sure party leader is not ill
		assertEquals(Health.HEALTHY, people.get(0).getHealth());
		//check to make sure no more than one person has fallen ill per run
		assertEquals(1, numIll);
		
	}
	/**
	 *  Testing EventHandler's illPersonCheck method on 5 party members
	 *  all will be dead other than party leader, leader should become ill
	 *  by Michael Sandt
	 */
	@Test
	public void illPersonCheckLeader(){
		//set up party
		Party party = new Party();
		party.setPace(Pace.STEADY);
		party.setRations(Rations.NORMAL);
		//set up ArrayList of people
		ArrayList<Person> people = new ArrayList<Person>();
		Person person1 = new Person("Person1",true);
		people.add(person1);
		Person person2 = new Person("Person2",false);
		people.add(person2);
		Person person3 = new Person("Person3",false);
		people.add(person3);
		Person person4 = new Person("Person4",false);
		people.add(person4);
		Person person5 = new Person("Person5",false);
		people.add(person5);
		//set party to healthy
		for(int i=1; i<people.size(); i++){
			people.get(i).setHealth(Health.DEAD);
		}
		//add people to party
		party.setParty(people);
		assertEquals(party.getNumberOfAlivePartyMembers(), 1);
		assertEquals(party.getNumberOfIllOrDeadPartyMembers(), 4);
		//create event handler
		EventHandler handler = new EventHandler(party);
		EventReturnType retVal = null;
		//keep running check until something bad happens
		do{
			retVal = handler.illPersonCheck(party, null);
		}
		while(!retVal.isBad());
		
		int numIll = 0;
		for(int i=0; i<people.size(); i++)
		{
			if(people.get(i).equals(retVal.getIll()))
				numIll++;
		}
		//check to make sure party leader is not ill
		//assertEquals(Health.ILL, people.get(0).getHealth());
		//check to make sure no more than one person has fallen ill per run
		assertEquals(1, numIll);
		
	}
	/**
	 * Testing the Inventory class's add() method:
	 * testing expected values and exception
	 * by Casey Tisdel
	 */
	@Test
	public void addTest() {
		Inventory inventory = new Inventory();
		
		inventory.setAmmo(100);
		inventory.setAxles(2);
		inventory.setClothing(6);
		inventory.setFood(200);
		inventory.setOxen(4);
		inventory.setTongues(2);
		inventory.setWheels(4);
		
		//add AMMO to inventory and check math works
		inventory.add(ItemType.AMMO,50);
		assertEquals(150, inventory.getAmmo());
		assertEquals(2, inventory.getAxles());
		assertEquals(6, inventory.getClothing());
		assertEquals(200, inventory.getFood());
		assertEquals(4, inventory.getOxen());
		assertEquals(2, inventory.getTongues());
		assertEquals(4, inventory.getWheels());
		//add Whores
	}
	
	
	
	/**
	 * Testing the GameEnginge's updateFood method on Party with Rations
	 * set to NONE and 5 Party members alive.
	 */
	@Test
	public void updateFoodRationsNoneTest() {
		// makes an array of People to give to the party
		ArrayList<Person> people = new ArrayList<Person>();
		Person person1 = new Person("Person1",true);
		people.add(person1);
		Person person2 = new Person("Person2",false);
		people.add(person2);
		Person person3 = new Person("Person3",true);
		people.add(person3);
		Person person4 = new Person("Person4",true);
		people.add(person4);
		Person person5 = new Person("Person5",true);
		people.add(person5);
		
		Party party = new Party(); // makes a new party
		party.setRations(Rations.NONE); // set rations to none
		party.getInventory().setFood(100); // set food to 100
		party.setWeight(100); // so weight must be 100
		party.setParty(people); // sets the people in the party
		GameEngine engine = new GameEngine(party, null); // make a GameEngine
		
        try {
        	// gets all the methods for engine
        	final Method[] methods = engine.getClass().getDeclaredMethods();
        	// goes through each method
    		for (int i=0; i<methods.length; i++) {
    			// finds updateFood
    			if (methods[i].getName().equals("updateFood")) {
    				methods[i].setAccessible(true);
    				methods[i].invoke(engine); // calls updateFood on engine
    			}
    		}
		} catch (Exception e) {
			e.printStackTrace();
		}
        party = engine.getParty(); // refreshes the party
        
        // makes sure food and weight were correctly updated
        assertEquals(100, party.getInventory().getFood());
        assertEquals(100, party.getWeight());
	}
	
	/**
	 * Testing the GameEnginge's updateFood method on Party with Rations
	 * set to BAREBONES and 5 Party members alive.
	 */
	@Test
	public void updateFoodRationsBarebonesTest() {
		// makes an array of People to give to the party
		ArrayList<Person> people = new ArrayList<Person>();
		Person person1 = new Person("Person1",true);
		people.add(person1);
		Person person2 = new Person("Person2",false);
		people.add(person2);
		Person person3 = new Person("Person3",true);
		people.add(person3);
		Person person4 = new Person("Person4",true);
		people.add(person4);
		Person person5 = new Person("Person5",true);
		people.add(person5);
		
        Party party = new Party();
        party.setRations(Rations.BAREBONES); // set rations to barebones
		party.getInventory().setFood(100); // set food to 100
		party.setWeight(100); // so weight must be 100
		party.setParty(people); // sets the people in the party
		GameEngine engine = new GameEngine(party, null); // make a GameEngine
		
        try {
        	// gets all the methods for engine
        	final Method[] methods = engine.getClass().getDeclaredMethods();
        	// goes through each method
    		for (int i=0; i<methods.length; i++) {
    			// finds updateFood
    			if (methods[i].getName().equals("updateFood")) {
    				methods[i].setAccessible(true);
    				methods[i].invoke(engine); // calls updateFood on engine
    			}
    		}
		} catch (Exception e) {
			e.printStackTrace();
		}
        party = engine.getParty(); // refreshes the party
        
        // makes sure food and weight were correctly updated
        assertEquals(95, party.getInventory().getFood());
        assertEquals(95, party.getWeight());
	}
       
	/**
	 * Testing the GameEnginge's updateFood method on Party with Rations
	 * set to MEAGER and 5 Party members alive.
	 */
	@Test
	public void updateFoodRationsMeagerTest() {
		// makes an array of People to give to the party
		ArrayList<Person> people = new ArrayList<Person>();
		Person person1 = new Person("Person1",true);
		people.add(person1);
		Person person2 = new Person("Person2",false);
		people.add(person2);
		Person person3 = new Person("Person3",true);
		people.add(person3);
		Person person4 = new Person("Person4",true);
		people.add(person4);
		Person person5 = new Person("Person5",true);
		people.add(person5);
		
        Party party = new Party();
        party.setRations(Rations.MEAGER); // set rations to meager
		party.getInventory().setFood(100); // set food to 100
		party.setWeight(100); // so weight must be 100
		party.setParty(people); // sets the people in the party
		GameEngine engine = new GameEngine(party, null); // make a GameEngine
		
        try {
        	// gets all the methods for engine
        	final Method[] methods = engine.getClass().getDeclaredMethods();
        	// goes through each method
    		for (int i=0; i<methods.length; i++) {
    			// finds updateFood
    			if (methods[i].getName().equals("updateFood")) {
    				methods[i].setAccessible(true);
    				methods[i].invoke(engine); // calls updateFood on engine
    			}
    		}
		} catch (Exception e) {
			e.printStackTrace();
		}
        party = engine.getParty(); // refreshes the party
        
        // makes sure food and weight were correctly updated
        assertEquals(90, party.getInventory().getFood());
        assertEquals(90, party.getWeight());
	}
	
	/**
	 * Testing the GameEnginge's updateFood method on Party with Rations
	 * set to NORMAL and 5 Party members alive.
	 */
	@Test
	public void updateFoodRationsNormalTest() {
		// makes an array of People to give to the party
		ArrayList<Person> people = new ArrayList<Person>();
		Person person1 = new Person("Person1",true);
		people.add(person1);
		Person person2 = new Person("Person2",false);
		people.add(person2);
		Person person3 = new Person("Person3",true);
		people.add(person3);
		Person person4 = new Person("Person4",true);
		people.add(person4);
		Person person5 = new Person("Person5",true);
		people.add(person5);
		
        Party party = new Party();
        party.setRations(Rations.NORMAL); // set rations to normal
		party.getInventory().setFood(100); // set food to 100
		party.setWeight(100); // so weight must be 100
		party.setParty(people); // sets the people in the party
		GameEngine engine = new GameEngine(party, null); // make a GameEngine
		
        try {
        	// gets all the methods for engine
        	final Method[] methods = engine.getClass().getDeclaredMethods();
        	// goes through each method
    		for (int i=0; i<methods.length; i++) {
    			// finds updateFood
    			if (methods[i].getName().equals("updateFood")) {
    				methods[i].setAccessible(true);
    				methods[i].invoke(engine); // calls updateFood on engine
    			}
    		}
		} catch (Exception e) {
			e.printStackTrace();
		}
        party = engine.getParty(); // refreshes the party
        
        // makes sure food and weight were correctly updated
        assertEquals(85, party.getInventory().getFood());
        assertEquals(85, party.getWeight());
	}
	
	/**
	 * Testing the GameEnginge's updateFood method on Party with Rations
	 * set to WELL_FED and 5 Party members alive.
	 */
	@Test
	public void updateFoodRationsWellFedTest() {
		// makes an array of People to give to the party
		ArrayList<Person> people = new ArrayList<Person>();
		Person person1 = new Person("Person1",true);
		people.add(person1);
		Person person2 = new Person("Person2",false);
		people.add(person2);
		Person person3 = new Person("Person3",true);
		people.add(person3);
		Person person4 = new Person("Person4",true);
		people.add(person4);
		Person person5 = new Person("Person5",true);
		people.add(person5);
		
        Party party = new Party();
        party.setRations(Rations.WELL_FED); // set rations to well fed
		party.getInventory().setFood(100); // set food to 100
		party.setWeight(100); // so weight must be 100
		party.setParty(people); // sets the people in the party
		GameEngine engine = new GameEngine(party, null); // make a GameEngine
		
        try {
        	// gets all the methods for engine
        	final Method[] methods = engine.getClass().getDeclaredMethods();
        	// goes through each method
    		for (int i=0; i<methods.length; i++) {
    			// finds updateFood
    			if (methods[i].getName().equals("updateFood")) {
    				methods[i].setAccessible(true);
    				methods[i].invoke(engine); // calls updateFood on engine
    			}
    		}
		} catch (Exception e) {
			e.printStackTrace();
		}
        party = engine.getParty(); // refreshes the party
        
     // makes sure food and weight were correctly updated
        assertEquals(75, party.getInventory().getFood());
        assertEquals(75, party.getWeight());
	}
	
	/**
	 * Testing the GameEnginge's updateFood method on Party with Rations
	 * set to NORMAL and 4 Party members alive.
	 */
	@Test
	public void updateFoodFourMembersTest() {
		// makes an array of People to give to the party
		ArrayList<Person> people = new ArrayList<Person>();
		Person person1 = new Person("Person1",true);
		people.add(person1);
		Person person2 = new Person("Person2",false);
		people.add(person2);
		Person person3 = new Person("Person3",true);
		people.add(person3);
		Person person4 = new Person("Person4",true);
		people.add(person4);
		Person person5 = new Person("Person5",true);
		people.add(person5);
		person5.setHealth(Health.DEAD);
		
        Party party = new Party();
        party.setRations(Rations.NORMAL); // set rations to normal
		party.getInventory().setFood(100); // set food to 100
		party.setWeight(100); // so weight must be 100
		party.setParty(people); // sets the people in the party
		GameEngine engine = new GameEngine(party, null); // make a GameEngine
		
        try {
        	// gets all the methods for engine
        	final Method[] methods = engine.getClass().getDeclaredMethods();
        	// goes through each method
    		for (int i=0; i<methods.length; i++) {
    			// finds updateFood
    			if (methods[i].getName().equals("updateFood")) {
    				methods[i].setAccessible(true);
    				methods[i].invoke(engine); // calls updateFood on engine
    			}
    		}
		} catch (Exception e) {
			e.printStackTrace();
		}
        party = engine.getParty(); // refreshes the party
        
     // makes sure food and weight were correctly updated
        assertEquals(88, party.getInventory().getFood());
        assertEquals(88, party.getWeight());
	}
	
	/**
	 * Testing the GameEnginge's updateFood method on Party with Rations
	 * set to NORMAL and 3 Party members alive.
	 */
	@Test
	public void updateFoodThreeMembersTest() {
		// makes an array of People to give to the party
		ArrayList<Person> people = new ArrayList<Person>();
		Person person1 = new Person("Person1",true);
		people.add(person1);
		Person person2 = new Person("Person2",false);
		people.add(person2);
		Person person3 = new Person("Person3",true);
		people.add(person3);
		Person person4 = new Person("Person4",true);
		people.add(person4);
		Person person5 = new Person("Person5",true);
		people.add(person5);
		person5.setHealth(Health.DEAD);
		person4.setHealth(Health.DEAD);
		
        Party party = new Party();
        party.setRations(Rations.NORMAL); // set rations to normal
		party.getInventory().setFood(100); // set food to 100
		party.setWeight(100); // so weight must be 100
		party.setParty(people); // sets the people in the party
		GameEngine engine = new GameEngine(party, null); // make a GameEngine
		
        try {
        	// gets all the methods for engine
        	final Method[] methods = engine.getClass().getDeclaredMethods();
        	// goes through each method
    		for (int i=0; i<methods.length; i++) {
    			// finds updateFood
    			if (methods[i].getName().equals("updateFood")) {
    				methods[i].setAccessible(true);
    				methods[i].invoke(engine); // calls updateFood on engine
    			}
    		}
		} catch (Exception e) {
			e.printStackTrace();
		}
        party = engine.getParty(); // refreshes the party
        
     // makes sure food and weight were correctly updated
        assertEquals(91, party.getInventory().getFood());
        assertEquals(91, party.getWeight());
	}
	
	/**
	 * Testing the GameEnginge's updateFood method on Party with Rations
	 * set to NORMAL and 2 Party members alive.
	 */
	@Test
	public void updateFoodTwoMembersTest() {
		// makes an array of People to give to the party
		ArrayList<Person> people = new ArrayList<Person>();
		Person person1 = new Person("Person1",true);
		people.add(person1);
		Person person2 = new Person("Person2",false);
		people.add(person2);
		Person person3 = new Person("Person3",true);
		people.add(person3);
		Person person4 = new Person("Person4",true);
		people.add(person4);
		Person person5 = new Person("Person5",true);
		people.add(person5);
		person5.setHealth(Health.DEAD);
		person4.setHealth(Health.DEAD);
		person3.setHealth(Health.DEAD);
		
        Party party = new Party();
        party.setRations(Rations.NORMAL); // set rations to normal
		party.getInventory().setFood(100); // set food to 100
		party.setWeight(100); // so weight must be 100
		party.setParty(people); // sets the people in the party
		GameEngine engine = new GameEngine(party, null); // make a GameEngine
		
        try {
        	// gets all the methods for engine
        	final Method[] methods = engine.getClass().getDeclaredMethods();
        	// goes through each method
    		for (int i=0; i<methods.length; i++) {
    			// finds updateFood
    			if (methods[i].getName().equals("updateFood")) {
    				methods[i].setAccessible(true);
    				methods[i].invoke(engine); // calls updateFood on engine
    			}
    		}
		} catch (Exception e) {
			e.printStackTrace();
		}
        party = engine.getParty(); // refreshes the party
        
     // makes sure food and weight were correctly updated
        assertEquals(94, party.getInventory().getFood());
        assertEquals(94, party.getWeight());
	}
	
	/**
	 * Testing the GameEnginge's updateFood method on Party with Rations
	 * set to NORMAL and 1 Party members alive.
	 */
	@Test
	public void updateFoodOneMembersTest() {
		// makes an array of People to give to the party
		ArrayList<Person> people = new ArrayList<Person>();
		Person person1 = new Person("Person1",true);
		people.add(person1);
		Person person2 = new Person("Person2",false);
		people.add(person2);
		Person person3 = new Person("Person3",true);
		people.add(person3);
		Person person4 = new Person("Person4",true);
		people.add(person4);
		Person person5 = new Person("Person5",true);
		people.add(person5);
		person5.setHealth(Health.DEAD);
		person4.setHealth(Health.DEAD);
		person3.setHealth(Health.DEAD);
		person2.setHealth(Health.DEAD);
		
        Party party = new Party();
        party.setRations(Rations.NORMAL); // set rations to normal
		party.getInventory().setFood(100); // set food to 100
		party.setWeight(100); // so weight must be 100
		party.setParty(people); // sets the people in the party
		GameEngine engine = new GameEngine(party, null); // make a GameEngine
		
        try {
        	// gets all the methods for engine
        	final Method[] methods = engine.getClass().getDeclaredMethods();
        	// goes through each method
    		for (int i=0; i<methods.length; i++) {
    			// finds updateFood
    			if (methods[i].getName().equals("updateFood")) {
    				methods[i].setAccessible(true);
    				methods[i].invoke(engine); // calls updateFood on engine
    			}
    		}
		} catch (Exception e) {
			e.printStackTrace();
		}
        party = engine.getParty(); // refreshes the party
        
     // makes sure food and weight were correctly updated
        assertEquals(97, party.getInventory().getFood());
        assertEquals(97, party.getWeight());
	}
	
	/**
	 * Testing the severStormCheck method of EventHandler.
	 * This test checks the loss of food on a party after encountering a storm.
	 * by Sean Cleary
	 */
	@Test
	public void severeStormTest()
	{
		//create party
		ArrayList<Person> people = new ArrayList<Person>();
		Person person1 = new Person("Person1",true);
		people.add(person1);
		Person person2 = new Person("Person2",false);
		people.add(person2);
		Person person3 = new Person("Person3",true);
		people.add(person3);
		Person person4 = new Person("Person4",true);
		people.add(person4);
		Person person5 = new Person("Person5",true);
		people.add(person5);
		for(int i = 1; i < people.size(); i++)
			people.get(i).setHealth(Health.DEAD);
		Party party = new Party();
		party.setParty(people);
		party.setRations(Rations.NORMAL);
		
		//testing no food
		
		party.getInventory().setFood(0);
		EventReturnType returnVal = new EventReturnType(0);
		EventHandler e = new EventHandler(party);
		do
		{
			returnVal = e.severeStormCheck(party, null);
		}while(!returnVal.isBad());
		assertEquals(true, returnVal.isBad());
		assertEquals(0, returnVal.getFood()); //food is still 0
		
		//testing little food
		
		party.getInventory().setFood(1);
		do
		{
			returnVal = e.severeStormCheck(party, null);
		}while(!returnVal.isBad());
		assertEquals(true, returnVal.isBad());
		assertEquals(party.getInventory().getFood(), returnVal.getFood()); //food lost is equal to remaining food
		
		//testing rations variable
		
		//normal
		party.getInventory().setFood(12);
		do
		{
			returnVal = e.severeStormCheck(party, null);
		}while(!returnVal.isBad());
		assertEquals(true, returnVal.isBad());
		assertEquals(true, returnVal.getFood() != 0); //food was taken
		assertEquals(0, returnVal.getFood() % 3); //food is 3*(number of days) pounds less
		//which will leave it at 9, 6, or 3 , which all %3 = 0
		
		//well-fed
		party.setRations(Rations.WELL_FED);
		party.getInventory().setFood(20);
		do
		{
			returnVal = e.severeStormCheck(party, null);
		}while(!returnVal.isBad());
		assertEquals(true, returnVal.isBad());
		assertEquals(true, returnVal.getFood() != 0); //food was taken
		assertEquals(0, returnVal.getFood() % 5); //food is 5*(number of days) pounds less
		
		//none
		party.setRations(Rations.NONE);
		party.getInventory().setFood(20);
		do
		{
			returnVal = e.severeStormCheck(party, null);
		}while(!returnVal.isBad());
		assertEquals(true, returnVal.isBad());
		assertEquals(0, returnVal.getFood()); //food is not taken
		
		//testing party size variable
		
		party.setRations(Rations.NORMAL);
		for(int i = 0; i < people.size(); i++)
			party.getParty().get(i).setHealth(Health.HEALTHY);
		party.getInventory().setFood(90);
		do
		{
			returnVal = e.severeStormCheck(party, null);
		}while(!returnVal.isBad());
		assertEquals(true, returnVal.isBad());
		assertEquals(true, returnVal.getFood() > 0); //food more than 0
		assertEquals(true, returnVal.getFood() % 5 == 0); //and is a multiple of 5(number of party members) 
		assertEquals(true, returnVal.getFood() % 3 == 0);//and 3 (rations)
		
		
	}
		
}


