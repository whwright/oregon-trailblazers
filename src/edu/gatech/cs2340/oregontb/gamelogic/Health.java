package edu.gatech.cs2340.oregontb.gamelogic;

import java.io.Serializable;

/**
 * Health is an enumerated type that represents the health of a Person. 
 */
public enum Health implements Serializable
{
	DEAD {
		public String toString() {
			return "dead";
		}
	},
	VERY_ILL {
		public String toString() {
			return "very ill";
		}
	},
	ILL {
		public String toString() {
			return "ill";
		}
	},
	HEALTHY {
		public String toString() {
			return "healthy";
		}
	}
}
