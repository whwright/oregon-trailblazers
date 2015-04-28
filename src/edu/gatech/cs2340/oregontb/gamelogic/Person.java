package edu.gatech.cs2340.oregontb.gamelogic;

import java.io.Serializable;

/**
 * The Person class represents a person in the party. They have a name, health, and know whether
 * they are the leader or not. There are getter and setter methods for each.
 */
public class Person implements Serializable{
	private String name;
	private Health health;
	private boolean isLeader;
	
	public Person(String name, boolean isLeader) {
		this.name = name;
		health = Health.HEALTHY;
		this.isLeader = isLeader;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Health getHealth() {
		return health;
	}

	public void setHealth(Health health) {
		this.health = health;
	}

	public boolean isLeader() {
		return isLeader;
	}

	public void setLeader(boolean isLeader) {
		this.isLeader = isLeader;
	}
	
	public String toString() {
		return "{" + name + " " + health + " " + isLeader + "}";
	}
}
