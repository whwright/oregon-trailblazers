package edu.gatech.cs2340.oregontb.huntinggame;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.Random;

import javax.imageio.ImageIO;


/**
 * This represents a player in the hunting game. It keeps track of their bullets and how much meat
 * is obtained from hunting.
 */
public class HuntingGamePlayer {
	private int[] scores;
	private final int BEAR = 0, DEER = 1, BUNNY = 2;
	private BufferedImage icon;
	private int xpos, ypos, width, height;
	private int bullets;
	private Random rand;
	
	/**
	 * HuntingGamePlayer constructor
	 * @param bullets
	 */
	public HuntingGamePlayer(int bullets) {
		this.scores = new int[3];
		this.bullets = bullets;
		this.rand = new Random();
	}
	
	/**
	 * Keeps count of the number of animals killed and what type
	 * @param animal
	 * @param dead
	 */
	public void kill(Animal animal, boolean dead) {
		if (animal instanceof Bear && !dead)
			scores[BEAR]++;
		else if (animal instanceof Deer && !dead)
			scores[DEER]++;
		else if (animal instanceof Bunny && !dead)
			scores[BUNNY]++;
	}
	
	/**
	 * Returns the amount of bullets the player has.
	 * @return int num of bullets
	 */
	public int getBullets() {
		return bullets;
	}
	
	public void setBullets(int ammo) {
		this.bullets = ammo;		
	}
	
	/**
	 * Shoots 1 bullet.
	 */
	public void shoot() {
		bullets--;
	}
	
	/**
	 * Returns an array of scores.
	 * @return int[] array of scores
	 */
	public int[] getScores() {
		return scores;
	}
	
	/**
	 * Returns the amount of Bear kills the Player has.
	 * @return int num of bear kills
	 */
	public int getBearKills() {
		return scores[BEAR];
	}
	
	/**
	 * Returns the amount of Deer kills the Player has.
	 * @return int num of deer kills
	 */
	public int getDeerKills() {
		return scores[DEER];
	}
	
	/**
	 * Returns the amount of Bunny kills the Player has.
	 * @return int num of bunny kills
	 */
	public int getBunnyKills() {
		return scores[BUNNY];
	}
	
	/**
	 * Calculates the amount of meat the player gets at the end of hunting.
	 * @return int pounds of meat
	 */
	public int calculateMeat() {
		// starts meat at 0
		int meat = 0;
		
		// bears are 1-100 pounds
		for(int i=0; i<getBearKills(); i++) {
			int temp = rand.nextInt(100)+1;
			meat += temp;
		}
		// deer at 1-50 pounds
		for(int i=0; i<getDeerKills(); i++) {
			int temp = rand.nextInt(50)+1;
			meat += temp;
		}
		// bunnies are 1-5 pounds
		for(int i=0; i<getBunnyKills(); i++) {
			int temp = rand.nextInt(5)+1;
			meat += temp;
		}
		
		return meat;
	}
	
	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	public String toString() {
		String temp = "";
		temp += "Bears: ";
		temp += scores[BEAR]+", ";
		temp += "Deer: ";
		temp += scores[DEER]+", ";
		temp += "Bunnys: ";
		temp += scores[BUNNY];
		return temp;
	}
	
	

}

