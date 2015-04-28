package edu.gatech.cs2340.oregontb.userinterface;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import edu.gatech.cs2340.oregontb.gamelogic.*;
import javax.swing.border.LineBorder;


public class MapPanel extends JPanel {

	/**
	 * Create the panel.
	 */
	
	private static final String MAP_FILENAME = "trailmap.jpg"; // image name for the trail map
	private static final String WAGON_FILENAME = null; // image name for the wagon icon on the map
	
	private OregonTrailPresenter presenter;
	
	private Party party;
	private Image map;
	private Image wagonIcon;
	private JLabel lblYouHaveTraveled;
	//private OregonTrailPresenter presenter; // TODO add this when done debugging
	
	public MapPanel(Party party, OregonTrailPresenter presenter) {
		this.presenter = presenter;
		setBorder(new LineBorder(new Color(0, 0, 0)));
		this.party = party;
		setLayout(null);
		
		JButton btnGoBack = new JButton("Go Back");
		btnGoBack.setBounds(31, 282, 89, 23);
		add(btnGoBack);
		btnGoBack.addActionListener(new BackListener());
		
		lblYouHaveTraveled = new JLabel("You have traveled " + party.getTotalDistanceTraveled() + " miles.");
		lblYouHaveTraveled.setBounds(150, 286, 237, 14);
		add(lblYouHaveTraveled);
		map = new ImageIcon(MAP_FILENAME).getImage();
		//wagonIcon = new ImageIcon(WAGON_FILENAME).getImage();
	}
	
	@Override
	public void paintComponent(Graphics g) {
		super.paintComponent(g);
		g.drawImage(map, 0, 0, null);
		g.setColor(Color.LIGHT_GRAY); // draw a gray box at the bottom of the image
		g.fillRect(0, 275, 500, 40);
		g.setColor(Color.BLACK);
		g.drawRect(0, 275, 500, 40); // draw a black border around the box
				
		Point wagon = distanceMapToGraph((double)party.getTotalDistanceTraveled());
		lblYouHaveTraveled.setText("You have traveled " + party.getTotalDistanceTraveled() + " miles.");
		g.drawOval((int)wagon.getX(), (int)wagon.getY(), 10, 10);

	}
	
	public void setParty(Party party) {
		this.party = party;
	}

	/**
	 * This function maps the total distance traveled to a coordinate to be drawn on the map.
	 * @param distance The total distance traveled
	 * @return The point representing the location of the Party on the map
	 */
	private Point distanceMapToGraph(double distance) {
		if (distance >= 0 && distance <= 753.708) {
			return new Point((int)(407 - (distance * 0.203)), (int)(197 - (distance * 0.0731)));
		}
		else if (distance > 753.708 && distance <= 1039.6705) {
			return new Point((int)(259 - ((distance - 753.703) * 0.2299)), (int)(142 - ((distance - 753.703) * -0.0383)));
		}
		else if (distance > 1039.6705 && distance <= 1229.542) {
			return new Point((int)(193 - ((distance - 1039.6705) * 0.1527)), (int)(153 - ((distance - 1039.6705) * 0.1527)));
		}
		else if (distance > 1229.542 && distance <= 1503.317) {
			return new Point((int)(164 - ((distance - 1229.542) * 0.2045)), (int)(124 - ((distance - 1229.542) * 0.0694)));
		}
		else if (distance > 1503.317 && distance <= 1671.837) {
			return new Point((int)(108 - ((distance - 1503.317) * 0.0593)), (int)(105 - ((distance - 1503.317) * 0.2076)));
		}
		else if (distance > 1671.837) {
			return new Point((int)(98 - ((distance - 1671.837) * 0.2098)), (int)(70 - ((distance - 1671.837) * 0.0514)));
		}
		return new Point(0, 0);
	}
	
	private class BackListener implements ActionListener {

		//@Override
		public void actionPerformed(ActionEvent e) {
			presenter.switchToMain();
		}
		
	}
}
