package edu.gatech.cs2340.oregontb.userinterface;

import java.util.List;
import java.util.ArrayList;

import javax.swing.JPanel;

import edu.gatech.cs2340.oregontb.gamelogic.Pace;
import edu.gatech.cs2340.oregontb.gamelogic.Profession;
import edu.gatech.cs2340.oregontb.gamelogic.Rations;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.JRadioButton;
import javax.swing.ButtonGroup;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.Image;
import java.awt.Graphics;
import javax.swing.ImageIcon;
import javax.swing.JComboBox;
import java.awt.Font;
import java.awt.Color;
import javax.swing.JPopupMenu;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import javax.swing.JMenuItem;
import javax.swing.JMenu;
import javax.swing.border.LineBorder;

/**
 * PartyConfigPanel is a JPanel where the user sets up their party.
 */
public class PartyConfigPanel extends JPanel implements IPartyConfigScreenView 
{
	private JTextField textField;
	private JTextField textField_1;
	private JTextField textField_2;
	private JTextField textField_3;
	private JTextField textField_4;
	// added radio buttons to instance data -wwright
	private JRadioButton rdbtnBanker, rdbtnCarpenter, rdbtnFarmer, rdbtnLeisurely, rdbtnGrueling, rdbtnSteady, rdbtnBarebones, rdbtnMeager, rdbtnNormal, rdbtnWellFed;
	private JButton btnGo, btnBack;
	private final ButtonGroup buttonGroup = new ButtonGroup();
	private final ButtonGroup buttonGroup_1 = new ButtonGroup();
	private final ButtonGroup buttonGroup_2 = new ButtonGroup();

	private static final String BACKGROUND_IMAGE = "partyconfigpanel_background.png";
	private Image background;
	
	private JComboBox professionComboBox, paceComboBox, rationComboBox;
	
	private OregonTrailPresenter presenter;

	/**
	 * Create the panel.
	 */
	public PartyConfigPanel(OregonTrailPresenter presenter) {
		this.presenter = presenter;
		//setBackground(Color.BLACK);
		background = new ImageIcon(BACKGROUND_IMAGE).getImage();
		
		setLayout(null);
		
		JLabel lblName = new JLabel("Your name:");
		lblName.setForeground(Color.WHITE);
		lblName.setFont(new Font("DialogInput", Font.BOLD, 20));
		lblName.setBounds(150, 116, 150, 30);
		add(lblName);
		
		textField = new JTextField();
		textField.setFont(new Font("DialogInput", Font.PLAIN, 14));
		textField.setBounds(281, 116, 150, 30);
		add(textField);
		textField.setColumns(10);
		
		JLabel lblCompanion = new JLabel("Companion 1:");
		lblCompanion.setForeground(Color.WHITE);
		lblCompanion.setFont(new Font("DialogInput", Font.BOLD, 20));
		lblCompanion.setBounds(125, 210, 150, 30);
		add(lblCompanion);
		
		textField_1 = new JTextField();
		textField_1.setFont(new Font("DialogInput", Font.PLAIN, 14));
		textField_1.setColumns(10);
		textField_1.setBounds(281, 210, 150, 30);
		add(textField_1);
		
		JLabel lblCompanion_1 = new JLabel("Companion 2:");
		lblCompanion_1.setForeground(Color.WHITE);
		lblCompanion_1.setFont(new Font("DialogInput", Font.BOLD, 20));
		lblCompanion_1.setBounds(125, 304, 150, 30);
		add(lblCompanion_1);
		
		textField_2 = new JTextField();
		textField_2.setFont(new Font("DialogInput", Font.PLAIN, 14));
		textField_2.setColumns(10);
		textField_2.setBounds(281, 492, 150, 30);
		add(textField_2);
		
		JLabel lblCompanion_2 = new JLabel("Companion 3:");
		lblCompanion_2.setForeground(Color.WHITE);
		lblCompanion_2.setFont(new Font("DialogInput", Font.BOLD, 20));
		lblCompanion_2.setBounds(125, 398, 150, 30);
		add(lblCompanion_2);
		
		textField_3 = new JTextField();
		textField_3.setFont(new Font("DialogInput", Font.PLAIN, 14));
		textField_3.setColumns(10);
		textField_3.setBounds(281, 398, 150, 30);
		add(textField_3);
		
		JLabel lblCompanion_3 = new JLabel("Companion 4:");
		lblCompanion_3.setForeground(Color.WHITE);
		lblCompanion_3.setFont(new Font("DialogInput", Font.BOLD, 20));
		lblCompanion_3.setBounds(125, 492, 150, 25);
		add(lblCompanion_3);
		
		textField_4 = new JTextField();
		textField_4.setFont(new Font("DialogInput", Font.PLAIN, 14));
		textField_4.setColumns(10);
		textField_4.setBounds(281, 304, 150, 30);
		add(textField_4);
		
		JLabel lblChooseAProfession = new JLabel("Choose a Profession:");
		lblChooseAProfession.setForeground(Color.WHITE);
		lblChooseAProfession.setFont(new Font("DialogInput", Font.BOLD, 20));
		lblChooseAProfession.setBounds(546, 116, 235, 30);
		add(lblChooseAProfession);
		
		JLabel lblChooseAPace = new JLabel("Choose a Pace:");
		lblChooseAPace.setForeground(Color.WHITE);
		lblChooseAPace.setFont(new Font("DialogInput", Font.BOLD, 20));
		lblChooseAPace.setBounds(546, 210, 230, 35);
		add(lblChooseAPace);
		
		JLabel lblChooseARation = new JLabel("Choose a Ration:");
		lblChooseARation.setForeground(Color.WHITE);
		lblChooseARation.setFont(new Font("DialogInput", Font.BOLD, 20));
		lblChooseARation.setBounds(546, 304, 220, 30);
		add(lblChooseARation);
		
		Listener listener = new Listener();
		
		btnGo = new JButton("Go!");
		btnGo.setBorder(new LineBorder(new Color(0, 0, 0)));
		btnGo.setBackground(Color.BLACK);
		btnGo.setFont(new Font("DialogInput", Font.BOLD, 22));
		btnGo.setForeground(Color.WHITE);
		btnGo.addActionListener(listener);
		btnGo.setBounds(505, 451, 90, 30);
		add(btnGo);
		
		btnBack = new JButton("Back");
		btnBack.setBorder(new LineBorder(new Color(0, 0, 0)));
		btnBack.setBackground(Color.BLACK);
		btnBack.setFont(new Font("DialogInput", Font.BOLD, 22));
		btnBack.setForeground(Color.WHITE);
		btnBack.addActionListener(listener);
		btnBack.setBounds(660, 451, 90, 30);
		add(btnBack);
		
		String[] professions = {"Banker", "Farmer", "Carpenter" };
		professionComboBox = new JComboBox(professions);
		professionComboBox.setForeground(Color.BLACK);
		professionComboBox.setFont(new Font("DialogInput", Font.PLAIN, 20));
		professionComboBox.setBounds(556, 152, 150, 30);
		professionComboBox.setSelectedIndex(0);
		professionComboBox.addActionListener(listener);
		//professionComboBox.setSize(new Dimension(170,100));
		//professionComboBox.setPreferredSize(new Dimension(200,50));
		add(professionComboBox);
		
		String[] paces = { "Leisurely", "Steady", "Grueling" };
		paceComboBox = new JComboBox(paces);
		paceComboBox.setForeground(Color.BLACK);
		paceComboBox.setFont(new Font("DialogInput", Font.PLAIN, 20));
		paceComboBox.setBounds(556, 251, 150, 27);
		paceComboBox.setSelectedIndex(1);
		paceComboBox.addActionListener(listener);
		add(paceComboBox);
		
		String[] rations = { "Meager", "Barebones", "Normal", "Well Fed" };
		rationComboBox = new JComboBox(rations);
		rationComboBox.setForeground(Color.BLACK);
		rationComboBox.setFont(new Font("DialogInput", Font.PLAIN, 20));
		rationComboBox.setSelectedIndex(2);
		rationComboBox.setBounds(556, 340, 150, 27);
		rationComboBox.addActionListener(listener);
		add(rationComboBox);
//		setFocusTraversalPolicy(new FocusTraversalOnArray(new Component[]{textField, textField_1, textField_2, textField_3, textField_4, professionComboBox, paceComboBox, rationComboBox, btnGo, btnBack}));

		
		// JTEXTFIELD
		JTextArea viewPartyTextField = new JTextArea();
		viewPartyTextField.setFont(new Font("DialogInput", Font.PLAIN, 14));
		viewPartyTextField.setForeground(Color.WHITE);
		viewPartyTextField.setText("Welcome adventurer! Before you can hit the trail you need to choose your profession, the pace you wish to travel at, " +
				"and how you want to ration your food. You can also enter your name and the names of 4 companions to begin. If you do not wish to choose names, " +
				"random ones can be assigned. When you are ready press go. To return to the main screen press back.");
		viewPartyTextField.setEditable(false);
		viewPartyTextField.setWrapStyleWord(true);
		viewPartyTextField.setLineWrap(true);
		viewPartyTextField.setBackground(Color.BLACK);
		viewPartyTextField.setBounds(135, 10, 631, 90);
		add(viewPartyTextField);
		
	}
	
	//@Override
	public String getName() {
		return textField.getText();
	}

	//@Override
	public List<String> getOtherPartyNames() {
		List<String> names = new ArrayList<String>();
		names.add(textField_1.getText());
		names.add(textField_2.getText());
		names.add(textField_3.getText());
		names.add(textField_4.getText());
		return names;
	}
	
	//paint background
	public void paintComponent(Graphics g) {
		super.paintComponent(g);
		g.drawImage(background, 0, 0, null);
	}
	
	private class Listener implements ActionListener 
	{
		Profession profession = Profession.BANKER;
		Pace pace = Pace.STEADY;
		Rations rations = Rations.NORMAL;
		
		//@Override
		public void actionPerformed(ActionEvent e) 
		{
			// updates profession if combo box is used
			if (e.getSource() == professionComboBox) {
				JComboBox cb = (JComboBox) e.getSource();
				String prof = (String) cb.getSelectedItem();
				if (prof.equals("Banker"))
					profession = Profession.BANKER;
				else if (prof.equals("Farmer"))
					profession = Profession.FARMER;
				else if (prof.equals("Carpenter"))
					profession = Profession.CARPENTER;
			}
			
			// updates pace if combo box is used
			if (e.getSource() == paceComboBox) {
				JComboBox cb = (JComboBox) e.getSource();
				String tempPace = (String) cb.getSelectedItem();
				if (tempPace.equals("Leisurely"))
					pace = Pace.LEISURELY;
				else if (tempPace.equals("Steady"))
					pace = Pace.STEADY;
				else if (tempPace.equals("Grueling"))
					pace = Pace.GRUELING;
			}
			
			// update rations if combo box is used
			if (e.getSource() == rationComboBox) {
				JComboBox cb = (JComboBox) e.getSource();
				String ration = (String) cb.getSelectedItem();
				if (ration.equals("Meager"))
					rations = Rations.MEAGER;
				else if (ration.equals("Normal"))
					rations = Rations.NORMAL;
				else if (ration.equals("Barebones"))
					rations = Rations.BAREBONES;
				else if (ration.equals("Well Fed"))
					rations = Rations.WELL_FED;
			}

			if (e.getSource() == btnGo)	
				presenter.partyGoButtonPressed(profession, pace, rations);
			else if (e.getSource() == btnBack)
				presenter.switchToPanel(OregonTrailPresenter.START_PANEL);
			
		}
	}
	private static void addPopup(Component component, final JPopupMenu popup) {
		component.addMouseListener(new MouseAdapter() {
			public void mousePressed(MouseEvent e) {
				if (e.isPopupTrigger()) {
					showMenu(e);
				}
			}
			public void mouseReleased(MouseEvent e) {
				if (e.isPopupTrigger()) {
					showMenu(e);
				}
			}
			private void showMenu(MouseEvent e) {
				popup.show(e.getComponent(), e.getX(), e.getY());
			}
		});
	}
}
