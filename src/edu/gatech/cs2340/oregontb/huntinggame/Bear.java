package edu.gatech.cs2340.oregontb.huntinggame;

public class Bear extends Animal {

	public Bear(int x, int y, String ori) {
		super(x, y, "bear", ori);
	}
	
	/* (non-Javadoc)
	 * @see edu.gatech.cs2340.oregontb.huntinggame.Animal#move()
	 */
	public void move() {
		if (!dead && orientation.equals("right"))
			xpos = xpos + 10;
		else if (!dead && orientation.equals("left"))
			xpos = xpos - 10;
	}


}

