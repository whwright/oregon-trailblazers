package edu.gatech.cs2340.oregontb.userinterface;

import javax.swing.*;

import java.awt.Font;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.*;
import java.util.ArrayList;
import edu.gatech.cs2340.oregontb.gamelogic.*;

public class LoadGamePanel extends JPanel {
	
	private static final String BACKGROUND_IMAGE = "loadpanel_background.png"; // change this line to change the background image
	private Image background;
	private OregonTrailPresenter presenter;
	private JRadioButton btn0, btn1, btn2, btn3, btn4, btn5, btn6, btn7;
	private JButton btnLoad, btnDelete, btnDown, btnUp, btnDeleteAll, btnBack;
	private ButtonGroup buttons;
	
	private BufferedReader reader;
	private BufferedWriter writer;
	private ArrayList<String> names;
	private Party party;
	private ImageIcon arrowUp, arrowDown;
	
	
	public LoadGamePanel(OregonTrailPresenter presenter) {
		setBackground(Color.BLACK);
		setLayout(null);
		this.presenter = presenter;
		background = new ImageIcon(BACKGROUND_IMAGE).getImage();
		Listener listener = new Listener();
		
		// creates array of strings that are saved game names
		this.names = new ArrayList<String>();
		// puts all the names in the array games
		try {
			this.reader = new BufferedReader(new FileReader(new File("Saved_Games.txt")));
			String line = null;
			while ( (line = reader.readLine()) != null ) 
				names.add(line);
		} catch (FileNotFoundException e) {
			System.out.println("The Saved_Games file is missing!");
			e.printStackTrace();
		} catch (IOException e1) {
			e1.printStackTrace();
		} finally {
			try {
				reader.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		
		JLabel lblSavedGames = new JLabel("Saved games");
		lblSavedGames.setForeground(Color.WHITE);
		lblSavedGames.setFont(new Font("DialogInput", Font.PLAIN, 28));
		lblSavedGames.setBounds(483, 142, 198, 36);
		add(lblSavedGames);
		
		JSeparator separator = new JSeparator();
		separator.setForeground(Color.WHITE);
		separator.setBounds(476, 179, 195, 10);
		add(separator);
		
		buttons = new ButtonGroup();
		
		// RADIO BUTTONS
		btn0 = new JRadioButton("empty");
		btn0.setFont(new Font("DialogInput", Font.PLAIN, 13));
		btn0.setForeground(Color.WHITE);
		btn0.setActionCommand(null);
		btn0.setBounds(504, 195, 233, 23);
		add(btn0);
		
		btn1 = new JRadioButton("empty");
		btn1.setFont(new Font("DialogInput", Font.PLAIN, 13));
		btn1.setForeground(Color.WHITE);
		btn1.setActionCommand(null);
		btn1.setBounds(504, 224, 233, 23);
		add(btn1);
		
		btn2 = new JRadioButton("empty");
		btn2.setFont(new Font("DialogInput", Font.PLAIN, 13));
		btn2.setForeground(Color.WHITE);
		btn2.setActionCommand(null);
		btn2.setBounds(504, 253, 233, 23);
		add(btn2);
		
		btn3 = new JRadioButton("empty");
		btn3.setFont(new Font("DialogInput", Font.PLAIN, 13));
		btn3.setForeground(Color.WHITE);
		btn3.setActionCommand(null);
		btn3.setBounds(504, 282, 233, 23);
		add(btn3);
		
		btn4 = new JRadioButton("empty");
		btn4.setFont(new Font("DialogInput", Font.PLAIN, 13));
		btn4.setForeground(Color.WHITE);
		btn4.setActionCommand(null);
		btn4.setBounds(504, 311, 233, 23);
		add(btn4);
		
		btn5 = new JRadioButton("empty");
		btn5.setFont(new Font("DialogInput", Font.PLAIN, 13));
		btn5.setForeground(Color.WHITE);
		btn5.setActionCommand(null);
		btn5.setBounds(504, 340, 233, 23);
		add(btn5);
		
		btn6 = new JRadioButton("empty");
		btn6.setFont(new Font("DialogInput", Font.PLAIN, 13));
		btn6.setForeground(Color.WHITE);
		btn6.setActionCommand(null);
		btn6.setBounds(504, 369, 233, 23);
		add(btn6);
		
		btn7 = new JRadioButton("empty");
		btn7.setFont(new Font("DialogInput", Font.PLAIN, 13));
		btn7.setForeground(Color.WHITE);
		btn7.setActionCommand(null);
		btn7.setBounds(504, 398, 233, 23);
		add(btn7);
		
		buttons = new ButtonGroup();
		buttons.add(btn0);
		buttons.add(btn1);
		buttons.add(btn2);
		buttons.add(btn3);
		buttons.add(btn4);
		buttons.add(btn5);
		buttons.add(btn6);
		buttons.add(btn7);
		
		// JTEXTFIELD
		JTextArea viewPartyTextField = new JTextArea();
		viewPartyTextField.setFont(new Font("DialogInput", Font.PLAIN, 15));
		viewPartyTextField.setForeground(Color.WHITE);
		viewPartyTextField.setText("To load your game, please select the button next to your player's name and click Load. " +
				"Not all saved games may be visible. To see more games please click the up or down arrow. If you wish to delete " +
				"an old saved game, select the game and click Delete. Or, if you wish to delete all saved games press Delete All. If your" +
				"game does not appear, it has been lost, and the OT Blazer's apologize for the inconvenience.");
		viewPartyTextField.setEditable(false);
		viewPartyTextField.setWrapStyleWord(true);
		viewPartyTextField.setLineWrap(true);
		viewPartyTextField.setBackground(Color.BLACK);
		viewPartyTextField.setBounds(36, 179, 359, 177);
		add(viewPartyTextField);
		
		btnLoad = new JButton("Load");
		btnLoad.setBorder(null);
		btnLoad.setBackground(Color.BLACK);
		btnLoad.setFont(new Font("DialogInput", Font.BOLD, 22));
		btnLoad.setForeground(Color.WHITE);
		btnLoad.setBounds(482, 446, 100, 30);
		btnLoad.addActionListener(listener);
		add(btnLoad);
		
		btnDelete = new JButton("Delete");
		btnDelete.setBorder(null);
		btnDelete.setBackground(Color.BLACK);
		btnDelete.setFont(new Font("DialogInput", Font.BOLD, 22));
		btnDelete.setForeground(Color.WHITE);
		btnDelete.setBounds(639, 446, 120, 30);
		btnDelete.addActionListener(listener);
		add(btnDelete);
		
		//btnDown = new JButton("Down");
		btnDown = new ArrowButton(SwingConstants.SOUTH, 1, 25);
		btnDown.setForeground(Color.WHITE);
		btnDown.setBounds(428, 332, 70, 70);
		btnDown.addActionListener(listener);
		add(btnDown);
		
		//btnUp = new JButton("Up");
		btnUp = new ArrowButton(SwingConstants.NORTH, 1, 25);
		btnUp.setForeground(Color.WHITE);
		btnUp.setBounds(428, 224, 70, 70);
		btnUp.addActionListener(listener);
		add(btnUp);
		
		btnDeleteAll = new JButton("Delete All");
		btnDeleteAll.setBorder(null);
		btnDeleteAll.setBackground(Color.BLACK);
		btnDeleteAll.setFont(new Font("DialogInput", Font.BOLD, 22));
		btnDeleteAll.setForeground(Color.WHITE);
		btnDeleteAll.setBounds(639, 506, 120, 30);
		btnDeleteAll.addActionListener(listener);
		add(btnDeleteAll);
		
		btnBack = new JButton("Back");
		btnBack.setBorder(null);
		btnBack.setBackground(Color.BLACK);
		btnBack.setFont(new Font("DialogInput", Font.BOLD, 22));
		btnBack.setForeground(Color.WHITE);
		btnBack.addActionListener(listener);
		btnBack.setBounds(482, 506, 100, 30);
		add(btnBack);
	}
	
	/* (non-Javadoc)
	 * @see javax.swing.JComponent#paintComponent(java.awt.Graphics)
	 */
	public void paintComponent(Graphics g) {
		super.paintComponent(g);
		g.drawImage(background, 0, 0, null);
	}
	
	/**
	 * Sets the 8 JRadioButtons on the panel to the saved games in names, from
	 * i to i+7. If the name doesn't exist, it will read "empty".
	 * @param i
	 */
	public void initialize(int i) {
		// clears selection so it doesnt mess up the scrolling
		buttons.clearSelection();
		int size = names.size();
		
		// for each button, that exists in names
		//			if it has a name set the name and ActionCommand
		// 			else set name to "empty" and ActionCommand to null
		
		if (i < size) {
			if (names.get(i) != null) {
				btn0.setText(names.get(i));
				btn0.setActionCommand(names.get(i));
			}
			else {
				btn0.setText("empty");
				btn0.setActionCommand(null);
			}
		}
		else {
			btn0.setText("empty");
			btn0.setActionCommand(null);
		}
		
		i++;
		if (i < size) {
			if (names.get(i) != null) {
				btn1.setText(names.get(i));
				btn1.setActionCommand(names.get(i));
			}
			else {
				btn1.setText("empty");
				btn1.setActionCommand(null);
			}
				
		}
		else {
			btn1.setText("empty");
			btn1.setActionCommand(null);
		}
		
		i++;
		if (i < size) {
			if (names.get(i) != null) {
				btn2.setText(names.get(i));
				btn2.setActionCommand(names.get(i));
			}
			else {
				btn2.setText("empty");
				btn2.setActionCommand(null);
			}
		}
		else {
			btn2.setText("empty");
			btn2.setActionCommand(null);
		}
			
		i++;
		if (i < size) {
			if (names.get(i) != null) {
				btn3.setText(names.get(i));
				btn3.setActionCommand(names.get(i));
			}
			else {
				btn3.setText("empty");
				btn3.setActionCommand(null);
			}
		}
		else {
			btn3.setText("empty");
			btn3.setActionCommand(null);
		}
			
		i++;
		if (i < size) {
			if (names.get(i) != null) {
				btn4.setText(names.get(i));
				btn4.setActionCommand(names.get(i));
			}
			else {
				btn4.setText("empty");
				btn4.setActionCommand(null);
			}
		}
		else {
			btn4.setText("empty");
			btn4.setActionCommand(null);
		}
		
		i++;
		if (i < size) {
			if (names.get(i) != null) {
				btn5.setText(names.get(i));
				btn5.setActionCommand(names.get(i));
			}
			else {
				btn5.setText("empty");
				btn5.setActionCommand(null);
			}
		}
		else {
			btn5.setText("empty");
			btn5.setActionCommand(null);
		}
		
		i++;
		if (i < size) {
			if (names.get(i) != null) {
				btn6.setText(names.get(i));
				btn6.setActionCommand(names.get(i));
			}
			else {
				btn6.setText("empty");
				btn6.setActionCommand(null);
			}
		}
		else {
			btn6.setText("empty");
			btn6.setActionCommand(null);
		}
		
		i++;
		if (i < size) {
			if (names.get(i) != null) {
				btn7.setText(names.get(i));
				btn7.setActionCommand(names.get(i));
			}
			else {
				btn7.setText("empty");
				btn7.setActionCommand(null);
			}
		}
		else {
			btn7.setText("empty");
			btn7.setActionCommand(null);
		}
	}
	
	/**
	 * This is the ActionListener class for the LoadGamePanel. It is called when the user presses
	 * Load, Delete, Up, or Down.
	 */
	private class Listener implements ActionListener {

		private int i = 0;

		public void actionPerformed(ActionEvent e) {
			// load
			if (e.getSource() == btnLoad) {
				// gets name of saved game
				String saveName = buttons.getSelection().getActionCommand();
				
				InputStream file, buffer;
				ObjectInput input = null;
				 try {
			      // loads Party object and passes it to presenter
			      file = new FileInputStream("Save_"+saveName+".txt");
			      buffer = new BufferedInputStream(file);
			      input = new ObjectInputStream (buffer);
			      presenter.loadGame((Party) input.readObject(), saveName);
				 }
				 catch (FileNotFoundException e1) {
					 e1.printStackTrace();
				 }
				 catch (IOException e2) {
					 e2.printStackTrace();
				 } catch (ClassNotFoundException e3) {
					e3.printStackTrace();
				} finally {
					try {
						input.close();
					} catch (IOException e1) {
						e1.printStackTrace();
					}
				}
			     
				
			}
			
			// delete
			else if (e.getSource() == btnDelete) {
				boolean ready = true;
				// will ask user again if they do not select a game to delete
				if (buttons.getSelection() == null) {
					JOptionPane.showMessageDialog(null, "Please choose a game to delete.");
				}
				else {
					// gets game name
					String gameName = buttons.getSelection().getActionCommand(); // gets the name of the button selected
					// makes sure the selected game isnt an empty save game
					if (gameName == null) {
						JOptionPane.showMessageDialog(null, "Please choose a saved game that is not empty.");
						ready = false;
					}
					// if its not an empty save gave
					if (ready) {
						Object[] options = {"Yes", "No"};
						// confirm with user
						int choice = JOptionPane.showOptionDialog(null, "Are you sure you want to delete "+gameName+"?", 
								"Are you sure?",JOptionPane.YES_NO_OPTION,  JOptionPane.QUESTION_MESSAGE, null, options, options[1]);
						// delete game
						if (choice == 0)
							delete(gameName);
					}
				}
			}
			
			else if (e.getSource() == btnDeleteAll) {
				Object[] options = {"Yes", "No"};
				// confirm with user
				int choice = JOptionPane.showOptionDialog(null, "Are you sure you want to delete all of your saved games?", 
						"Are you sure?",JOptionPane.YES_NO_OPTION,  JOptionPane.QUESTION_MESSAGE, null, options, options[1]);
				// delete game
				if (choice == 0) {
					while (!names.isEmpty())
						delete(names.get(0));
					}
				}
				

			
			
			// will scroll up (if possible) one saved game
			else if (e.getSource() == btnUp) {
				if (--i >= 0)
					initialize(i);
			}
			
			// will scroll down one saved game
			else if (e.getSource() == btnDown) {
				//if (++i < names.size();
				initialize(++i);
			}
			
			else if (e.getSource() == btnBack) {
				presenter.switchToPanel(OregonTrailPresenter.START_PANEL);
			}
		}
		
	}
	
	/**
	 * This method deletes the saved game of the name thats is passed in.
	 * @param gameName User's name that the game is saved under
	 */
	private void delete(String gameName) {
		// if the selected button is a saved game
		if (gameName != null) { // if there is a saved game at that button
			// delete the file
			File f = new File("Save_"+gameName+".txt");
			f.delete();
			
			// removes the name from the names list
			for (int i=0; i< names.size(); i++) {
				String name = names.get(i).trim();
				if (name.equals(gameName))
					names.remove(i);
			}
			try {
				// this will erase the file										
				writer = new BufferedWriter(new FileWriter(new File("Saved_Games.txt")));
				// this writes all the names in the names list (and not the one we just deleted)
				for (String name : names)
					writer.write(name+"\n");
			} catch (IOException e1) {
				e1.printStackTrace();
			} finally {
				try {
					writer.close();
				} catch (IOException e1) {
					e1.printStackTrace();
				}
			}
			// updates the buttons
			initialize(0);
		}
		
	}
}
