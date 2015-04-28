package edu.gatech.cs2340.oregontb.gamelogic;

import javax.swing.JOptionPane;

public class Store 
{
	private double priceScaleFactor;
	private Party party;
	
	public Store(double priceScaleFactor, Party party) 
	{
		this.priceScaleFactor = priceScaleFactor;
		this.party = party;
	}
	
	public boolean buy(Inventory inventory, int totalSpent, int totalWeight)
	{
		// checks to make sure the wagon isnt over max weight and the party hasnt spent more 
		// money than they have
		if (party.getMoney() < totalSpent && party.getMaxWeight() < totalWeight)
		{
			JOptionPane.showMessageDialog(null, "You do not have enough money, or enough space in your wagon.");
			return false;
		}
		else if (party.getMoney() < totalSpent && party.getMaxWeight() >= totalWeight)
		{
			JOptionPane.showMessageDialog(null, "You do not have enough money.");
			return false;
		}
		else if (party.getMoney() >= totalSpent && party.getMaxWeight() < totalWeight)
		{
			JOptionPane.showMessageDialog(null, "You do not have enough space in your wagon.");
			return false;
		}
		else
		{
			party.setMoney(party.getMoney() - totalSpent);
			party.setWeight(party.getWeight() + totalWeight);
			addToInventory(inventory);
			return true;
			
		}
	}
	
	private void addToInventory(Inventory inventory) {
		party.getInventory().addAmmo(inventory.getAmmo());
		party.getInventory().addAxle(inventory.getAxles());
		party.getInventory().addFood(inventory.getFood());
		party.getInventory().addOxen(inventory.getOxen());
		party.getInventory().addClothing(inventory.getClothing());
		party.getInventory().addWheel(inventory.getWheels());
		party.getInventory().addTongues(inventory.getTongues());
	}
	
	public int getPartyMoney()
	{
		return party.getMoney();
	}
	
	public int getPartyWeight() {
		return party.getWeight();
	}
	
	public int getUpdatedMoney(int totalSpent)
	{
		return (party.getMoney() - totalSpent);
	}
	
	public int getUpdatedWeight(int totalWeight)
	{
		return (party.getWeight() + totalWeight);
	}

	
	public double getPrice(ItemType item)
	{
		return (double)(item.basePrice() * priceScaleFactor);
	}
	
	public double getPriceScaleFactor() {
		return priceScaleFactor;
	}
}
