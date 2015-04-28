package edu.gatech.cs2340.oregontb.userinterface;
import edu.gatech.cs2340.oregontb.gamelogic.*;

import javax.swing.*;
import javax.swing.border.LineBorder;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;

import java.awt.*;
import java.awt.event.*;
import java.util.*;


/**
 * StorePanel is a JPanel that represents the store. This is where the user buy items for their journey.
 */
public class StorePanel extends JPanel implements IStorePanel
{
	private OregonTrailPresenter presenter;
	private Store store;
	
	// fields where input is taken from
	private JTextField oxenField, foodField, clothingField, ammoField, wheelField, tongueField, axleField;
	// price labels
	private JLabel oxenPrice, foodPrice, clothingPrice, ammoPrice, wheelPrice, axlePrice, tonguePrice;
	// user's wagon labels
	private JLabel currentMoneyLabel, currentWeightLabel;
	// buttons
	private JButton btnUpdate, btnGo, btnReset;
	
	private static final String BACKGROUND_IMAGE = "plainpanel_background.png";
	private Image background;

	
	/**
	 * Create the panel.
	 */
	public StorePanel(OregonTrailPresenter presenter, Store store) {
		background = new ImageIcon(BACKGROUND_IMAGE).getImage();
		this.presenter = presenter;
		this.store = store;
		
		Listener listener = new Listener();
		UpdateListener updateListener = new UpdateListener();
		
		setBackground(Color.BLACK);
		setLayout(null);
		
		// JTEXT FIELDS (user input)
		oxenField = new JTextField();
		oxenField.setFont(new Font("DialogInput", Font.PLAIN, 15));
		oxenField.setBounds(235, 238, 48, 28);
		oxenField.setColumns(10);
		oxenField.getDocument().addDocumentListener(updateListener);
		add(oxenField);
		
		foodField = new JTextField();
		foodField.setFont(new Font("DialogInput", Font.PLAIN, 15));
		foodField.setBounds(202, 272, 48, 28);
		foodField.setColumns(10);
		foodField.getDocument().addDocumentListener(updateListener);
		add(foodField);
		
		
		clothingField = new JTextField();
		clothingField.setFont(new Font("DialogInput", Font.PLAIN, 15));
		clothingField.setBounds(235, 306, 48, 28);
		clothingField.setColumns(10);
		clothingField.getDocument().addDocumentListener(updateListener);
		add(clothingField);
		
		
		ammoField = new JTextField();
		ammoField.setFont(new Font("DialogInput", Font.PLAIN, 15));
		ammoField.setBounds(309, 340, 48, 28);
		ammoField.setColumns(10);
		ammoField.getDocument().addDocumentListener(updateListener);
		add(ammoField);
		

		
		wheelField = new JTextField();
		wheelField.setFont(new Font("DialogInput", Font.PLAIN, 15));
		wheelField.setBounds(169, 430, 48, 28);
		wheelField.setColumns(10);
		wheelField.getDocument().addDocumentListener(updateListener);
		add(wheelField);
		
		
		tongueField = new JTextField();
		tongueField.setFont(new Font("DialogInput", Font.PLAIN, 15));
		tongueField.setBounds(179, 463, 48, 28);
		tongueField.setColumns(10);
		tongueField.getDocument().addDocumentListener(updateListener);
		add(tongueField);
		
		
		axleField = new JTextField();
		axleField.setFont(new Font("DialogInput", Font.PLAIN, 15));
		axleField.setBounds(157, 497, 48, 28);
		axleField.setColumns(10);
		axleField.getDocument().addDocumentListener(updateListener);
		add(axleField);
		// END JTEXT FIELDS
		
		// ITEM LABELS
		JLabel lblOxen = new JLabel("Oxen (a set of 2)");
		lblOxen.setForeground(Color.WHITE);
		lblOxen.setFont(new Font("DialogInput", Font.PLAIN, 15));
		lblOxen.setBounds(105, 238, 145, 28);
		add(lblOxen);
		
		JLabel lblFood = new JLabel("Food (5 lbs)");
		lblFood.setForeground(Color.WHITE);
		lblFood.setFont(new Font("DialogInput", Font.PLAIN, 15));
		lblFood.setBounds(105, 272, 117, 28);
		add(lblFood);
		
		JLabel lblClothing = new JLabel("Clothing (1 set)");
		lblClothing.setForeground(Color.WHITE);
		lblClothing.setFont(new Font("DialogInput", Font.PLAIN, 15));
		lblClothing.setBounds(105, 306, 142, 28);
		add(lblClothing);
		
		JLabel lblAmmunition = new JLabel("Ammunition (20 round box)");
		lblAmmunition.setForeground(Color.WHITE);
		lblAmmunition.setFont(new Font("DialogInput", Font.PLAIN, 15));
		lblAmmunition.setBounds(105, 340, 220, 28);
		add(lblAmmunition);
		
		JLabel lblWheel = new JLabel("Wheels");
		lblWheel.setForeground(Color.WHITE);
		lblWheel.setFont(new Font("DialogInput", Font.PLAIN, 15));
		lblWheel.setBounds(108, 430, 61, 28);
		add(lblWheel);
		
		JLabel lblTongue = new JLabel("Tongues");
		lblTongue.setForeground(Color.WHITE);
		lblTongue.setFont(new Font("DialogInput", Font.PLAIN, 15));
		lblTongue.setBounds(108, 464, 61, 28);
		add(lblTongue);
		
		JLabel lblAxle = new JLabel("Axles");
		lblAxle.setForeground(Color.WHITE);
		lblAxle.setFont(new Font("DialogInput", Font.PLAIN, 15));
		lblAxle.setBounds(108, 497, 61, 28);
		add(lblAxle);
		
		JLabel lblWagonHeader = new JLabel("Spare wagon parts");
		lblWagonHeader.setForeground(Color.WHITE);
		lblWagonHeader.setFont(new Font("DialogInput", Font.PLAIN, 20));
		lblWagonHeader.setBounds(108, 383, 208, 25);
		add(lblWagonHeader);
		// END ITEM LABELS
		
		// HEADERS AND SEPARATORS
		JLabel lblItemHeader = new JLabel("Items");
		lblItemHeader.setForeground(Color.WHITE);
		lblItemHeader.setFont(new Font("DialogInput", Font.PLAIN, 20));
		lblItemHeader.setBounds(108, 200, 70, 25);
		add(lblItemHeader);
		
		JSeparator separator = new JSeparator();
		separator.setForeground(Color.WHITE);
		separator.setBackground(Color.BLACK);
		separator.setBounds(95, 405, 455, 16);
		add(separator);
		
		JSeparator separator_1 = new JSeparator();
		separator_1.setForeground(Color.WHITE);
		separator_1.setBackground(Color.BLACK);
		separator_1.setBounds(95, 221, 455, 16);
		add(separator_1);
		
		JLabel lblPriceHeader = new JLabel("Price");
		lblPriceHeader.setForeground(Color.WHITE);
		lblPriceHeader.setFont(new Font("DialogInput", Font.PLAIN, 20));
		lblPriceHeader.setBounds(398, 200, 61, 25);
		add(lblPriceHeader);
		// END HEADERS AND SEPARATORS
		
		// PRICE LABELS THAT NEED TO BE UPDATED BASED ON STORE
		oxenPrice = new JLabel("");
		oxenPrice.setForeground(Color.WHITE);
		oxenPrice.setFont(new Font("DialogInput", Font.PLAIN, 15));
		oxenPrice.setHorizontalAlignment(SwingConstants.CENTER);
		oxenPrice.setBounds(398, 238, 61, 28);
		add(oxenPrice);
		
		foodPrice = new JLabel("");
		foodPrice.setForeground(Color.WHITE);
		foodPrice.setFont(new Font("DialogInput", Font.PLAIN, 15));
		foodPrice.setHorizontalAlignment(SwingConstants.CENTER);
		foodPrice.setBounds(398, 272, 61, 28);
		add(foodPrice);
		
		clothingPrice = new JLabel("");
		clothingPrice.setForeground(Color.WHITE);
		clothingPrice.setFont(new Font("DialogInput", Font.PLAIN, 15));
		clothingPrice.setHorizontalAlignment(SwingConstants.CENTER);
		clothingPrice.setBounds(398, 306, 61, 28);
		add(clothingPrice);
		
		ammoPrice = new JLabel("");
		ammoPrice.setForeground(Color.WHITE);
		ammoPrice.setFont(new Font("DialogInput", Font.PLAIN, 15));
		ammoPrice.setHorizontalAlignment(SwingConstants.CENTER);
		ammoPrice.setBounds(398, 340, 61, 28);
		add(ammoPrice);
		
		wheelPrice = new JLabel("");
		wheelPrice.setForeground(Color.WHITE);
		wheelPrice.setFont(new Font("DialogInput", Font.PLAIN, 15));
		wheelPrice.setHorizontalAlignment(SwingConstants.CENTER);
		wheelPrice.setBounds(398, 430, 61, 28);
		add(wheelPrice);
		
		tonguePrice = new JLabel("");
		tonguePrice.setForeground(Color.WHITE);
		tonguePrice.setFont(new Font("DialogInput", Font.PLAIN, 15));
		tonguePrice.setHorizontalAlignment(SwingConstants.CENTER);
		tonguePrice.setBounds(398, 464, 61, 28);
		add(tonguePrice);
		
		axlePrice = new JLabel("");
		axlePrice.setForeground(Color.WHITE);
		axlePrice.setFont(new Font("DialogInput", Font.PLAIN, 15));
		axlePrice.setHorizontalAlignment(SwingConstants.CENTER);
		axlePrice.setBounds(398, 498, 61, 28);
		add(axlePrice);
		// END PRICE LABELS
		
		// WEIGHT LABELS SECTION
		JLabel lblWeight = new JLabel("Weight");
		lblWeight.setForeground(Color.WHITE);
		lblWeight.setFont(new Font("DialogInput", Font.PLAIN, 20));
		lblWeight.setBounds(475, 200, 75, 25);
		add(lblWeight);
		
		JLabel lblLbs_1 = new JLabel("0 lbs");
		lblLbs_1.setForeground(Color.WHITE);
		lblLbs_1.setFont(new Font("DialogInput", Font.PLAIN, 15));
		lblLbs_1.setHorizontalAlignment(SwingConstants.CENTER);
		lblLbs_1.setBounds(475, 238, 61, 28);
		add(lblLbs_1);
		
		JLabel lblLbs = new JLabel("5 lbs");
		lblLbs.setForeground(Color.WHITE);
		lblLbs.setFont(new Font("DialogInput", Font.PLAIN, 15));
		lblLbs.setHorizontalAlignment(SwingConstants.CENTER);
		lblLbs.setBounds(475, 272, 61, 28);
		add(lblLbs);
		
		JLabel lblLbs_2 = new JLabel("2 lbs");
		lblLbs_2.setForeground(Color.WHITE);
		lblLbs_2.setFont(new Font("DialogInput", Font.PLAIN, 15));
		lblLbs_2.setHorizontalAlignment(SwingConstants.CENTER);
		lblLbs_2.setBounds(475, 306, 61, 28);
		add(lblLbs_2);
		
		JLabel lblLbs_3 = new JLabel("3 lbs");
		lblLbs_3.setForeground(Color.WHITE);
		lblLbs_3.setFont(new Font("DialogInput", Font.PLAIN, 15));
		lblLbs_3.setHorizontalAlignment(SwingConstants.CENTER);
		lblLbs_3.setBounds(475, 340, 61, 28);
		add(lblLbs_3);
		
		JLabel lblLbs_4 = new JLabel("75 lbs");
		lblLbs_4.setForeground(Color.WHITE);
		lblLbs_4.setFont(new Font("DialogInput", Font.PLAIN, 15));
		lblLbs_4.setHorizontalAlignment(SwingConstants.CENTER);
		lblLbs_4.setBounds(475, 430, 61, 28);
		add(lblLbs_4);
		
		JLabel lblLbs_6 = new JLabel("100 lbs");
		lblLbs_6.setForeground(Color.WHITE);
		lblLbs_6.setFont(new Font("DialogInput", Font.PLAIN, 15));
		lblLbs_6.setHorizontalAlignment(SwingConstants.CENTER);
		lblLbs_6.setBounds(475, 463, 61, 28);
		add(lblLbs_6);
		
		JLabel lblLbs_5 = new JLabel("125 lbs");
		lblLbs_5.setForeground(Color.WHITE);
		lblLbs_5.setFont(new Font("DialogInput", Font.PLAIN, 15));
		lblLbs_5.setHorizontalAlignment(SwingConstants.CENTER);
		lblLbs_5.setBounds(475, 497, 61, 28);
		add(lblLbs_5);
		// END WEIGHT LABELS
		
		// MORE SEPARATORS
		JSeparator separator_4 = new JSeparator();
		separator_4.setForeground(Color.WHITE);
		separator_4.setBackground(Color.BLACK);
		separator_4.setOrientation(SwingConstants.VERTICAL);
		separator_4.setBounds(542, 187, 10, 350);
		add(separator_4);
		
		JSeparator separator_5 = new JSeparator();
		separator_5.setForeground(Color.WHITE);
		separator_5.setOrientation(SwingConstants.VERTICAL);
		separator_5.setBackground(Color.BLACK);
		separator_5.setBounds(381, 187, 10, 350);
		add(separator_5);
		
		JSeparator separator_6 = new JSeparator();
		separator_6.setForeground(Color.WHITE);
		separator_6.setOrientation(SwingConstants.VERTICAL);
		separator_6.setBackground(Color.BLACK);
		separator_6.setBounds(459, 187, 10, 350);
		add(separator_6);
		
		// USER'S WAGON INFO
		JLabel lblMoney = new JLabel("Current Money");
		lblMoney.setForeground(Color.WHITE);
		lblMoney.setFont(new Font("DialogInput", Font.PLAIN, 20));
		lblMoney.setBounds(625, 272, 150, 25);
		add(lblMoney);
		
		JSeparator separator_7 = new JSeparator();
		separator_7.setForeground(Color.WHITE);
		separator_7.setBackground(Color.BLACK);
		separator_7.setBounds(617, 295, 160, 16);
		add(separator_7);
		
		currentMoneyLabel = new JLabel("$");
		currentMoneyLabel.setForeground(Color.WHITE);
		currentMoneyLabel.setFont(new Font("DialogInput", Font.PLAIN, 20));
		currentMoneyLabel.setHorizontalAlignment(SwingConstants.LEFT);
		currentMoneyLabel.setBounds(645, 306, 155, 35);
		add(currentMoneyLabel);
		
		JLabel lblCurrentWeight = new JLabel("Current Weight");
		lblCurrentWeight.setForeground(Color.WHITE);
		lblCurrentWeight.setFont(new Font("DialogInput", Font.PLAIN, 20));
		lblCurrentWeight.setBounds(625, 380, 150, 25);
		add(lblCurrentWeight);
		
		JSeparator separator_8 = new JSeparator();
		separator_8.setForeground(Color.WHITE);
		separator_8.setBackground(Color.BLACK);
		separator_8.setBounds(617, 430, 160, 16);
		add(separator_8);
		
		currentWeightLabel = new JLabel("0 lbs");
		currentWeightLabel.setForeground(Color.WHITE);
		currentWeightLabel.setFont(new Font("DialogInput", Font.PLAIN, 20));
		currentWeightLabel.setHorizontalAlignment(SwingConstants.LEFT);
		currentWeightLabel.setBounds(645, 445, 155, 35);
		add(currentWeightLabel);
		// END USER'S WAGON INFO
		
		// BUTTONS
		
		/*
		btnUpdate = new JButton("Update");
		btnUpdate.addActionListener(listener);
		btnUpdate.setBounds(166, 128, 117, 29);
		add(btnUpdate);
		*/
		
		
		btnReset = new JButton("Clear your wagon");
		btnReset.setBorder(new LineBorder(Color.WHITE));
		btnReset.setBackground(Color.BLACK);
		btnReset.setFont(new Font("DialogInput", Font.BOLD, 20));
		btnReset.setForeground(Color.WHITE);
		btnReset.addActionListener(listener);
		btnReset.setBounds(381, 138, 210, 35);
		add(btnReset);
		
		
		btnGo = new JButton("Hit the trail!");
		btnGo.setBorder(new LineBorder(Color.WHITE));
		btnGo.setBackground(Color.BLACK);
		btnGo.setFont(new Font("DialogInput", Font.BOLD, 20));
		btnGo.setForeground(Color.WHITE);
		btnGo.addActionListener(listener);		
		btnGo.setBounds(166, 138, 150, 35);
		add(btnGo);
		
		JLabel lblmaxLbs = new JLabel("(max 3500 lbs)");
		lblmaxLbs.setForeground(Color.WHITE);
		lblmaxLbs.setFont(new Font("DialogInput", Font.PLAIN, 20));
		lblmaxLbs.setBounds(625, 405, 150, 25);
		add(lblmaxLbs);
		
		
		// JTEXTFIELD
		JTextArea viewPartyTextField = new JTextArea();
		viewPartyTextField.setFont(new Font("DialogInput", Font.PLAIN, 15));
		viewPartyTextField.setForeground(Color.WHITE);
		viewPartyTextField.setText("Welcome to the store, adventurer! You need to load up your wagon with supplies before you can hit the trail. " +
				"You will need atleast 2 oxen to pull your wagon, food and clothes to keep you and your companions alive, and ammunition if you wish to hunt. " +
				"Since you have a wagon, you already have 2 axles, 1 tongue, and 4 wheels. However, if any of them happen to break you will need extras. " +
				"Your wagon capacity and remaining money are displayed below.");
		viewPartyTextField.setEditable(false);
		viewPartyTextField.setWrapStyleWord(true);
		viewPartyTextField.setLineWrap(true);
		viewPartyTextField.setBackground(Color.BLACK);
		viewPartyTextField.setBounds(27, 10, 748, 122);
		add(viewPartyTextField);
	}
	

	//paint background
	public void paintComponent(Graphics g) {
		super.paintComponent(g);
		g.drawImage(background, 0, 0, null);
	}
	
	public void clearFields() {
		oxenField.setText("");
		foodField.setText("");
		clothingField.setText("");
		ammoField.setText("");
		wheelField.setText("");
		tongueField.setText("");
		axleField.setText("");
	}


	public void setCurrentMoney(int money) {
		currentMoneyLabel.setText("$ "+Integer.toString(money));
		
	}


	public void setCurrentWeight(int weight) {
		currentWeightLabel.setText(Integer.toString(weight)+" lbs");
	}


	public void setOxenPrice() {
		oxenPrice.setText(Integer.toString((int)store.getPrice(ItemType.OXEN)));
		
	}


	public void setFoodPrice() {
		foodPrice.setText(Integer.toString((int)store.getPrice(ItemType.FOOD)));
		
	}


	public void setClothingPrice() {
		clothingPrice.setText(Integer.toString((int)store.getPrice(ItemType.CLOTHING)));
		
	}


	public void setAmmoPrice() {
		ammoPrice.setText(Integer.toString((int)store.getPrice(ItemType.AMMO)));
		
	}


	public void setWheelPrice() {
		wheelPrice.setText(Integer.toString((int)store.getPrice(ItemType.WHEEL)));
		
	}


	public void setTonguePrice() {
		tonguePrice.setText(Integer.toString((int)store.getPrice(ItemType.TONGUE)));
		
	}


	public void setAxlePrice() {
		axlePrice.setText(Integer.toString((int)store.getPrice(ItemType.AXLE)));
		
	}


	public int getOxenQuantity() {
		if (oxenField.getText().equals(""))
			return 0;
		else
			return Math.abs(Integer.parseInt(oxenField.getText())); 
	}


	public int getFoodQuantity() {
		if (foodField.getText().equals(""))
			return 0;
		else
			return Math.abs(Integer.parseInt(foodField.getText()));
	}


	public int getClothingQuantity() {
			if (clothingField.getText().equals(""))	
				return 0;
			else
				return Math.abs(Integer.parseInt(clothingField.getText()));
	}


	public int getAmmoQuantity() {
			if (ammoField.getText().equals(""))
				return 0;
			else
				return Math.abs(Integer.parseInt(ammoField.getText()));
	}	


	public int getWheelQuantity() {
			if (wheelField.getText().equals(""))
				return 0;
			else
				return Math.abs(Integer.parseInt(wheelField.getText()));
	}


	public int getTongueQuantity() {
			if (tongueField.getText().equals(""))
				return 0;
			else
				return Math.abs(Integer.parseInt(tongueField.getText()));
	}


	public int getAxleQuantity() {
			if (axleField.getText().equals(""))
				return 0;
			else
				return Math.abs(Integer.parseInt(axleField.getText()));
	}
	
	
	/**
	 * This is an ActionListener class, which will update the Party's info upon pressing "Update" (this allows the user to go
	 * over money and weight), reset the Party's info upon pressing "Reset", and finally upon pressing "Go" the desired purchases
	 * are made and the Party's data is updated.
	 */
	private class Listener implements ActionListener
	{
		public void actionPerformed(ActionEvent e)
		{
			if (e.getSource() == oxenField || e.getSource() == foodField || e.getSource() == clothingField
					|| e.getSource() == ammoField || e.getSource() == tongueField || e.getSource() == wheelField || e.getSource() == axleField) {
				int totalSpent = 0;
				int totalWeight = 0;
				try {	
					// sums totalSpent
					totalSpent += getOxenQuantity() * store.getPrice(ItemType.OXEN);
					totalSpent += getFoodQuantity() * store.getPrice(ItemType.FOOD);
					totalSpent += getClothingQuantity() * store.getPrice(ItemType.CLOTHING);
					totalSpent += getAmmoQuantity() * store.getPrice(ItemType.AMMO);
					totalSpent += getWheelQuantity() * store.getPrice(ItemType.WHEEL);
					totalSpent += getTongueQuantity() * store.getPrice(ItemType.TONGUE);
					totalSpent += getAxleQuantity() * store.getPrice(ItemType.AXLE);
					
					// sums totalWeight
					totalWeight += getOxenQuantity() * ItemType.OXEN.weight();
					totalWeight += getFoodQuantity() * ItemType.FOOD.weight();
					totalWeight += getClothingQuantity() * ItemType.CLOTHING.weight();
					totalWeight += getAmmoQuantity() * ItemType.AMMO.weight();
					totalWeight += getWheelQuantity() * ItemType.WHEEL.weight();
					totalWeight += getTongueQuantity() * ItemType.TONGUE.weight();
					totalWeight += getAxleQuantity() * ItemType.AXLE.weight();
					
					// sets labels to totalSpent and totalWeight
					currentMoneyLabel.setText("$ "+Integer.toString(store.getUpdatedMoney(totalSpent)));
					currentWeightLabel.setText(Integer.toString(store.getUpdatedWeight(totalWeight))+" lbs");
					
				}
				// makes sure the user input is only numbers
				catch (NumberFormatException f) {
					JOptionPane.showMessageDialog(null, "Please enter numbers only.");
				}
			}
			
			else if (e.getSource() == btnReset)
			{
				// resets the labels and text fields
				currentMoneyLabel.setText("$ "+Integer.toString(store.getPartyMoney()));
				currentWeightLabel.setText(Integer.toString(store.getPartyWeight())+" lbs");
				oxenField.setText("");
				foodField.setText("");
				clothingField.setText("");
				ammoField.setText("");
				wheelField.setText("");
				tongueField.setText("");
				axleField.setText("");
			}
			
			else if (e.getSource() == btnGo)
			{
				int totalSpent = 0;
				int totalWeight = 0;
				Inventory tempInventory = new Inventory();
				try{	
					// sums totalSpent
					totalSpent += getOxenQuantity() * store.getPrice(ItemType.OXEN);
					totalSpent += getFoodQuantity() * store.getPrice(ItemType.FOOD);
					totalSpent += getClothingQuantity() * store.getPrice(ItemType.CLOTHING);
					totalSpent += getAmmoQuantity() * store.getPrice(ItemType.AMMO);
					totalSpent += getWheelQuantity() * store.getPrice(ItemType.WHEEL);
					totalSpent += getTongueQuantity() * store.getPrice(ItemType.TONGUE);
					totalSpent += getAxleQuantity() * store.getPrice(ItemType.AXLE);
					
					// sums totalWeight
					totalWeight += getOxenQuantity() * ItemType.OXEN.weight();
					totalWeight += getFoodQuantity() * ItemType.FOOD.weight();
					totalWeight += getClothingQuantity() * ItemType.CLOTHING.weight();
					totalWeight += getAmmoQuantity() * ItemType.AMMO.weight();
					totalWeight += getWheelQuantity() * ItemType.WHEEL.weight();
					totalWeight += getTongueQuantity() * ItemType.TONGUE.weight();
					totalWeight += getAxleQuantity() * ItemType.AXLE.weight();
					
					// adds everything to a temporary inventory
					tempInventory.addOxen(getOxenQuantity()*2);
					tempInventory.addFood(getFoodQuantity()*5);
					tempInventory.addClothing(getClothingQuantity());
					tempInventory.addAmmo(getAmmoQuantity()*20);
					tempInventory.addWheel(getWheelQuantity());
					tempInventory.addTongues(getTongueQuantity());
					tempInventory.addAxle(getAxleQuantity());
					
					// updates labels to totalSpent and totalWeight
					currentMoneyLabel.setText("$ "+Integer.toString(store.getUpdatedMoney(totalSpent)));
					currentWeightLabel.setText(Integer.toString(store.getUpdatedWeight(totalWeight))+" lbs");
					
					// passes the inventory, totalSpent and totalWeight to store 
					boolean readyForSwitch = store.buy(tempInventory, totalSpent, totalWeight);
					
					if (readyForSwitch == true)
						presenter.switchToMain();
					
					
		
				}
				catch (NumberFormatException f)
				{
					JOptionPane.showMessageDialog(null, "Please enter numbers only.");
				}
				/*
				finally {
					System.out.println(party.getInventory());
					System.out.println();
					System.out.println("Money: "+party.getMoney());
					System.out.println("Weight: "+party.getWeight());
					JOptionPane.showMessageDialog(null, "Party's inventory, money, and weight are printed to console. This is the end of functionality for M6.");
					System.exit(0);
				}
				*/

			}
		}
	}
	
	private class UpdateListener implements DocumentListener {

		public void changedUpdate(DocumentEvent arg0) {
			int totalSpent = 0;
			int totalWeight = 0;
			try {	
				// sums totalSpent
				totalSpent += getOxenQuantity() * store.getPrice(ItemType.OXEN);
				totalSpent += getFoodQuantity() * store.getPrice(ItemType.FOOD);
				totalSpent += getClothingQuantity() * store.getPrice(ItemType.CLOTHING);
				totalSpent += getAmmoQuantity() * store.getPrice(ItemType.AMMO);
				totalSpent += getWheelQuantity() * store.getPrice(ItemType.WHEEL);
				totalSpent += getTongueQuantity() * store.getPrice(ItemType.TONGUE);
				totalSpent += getAxleQuantity() * store.getPrice(ItemType.AXLE);
				
				// sums totalWeight
				totalWeight += getOxenQuantity() * ItemType.OXEN.weight();
				totalWeight += getFoodQuantity() * ItemType.FOOD.weight();
				totalWeight += getClothingQuantity() * ItemType.CLOTHING.weight();
				totalWeight += getAmmoQuantity() * ItemType.AMMO.weight();
				totalWeight += getWheelQuantity() * ItemType.WHEEL.weight();
				totalWeight += getTongueQuantity() * ItemType.TONGUE.weight();
				totalWeight += getAxleQuantity() * ItemType.AXLE.weight();
				
				// sets labels to totalSpent and totalWeight
				currentMoneyLabel.setText("$ "+Integer.toString(store.getUpdatedMoney(totalSpent)));
				currentWeightLabel.setText(Integer.toString(store.getUpdatedWeight(totalWeight))+" lbs");
				
			}
			// makes sure the user input is only numbers
			catch (NumberFormatException f) {
				JOptionPane.showMessageDialog(null, "Please enter numbers only.");
			}
		}

		public void insertUpdate(DocumentEvent arg0) {
			int totalSpent = 0;
			int totalWeight = 0;
			try {	
				// sums totalSpent
				totalSpent += getOxenQuantity() * store.getPrice(ItemType.OXEN);
				totalSpent += getFoodQuantity() * store.getPrice(ItemType.FOOD);
				totalSpent += getClothingQuantity() * store.getPrice(ItemType.CLOTHING);
				totalSpent += getAmmoQuantity() * store.getPrice(ItemType.AMMO);
				totalSpent += getWheelQuantity() * store.getPrice(ItemType.WHEEL);
				totalSpent += getTongueQuantity() * store.getPrice(ItemType.TONGUE);
				totalSpent += getAxleQuantity() * store.getPrice(ItemType.AXLE);
				
				// sums totalWeight
				totalWeight += getOxenQuantity() * ItemType.OXEN.weight();
				totalWeight += getFoodQuantity() * ItemType.FOOD.weight();
				totalWeight += getClothingQuantity() * ItemType.CLOTHING.weight();
				totalWeight += getAmmoQuantity() * ItemType.AMMO.weight();
				totalWeight += getWheelQuantity() * ItemType.WHEEL.weight();
				totalWeight += getTongueQuantity() * ItemType.TONGUE.weight();
				totalWeight += getAxleQuantity() * ItemType.AXLE.weight();
				
				// sets labels to totalSpent and totalWeight
				currentMoneyLabel.setText("$ "+Integer.toString(store.getUpdatedMoney(totalSpent)));
				currentWeightLabel.setText(Integer.toString(store.getUpdatedWeight(totalWeight))+" lbs");
				
			}
			// makes sure the user input is only numbers
			catch (NumberFormatException f) {
				JOptionPane.showMessageDialog(null, "Please enter numbers only.");
			}
			
		}

		public void removeUpdate(DocumentEvent arg0) {
			int totalSpent = 0;
			int totalWeight = 0;
			try {	
				// sums totalSpent
				totalSpent += getOxenQuantity() * store.getPrice(ItemType.OXEN);
				totalSpent += getFoodQuantity() * store.getPrice(ItemType.FOOD);
				totalSpent += getClothingQuantity() * store.getPrice(ItemType.CLOTHING);
				totalSpent += getAmmoQuantity() * store.getPrice(ItemType.AMMO);
				totalSpent += getWheelQuantity() * store.getPrice(ItemType.WHEEL);
				totalSpent += getTongueQuantity() * store.getPrice(ItemType.TONGUE);
				totalSpent += getAxleQuantity() * store.getPrice(ItemType.AXLE);
				
				// sums totalWeight
				totalWeight += getOxenQuantity() * ItemType.OXEN.weight();
				totalWeight += getFoodQuantity() * ItemType.FOOD.weight();
				totalWeight += getClothingQuantity() * ItemType.CLOTHING.weight();
				totalWeight += getAmmoQuantity() * ItemType.AMMO.weight();
				totalWeight += getWheelQuantity() * ItemType.WHEEL.weight();
				totalWeight += getTongueQuantity() * ItemType.TONGUE.weight();
				totalWeight += getAxleQuantity() * ItemType.AXLE.weight();
				
				// sets labels to totalSpent and totalWeight
				currentMoneyLabel.setText("$ "+Integer.toString(store.getUpdatedMoney(totalSpent)));
				currentWeightLabel.setText(Integer.toString(store.getUpdatedWeight(totalWeight))+" lbs");
				
			}
			// makes sure the user input is only numbers
			catch (NumberFormatException f) {
				JOptionPane.showMessageDialog(null, "Please enter numbers only.");
			}
			
		}
	}
}





/*
Player can enter the store
Items for sale are displayed with their prices
Player can buy items
For every item purchased, that amount is deducted from the player's cash
For every item purchased, that items weight is deducted from the the wagon capacity
Player cannot buy an item if they don't have the money
Player cannot buy the item if the wagon's load would be exceeded
Every item purchased is added to the inventory
*/