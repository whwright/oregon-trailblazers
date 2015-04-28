package edu.gatech.cs2340.oregontb.gamelogic;

import java.util.LinkedList;

public class LocationList {
	private LinkedList<LocationNode> locationList;
	private Party party;
	
	public LocationList(Party party) {
		this.locationList = new LinkedList<LocationNode>();
		this.party = party;
		init();
	}
	
	/**
	 * Init constructs the LinkedList upon creation to reflect the locations that will be used in the game.
	 * This needs to be implemented manually.
	 * by Sean Gillespie
	 */
	public void init() 
	{
		locationList.add(new NormalLocation("Independence",102));
		locationList.add(new RiverLocation("Kansas River",83,6,10,true));
		locationList.add(new RiverLocation("Big Blue",119,2,4,false));
		StoreLocation fortKearny = new StoreLocation("Fort Kearny",250);
		// *** DECIDE ON PRICE SCALE FACTOR
		fortKearny.setStore(1, party);
		locationList.add(fortKearny);
		locationList.add(new NormalLocation("Chimney Rock", 86));
		StoreLocation fortLaramie = new StoreLocation("Fort Laramie",190);
		// *** DECIDE ON PRICE SCALE FACTOR
		fortLaramie.setStore(2, party);
		locationList.add(fortLaramie);
		locationList.add(new NormalLocation("Independence Rock",102));
		locationList.add(new NormalLocation("South Pass",125));
		StoreLocation fortBridger = new StoreLocation("Fort Bridger",162);
		// *** DECIDE ON PRICE SCALE FACTOR
		fortBridger.setStore(2,party);
		locationList.add(fortBridger);
		locationList.add(new NormalLocation("Soda Springs",57));
		StoreLocation fortHall = new StoreLocation("Fort Hall",182);
		// *** DECIDE ON PRICE SCALE FACTOR
		fortHall.setStore(3,party);
		locationList.add(fortHall);
		locationList.add(new RiverLocation("Snake River Crossing",114,4,8,false));
		StoreLocation fortBoise = new StoreLocation("Fort Boise",162);
		// *** DECIDE ON PRICE SCALE FACTOR
		fortBoise.setStore(3,party);
		locationList.add(fortBoise);
		locationList.add(new NormalLocation("Blue Mountains",55));
		StoreLocation fortWallaWalla = new StoreLocation("For Walla Walla",120);
		// *** DECIDE ON PRICE SCALE FACTOR
		fortWallaWalla.setStore(4,party);
		locationList.add(fortWallaWalla);
		locationList.add(new NormalLocation("Dalles",0));
		// end location, necessary?
		//locationList.add(new NormalLocation("End",0));
		// float down Columbia?
		
		//etc etc
		//another option is to initialize using a text file, which would have a lot less code.
		//-Sean C.
		
		//TODO implement
	}
	
	/*
	 * The commented code returns the LocationNode at a distance of 'location' a.k.a. getLocation(100)
	 * would return the location at 100 miles (or the next location after that mile marker a.k.a the
	 * location  at 105 miles if there is no location at 100 miles) (it would be simple to change it to 
	 * the location BEFORE the mile marker if need be)
	 * 
	 * The uncommented code just returns the LocationNode with respect to the list a.k.a. getLocation (2)
	 * returns the second location in the list.
	 * 
	 * -Sean C.
	 */
	public LocationNode getLocation(int location) {
		return locationList.get(location);	
//		LocationNode temp = locationList.getFirst();
//		do
//		{
//			location -= temp.distanceToNextLocation;
//			temp = temp.nextLocation;
//		}while(location > 0);
//		return temp;
	}
		
	public int getDistanceToNextLocation(int location) {
		return locationList.get(location).getDistance();
	}
}
