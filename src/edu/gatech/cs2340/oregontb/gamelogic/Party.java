package edu.gatech.cs2340.oregontb.gamelogic;

import java.util.List;
import java.io.*;

/**
 * The Party class stores data that is related to the party: location, inventory, rations, pace, etc.
 * There are getters and setters for each piece of data.
 */
public class Party implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private int locationNumber;
	private int distanceToNextLocation;
	private Inventory inventory;
	private Rations rations;
	private Pace pace;
	private List<Person> party;
	private int money;	
	private int weight;
	public static final int MAX_WEIGHT=3500;
	private Profession profession; 
	private int totalDistanceTraveled;
	private int tiredOxen;
	
	public Party() {
		locationNumber = 0;
		distanceToNextLocation = 0;
		inventory = new Inventory();
		rations = null;
		pace = null;
		party = null;
		money = 0;
		weight = 0;
		profession = null;
		totalDistanceTraveled = 0;
		tiredOxen = 0;
	}

	public int getLocationNumber() {
		return locationNumber;
	}

	public void setLocationNumber(int locationNumber) {
		this.locationNumber = locationNumber;
	}

	public int getDistanceToNextLocation() {
		return distanceToNextLocation;
	}

	public void setDistanceToNextLocation(int distanceToNextLocation) {
		this.distanceToNextLocation = distanceToNextLocation;
	}

	public Inventory getInventory() {
		return inventory;
	}

	public void setInventory(Inventory inventory) {
		this.inventory = inventory;
	}

	public Rations getRations() {
		return rations;
	}

	public void setRations(Rations rations) {
		this.rations = rations;
	}

	public Pace getPace() {
		return pace;
	}

	public void setPace(Pace pace) {
		this.pace = pace;
	}

	public List<Person> getParty() {
		return party;
	}
	
	public String getPartyLeader() {
		return party.get(0).getName();
	}

	public void setParty(List<Person> party) {
		this.party = party;
	}

	public int getMoney() {
		return money;
	}

	public void setMoney(int money) {
		this.money = money;
	}

	public int getWeight() {
		return weight;
	}

	public void setWeight(int weight) {
		if (weight <= MAX_WEIGHT)
			this.weight = weight;
		else
			this.weight = MAX_WEIGHT;
	}

	public Profession getProfession() {
		return profession;
	}

	public void setProfession(Profession profession) {
		this.profession = profession;
	}
	
	public int getMaxWeight()
	{
		return MAX_WEIGHT;
	}
	
	public int getTotalDistanceTraveled() {
		return totalDistanceTraveled;
	}

	public void setTotalDistanceTraveled(int totalDistanceTraveled) {
		this.totalDistanceTraveled = totalDistanceTraveled;
	}
	
	public void setTiredOxen(int tiredOxen)
	{
		this.tiredOxen = tiredOxen;
	}
	
	public int getTiredOxen()
	{
		return tiredOxen;
	}
	
	/**
	 * GetNumberOfAlivePartyMembers checks the party to see how many people are still alive, and returns the number
	 * of party members still alive.
	 * @return The number of party members still alive
	 */
	public int getNumberOfAlivePartyMembers() {
		int count = 0;
		for (Person person: party) {
			if (person.getHealth() != Health.DEAD)
				count++;
		}
		return count;
	}
	
	public int getNumberOfIllOrDeadPartyMembers() {
		int count = 0;
		for (Person person: party) {
			if (person.getHealth() != Health.HEALTHY)
				count++;
		}
		return count;
	}

	/**
	 * This method prints out all of the party's data, and is used to TEST data entry.
	 */
	public void print()
	{
		System.out.println("locationNumber: " + locationNumber);
		System.out.println("distanceToNextLocation: " + distanceToNextLocation);
		System.out.println("Inventory: " + inventory);
		System.out.println("Rations: " + rations);
		System.out.println("Pace: " + pace);
		System.out.println("Party: " + party);
		System.out.println("Money: " + money);
		System.out.println("Weight: " + weight);
		System.out.println("Profession: " + profession);
		System.out.println("Number of Tired Oxen: " + tiredOxen);
	}
}
