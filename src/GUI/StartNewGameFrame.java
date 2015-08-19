package GUI;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
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
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.ListSelectionModel;
import javax.swing.ScrollPaneConstants;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;

import Center.Game;
import Center.GameEvent;
import Center.Player;

public class StartNewGameFrame extends JDialog {
	
	//Konstanten
	private static final String START = "Spiel starten";
	private static final String CANCEL = "Abbrechen";
	private static final String SPIELERWAHL = "Spieler auswaehlen:";
	private static final String GAMEWAHL = "Spiel auswaehlen:";
	
	//JPanels
	JPanel southPanel;
	JPanel centerPanel;
	JPanel centerBoxPanel;
	JPanel westPanel;
	JPanel westBoxPanel;
	JPanel eastPanel;
	JPanel eastBoxPanel;
	
	//JButtons
	JButton buttonStart;
	JButton buttonCancel;
	
	//JList 
	JList<Player> playerList;
	
	//Arrays
	Player playerArray[];
	
	//Scroller
	JScrollPane playerScroller;
	
	//JLabels
	JLabel lblSpielerauswahl;
	JLabel lblGameauswahl;
	
	//JTextAreas
	JTextArea playerFeld;
	JTextArea gameFeld;
	
	//JComboBox
	JComboBox<Game> gameBox;

	public StartNewGameFrame(JFrame owner) {
		
		initializeWestFrame();
		initializeCenterFrame();
		initializeSouthFrame();
		
		this.setModal(true);
		this.setLocation(500, 150);
		this.pack();
		this.setVisible(true);
		this.setSize(500, 500);
		this.setDefaultCloseOperation(DISPOSE_ON_CLOSE);
	}
	
	public void initializeCenterFrame() {
		centerPanel = new JPanel();
		centerBoxPanel = new JPanel();
		playerFeld = new JTextArea(6, 15);
		gameFeld = new JTextArea(6, 15);
		playerFeld.setLineWrap(true);
		gameFeld.setLineWrap(true);
		playerFeld.setEditable(false);
		gameFeld.setEditable(false);
		centerBoxPanel.setLayout(new BoxLayout(centerBoxPanel, BoxLayout.Y_AXIS));
		centerBoxPanel.add(playerFeld);
		centerBoxPanel.add(Box.createRigidArea(new Dimension(0,10)));
		centerBoxPanel.add(gameFeld);
		centerPanel.add(centerBoxPanel);
		this.getContentPane().add(BorderLayout.CENTER, centerPanel);
		
	}
	
	public void initializeWestFrame() {
		westBoxPanel = new JPanel();
		westPanel = new JPanel();
		lblSpielerauswahl = new JLabel(SPIELERWAHL);
		lblGameauswahl = new JLabel(GAMEWAHL);
		Vector<Game> vec = new Vector<Game>();
		for(int i = 0; i < MainFrame.MAIN.getCenter().getGames().size(); i++) {
			vec.add(MainFrame.MAIN.getCenter().getGames().get(i));
		}
		gameBox = new JComboBox<Game>(vec);
		playerArray = new Player[MainFrame.MAIN.getCenter().getPlayers().size()];
		for(int i = 0; i < MainFrame.MAIN.getCenter().getPlayers().size(); i++) {
			playerArray[i] = MainFrame.MAIN.getCenter().getPlayers().get(i);
		}
		playerList = new JList<Player>(playerArray);
		playerList.setSelectionMode(ListSelectionModel.MULTIPLE_INTERVAL_SELECTION);
		playerScroller = new JScrollPane(playerList);
		playerScroller.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);
		playerScroller.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
		playerList.addListSelectionListener(new ListSelectionListener() {
			
			@Override
			public void valueChanged(ListSelectionEvent e) {
				playerFeld.setText("");
				List<Player> people = playerList.getSelectedValuesList();
				for(int i = 0; i < people.size(); i++) {
					playerFeld.setText(playerFeld.getText() + people.get(i).getNickname()+"\n");
				}
				
				
			}
		});
		gameBox.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				gameFeld.setText("");
				gameFeld.setText(gameBox.getSelectedItem().toString());
			}
		});
		
		westBoxPanel.setLayout(new BoxLayout(westBoxPanel, BoxLayout.Y_AXIS));
		westBoxPanel.add(lblSpielerauswahl);
		westBoxPanel.add(playerScroller);
		westBoxPanel.add(Box.createRigidArea(new Dimension(0,10)));
		westBoxPanel.add(lblGameauswahl);
		westBoxPanel.add(gameBox);
		westPanel.add(westBoxPanel);
		playerList.setVisibleRowCount(5);
		
		this.getContentPane().add(BorderLayout.WEST, westPanel);	
		
	}
	

	public void initializeSouthFrame() {
		
		southPanel = new JPanel();
		buttonStart = new JButton(START);
		buttonCancel = new JButton(CANCEL);
		buttonStart.addActionListener(new StartGameActionListener());
		southPanel.add(buttonStart);
		southPanel.add(buttonCancel);
		buttonCancel.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				StartNewGameFrame.this.dispose();
			}
		});
		this.getContentPane().add(BorderLayout.SOUTH, southPanel);
	}
	
	
	private class StartGameActionListener implements ActionListener {
		
		@Override
		public void actionPerformed(ActionEvent actionEvent) {
			Game gameToPlay = (Game)gameBox.getSelectedItem();
			ArrayList<Player> players = new ArrayList<Player>();
			List<Player> people = playerList.getSelectedValuesList();
			if(people.size() > gameToPlay.getMaxAnzPlayers()) {
				JOptionPane.showMessageDialog(null, "Zu viele Spieler!",
						"Fehler", JOptionPane.ERROR_MESSAGE);
				playerScroller.requestFocus();
			} else {
			    for(int i = 0; i < people.size(); i++) {
				    players.add(people.get(i));
			    }
			    int usersChoice = JOptionPane.showConfirmDialog(null, "Spiel jetzt starten?"
			    		,"Sicher?", JOptionPane.YES_NO_OPTION);
			    if(usersChoice == JOptionPane.YES_OPTION) {
			    	MainFrame.MAIN.getCenter().addGameEvent(gameToPlay, players);
			    	MainFrame.MAIN.centerFeld.setText(gameToPlay.getName() + " wurde gestartet");
			    	StartNewGameFrame.this.dispose();
			    } 
			}
					
		}
	}
}
