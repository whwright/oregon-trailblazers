package edu.gatech.cs2340.oregontb.gamelogic;

public class StoreLocation extends LocationNode {
	private Store store;
	
	public StoreLocation(String name, int distance) {
		super(name, distance);
		//TODO implement
	}
	
	public void setStore(int priceScale, Party party) {
		this.store = new Store(priceScale, party);
	}
	
	public double getPriceScaleFactor() {
		return store.getPriceScaleFactor();
	}
	
}
