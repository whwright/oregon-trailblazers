package edu.gatech.cs2340.oregontb.huntinggame;

public class Bunny extends Animal{
	
	Bunny(int x, int y, String ori) {
		super(x, y, "bunny", ori);
	}

	/* (non-Javadoc)
	 * @see edu.gatech.cs2340.oregontb.huntinggame.Animal#move()
	 */
	public void move() {
		if (!dead && orientation.equals("right"))
			xpos = xpos + 26;
		else if (!dead && orientation.equals("left"))
			xpos = xpos - 26;
	}

}
