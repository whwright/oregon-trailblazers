package edu.gatech.cs2340.oregontb.gamelogic;

/**
 * EventReturnType encapsulates a return type for EventHandlers. It contains information of how much of each inventory item
 * is to be lost as the result of an event, as well as the status of party members.
 * @author Oregon Trailblazers (Michael Sandt, Casey Tisdel, Sean Cleary, Harrison Wright, Sean Gillespie)
 *
 */
public class EventReturnType extends Inventory {
	
	private Person deceased; // the Person who may die due to an event
	private Person ill; // the Person who may become ill.
	private Person veryIll; //the Person who may become very ill.
	private Person healthy; //the Person who may become healthy.
	private boolean isBad; // used to check and see if anything bad actually happened
	private int tiredOxen;
	private int deadOxen;
	private int daysLost;
	
	private int id;
	
	public EventReturnType(int id) {
		this.id = id;
		deceased = null;
		ill = null;
		veryIll = null;
		healthy = null;
		isBad = false;
		tiredOxen = 0;
		deadOxen = 0;
	}
	
	/**
	 * Adds a Person to be marked for death.
	 * @param deceased The person
	 */
	public void addDeadPerson(Person deceased) {
		this.deceased = deceased;
	}
	
	public void addIllPerson(Person ill) {
		this.ill = ill;
	}
	
	/**
	 * Returns whether or not this EventReturnType has penalties associated with it.
	 * @return Whether or not this EventReturnTYpe has penalties associated with it.
	 */
	public boolean isBad() {
		return isBad;
	}
	
	public void setBad(boolean isBad) {
		this.isBad = isBad;
	}
	
	public Person getDeceased() {
		return deceased;
	}
	
	public Person getIll() {
		return ill;
	}
	public Person getVeryIll() {
		return veryIll;
	}

	public void addVeryIllPerson(Person veryIll) {
		this.veryIll = veryIll;
	}

	public Person getHealthy() {
		return healthy;
	}

	public void addHealthyPerson(Person healthy) {
		this.healthy = healthy;
	}
	
	public void setTiredOxen(int tiredOxen)
	{
		this.tiredOxen = tiredOxen;
	}
	
	public int getTiredOxen()
	{
		return tiredOxen;
	}
	
	public void setDeadOxen(int deadOxen)
	{
		this.deadOxen = deadOxen;
	}
	
	public int getDeadOxen()
	{
		return deadOxen;
	}
	
	public int getID() {
		return id;
	}
	
	public void setDaysLost(int daysLost) {
		this.daysLost = daysLost;
	}
	
	public int getDaysLost() {
		return daysLost;
	}
	/**
	 * Allows the user to print the EventReturnType and have it show in String form what items were lost.
	 * @return A String representation of the EventReturnType
	 */
	@Override
	public String toString() {
		String output = "\n";
		if (oxen != 0)
			output += "Oxen: " + Math.abs(oxen) + "\n";
		if (food != 0)
			output += "Food: " + Math.abs(food) + "\n";
		if (clothing != 0)
			output += "Clothing: " + Math.abs(clothing) + "\n";
		if (ammunition != 0)
			output += "Ammo: " + Math.abs(ammunition) + "\n";
		if (wheels != 0)
			output += "Wheels: " + Math.abs(wheels) + "\n";
		if (axles != 0)
			output += "Axles: " + Math.abs(axles) + "\n";
		if (tongues != 0)
			output += "Tongues: " + Math.abs(tongues) + "\n";
		if (deceased != null) {
			output = "";
			output += "A party member, " + deceased.getName() + " has died.";
		}
		if (ill != null) {
			output = "";
			output += "A party member, " + ill.getName() + " has become ill.";
		}
		if (veryIll != null) {
			output = "";
			output += "A party member, " + veryIll.getName() + " has become very ill.";
		}
		if (healthy != null) {
			output = "";
			output += "A party member, " + healthy.getName() + " has recovered.";
		}
		return output;
	}
}
