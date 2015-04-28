package edu.gatech.cs2340.oregontb.userinterface;

import edu.gatech.cs2340.oregontb.gamelogic.*;


import java.awt.event.*;

import javax.swing.*;
import java.awt.Color;
import java.util.List;

public class PartyViewPanel extends JPanel {

	private OregonTrailPresenter presenter;
	private Party party;
	private JButton btnBack;
	private JComboBox paceComboBox, rationsComboBox;
	private JLabel oxenLabel, foodLabel, clothingLabel, ammoLabel, wheelLabel,
			tongueLabel, axleLabel;
	private JLabel paceLabel;
	private JLabel rationsLabel;
	private JButton btnMap;
	private JLabel person1label, person2label, person3label, person4label, person5label;

	/**
	 * Creates the panel.
	 * @param presenter
	 * @param party
	 */
	public PartyViewPanel(OregonTrailPresenter presenter, Party party) {

		this.presenter = presenter;
		this.party = party;
		Listener listener = new Listener();

		btnBack = new JButton("Back");
		btnBack.setBounds(120, 244, 75, 29);
		btnBack.addActionListener(listener);
		setLayout(null);

		JLabel inventoryLabel = new JLabel("Inventory");
		inventoryLabel.setBounds(32, 20, 117, 16);
		add(inventoryLabel);
		add(btnBack);

		String[] paces = { "Stopped", "Leisurely", "Steady", "Grueling" };
		paceComboBox = new JComboBox(paces);
		paceComboBox.setBounds(255, 188, 126, 27);
		paceComboBox.addActionListener(listener);
		add(paceComboBox);

		String rations[] = { "None", "Barebones", "Meager", "Normal",
				"Well Fed" };
		rationsComboBox = new JComboBox(rations);
		rationsComboBox.setBounds(250, 246, 131, 27);
		rationsComboBox.addActionListener(listener);
		add(rationsComboBox);

		JSeparator separator = new JSeparator();
		separator.setBackground(Color.BLACK);
		separator.setBounds(21, 32, 104, 12);
		add(separator);

		JLabel lblOxen = new JLabel("Oxen: ");
		lblOxen.setBounds(31, 50, 41, 16);
		add(lblOxen);

		JLabel lblFood = new JLabel("Food:");
		lblFood.setBounds(31, 72, 41, 16);
		add(lblFood);

		JLabel lblClothing = new JLabel("Clothing:");
		lblClothing.setBounds(31, 94, 61, 16);
		add(lblClothing);

		JLabel lblAmmo = new JLabel("Ammunition:");
		lblAmmo.setBounds(31, 116, 82, 16);
		add(lblAmmo);

		JLabel lblWheels = new JLabel("Wagon Wheels:");
		lblWheels.setBounds(31, 145, 104, 16);
		add(lblWheels);

		JLabel lblAxles = new JLabel("Wagon Axles:");
		lblAxles.setBounds(31, 167, 93, 16);
		add(lblAxles);

		JLabel lblTongues = new JLabel("Wagon Tongues:");
		lblTongues.setBounds(31, 189, 110, 16);
		add(lblTongues);

		oxenLabel = new JLabel("");
		oxenLabel.setBounds(74, 50, 61, 16);
		add(oxenLabel);

		foodLabel = new JLabel("");
		foodLabel.setBounds(74, 72, 61, 16);
		add(foodLabel);

		clothingLabel = new JLabel("");
		clothingLabel.setBounds(98, 94, 61, 16);
		add(clothingLabel);

		ammoLabel = new JLabel("");
		ammoLabel.setBounds(119, 116, 104, 16);
		add(ammoLabel);

		wheelLabel = new JLabel("");
		wheelLabel.setBounds(131, 145, 61, 16);
		add(wheelLabel);

		axleLabel = new JLabel("");
		axleLabel.setBounds(119, 167, 61, 16);
		add(axleLabel);

		tongueLabel = new JLabel("");
		tongueLabel.setBounds(143, 189, 61, 16);
		add(tongueLabel);

		paceLabel = new JLabel("Pace");
		paceLabel.setBounds(250, 166, 46, 14);
		add(paceLabel);

		rationsLabel = new JLabel("Rations");
		rationsLabel.setBounds(246, 226, 75, 14);
		add(rationsLabel);
		
		btnMap = new JButton("Map");
		btnMap.setBounds(34, 244, 75, 29);
		add(btnMap);
		
		JLabel lblParty = new JLabel("Party");
		lblParty.setBounds(255, 20, 61, 16);
		add(lblParty);
		
		JSeparator separator_1 = new JSeparator();
		separator_1.setBackground(Color.BLACK);
		separator_1.setBounds(240, 32, 104, 12);
		add(separator_1);
		
		person1label = new JLabel("");
		person1label.setBounds(255, 50, 172, 16);
		add(person1label);
		
		person2label = new JLabel("");
		person2label.setBounds(255, 71, 172, 16);
		add(person2label);
		
		person3label = new JLabel("");
		person3label.setBounds(255, 93, 172, 16);
		add(person3label);
		
		person4label = new JLabel("");
		person4label.setBounds(255, 115, 172, 16);
		add(person4label);
		
		person5label = new JLabel("");
		person5label.setBounds(255, 137, 172, 16);
		add(person5label);
		btnMap.addActionListener(new MapListener());
	}

	/**
	 * Initializes the combo box's to the Party's current Rations, Pace and Inventory
	 */
	public void initialize() {

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

		// sets the inventory up before switching to panel
		oxenLabel.setText(Integer.toString(party.getInventory().getOxen()));
		foodLabel.setText(party.getInventory().getFood() + " lbs");
		clothingLabel.setText(party.getInventory().getClothing() + " sets");
		ammoLabel.setText(party.getInventory().getAmmo() + " rounds");
		wheelLabel.setText(Integer.toString(party.getInventory().getWheels()));
		axleLabel.setText(Integer.toString(party.getInventory().getAxles()));
		tongueLabel.setText(Integer.toString(party.getInventory().getTongues()));
		
		// sets the Party's status up before switching to panel
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
	
	public void setParty(Party party) {
		this.party = party;
	}

	private class Listener implements ActionListener {
		public void actionPerformed(ActionEvent e) {
			// switch back
			if (e.getSource() == btnBack)
				presenter.switchToMain();

			// update Rations if combo box is used
			else if (e.getSource() == rationsComboBox) {
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
		}
	}
	
	private class MapListener implements ActionListener {

		//@Override
		public void actionPerformed(ActionEvent arg0) {
			presenter.switchToPanel(OregonTrailPresenter.MAP_PANEL);	
		}
		
	}
}
