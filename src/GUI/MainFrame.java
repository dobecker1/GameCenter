package GUI;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.LinkedList;

import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.ScrollPaneConstants;
import javax.xml.bind.JAXBException;

import Center.Center;
import Center.CenterDB;
import Center.Game;
import Center.GameEvent;
import Center.Player;
import Center.PlaysWhat;


public class MainFrame extends JFrame {
	
	public static final MainFrame MAIN = new MainFrame();
	private static CenterDB db;
	
	//Konstanten
	private static final String CLOSE = "Beenden";
	private static final String SAVE = "Center speichern";
	private static final String ADDPLAYER = "Spieler hinzufuegen";
	private static final String RMPLAYER = "Spieler loeschen";
	private static final String RMGAME = "Spiel loeschen";
	private static final String ADDGAME = "Spiel hinzufuegen";
	private static final String SHOWPROFILES = "Profile anzeigen";
	private static final String STARTGAME = "Neues Spiel starten";
	private static final String GAMECENTER = "GAME CENTER";
	private static final String SHOWGAMES = "Aktuelles Spiel anzeigen";
	
	
	
	//Panels
	JPanel mainPanel;
	JPanel southPanel;
	JPanel eastPanel;
	JPanel eastGridPanel;
	JPanel westBoxPanel;
	JPanel westPanel;
    JPanel northPanel;	
    JPanel centerPanel;
    JPanel centerAnzeige;
	
	//Buttons
	JButton closeButton;
	JButton addPlayerButton;
	JButton removePlayerButton;
	JButton addGameButton;
	JButton showProfilesButton;
	JButton startGameButton;
	JButton showCurrentGameButton;
	JButton removeGameButton;
	JButton saveCenterButton;
	
	//Labels
	JLabel northLabel;
	
	//TextAreas
	JTextArea centerFeld;
	
	//Scroller
	JScrollPane centerScroller;
	
	 Center center;
	 
	 
	 
	 
	
	public MainFrame() {
		db = new CenterDB();
		initializeEastFrame();
		initializeNorthFrame();
		initializeWestFrame();
		initializeSouthFrame();
		initializeCenterFrame();
		
		this.setVisible(true);
		this.setSize(1300, 800);
		this.setLocation(550, 150);
		this.setDefaultCloseOperation(EXIT_ON_CLOSE);
		this.pack();
		this.center = new Center();
	}
	
	
	private void initializeNorthFrame() {
		northPanel = new JPanel();
		northLabel = new JLabel(GAMECENTER);
		northLabel.setFont(new Font("Arial", Font.BOLD, 80));
		northPanel.add(northLabel);
		this.getContentPane().add(BorderLayout.NORTH, northPanel);
		
	}
	
	private void initializeCenterFrame() {
		centerPanel = new JPanel();
		centerFeld = new JTextArea(15, 20);
		centerFeld.setLineWrap(true);
		centerFeld.setEditable(false);
		centerPanel.add(centerFeld);
		this.getContentPane().add(BorderLayout.CENTER, centerPanel);
	}
	
	private void initializeEastFrame() {
		eastPanel = new JPanel();
		eastGridPanel = new JPanel();
		addPlayerButton = new JButton(ADDPLAYER);
		removePlayerButton = new JButton(RMPLAYER);
		addGameButton = new JButton(ADDGAME);
		removeGameButton = new JButton(RMGAME);
		showProfilesButton = new JButton(SHOWPROFILES);
		addPlayerButton.addActionListener(new addPlayerActionListener());
		removePlayerButton.addActionListener(new removePlayerActionListener());
		removeGameButton.addActionListener(new removeGameActionListener());
		addGameButton.addActionListener(new addGameActionListener());
		showProfilesButton.addActionListener(new showDataActionListener());
		eastGridPanel.add(addPlayerButton);
		eastGridPanel.add(addGameButton);
		eastGridPanel.add(removePlayerButton);
		eastGridPanel.add(removeGameButton);
		eastGridPanel.add(showProfilesButton);
		eastGridPanel.setLayout(new GridLayout(3, 2, 5, 10));
		eastPanel.add(eastGridPanel);
		this.getContentPane().add(BorderLayout.EAST, eastPanel);
		
	} 
	
	private void initializeWestFrame() {
		westBoxPanel = new JPanel();
		westPanel = new JPanel();
		startGameButton = new JButton(STARTGAME);
		showCurrentGameButton = new JButton(SHOWGAMES);
		
		showCurrentGameButton.addActionListener(new showCurrentGameActionListener());
		startGameButton.addActionListener(new startNewGameActionListener());
		westBoxPanel.add(startGameButton);
		westBoxPanel.add(Box.createRigidArea(new Dimension(0,10)));
		westBoxPanel.add(Box.createRigidArea(new Dimension(0,10)));
		westBoxPanel.add(showCurrentGameButton);
		westBoxPanel.setLayout(new BoxLayout(westBoxPanel, BoxLayout.Y_AXIS));
		westPanel.add(westBoxPanel);
		
		this.getContentPane().add(BorderLayout.WEST, westPanel);
		
	}
	
	private void initializeSouthFrame() {
		
		closeButton = new JButton(CLOSE);
		saveCenterButton = new JButton(SAVE);
		southPanel = new JPanel();
		southPanel.add(saveCenterButton);
		southPanel.add(closeButton);
		saveCenterButton.addActionListener(new saveCenterActionListener());
		closeButton.addActionListener(new ActionListener() {
			
		    @Override
		    public void actionPerformed(ActionEvent e) {
		    	MainFrame.this.dispose();
		    }
		});   
		this.getContentPane().add(BorderLayout.SOUTH, southPanel);
		
	}
	
	private class showCurrentGameActionListener implements ActionListener {
		@Override
		public void actionPerformed(ActionEvent actionEvent) {
			new ShowCurrentGameFrame(MainFrame.MAIN);
		}
	}
	
	private class startNewGameActionListener implements ActionListener {
		@Override
		public void actionPerformed(ActionEvent actionEvent) {
			new StartNewGameFrame(MainFrame.MAIN);
		}
	}
	
	
	private class addPlayerActionListener implements ActionListener {
		@Override
		public void actionPerformed(ActionEvent actionEvent) {
			 new AddPlayerFrame(MainFrame.MAIN);
		}
	}
	
	private class addGameActionListener implements ActionListener {
		@Override
		public void actionPerformed(ActionEvent actionEvent) {
			new AddGameFrame(MainFrame.MAIN);
		}
	}
	
	private class removePlayerActionListener implements ActionListener {
		@Override
		public void actionPerformed(ActionEvent actionEvent) {
			new RemovePlayerFrame(MainFrame.MAIN);
		}
	}
	
	private class removeGameActionListener implements ActionListener {
		@Override
		public void actionPerformed(ActionEvent e) {
			new RemoveGameFrame(MainFrame.MAIN);			
		}
		
	}
	
	private class saveCenterActionListener implements ActionListener  {

		@Override
		public void actionPerformed(ActionEvent e){
			db.runXmlBuilder();
		
	    }
	
	}
	
	private class showDataActionListener implements ActionListener {

		@Override
		public void actionPerformed(ActionEvent e) {
			new ShowProfilesFrame(MainFrame.MAIN);
			
		}
		
	}
	
	
	public Center getCenter() {
		return this.center;
	}
	
	
	public static void main(String[] args) {
		MainFrame main = MainFrame.MAIN;
		db.runXmlReader();
		db.runHistoryReader();
	}

	
	
	
	
	
	
	
	
	

}
