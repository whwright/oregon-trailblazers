package edu.gatech.cs2340.oregontb.gamelogic;

import java.io.Serializable;

/**
 * Rations is an enumerated type that represents the Party's rations.
 */
public enum Rations implements Serializable
{
	NONE(0) {
		public String toString() {
			return "None";
		}
	},
	BAREBONES(1) {
		public String toString() {
			return "Barebones";
		}
	},
	MEAGER(2) {
		public String toString() {
			return "Meager";
		}
	},
	NORMAL(3){
		public String toString() {
			return "Normal";
		}
	},
	WELL_FED(5){
		public String toString() {
			return "Well Fed";
		}
	};
	
	private int foodConsumed;
	
	private Rations(int food) {
		this.foodConsumed = food;
	}
	
	public int getFoodConsumed() {
		return foodConsumed;
	}
	
	public double getEventFactor()
	{
		if(foodConsumed != 0)
			return -(foodConsumed * 0.2 );
		return 3;
	}
	
}
