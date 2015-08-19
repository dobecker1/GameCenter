package GUI;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;
import java.util.Vector;

import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.ListSelectionModel;
import javax.swing.ScrollPaneConstants;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;

import Center.Game;
import Center.Player;

public class ShowProfilesFrame extends JDialog {
	//Konstanten
	private static final String CLOSE = "Schliessen";
	
	//Buttons
	JButton closeButton;
	JButton westButton;
	
	//JComboBox
	JComboBox<Game> gameBox;
	
	//JScrollPabe
	JScrollPane scroller;
	
	//JList
	JList<Player> playerList;
	
	//JTextArea
	JTextArea area;
	
	//JLabel
	JLabel lblPlayer;
	JLabel lblgame;
	
	//JPanel
	JPanel southPanel;
	JPanel centerPanel;
	JPanel centerBoxPanel;
	JPanel westPanel;
	JPanel westBoxPanel;
	
	public ShowProfilesFrame(JFrame owner) {
		initializeSouthFrame();
		initializeCenterFrame();
		initializeWestFrame();
		
		this.setModal(true);
		this.setLocation(500, 150);
		this.pack();
		this.setVisible(true);
		this.setSize(500, 500);
		this.setDefaultCloseOperation(DISPOSE_ON_CLOSE);
	}
	
	private void initializeCenterFrame() {
		centerPanel = new JPanel();
		centerBoxPanel = new JPanel();
		area = new JTextArea(20, 20);
		area.setLineWrap(true);
		area.setEditable(false);
		centerBoxPanel.add(area);
		centerBoxPanel.setLayout(new BoxLayout(centerBoxPanel, BoxLayout.Y_AXIS));
		centerPanel.add(centerBoxPanel);
		
		this.getContentPane().add(BorderLayout.CENTER, centerPanel);
		
	}
	
	private void initializeWestFrame() {
		westPanel = new JPanel();
		westBoxPanel = new JPanel();
		lblPlayer = new JLabel("Spieler auswaehlen");
		lblgame = new JLabel("Spiel auswaehlen");
		Vector<Game> vec = new Vector<Game>();
		for(int i = 0; i < MainFrame.MAIN.getCenter().getGames().size(); i++) {
			vec.add(MainFrame.MAIN.getCenter().getGames().get(i));
		}
		gameBox = new JComboBox<Game>(vec);
		gameBox.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				area.setText("");
				
			}
		});
		Player[] playerArray = new Player[MainFrame.MAIN.getCenter().getPlayers().size()];
		for(int i = 0; i < MainFrame.MAIN.getCenter().getPlayers().size(); i++) {
			playerArray[i] = MainFrame.MAIN.getCenter().getPlayers().get(i);
		}
		playerList = new JList<Player>(playerArray);
		playerList.setSelectionMode(ListSelectionModel.MULTIPLE_INTERVAL_SELECTION);
		scroller = new JScrollPane(playerList);
		scroller.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);
		scroller.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
		playerList.addListSelectionListener(new ListSelectionListener() {
			
			@Override
			public void valueChanged(ListSelectionEvent e) {
				List<Player> people = playerList.getSelectedValuesList();
				area.setText(((Game)gameBox.getSelectedItem()).getName()+
						" wurde "+((Game)gameBox.getSelectedItem()).getGameIsPlayed()+" mal gespielt.\n\n");
				for(int i = 0; i < people.size(); i++) {
					if((MainFrame.MAIN.getCenter().existPlaysWhat(people.get(i), (Game)gameBox.getSelectedItem()))){ 					area.setText(area.getText()+people.get(i).getNickname()+": \n");
						area.setText(area.getText()+"-hat "+MainFrame.MAIN.getCenter()
						.getPlaysWhat(people.get(i), (Game)gameBox.getSelectedItem()).getCount()+" mal gespielt.\n");
						area.setText(area.getText()+"-hat "+MainFrame.MAIN.getCenter()
						.getPlaysWhat(people.get(i), (Game)gameBox.getSelectedItem()).getWins()+" mal gewonnen.\n\n");
					}
				}
				
			}
		});
		westBoxPanel.add(lblPlayer);
		westBoxPanel.add(scroller);
		westBoxPanel.add(Box.createRigidArea(new Dimension(0,10)));
		westBoxPanel.add(lblgame);
		westBoxPanel.add(gameBox);
		westBoxPanel.setLayout(new BoxLayout(westBoxPanel, BoxLayout.Y_AXIS));
		westPanel.add(westBoxPanel);
		this.getContentPane().add(BorderLayout.WEST, westPanel);
		
		
	}
	
	private void initializeSouthFrame() {
		southPanel = new JPanel();
		closeButton = new JButton(CLOSE);
		closeButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				ShowProfilesFrame.this.dispose();
			}
		});
		southPanel.add(closeButton);
		this.getContentPane().add(BorderLayout.SOUTH, southPanel);
		
	}
}