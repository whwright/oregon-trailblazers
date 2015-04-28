package edu.gatech.cs2340.oregontb.userinterface;


import java.awt.*;
import javax.swing.*;
import java.awt.event.*;

/**
 * CreditsPanel is a JPanel for the credits page, which displays each team members name.
 */
public class CreditsPanel extends JPanel
{
	private OregonTrailPresenter presenter;
	private static final String BACKGROUND_IMAGE = "creditspanel_background.png"; // change this line to change the background image
	private Image background;


	
	public CreditsPanel(OregonTrailPresenter presenter) {
		//setBackground(Color.BLACK);
		this.presenter = presenter;
		setLayout(null);
		background = new ImageIcon(BACKGROUND_IMAGE).getImage();

		
		JLabel lblTeamOtBlazers = new JLabel("Brought to you by the Oregon Trail Blazers ");
		lblTeamOtBlazers.setHorizontalAlignment(SwingConstants.CENTER);
		lblTeamOtBlazers.setForeground(Color.BLACK);
		lblTeamOtBlazers.setFont(new Font("DialogInput", Font.PLAIN, 30));
		lblTeamOtBlazers.setBounds(10, 10, 780, 60);
		add(lblTeamOtBlazers);
		
		JLabel lblWilliamWright = new JLabel("William Wright");
		lblWilliamWright.setHorizontalAlignment(SwingConstants.CENTER);
		lblWilliamWright.setFont(new Font("DialogInput", Font.PLAIN, 18));
		lblWilliamWright.setForeground(Color.BLACK);
		lblWilliamWright.setBounds(10, 240, 733, 25);
		add(lblWilliamWright);
		
		JLabel lblSeanG = new JLabel("Sean Gillespie");
		lblSeanG.setHorizontalAlignment(SwingConstants.CENTER);
		lblSeanG.setFont(new Font("DialogInput", Font.PLAIN, 18));
		lblSeanG.setForeground(Color.BLACK);
		lblSeanG.setBounds(10, 209, 733, 25);
		add(lblSeanG);
		
		JLabel lblM = new JLabel("Michael Sandt");
		lblM.setHorizontalAlignment(SwingConstants.CENTER);
		lblM.setFont(new Font("DialogInput", Font.PLAIN, 18));
		lblM.setForeground(Color.BLACK);
		lblM.setBounds(10, 271, 733, 25);
		add(lblM);
		
		JLabel lblSeanCleary = new JLabel("Sean Cleary");
		lblSeanCleary.setHorizontalAlignment(SwingConstants.CENTER);
		lblSeanCleary.setFont(new Font("DialogInput", Font.PLAIN, 18));
		lblSeanCleary.setForeground(Color.BLACK);
		lblSeanCleary.setBounds(10, 302, 733, 25);
		add(lblSeanCleary);
		
		JLabel lblCaseyTisdel = new JLabel("Casey Tisdel");
		lblCaseyTisdel.setHorizontalAlignment(SwingConstants.CENTER);
		lblCaseyTisdel.setFont(new Font("DialogInput", Font.PLAIN, 18));
		lblCaseyTisdel.setForeground(Color.BLACK);
		lblCaseyTisdel.setBounds(10, 333, 733, 25);
		add(lblCaseyTisdel);
		
		JButton btnMain = new JButton("Back to Main");
		btnMain.addActionListener(new MainListener());
		btnMain.setBorder(null);
		btnMain.setBackground(Color.BLACK);
		btnMain.setFont(new Font("DialogInput", Font.BOLD, 22));
		btnMain.setForeground(Color.BLACK);
		btnMain.setBounds(100, 540, 170, 50);
		add(btnMain);
		
		JButton btnQuit = new JButton("Quit");
		btnQuit.setBorder(null);
		btnQuit.setBackground(Color.BLACK);
		btnQuit.setFont(new Font("DialogInput", Font.BOLD, 22));
		btnQuit.setForeground(Color.BLACK);
		btnQuit.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				System.exit(0);
			}
		});
		btnQuit.setBounds(550, 540, 150, 50);
		add(btnQuit);
		
		JLabel lblTeamMembers = new JLabel("Team Members:");
		lblTeamMembers.setFont(new Font("DialogInput", Font.PLAIN, 27));
		lblTeamMembers.setHorizontalAlignment(SwingConstants.CENTER);
		lblTeamMembers.setForeground(Color.BLACK);
		lblTeamMembers.setBounds(21, 165, 733, 50);
		add(lblTeamMembers);
	}
	
	//paint background
		public void paintComponent(Graphics g) {
			super.paintComponent(g);
			g.drawImage(background, 0, 0, null);
		}
	
	/**
	 * This is an ActionListener class which returns the user to the main page upon pressing "Main" button.
	 */
	private class MainListener implements ActionListener
	{
		public void actionPerformed(ActionEvent e)
		{
			presenter.switchToPanel(OregonTrailPresenter.START_PANEL);
		}
	}
}
