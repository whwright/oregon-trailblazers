package edu.gatech.cs2340.oregontb.gamelogic;

import java.io.Serializable;

/**
 * The Inventory class stores the number of items in a Party's or Store's inventory. Each item has 4 methods:
 * a getter, a setter, and an add and remove method.
 */
public class Inventory implements Serializable{
	// ITEMS:
	protected int oxen;
	protected int food;
	protected int clothing;
	protected int ammunition;
	protected int wheels;
	protected int axles;
	protected int tongues;
	
	public Inventory() {
		oxen = 0;
		food = 0;
		clothing = 0;
		ammunition = 0;
		wheels = 0;
		axles = 0;
		tongues = 0;
	}
	
	public void addToInventory(Inventory inventory) {
		addOxen(inventory.getOxen());
		addFood(inventory.getFood());
		addClothing(inventory.getClothing());
		addAmmo(inventory.getAmmo());
		addWheel(inventory.getWheels());
		addAxle(inventory.getAxles());
		addTongues(inventory.getTongues());
		
	}
	
	public void add(ItemType item, int quantity)
	{
		if (item == ItemType.OXEN)
			oxen += quantity;
		else if (item == ItemType.FOOD)
			food += quantity;
		else if (item == ItemType.CLOTHING)
			clothing += quantity;
		else if (item == ItemType.WHEEL)
			wheels +=quantity;
		else if (item == ItemType.AXLE)
			axles += quantity;
		else if (item == ItemType.TONGUE)
			tongues += quantity;
		else if (item == ItemType.AMMO)
			ammunition += quantity;
	}
	
	// OXEN
	public int getOxen() {
		return this.oxen;
	}
	
	public void setOxen(int x) {
		this.oxen = x;
	}
	
	public void addOxen(int x) {
		this.oxen += x;
	}
	
	public void removeOxen(int x) {
		this.oxen -= x;
	}
	
	// FOOD
	public int getFood() {
		return this.food;
	}
	
	public void setFood(int x) {
		this.food = x;
	}
	
	public void addFood(int x) {
		this.food += x;
	}
	
	public void removeFood(int x) {
		this.food -= x;
	}
	
	// CLOTHING
	public int getClothing() {
		return this.clothing;
	}
	
	public void setClothing(int x) {
		this.clothing = x;
	}
	
	public void addClothing(int x) {
		this.clothing += x;
	}
	
	public void removeClothing(int x) {
		this.clothing -= x;
	}
	
	// AMMO
	public int getAmmo() {
		return this.ammunition;
	}
	
	public void setAmmo(int x) {
		this.ammunition = x;
	}
	
	public void addAmmo(int x) {
		this.ammunition += x;
	}
	
	public void removeAmmo(int x) {
		this.ammunition -= x;
	}
	
	//WHEELS
	public int getWheels() {
		return this.wheels;
	}
	
	public void setWheels(int x) {
		this.wheels = x;
	}
	
	public void addWheel(int x) {
		this.wheels += x;
	}
	
	public void removeWheel(int x) {
		this.wheels -= x;
	}
	
	//AXLES
	public int getAxles() {
		return this.axles;
	}
	
	public void setAxles(int x) {
		this.axles = x;
	}
	
	public void addAxle(int x) {
		this.axles += x;
	}
	
	public void removeAxle(int x) {
		this.axles -= x;
	}
	
	//TONGUES
	public int getTongues() {
		return this.tongues;
	}
	
	public void setTongues(int x) {
		this.tongues = x;
	}
	
	public void addTongues(int x) {
		this.tongues += x;
	}
	
	public void removeTongues(int x) {
		this.tongues -= x;
	}
	
	/**
	 * toString method used to demo M6 -sean g.
	 */
	public String toString() {
		String output = "";
		output += "Oxen: " + oxen + "\n";
		output += "Food: " + food + "\n";
		output += "Clothing: " + clothing + "\n";
		output += "Ammo: " + ammunition + "\n";
		output += "Wheels: " + wheels + "\n";
		output += "Axles: " + axles + "\n";
		output += "Tongues: " + tongues + "\n";
		return output;
	}
}
