package edu.gatech.cs2340.oregontb.huntinggame;

import edu.gatech.cs2340.oregontb.gamelogic.*;
import edu.gatech.cs2340.oregontb.userinterface.*;

import java.applet.*;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.net.*;
import java.util.*;
import javax.swing.*;
import javax.swing.Timer;

public class HuntingPanel extends JPanel {
	private ArrayList<Animal> animalList;
	private Random rand;
	private final int MAX_X = 800, MAX_Y = 600;
	private HuntingGamePlayer player;
	private JButton returnButton;
	private JLabel bulletLabel;
	private boolean stillHunting;
	private AudioClip song; // Sound player
	private URL songPath;
	private int bear, deer, bunny;
	private Party party;
	private OregonTrailPresenter presenter;
	private Timer timer;

	/**
	 * Creates the panel.
	 */
	public HuntingPanel(OregonTrailPresenter presenter, Party party) {
		setLayout(null);
		setBackground(new Color(0,100,0));
		this.addMouseListener(new MouseControl());
		this.rand = new Random();
		this.presenter = presenter;
		this.party = party;
		this.player = new HuntingGamePlayer(party.getInventory().getAmmo());
		this.stillHunting = true;
		this.animalList = new ArrayList<Animal>();
		this.timer = new Timer(50, new TimerListener());

		// BUTTON AND LABEL
		returnButton = new JButton("Return to Trail");
		returnButton.setBounds(673, 556, 117, 29);
		returnButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				stillHunting = false;				
			}
		});
		add(returnButton);
		
		bulletLabel = new JLabel("Bullets: "+player.getBullets());
		bulletLabel.setBounds(678, 534, 112, 16);
		add(bulletLabel);

		//run();
	}
	
	/**
	 * Initializes the panel before switching.
	 * @param ammo
	 */
	public void initialize(int ammo) {
		this.player = new HuntingGamePlayer(party.getInventory().getAmmo());
		this.stillHunting = true;
		this.animalList = new ArrayList<Animal>();
		repaint();
		player.setBullets(ammo);
		bulletLabel.setText("Bullets: "+player.getBullets());
		
		presenter.switchToPanel(OregonTrailPresenter.HUNTING_PANEL);
		//run();
		timer.start();
	}
	
	/**
	 * Runs the game.
	 */
	public void run() {
		// starting message
		JOptionPane.showMessageDialog(null, "You have reached a nearby field to hunt. You will stay\nhere until " +
				"you run out of ammunition or click Return to Trail.");
		// while the player has bullets or doesn't click return to trail
		while (player.getBullets() > 0 && stillHunting) {
			// creates random animals
			checkForAnimal();
			// moves and updates screen
			repaint();
			update();
			
			timer.setDelay(50);
		}
		endHunting();
	}
	

	/* (non-Javadoc)
	 * @see javax.swing.JComponent#paintComponent(java.awt.Graphics)
	 */
	public void paintComponent(Graphics g) {
		super.paintComponent(g);
		for (int i = 0; i < animalList.size(); i++) {
			Animal temp = animalList.get(i);
			temp.paint((Graphics2D) g);
		}
	}
	
	public void setParty(Party party) {
		this.party = party;
	}

	/**
	 * Moves all of the animals.
	 */
	public void update() {
		for (int i = 0; i < animalList.size(); i++) {
			animalList.get(i).move();
		}
	} 
	
	/**
	 * This method is called when the player runs out of ammo, or they choose to leave. It will notify them
	 * how much they killed, and how much meat they got.
	 */
	private void endHunting() {
		if (stillHunting == false) {
			int meat = player.calculateMeat();
			if (meat > 250) {
				if (party.getWeight()+250 < Party.MAX_WEIGHT) {
					JOptionPane.showMessageDialog(null, "Very successful hunting trip! You killed "+player.getBearKills()+" bears, "+player.getDeerKills()+" deer, and" +
							" "+player.getBunnyKills()+" bunnies. You collected "+meat+" pounds of meat, but you can only carry 250 back to camp.");
					party.getInventory().addFood(250); // add the food
					party.setWeight(party.getWeight()+250); // add the weight
				}
				else {
					int meatDifference = Party.MAX_WEIGHT-party.getWeight();
					JOptionPane.showMessageDialog(null, "Very successful hunting trip! You killed "+player.getBearKills()+" bears, "+player.getDeerKills()+" deer, and" +
							" "+player.getBunnyKills()+" bunnies. You collected "+meat+" pounds of meat, but you can only carry "+meatDifference+" back to camp.");
					party.getInventory().addFood(meatDifference);
					party.setWeight(party.getWeight()+meatDifference);
				}
			}
			else {
				if (party.getWeight()+meat < Party.MAX_WEIGHT) {
					JOptionPane.showMessageDialog(null, "You killed "+player.getBearKills()+" bears, "+player.getDeerKills()+" deer, and" +
							" "+player.getBunnyKills()+" bunnies, and collected "+meat+" pounds of meat to bring back to camp");
					party.getInventory().addFood(meat);
					party.setWeight(party.getWeight()+meat);
					
				}
				else {
					int meatDifference = party.MAX_WEIGHT-party.getWeight();
					JOptionPane.showMessageDialog(null, "You killed "+player.getBearKills()+" bears, "+player.getDeerKills()+" deer, and" +
							" "+player.getBunnyKills()+" bunnies, and collected "+meat+" pounds of meat to bring back to camp");
					party.getInventory().addFood(meatDifference);
					party.setWeight(party.getWeight()+meatDifference);
				}
			}
			
			
		}
		
		else if (player.getBullets() == 0) {
			int meat = player.calculateMeat();
			if (meat > 250) {
				if (party.getWeight()+250 < Party.MAX_WEIGHT) {
					JOptionPane.showMessageDialog(null, "You have run out of ammo! Even though your hunting trip has ended early, you killed "+player.getBearKills()+" " +
							"bears, "+player.getDeerKills()+" deer, and"+player.getBunnyKills()+" bunnies and collected "+meat+" pounds of meat! However, you can " +
									"only carry 250 pounds back to camp");
					party.getInventory().addFood(250); // add the food
					party.setWeight(party.getWeight()+250); // add the weight
				}
				else {
					int meatDifference = Party.MAX_WEIGHT-party.getWeight();
					JOptionPane.showMessageDialog(null, "You have run out of ammo! Even though your hunting trip has ended early, you killed "+player.getBearKills()+" " +
							"bears, "+player.getDeerKills()+" deer, and"+player.getBunnyKills()+" bunnies and collected "+meat+" pounds of meat! However, you can " +
									"only carry 250 pounds back to camp");
					party.getInventory().addFood(meatDifference);
					party.setWeight(party.getWeight()+meatDifference);
					party.getInventory().setAmmo(player.getBullets());
				}
			}
			else {
				if (party.getWeight()+meat < Party.MAX_WEIGHT) {
					JOptionPane.showMessageDialog(null, "You have run out of ammo! However, you killed "+player.getBearKills()+" bears, "+player.getDeerKills()+" deer, and" +
							" "+player.getBunnyKills()+" bunnies and collected "+meat+" pounds of meat to take back to camp.");
					party.getInventory().addFood(meat);
					party.setWeight(party.getWeight()+meat);
				}
				else {
					int meatDifference = party.MAX_WEIGHT-party.getWeight();
					JOptionPane.showMessageDialog(null, "You have run out of ammo! However, you killed "+player.getBearKills()+" bears, "+player.getDeerKills()+" deer, and" +
							" "+player.getBunnyKills()+" bunnies and collected "+meat+" pounds of meat to take back to camp.");
					party.getInventory().addFood(meatDifference);
					party.setWeight(party.getWeight()+meatDifference);
				}
			}
			
		}
		party.getInventory().setAmmo(player.getBullets());
		presenter.switchToMain();
	}
	
	/**
	 * Randomly generates animals.
	 */
	private void checkForAnimal() {
		int x = 0;
		int y = rand.nextInt(MAX_Y)-40;
		int rand1 = rand.nextInt(100);
		String str = null;
		if (rand1 < 50) {
			str = "left";
		  	x = MAX_X-40;
		}
		else if (rand1 >= 50) {
			str = "right";
			x = 0;
		}
		
		double gen = rand.nextGaussian(); // gen is a number from -inf to +inf
		gen = Math.abs(gen); // gen is now a number from 0 to inf		
		if (gen >= 1.9 && gen < 2.1) { //1.19%
			animalList.add(new Bunny(x,y,str));
		}
		if(gen >= 2.1 && gen < 2.2) {  //0.9%
			animalList.add(new Bear(x,y,str));
		}
		if (gen >= 2.2 && gen < 2.3) { 
			animalList.add(new Deer(x,y,str));
		} 
	
	}
	/**
	*
	* @author Mike Sandt
	*
	*         TimerListener, will only run once every 50ms. Replaces the run
	*         method found above.
	*/
	private class TimerListener implements ActionListener {
		public void actionPerformed(ActionEvent arg0) {
			if (player.getBullets() > 0 && stillHunting) {
					checkForAnimal();
					repaint();
					update();
			} else {
					timer.stop();
					endHunting();
			}
		
		}	
	}


	/**
	 * This is called when the mouse is clicked
	 */
	private class MouseControl extends MouseAdapter {
		public void mousePressed(MouseEvent e) {
			// update bullets
			player.shoot();
			bulletLabel.setText("Bullets: "+Integer.toString(player.getBullets()));
		
			// for each animal
			for (int i = 0; i < animalList.size(); i++) {
				Animal temp = animalList.get(i);

				// is the animal hit?
				if (temp.containsPoint(e.getX(), e.getY())) {
					// counts the kill in Player's score
					player.kill(temp, temp.isDead());
					// sets the Animal to dead
					temp.kill();
					break;
				}
			}
		}
		
		/**
		 * Testing method that will print the location of the mouse and the range of each Animal.
		 * @param x
		 * @param y
		 */
		private void printTest(int x, int y) {
			System.out.println("Mouse: ("+x+", "+y+")");
			for( int i=0; i<animalList.size(); i++) {
				System.out.println("Animal "+i+": ( x:["+animalList.get(i).getXPos()+", "+animalList.get(i).getX2()+"], y:["+animalList.get(i).getYPos()+", "+animalList.get(i).getY2()+"] )");
			}
			System.out.println();
			
		}
	}
}

