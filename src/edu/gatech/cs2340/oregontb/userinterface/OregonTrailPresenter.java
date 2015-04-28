package edu.gatech.cs2340.oregontb.userinterface;

import edu.gatech.cs2340.oregontb.gamelogic.*;
import edu.gatech.cs2340.oregontb.huntinggame.*;

import javax.swing.*;
import java.awt.*;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

/**
 * OregonTrailPresenter is the intermediary class between the game logic and the game's user interface. It is responsible
 * for all interactions between the user interface and the game engine.
 * @author Oregon Trailblazers (Michael Sandt, Harrison Wright, Casey Tisdel, Sean Cleary, Sean Gillespie)
 */
public class OregonTrailPresenter {

	// These strings are used by the CardLayout to keep track of panels. The
	// switchToPanel method uses these strings to determine which panel to switch to.
	public static final String START_PANEL = "start";
	public static final String PARTY_PANEL = "party";
	public static final String CREDIT_PANEL = "credit";
	public static final String STORE_PANEL = "store";
	public static final String MAIN_PANEL = "main";
	//public static final String PARTY_VIEW_PANEL = "partyView";
	public static final String MAP_PANEL = "map";
	public static final String LOAD_GAME_PANEL = "load";
	public static final String HUNTING_PANEL = "hunting";
	
	private JPanel containerPanel;
	private GameEngine gameEngine;

	private IPartyConfigScreenView partyPanel;
	private StartPanel startPanel;
	private CreditsPanel creditPanel;
	private IStorePanel storePanel;
	private MainPanel mainPanel;
	private LoadGamePanel loadGamePanel;
	private HuntingPanel huntingPanel;
	private Random rand;
	
	/**
	 * The OregonTrailPresenter constructor is responsible for the initialization of all Panels and the GameEngine
	 * to be used in this program.
	 * @throws IOException 
	 */
	public OregonTrailPresenter() {
		// Frame and containerPanel initialization is left to the main method.
		containerPanel = null;
		this.rand = new Random();

		// The game engine is created, and a new party is associated with it.
		gameEngine = new GameEngine(new Party(), this);

		// All of the JPanels used in this program are initialized here.
		// ALL JPANELS GO HERE
		startPanel = new StartPanel(this);
		partyPanel = new PartyConfigPanel(this);
		creditPanel = new CreditsPanel(this);
		storePanel = new StorePanel(this, new Store(1, gameEngine.getParty()));
		mainPanel = new MainPanel(this, gameEngine.getParty(), gameEngine.getList());
		loadGamePanel = new LoadGamePanel(this);
		huntingPanel = new HuntingPanel(this, gameEngine.getParty());
		// END JPANELS

	}

	/**
	 * This method creates a container panel with a card layout, and adds all of
	 * the other panels (start, party, store, etc) to said panel.
	 * @return containerPanel
	 */
	public JPanel addAllPanelsToContainer() {
		// ALL JPANELS MUST BE ADDED TO THIS PANEL
		// IMPORTANT: The syntax for adding a new panel is (JPanel, String),
		// where the string is used to reference the panel in the CardLayout.
		containerPanel = new JPanel(new CardLayout());
		// ADD ALL JPANELS
		containerPanel.add(huntingPanel, HUNTING_PANEL); //0
		containerPanel.add(startPanel, START_PANEL); //1
		containerPanel.add((Component) partyPanel, PARTY_PANEL); //2
		containerPanel.add(creditPanel, CREDIT_PANEL); //3
		containerPanel.add(mainPanel, MAIN_PANEL); //4
		containerPanel.add(loadGamePanel, LOAD_GAME_PANEL); //5
		// STORE PANEL NEEDS TO BE THE LAST PANEL FOR ADD/REMOVE
		// *** IF YOU ADD ANOTHER PANEL YOU NEED TO CHANGE THE LINE OF CODE IN switchToStore!!! ******
		containerPanel.add((Component) storePanel, STORE_PANEL); //6
		// END ADDING JPANELS

		switchToPanel(START_PANEL);
		return containerPanel;
	}

	/**
	 * SwitchToPanel switches the current panel to another. The panel that gets
	 * switched to depends on the string.
	 * @param panel
	 */
	public void switchToPanel(String panel) {
		CardLayout c1 = (CardLayout) containerPanel.getLayout();
		c1.show(containerPanel, panel);
	}
	
	/**
	 * Called when the user pressed "Go" in PartyConfigPanel. Takes all the info from the panel
	 * and assigns it to the Party. Will not continue if information is missing.
	 * @param profession
	 * @param pace
	 * @param rations
	 */
	public void partyGoButtonPressed(Profession profession, Pace pace, Rations rations) {
		// gets other party members names
		List<String> names = partyPanel.getOtherPartyNames();
		List<Person> people = new ArrayList<Person>();		
		// adds leader to party
		people.add(new Person(partyPanel.getName(), true));
		// adds others to party
		for (String name : names) {
			people.add(new Person(name, false));
		}
		gameEngine.getParty().setParty(people); // sets the party
		gameEngine.getParty().setMoney(profession.getStartingMoney()); // sets starting money
		gameEngine.getParty().setProfession(profession); // set profession
		gameEngine.getParty().setPace(pace); // set pace
		gameEngine.getParty().setRations(rations); // set rations
		gameEngine.getParty().setInventory(new Inventory()); // set inventory
		gameEngine.getParty().setLocationNumber(0); // set locationNumber
		int distanceToNextLocation = gameEngine.getList().getLocation(0).getDistance(); // get distanceToNextLocation
		gameEngine.getParty().setDistanceToNextLocation(distanceToNextLocation); // set distanceToNextLocation
		gameEngine.getParty().getInventory().addTongues(1);
		gameEngine.getParty().getInventory().addWheel(4);
		gameEngine.getParty().getInventory().addAxle(2);
		
		// checks to make sure every text field has a name input
		boolean namesReady = true;
		if (partyPanel.getName().equals(""))
			namesReady = false;
		for (String name : names)
		{
			if (name.equals(""))
				namesReady = false;
		}
		
		int choice = -1;
		// if names are filled in
		if (namesReady) {
			// makes sure the user is ready, because there is no JRadioButtons to check
			Object[] options = { "Yes, I'm ready", "No, go back" };
			choice = JOptionPane.showOptionDialog(null,"Do you wish to continue?", "Are you sure?", JOptionPane.YES_NO_OPTION,
					JOptionPane.QUESTION_MESSAGE, null, options, options[1]);	
			
			// if they choose yes
			if (choice == 0) {
				initializeStorePanel(); // initialize panel
				switchToPanel(STORE_PANEL); // switch to panel
			}
		}
		// if they aren't
		else {
			// random names from south park
			String[] randomNames = { "Kenny", "Stan", "Kyle", "Cartman", "Bebe", "Butters", "Clyde", "Craig", "Jimmy",
					"Timmy", "Tweak", "Wendy", "Ike", "Mr. Garrison", "Mr. Mackey", "Mr. Slave",
					"Officer Barbrady", "Mr. Hankey", "Father Maxi", "Dr. Mephisto", "Starvin' Marvin",
					"Terrance", "Phillip", "Towelie", "Chef", "Pip", "Mr. Hat", "Artemis Clyde Frog", 
					"Polly Prissy Pants", "Rumpeltuskin" };
			Object[] options = { "Yes", "No" };
			// prompt user
			choice = JOptionPane.showOptionDialog(null, "You have not entered enough names for your party. " +
					"Would you like random names for the rest?\nIf you do not, please choose no and enter the names yourself.", 
					"There are names missing.",	JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE, null, options, options[1]);
			// if they choose yes
			if (choice == 0) {
				int random = rand.nextInt(30); // get a random number
				people =  new ArrayList<Person>(); // remake array list
				String tempName;
				if (partyPanel.getName().equals("")) { // if the leader doesnt have a name
					tempName = randomNames[random]; // get a name for the Leader, if it isn't filled in
					while (tempName == null) { // make sure it did not get a null pointer in the list 
						random = rand.nextInt(30);
						tempName = randomNames[random];
					}
					randomNames[random] = null; // remove the name from the list so its not repeated
					people.add(new Person(tempName, true)); // add the leader
				}
				else {
					people.add(new Person(partyPanel.getName(), true)); // if there is a name for the leader, add it as normal
				}
				for (String name : names) // for each companion name
				{
					random = rand.nextInt(30); // get a random number
					if (name.equals("")) { // if there isnt a name
						tempName = randomNames[random]; // get a random name
						while (tempName == null) { // make sure its not empty
							random = rand.nextInt(30);
							tempName = randomNames[random];
						}
						randomNames[random] = null; // remove the name
						people.add(new Person(tempName, false)); // add the person
					}
					else {
						people.add(new Person(name, false)); // add the person if there is a name for them
					}
				}
				gameEngine.getParty().setParty(people); // set the party
				initializeStorePanel(); // initialize the panel
				switchToPanel(STORE_PANEL); // switch to panel
			}
		}
	}

	/**
	 * Initializes the storePanel's labels
	 */
	public void initializeStorePanel() {
		// updates the users money and weight
		storePanel.setCurrentMoney(gameEngine.getParty().getMoney());
		storePanel.setCurrentWeight(gameEngine.getParty().getWeight());
		// updates all the item's price's
		storePanel.setOxenPrice();
		storePanel.setClothingPrice();
		storePanel.setFoodPrice();
		storePanel.setAmmoPrice();
		storePanel.setWheelPrice();
		storePanel.setTonguePrice();
		storePanel.setAxlePrice();
		// clears the JTextFields
		storePanel.clearFields();
	}

	
	/**
	 * Steps the game one turn
	 */
	public void step() {
		gameEngine.step();
	}
	
	/**
	 * Refreshes the mainPanel and switches to it
	 */
	public void switchToMain() {
		mainPanel.initialize();
		switchToPanel(MAIN_PANEL);
	}
	
	/**
	 * Asks the user if they want to enter the store, and switches to StorePanel if they say yes.
	 * @param priceScaleFactor taken from the location where the store is
	 */
	public void switchToStore(double priceScaleFactor, String name) {
		Object[] options = {"Yes, please", "No, thanks"};
		int choice = JOptionPane.showOptionDialog(null, "You have reached "+name+" where there is a store, would you like to shop for supplies?", 
				"You have reached a store!", JOptionPane.YES_NO_OPTION,  JOptionPane.QUESTION_MESSAGE, null, options, options[1]);
		
		if (choice == 0) {
			// *** IF YOU ADD ANOTHER PANEL YOU NEED TO INCREMENT THE INDEX THAT IS BEING REMOVED!!! ***
			containerPanel.remove(6);
			storePanel = new StorePanel(this, new Store(priceScaleFactor, gameEngine.getParty()));
			initializeStorePanel();
			containerPanel.add((Component) storePanel, STORE_PANEL);
			switchToPanel(STORE_PANEL);			
		}
	}
	
	/**
	 * Asks the user what they wish to do when reaching a RiverLocation, and passes the answer to the crossRiver method
	 * @param isFerry
	 * @param minDepth
	 * @param maxDepth
	 * @param name
	 */
	public void RiverCrossing(boolean isFerry, int minDepth, int maxDepth, String name) {
		int choice;
		String reached = "You have reached "+name+" and must cross it. ";
		// can ferry
		if (isFerry) {
			// can ford
			if (minDepth <= 3) {
				// ask user
				Object[] options = {"Caulk the wagon and risk crossing", "Take the ferry", "Ford the river",  };
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
				Object[] options = { "Caulk the wagon", "Take the ferry", };
				choice = JOptionPane.showOptionDialog(null, reached+"You can safely take the ferry " +
						"for a small fee, or take your chances caulking the wagon and floating across.","You have reached a river!", 
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
				Object[] options = {"Caulk the wagon", "Ford the river",  };
				choice = JOptionPane.showOptionDialog(null, reached+"You can take your chances fording " +
						"the river, or take your chances caulking the wagon and floating across.","You have reached a river!", 
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
				Object[] options = {"Caulk the wagon" };
				choice = JOptionPane.showOptionDialog(null, reached+"You must take your chances caulking " +
						"your wagon.","You have reached a river!", 
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
	public void switchToLoadGamePanel() {
		loadGamePanel.initialize(0);
		switchToPanel(OregonTrailPresenter.LOAD_GAME_PANEL);
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
		if (choice.equals("Caulk the wagon")) {
			EventReturnType result = gameEngine.getHandler().wagonFlipCheck(gameEngine.getParty(), "caulk");
			if (!result.isBad()) {
				// nothing bad happened!
				JOptionPane.showMessageDialog(null, "You successfully crossed the river!");
				return;
			}
			else {
				gameEngine.getParty().getInventory().removeFood(result.getFood()); // remove lost food
				gameEngine.getParty().getInventory().removeClothing(result.getClothing()); // remove lost clothing
				gameEngine.getParty().getInventory().removeAxle(result.getAxles()); // remove lost axles
				if (result.getDeceased() != null) {
					List<Person> pList = gameEngine.getParty().getParty();
					pList.get(pList.indexOf(result.getDeceased())).setHealth(Health.DEAD); // mark the dead person as dead
				}
				JOptionPane.showMessageDialog(null, "While attempting to caulk your wagon and float across the river, your wagon tipped! You lost\n" + result.toString());
				return;
			}
			
		}
		// ford
		else if (choice.equals("Ford the river")) {
			EventReturnType result = gameEngine.getHandler().wagonFlipCheck(gameEngine.getParty(), "ford");
			if (!result.isBad()) {
				// nothing bad happened!
				JOptionPane.showMessageDialog(null, "You successfully forded the river!");
				return;
			}
			else {
				gameEngine.getParty().getInventory().removeFood(result.getFood()); // remove lost food
				gameEngine.getParty().getInventory().removeClothing(result.getClothing()); // remove lost clothing
				gameEngine.getParty().getInventory().removeAxle(result.getAxles()); // remove lost axles
				if (result.getDeceased() != null) {
					List<Person> pList = gameEngine.getParty().getParty();
					pList.get(pList.indexOf(result.getDeceased())).setHealth(Health.DEAD); // mark the dead person as dead
				}
				JOptionPane.showMessageDialog(null, "While attempting to ford the river, your wagon tipped! You lost\n" + result.toString());
				return;
			}
		}
		// ferry
		else if (choice.equals("Take the ferry")) { 
			// checks to make sure the Party has $50
			if (gameEngine.getParty().getMoney() < 50) {
				// will notify user and try again if not
				JOptionPane.showMessageDialog(null, "You do not have enough money to take the ferry.");
				RiverCrossing(isFerry, minDepth, maxDepth, name);
			}
			// subtracts 50 and tells the party
			else {
				gameEngine.getParty().setMoney(gameEngine.getParty().getMoney() - 50);
				JOptionPane.showMessageDialog(null, "Congratulations, you have made it across the river safely!");
			}
		}
		}
		

	public void loadGame(Party party, String saveName) {
		gameEngine.setParty(party);
		huntingPanel.setParty(party);
		mainPanel.setParty(party);
		mainPanel.setFirstSave(false);
		mainPanel.setSaveName(saveName);
		mainPanel.initialize();
		switchToMain();
		JOptionPane.showMessageDialog(null, "Your game has been loaded from your last save.\n Please remember to save again before exiting.");
	}
	
	public void switchToHuntPanel() {
		huntingPanel.initialize(gameEngine.getParty().getInventory().getAmmo());
	}
	

}
