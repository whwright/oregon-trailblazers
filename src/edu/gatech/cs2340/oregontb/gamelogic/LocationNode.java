package edu.gatech.cs2340.oregontb.gamelogic;

public abstract class LocationNode {
	//protected LocationNode nextLocation;
	protected int distanceToNextLocation;
	protected String name;
	
	public LocationNode(String name, int distance) {
		this.name = name;
		distanceToNextLocation = distance;
	}
	
	public int getDistance() {
		return distanceToNextLocation;
	}
	
	public String toString() {
		return name;
	}
}
