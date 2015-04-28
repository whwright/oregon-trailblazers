package edu.gatech.cs2340.oregontb.gamelogic;

import java.io.Serializable;

/**
 * Pace is an enumerated type that represents the Party's pace. 
 */
public enum Pace implements Serializable
{
	STOPPED(0) {
		public String toString() {
			return "Stopped";
		}
			},
	LEISURELY(5) {
		public String toString() {
			return "Leisurely";
		}
			},
	STEADY(10) {
		public String toString() {
			return "Steady";
		}
			},
	GRUELING(15) {
		public String toString() {
			return "Grueling";
		}
			};
	
	private int distanceTraveled;
	
	private Pace(int distance) {
		distanceTraveled = distance;
	}
	
	public int getDistanceTraveled() {
		return distanceTraveled;
	}
	
	public double getEventFactor()
	{
		return (distanceTraveled)/15 + 1;
	}
}
