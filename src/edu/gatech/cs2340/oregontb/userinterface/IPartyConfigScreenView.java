package edu.gatech.cs2340.oregontb.userinterface;

import edu.gatech.cs2340.oregontb.gamelogic.*;
import java.util.List;

/**
 * IPartyConfigScreenView is an interface by which OregonTrailPresenter interacts with the PartyConfigPanel.
 * @author Oregon Trailblazers (Michael Sandt, Harrison Wright, Casey Tisdel, Sean Cleary, Sean Gillespie)
 *
 */
public interface IPartyConfigScreenView {
	
	/**
	 * Returns the leader's name, as a String.
	 * @return String
	 */
	public String getName();
	
	/**
	 * Returns the other 4 party member's names, as a List.
	 * @return List<String>
	 */
	public List<String> getOtherPartyNames();

}
