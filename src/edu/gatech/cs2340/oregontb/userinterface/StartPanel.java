package edu.gatech.cs2340.oregontb.userinterface;

import javax.swing.*;
import java.awt.event.*;
import java.awt.*;
import java.io.*;
import javax.swing.border.LineBorder;



/**
 * StartPanel is the JPanel that is the "main screen." The user can choose to start a new game, load an old game,
 * see credits, or quit.
 */
public class StartPanel extends JPanel 
{
	private static final String BACKGROUND_IMAGE = "startpanel_background.png"; // change this line to change the background image
	private OregonTrailPresenter presenter;
	private Image background;
	private JButton btnNewGame, btnLoadGame, btnCredits, btnQuit;
	
	/**
	 * Create the panel.
	 */
	public StartPanel(OregonTrailPresenter presenter) {
		//setBackground(Color.BLACK);
		this.presenter = presenter;
		background = new ImageIcon(BACKGROUND_IMAGE).getImage();
		setLayout(null);
		
		Listener listener = new Listener();
		
		btnNewGame = new JButton("");
		btnNewGame.setBorder(new LineBorder(Color.WHITE));
		btnNewGame.setBackground(Color.BLACK);
		btnNewGame.setOpaque(false);
		btnNewGame.addActionListener(listener);
		btnNewGame.setBounds(358, 268, 220, 50);
		add(btnNewGame);
		
		btnLoadGame = new JButton("");
		btnLoadGame.setBorder(new LineBorder(Color.WHITE));
		btnLoadGame.setBackground(Color.BLACK);
		btnLoadGame.setOpaque(false);
		btnLoadGame.setBounds(597, 268, 140, 50);
		btnLoadGame.addActionListener(listener);
		add(btnLoadGame);
		
		btnQuit = new JButton("");
		btnQuit.setBorder(new LineBorder(Color.WHITE));
		btnQuit.setBackground(Color.BLACK);
		btnQuit.setOpaque(false);
		btnQuit.setFont(new Font("DialogInput", Font.BOLD, 22));
		btnQuit.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				System.exit(0);
			}
		});
		btnQuit.setBounds(597, 343, 130, 50);
		add(btnQuit);
		
		btnCredits = new JButton("");
		btnCredits.setBorder(new LineBorder(Color.WHITE));
		btnCredits.setBackground(Color.BLACK);
		btnCredits.setOpaque(false);
		btnCredits.setFont(new Font("DialogInput", Font.BOLD, 22));
		btnCredits.addActionListener(listener);
		btnCredits.setBounds(370, 343, 205, 50);
		add(btnCredits);
//		setFocusTraversalPolicy(new FocusTraversalOnArray(new Component[]{btnNewGame, btnLoadGame, btnQuit, btnCredits}));

	}
	
	/* (non-Javadoc)
	 * @see javax.swing.JComponent#paintComponent(java.awt.Graphics)
	 */
	public void paintComponent(Graphics g) {
		super.paintComponent(g);
		g.drawImage(background, 0, 0, null);
	}
	
	/**
	 * This is the ActionListener class for the LOAD GAME, CREDITS, and NEW GAME buttons.
	 * When pressed, the buttons will switch to the corresponding panel.
	 */
	private class Listener implements ActionListener {

		//@Override
		public void actionPerformed(ActionEvent e) {
			// new game
			if (e.getSource() == btnNewGame)
				presenter.switchToPanel(OregonTrailPresenter.PARTY_PANEL);
			
			// credits
			else if (e.getSource() == btnCredits)
				presenter.switchToPanel(OregonTrailPresenter.CREDIT_PANEL);
			
			// load game
			else if (e.getSource() == btnLoadGame) 
				presenter.switchToLoadGamePanel();
				
		}
		
	}
}
