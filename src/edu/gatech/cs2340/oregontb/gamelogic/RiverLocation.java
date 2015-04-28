package edu.gatech.cs2340.oregontb.gamelogic;

public class RiverLocation extends LocationNode {
	private int minDepth, maxDepth;
	private boolean isFerry;
	
	public RiverLocation(String name, int distance, int min, int max, boolean ferry) {
		super(name, distance);
		minDepth = min;
		maxDepth = max;
		isFerry = ferry;
		//TODO implement
	}
	
	public int getMinDepth() {
		return minDepth;
	}
	
	public int getMaxDepth() {
		return maxDepth;
	}
	
	public boolean getIsFerry() {
		return isFerry;
	}
}
