package edu.gatech.cs2340.oregontb.huntinggame;

import java.awt.*;
import java.awt.image.*;
import java.io.File;
import java.io.IOException;
import javax.imageio.ImageIO;

public abstract class Animal extends Component {
	protected BufferedImage[] iconsRight, iconsLeft, icons;
	protected BufferedImage currentIcon;
	protected int xpos, ypos, width, height;
	protected String name, orientation;
	protected boolean dead;

	// final ints to represent the position in the BufferedImage arrays
	public static final int MOVE = 0;
	public static final int SHOT = 1;
	public static final int DEAD = 2;
	
	public Animal(int x, int y, String name, String orientation) {
		this.xpos = x;
		this.ypos = y;
		this.name = name;
		this.orientation = orientation;
		this.iconsRight = new BufferedImage[3];
		this.iconsLeft = new BufferedImage[3];
		this.dead = false;
		try {
			iconsLeft[0] = ImageIO.read(new File(name+"_moving_left.png"));
			iconsRight[0] = ImageIO.read(new File(name+"_moving_right.png"));
			iconsLeft[1] = ImageIO.read(new File(name+"_shot_left.png"));
			iconsRight[1] = ImageIO.read(new File(name+"_shot_right.png"));
			iconsLeft[2] = ImageIO.read(new File(name+"_dead_left.png"));
			iconsRight[2] = ImageIO.read(new File(name+"_dead_right.png"));
			if (orientation.equals("left")) {
				icons = iconsLeft;
				currentIcon = icons[MOVE];				
			}
			else if (orientation.equals("right")) {
				icons = iconsRight;
				currentIcon = icons[MOVE];	
			}
			this.width = currentIcon.getWidth();
			this.height = currentIcon.getHeight();
		} catch (IOException e) {
			e.printStackTrace();
		}

		// loop for setting transparent pixels, could possibly be made useless
		// if we can find a way to
		// automatically encode images with proper alpha settings.
		for(int i=0; i<icons.length; i++) {
			BufferedImage temp = icons[i];
			int h = temp.getHeight();
			int w = temp.getWidth();
			for (int y2 = 0; y2 < h; y2++) {
				for (int x2 = 0; x2 < w; x2++) {
					int argb = temp.getRGB(x2, y2);
					if ((argb & 0x00FFFFFF) == 0x00000000) {
						temp.setRGB(x2, y2, 0);
					}
				}
			}
		}

	}
	
	/**
	 * Will flag the Animal as dead and switch their current icon to Dead.
	 */
	public void kill() {
		dead = true;	
		currentIcon = icons[DEAD];	
	}
	
	/**
	 * Will return true if the Animal is dead, and false if not.
	 * @return boolean 
	 */
	public boolean isDead() {
		return dead;
	}
	
	/**
	 * Sets the boolean dead
	 * @param boolean bool
	 */
	public void setDead(boolean bool) {
		this.dead = bool;
	}

	/**
	 * Will return True if the Animal is in the given point, and false if not.
	 * @param x
	 * @param y
	 * @return boolean
	 */
	public boolean containsPoint(int x, int y) {
		if (inXRange(x) && inYRange(y))
			return true;
		else
			return false;
	}

	/**
	 * Gets the current BufferedImage.
	 * @return BufferedImage that is the current icon
	 */
	public BufferedImage getCurrentIcon() {
		return currentIcon;
	}
	
	/**
	 * Sets the current icon to the position in the array passed in (can use final ints at top).
	 * @param a
	 */
	public void setCurrentIcon(int a) {
		currentIcon = icons[a];
	}
	
	/* (non-Javadoc)
	 * @see java.awt.Component#getName()
	 */
	public String getName() {
		return name;
	}


	/**
	 * Returns the x position of the Animal.
	 * @return int representing x position
	 */
	public int getXPos() {
		return xpos;
	}

	/**
	 * Returns the y position of the Animal.
	 * @return int representing the y position
	 */
	public int getYPos() {
		return ypos;
	}

	/**
	 * Returns the 2nd x position of the Animal (x pos + width).
	 * @return int representing 2nd x position
	 */
	public int getX2() {
		return xpos + width;
	}

	/**
	 * Returns the 2nd y position of the Animal (y pos + height).
	 * @return int representing 2nd y position
	 */
	public int getY2() {
		return ypos + height;
	}
	
	/* (non-Javadoc)
	 * @see java.awt.Component#getWidth()
	 */
	public int getWidth() {
		return width;
	}
	
	/* (non-Javadoc)
	 * @see java.awt.Component#getHeight()
	 */
	public int getHeight() {
		return height;
	}

	/**
	 * Will move the Animal a certain distance, depending on which Animal it is.
	 */
	public abstract void move(); 

	/**
	 * Paints the Animal on the screen.
	 * @param g
	 */
	public void paint(Graphics2D g) {
		g.drawImage(currentIcon, null, xpos, ypos);
	}

	/**
	 * Returns True if the point is in the X range of the Animal, and False if it's not.
	 * @param x
	 * @return boolean
	 */
	private boolean inXRange(int x) {
		if (x >= xpos && x <= (xpos + width))
			return true;
		else
			return false;
	}

	/**
	 * Returns True if the point is in the Y range of the Animal, and False if it's not.
	 * @param y
	 * @return boolean
	 */
	private boolean inYRange(int y) {
		if (y >= ypos && y <= (ypos + height))
			return true;
		else
			return false;
	}
	
	
}

