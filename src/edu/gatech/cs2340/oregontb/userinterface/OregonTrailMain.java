package edu.gatech.cs2340.oregontb.userinterface;

import javax.swing.*;
import java.awt.*;
import java.io.IOException;

/**
 * This is the driver class for the Oregon Trail game, which contains the main method and initializes
 * the JFrame and containerPanel.
 */
public class OregonTrailMain 
{
	public static void main(String[] args) throws IOException {
		// Initialize the frame
		JFrame frame = new JFrame("Oregon Trail");
				
		// Create the OregonTrailPresenter
		OregonTrailPresenter oregonTrailPresenter = new OregonTrailPresenter();
		
		// Add all of this program's panels to the container panel.
		JPanel containerPanel = oregonTrailPresenter.addAllPanelsToContainer();
		
		// Add the containerPanel, and make visible. 
		frame.getContentPane().add(containerPanel);
		frame.setPreferredSize(new Dimension(800, 620));
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.pack();
		frame.setVisible(true);
	}
}
