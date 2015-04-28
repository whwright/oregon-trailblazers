package edu.gatech.cs2340.oregontb.huntinggame;

public class Deer extends Animal {

	public Deer(int x, int y, String ori) {
		super(x, y,"deer", ori);
	}
	
	/* (non-Javadoc)
	 * @see edu.gatech.cs2340.oregontb.huntinggame.Animal#move()
	 */
	public void move() {
		if (!dead && orientation.equals("right"))
			xpos = xpos + 18;
		else if (!dead && orientation.equals("left"))
			xpos = xpos - 18;
	}

}
