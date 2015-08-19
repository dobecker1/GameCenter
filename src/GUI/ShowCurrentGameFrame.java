package GUI;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;
import java.util.Vector;

import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextArea;
import javax.swing.JTextField;

import Center.Player;

public class ShowCurrentGameFrame extends JDialog {
	
	//Konstanten
	private static final String END = "Spiel beenden";
	private static final String CLOSE = "Schliessen";
	private static final String POINTS = "Punkte eingeben";
	
	//JPanels
	JPanel southPanel;
	JPanel westPanel;
	JPanel westBoxPanel;
	JPanel eastPanel;
	JPanel eastBoxPanel;	
	
	//JButtons
	JButton closeButton;
	JButton endGameButton;
	JButton updatePointsButton;
	
	//JTextAreas
	JTextArea gameArea;
	JTextArea playerArea;
	
	//JTextField 
	JTextField pointField;
	
	//JLabels
	JLabel gamelbl;
	JLabel playerlbl;
	JLabel winnerlbl;
	
	//JComboBox
	JComboBox<Player> playerBox;
	JComboBox<Player> winnerBox;
	
	
	public ShowCurrentGameFrame(JFrame owner) {
		
		initializeWestFrame();
		initializeEastFrame();
		initializeSouthFrame();
		
		this.setModal(true);
		this.setLocation(500, 150);
		this.pack();
		this.setVisible(true);
		this.setSize(500, 500);
		this.setDefaultCloseOperation(DISPOSE_ON_CLOSE);
	}
	
	public void initializeWestFrame() {
		westPanel = new JPanel();
		westBoxPanel = new JPanel();
		gameArea = new JTextArea();
		playerArea = new JTextArea();
		gamelbl = new JLabel("Spiel:");
		playerlbl = new JLabel("Spieler:");
		gameArea.setText(MainFrame.MAIN.getCenter().getCurrentGameEvent().getCurrentGame().toString());
		playerArea.setText(MainFrame.MAIN.getCenter().getCurrentGameEvent().printPlayers());
		gameArea.setEditable(false);
		playerArea.setEditable(false);
		westBoxPanel.add(gamelbl);
		westBoxPanel.add(gameArea);
		westBoxPanel.add(Box.createRigidArea(new Dimension(0,10)));
		westBoxPanel.add(playerlbl);
		westBoxPanel.add(playerArea);
		westBoxPanel.setLayout(new BoxLayout(westBoxPanel, BoxLayout.Y_AXIS));
		westPanel.add(westBoxPanel);
		
		this.getContentPane().add(BorderLayout.WEST, westPanel);
		
	}
	
	public void initializeEastFrame() {
		eastPanel = new JPanel();
		eastBoxPanel = new JPanel();
		pointField = new JTextField();
		winnerlbl = new JLabel("Gewinner auswaehlen:");
		eastBoxPanel.setLayout(new BoxLayout(eastBoxPanel, BoxLayout.Y_AXIS));
		updatePointsButton = new JButton("Punkte aktualisieren");
		Vector<Player> vec = new Vector<Player>();
		for(int i = 0; i < MainFrame.MAIN.getCenter().getCurrentGameEvent().
				getCurrentPlayers().size(); i++) {
			vec.add(MainFrame.MAIN.getCenter().getCurrentGameEvent()
					.getCurrentPlayers().get(i));
		}
		playerBox = new JComboBox<Player>(vec);
		winnerBox = new JComboBox<Player>(vec);
		updatePointsButton.addActionListener(new UpdatePointsActionListener());
		eastBoxPanel.add(playerBox);
		eastBoxPanel.add(pointField);
		eastBoxPanel.add(updatePointsButton);
		eastBoxPanel.add(Box.createRigidArea(new Dimension(0,30)));
		eastBoxPanel.add(winnerlbl);
		eastBoxPanel.add(winnerBox);
		eastPanel.add(eastBoxPanel);
		this.getContentPane().add(BorderLayout.EAST, eastPanel);
	}
	
	public void initializeSouthFrame() {
		southPanel = new JPanel();
		endGameButton = new JButton(END);
		closeButton = new JButton(CLOSE);
		endGameButton.addActionListener(new BeendeSpielActionListener());
		closeButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				ShowCurrentGameFrame.this.dispose();
			}
		});
		
		southPanel.add(endGameButton);
		southPanel.add(closeButton);

		this.getContentPane().add(BorderLayout.SOUTH, southPanel);
	}
	
	private class UpdatePointsActionListener implements ActionListener {

		@Override
		public void actionPerformed(ActionEvent actionEvent) {

			MainFrame.MAIN.getCenter().getCurrentGameEvent()
			.setPoints(Integer.parseInt(pointField.getText()),(Player) playerBox.getSelectedItem());
			playerArea.setText(MainFrame.MAIN.getCenter().getCurrentGameEvent().printPlayers());
		}
		
	}
	
	private class BeendeSpielActionListener implements ActionListener {

		@Override
		public void actionPerformed(ActionEvent actionEvent) {
			Player winner = (Player) winnerBox.getSelectedItem();
			MainFrame.MAIN.getCenter().getCurrentGameEvent().setWinner(winner);
			MainFrame.MAIN.getCenter().getPlaysWhat(winner, MainFrame.MAIN.getCenter().getCurrentGameEvent().getCurrentGame()).playerWins(winner);
			MainFrame.MAIN.getCenter().beendeSpiel(MainFrame.MAIN.getCenter().getCurrentGameEvent());
			MainFrame.MAIN.centerFeld.setText(MainFrame.MAIN.getCenter().getHistory().getLast()
					.getCurrentGame().getName()+" wurde beendet\nGewonnen hat "+ winner.getNickname());
			ShowCurrentGameFrame.this.dispose();
			
		}
		
	}
	

}
