package edu.gatech.cs2340.oregontb.userinterface;

import java.awt.event.*;
import java.io.*;
import java.awt.*;
import javax.swing.*;
import edu.gatech.cs2340.oregontb.gamelogic.*;
import java.awt.Font;
import java.util.List;

public class MainPanel extends JPanel {
	
	/**
	 * The presenter, which communicates the interface with the GameEngine.
	 */
	private OregonTrailPresenter presenter;
	/**
	 * An instance of the party, passed in from the presenter to make for 
	 * easier data alteration on the MainPanel.
	 */
	private Party party;
	/**
	 * Writer that writes to files for saving games.
	 */
	private Writer writer;
	/**
	 * Boolean, will be false if the user has saved once and true if they have 
	 * not saved yet.
	 */
	private boolean firstSave;
	/**
	 * Holds the saved name of the user.
	 */
	private String saveName;
	/**
	 * Filename for the Map image.
	 */
	private static final String MAP_FILENAME = "trailmap.jpg"; // image name for the trail map
	/**
	 * The Map image. and now the background image.
	 */
	private Image map;
	
	private static final String BACKGROUND_IMAGE = "mainpanel.png";
	private Image background;
	
	/**
	 * ComboBox's for Pace and Rations
	 */
	private JComboBox paceComboBox, rationsComboBox;
	/**
	 * Labels for the user's inventory.
	 */
	private JLabel oxenLabel, foodLabel, clothingLabel, ammoLabel, wheelLabel, tongueLabel, axleLabel, 
		lblWeight, lblMoney, rationsLabel, paceLabel;
	/**
	 * Labels for the user's party.
	 */
	private JLabel person1label, person2label, person3label, person4label, person5label;
	/**
	 * Other labels used for each step of the game.
	 */
	private JLabel distanceTraveledLabel, foodRemainingLabel, lblYouHaveTraveled;
	/**
	 * The button components used.
	 */
	private JButton btnStep, btnSaveGame, btnHunt;
	private JLabel lblNextLocation;
	private LocationList locationList;
	
	
	/**
	 * Creates the panel.
	 * @param presenter
	 * @param party
	 * @throws IOException 
	 */
	public MainPanel(OregonTrailPresenter presenter, Party party, LocationList locationList) {
		setBackground(Color.WHITE);
		background = new ImageIcon(BACKGROUND_IMAGE).getImage();
		
		this.party = party;
		this.presenter = presenter;
		this.locationList = locationList;
		
		// opens the Saved_Games file to append
		try {
			this.writer = new BufferedWriter(new FileWriter("Saved_Games.txt", true));
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		this.firstSave = true;
		
		setLayout(null);
		
		// ActionListener
		Listener listener = new Listener();

		JLabel inventoryLabel = new JLabel("Inventory");
		inventoryLabel.setBounds(54, 368, 117, 16);
		add(inventoryLabel);

		String[] paces = { "Stopped", "Leisurely", "Steady", "Grueling" };
		paceComboBox = new JComboBox(paces);
		paceComboBox.setBounds(542, 152, 126, 27);
		paceComboBox.addActionListener(listener);
		add(paceComboBox);

		String rations[] = { "None", "Barebones", "Meager", "Normal",
				"Well Fed" };
		rationsComboBox = new JComboBox(rations);
		rationsComboBox.setBounds(542, 203, 131, 27);
		rationsComboBox.addActionListener(listener);
		add(rationsComboBox);

		JSeparator separator = new JSeparator();
		separator.setBackground(Color.BLACK);
		separator.setBounds(31, 384, 104, 12);
		add(separator);

		JLabel lblOxen = new JLabel("Oxen: ");
		lblOxen.setBounds(42, 402, 41, 16);
		add(lblOxen);

		JLabel lblFood = new JLabel("Food:");
		lblFood.setBounds(42, 424, 41, 16);
		add(lblFood);

		JLabel lblClothing = new JLabel("Clothing:");
		lblClothing.setBounds(42, 447, 61, 16);
		add(lblClothing);

		JLabel lblAmmo = new JLabel("Ammunition:");
		lblAmmo.setBounds(42, 469, 82, 16);
		add(lblAmmo);

		JLabel lblWheels = new JLabel("Wagon Wheels:");
		lblWheels.setBounds(42, 491, 104, 16);
		add(lblWheels);

		JLabel lblAxles = new JLabel("Wagon Axles:");
		lblAxles.setBounds(42, 513, 93, 16);
		add(lblAxles);

		JLabel lblTongues = new JLabel("Wagon Tongues:");
		lblTongues.setBounds(42, 535, 110, 16);
		add(lblTongues);

		oxenLabel = new JLabel("");
		oxenLabel.setBackground(Color.WHITE);
		oxenLabel.setBounds(79, 402, 61, 16);
		add(oxenLabel);

		foodLabel = new JLabel("");
		foodLabel.setBackground(Color.WHITE);
		foodLabel.setBounds(79, 424, 61, 16);
		add(foodLabel);

		clothingLabel = new JLabel("");
		clothingLabel.setBackground(Color.WHITE);
		clothingLabel.setBounds(103, 447, 61, 16);
		add(clothingLabel);

		ammoLabel = new JLabel("");
		ammoLabel.setBackground(Color.WHITE);
		ammoLabel.setBounds(120, 469, 104, 16);
		add(ammoLabel);

		wheelLabel = new JLabel("");
		wheelLabel.setBackground(Color.WHITE);
		wheelLabel.setBounds(134, 491, 61, 16);
		add(wheelLabel);

		axleLabel = new JLabel("");
		axleLabel.setBackground(Color.WHITE);
		axleLabel.setBounds(135, 513, 61, 16);
		add(axleLabel);

		tongueLabel = new JLabel("");
		tongueLabel.setBackground(Color.WHITE);
		tongueLabel.setBounds(152, 535, 61, 16);
		add(tongueLabel);

		paceLabel = new JLabel("Pace");
		paceLabel.setBounds(532, 132, 46, 14);
		add(paceLabel);

		rationsLabel = new JLabel("Rations");
		rationsLabel.setBounds(532, 185, 75, 14);
		add(rationsLabel);
		
		JLabel lblParty = new JLabel("Party");
		lblParty.setBounds(258, 368, 61, 16);
		add(lblParty);
		
		JSeparator separator_1 = new JSeparator();
		separator_1.setBackground(Color.BLACK);
		separator_1.setBounds(229, 384, 104, 12);
		add(separator_1);
		
		person1label = new JLabel("");
		person1label.setBackground(Color.WHITE);
		person1label.setBounds(241, 413, 172, 16);
		add(person1label);
		
		person2label = new JLabel("");
		person2label.setBackground(Color.WHITE);
		person2label.setBounds(237, 457, 172, 16);
		add(person2label);
		
		person3label = new JLabel("");
		person3label.setBackground(Color.WHITE);
		person3label.setBounds(235, 435, 172, 16);
		add(person3label);
		
		person4label = new JLabel("");
		person4label.setBackground(Color.WHITE);
		person4label.setBounds(235, 479, 172, 16);
		add(person4label);
		
		person5label = new JLabel("");
		person5label.setBackground(Color.WHITE);
		person5label.setBounds(234, 501, 172, 16);
		add(person5label);
		
		// LABELS
		JLabel distanceTraveled = new JLabel("Distance Traveled:");
		distanceTraveled.setBounds(532, 29, 116, 16);
		add(distanceTraveled);
		
		distanceTraveledLabel = new JLabel("");
		distanceTraveledLabel.setBounds(654, 29, 82, 16);
		add(distanceTraveledLabel);
		
		JLabel foodRemaining = new JLabel("Food Remaining (lbs):");
		foodRemaining.setBounds(507, 51, 141, 16);
		add(foodRemaining);
		
		foodRemainingLabel = new JLabel("");
		foodRemainingLabel.setBounds(654, 51, 82, 16);
		add(foodRemainingLabel);		
		// END LABELS
		
		// BUTTONS
		btnStep = new JButton("Step");
		btnStep.setBounds(482, 241, 117, 29);
		btnStep.addActionListener(listener);
		add(btnStep);
		
		btnSaveGame = new JButton("Save Game");
		btnSaveGame.setBounds(647, 241, 117, 29);
		btnSaveGame.addActionListener(listener);
		add(btnSaveGame);
		
		btnHunt = new JButton("Hunt");
		btnHunt.setBounds(555, 279, 117, 29);
		btnHunt.addActionListener(listener);
		add(btnHunt);
		
		lblWeight = new JLabel("Weight: ");
		lblWeight.setBounds(21, 323, 159, 16);
		add(lblWeight);
		
		lblMoney = new JLabel("Money: ");
		lblMoney.setBounds(21, 343, 141, 16);
		add(lblMoney);
		
		lblYouHaveTraveled = new JLabel("You have traveled " + party.getTotalDistanceTraveled() + " miles.");
		lblYouHaveTraveled.setBounds(150, 286, 237, 14);
		add(lblYouHaveTraveled);
		
		lblNextLocation = new JLabel("Next Location: ");
		lblNextLocation.setBounds(517, 75, 295, 16);
		add(lblNextLocation);
		
		map = new ImageIcon(MAP_FILENAME).getImage();
	}
	
	@Override
	public void paintComponent(Graphics g) {
		super.paintComponent(g);
		g.drawImage(background, 0, 0, null);
		g.drawImage(map, 0, 0, null);
		g.setColor(Color.LIGHT_GRAY); // draw a gray box at the bottom of the image
		g.fillRect(0, 275, 500, 40);
		g.setColor(Color.BLACK);
		g.drawRect(0, 275, 500, 40); // draw a black border around the box
				
		Point wagon = distanceMapToGraph((double)party.getTotalDistanceTraveled());
		lblYouHaveTraveled.setText("You have traveled " + party.getTotalDistanceTraveled() + " miles.");
		g.drawOval((int)wagon.getX(), (int)wagon.getY(), 10, 10);
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
	
	/**
	 * Initializes the distanceTraveledLabel, foodRemainingLabel, currentPaceLabel, and currentRationsLabel before switching to
	 * the MainPanel.
	 */
	public void initialize() {
		// updates the map
		Point wagon = distanceMapToGraph((double)party.getTotalDistanceTraveled());
		lblYouHaveTraveled.setText("You have traveled " + party.getTotalDistanceTraveled() + " miles.");
		repaint();
		
		// updates the labels
		distanceTraveledLabel.setText(party.getTotalDistanceTraveled() + " Miles");
		foodRemainingLabel.setText(party.getInventory().getFood() + " lbs");
		lblNextLocation.setText("Next Stop: "+locationList.getLocation(party.getLocationNumber()+1).toString()+" ("+party.getDistanceToNextLocation()+" miles)");
		lblWeight.setText("Weight: "+party.getWeight()+" lbs");
		lblMoney.setText("Money: $ "+party.getMoney());
		
		// sets rationsComboBox to correct rations
		if (party.getRations() == Rations.NONE)
			rationsComboBox.setSelectedIndex(0);
		else if (party.getRations() == Rations.BAREBONES)
			rationsComboBox.setSelectedIndex(1);
		else if (party.getRations() == Rations.MEAGER)
			rationsComboBox.setSelectedIndex(2);
		else if (party.getRations() == Rations.NORMAL)
			rationsComboBox.setSelectedIndex(3);
		else if (party.getRations() == Rations.WELL_FED)
			rationsComboBox.setSelectedIndex(4);

		// sets paceComboBox to correct pace
		if (party.getPace() == Pace.STOPPED)
			paceComboBox.setSelectedIndex(0);
		else if (party.getPace() == Pace.LEISURELY)
			paceComboBox.setSelectedIndex(1);
		else if (party.getPace() == Pace.STEADY)
			paceComboBox.setSelectedIndex(2);
		else if (party.getPace() == Pace.GRUELING)
			paceComboBox.setSelectedIndex(3);

		// updates the inventory
		oxenLabel.setText(Integer.toString(party.getInventory().getOxen()));
		foodLabel.setText(party.getInventory().getFood() + " lbs");
		clothingLabel.setText(party.getInventory().getClothing() + " sets");
		ammoLabel.setText(party.getInventory().getAmmo() + " rounds");
		wheelLabel.setText(Integer.toString(party.getInventory().getWheels()));
		axleLabel.setText(Integer.toString(party.getInventory().getAxles()));
		tongueLabel.setText(Integer.toString(party.getInventory().getTongues()));
		
		// updates the party's statuss
		List<Person> people = party.getParty();
		Person person1 = people.get(0);
		person1label.setText(person1.getName()+": "+person1.getHealth().toString());
		Person person2 = people.get(1);
		person2label.setText(person2.getName()+": "+person2.getHealth().toString());
		Person person3 = people.get(2);
		person3label.setText(person3.getName()+": "+person3.getHealth().toString());
		Person person4 = people.get(3);
		person4label.setText(person4.getName()+": "+person4.getHealth().toString());
		Person person5 = people.get(4);
		person5label.setText(person5.getName()+": "+person5.getHealth().toString());

	}
	
	/**
	 * Setter for the party variable. Used when loading games.
	 * @param party
	 */
	public void setParty(Party party) {
		this.party = party;
	}
	
	/**
	 * Setter for the boolean firstSave.
	 * @param bool
	 */
	public void setFirstSave(boolean bool) {
		firstSave = bool;
	}
	
	/**
	 * Setter for the saveName.
	 * @param name
	 */
	public void setSaveName(String name) {
		saveName = name;
	}
	
	/**
	 * ActionListener class for the MainPanel.
	 */
	private class Listener implements ActionListener {
		
		public void actionPerformed(ActionEvent e) {
			// update Rations if combo box is used
			if (e.getSource() == rationsComboBox) {
				JComboBox cb = (JComboBox) e.getSource();
				String rations = (String) cb.getSelectedItem();
				if (rations.equals("None"))
					party.setRations(Rations.NONE); 
				else if (rations.equals("Barebones")) 
					party.setRations(Rations.BAREBONES);
				else if (rations.equals("Meager")) 
					party.setRations(Rations.MEAGER);				
				else if (rations.equals("Normal")) 
					party.setRations(Rations.NORMAL);
				else if (rations.equals("Well Fed")) 
					party.setRations(Rations.WELL_FED);
			}

			// update Pace is combo box is used
			else if (e.getSource() == paceComboBox) {
				JComboBox cb = (JComboBox) e.getSource();
				String pace = (String) cb.getSelectedItem();
				if (pace.equals("Stopped")) 
					party.setPace(Pace.STOPPED);
				else if (pace.equals("Leisurely")) 
					party.setPace(Pace.LEISURELY);
				else if (pace.equals("Steady")) 
					party.setPace(Pace.STEADY);
				else if (pace.equals("Grueling")) 
					party.setPace(Pace.GRUELING);
			}
			
			else if (e.getSource() == btnHunt) {
				if (party.getInventory().getAmmo() > 0)
					presenter.switchToHuntPanel();
				else
					JOptionPane.showMessageDialog(null, "You don't have any ammunition.");
			}
			
			// take a step in game
			else if (e.getSource() == btnStep) {
				presenter.step(); // steps the game
				initialize(); // updates the panel
			}
			
			// save game
			else if (e.getSource() == btnSaveGame) {
				File game = null;
				String name = party.getPartyLeader(); // get party leaders name
				// if its the first save
				if (firstSave) {
					saveName = name; // store saveName
					game = new File("Save_"+saveName+".txt"); // create a new file
					int i = 0;
					// if that file already exists, add a number to it until its unique
					while (game.exists()) {
						i++;
						saveName = incrementName(name,i);
						game = new File("Save_"+saveName+".txt");
					}
				}
				else
					game = new File("Save_"+saveName+".txt");
				// create output streams
		        FileOutputStream ostream;
		        ObjectOutputStream p = null;
				try {
					ostream = new FileOutputStream(game);
					p = new ObjectOutputStream(ostream);
					// saves party object
					p.writeObject(party);
					// adds saveName to Saved_Games and updates Saved_Games
					if (firstSave)
						writer.write(saveName+"\n");
					// if its the first save, tell the user the name their game is stored ass
					if (firstSave) {
						JOptionPane.showMessageDialog(null, "Your game has been saved under the name \""+saveName+"\".");
						firstSave = false; 
					}
					// else just tell the user the game saved
					else
						JOptionPane.showMessageDialog(null, "Game saved.");
				} catch (FileNotFoundException e1) {
					e1.printStackTrace();
				} catch (IOException e2) {
					e2.printStackTrace();
				} finally {
					// close output streams
					try {
						p.close();
						writer.close();
					} catch (IOException e1) {
						e1.printStackTrace();
					}
				}
			}
		}
		
		private String incrementName(String name, int i) {
			return name+i;
		}
	}
}
