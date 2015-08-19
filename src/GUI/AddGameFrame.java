package GUI;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;

import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.ComboBoxModel;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

public class AddGameFrame extends JDialog {
	//Konstanten
	private static final String NAME = "Name: ";
	private static final String TYPE = "Spieltyp: ";
	private static final String ANZ = "Maximale Spieleranzahl: ";
	private static final String OK = "OK";
	private static final String CANCEL = "Abbrechen";
	private static final String FEingabe = "Falsche Eigabe!";
	
	//JPanels
	JPanel southPanel;
	JPanel centerPanel;
	
	//JButtons
	JButton buttonOk;
	JButton buttonCancel;
	
	//JLabels
	JLabel lblName;
	JLabel lblType;
	JLabel lblMaxAnzPly;
	
	//JTextFields
	JTextField jtName;
	JTextField jtType;
	JTextField jtAnz;
	
		
	public AddGameFrame(JFrame owner) {
		
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
		lblName = new JLabel(NAME);
		lblType = new JLabel(TYPE);
		lblMaxAnzPly = new JLabel(ANZ);
		jtName = new JTextField("Name");
		jtType = new JTextField("Typ");
		jtAnz = new JTextField("Maximale Anzahl Spieler");
		
		jtName.addFocusListener(new FocusListener() {
			@Override
			public void focusGained(FocusEvent e) {
				if(jtName.getText().equals("Name")) {
					jtName.setText("");
				}
			}

			
			@Override
			public void focusLost(FocusEvent e) {
				if(jtName.getText().isEmpty() || jtName.getText().equals("Name")) {
					lblName.setText(lblName.getText()+FEingabe);
					jtName.requestFocus();
				} else {
					lblName.setText(NAME);
				}
			}
		});
		
		jtType.addFocusListener(new FocusListener() {

			@Override
			public void focusGained(FocusEvent e) {
				if(jtType.getText().equals("Typ")) {
					jtType.setText("");
				}				
			}

			@Override
			public void focusLost(FocusEvent e) {
				if(jtType.getText().isEmpty() || jtType.getText().equals("Typ")) {
					lblType.setText(lblType.getText()+FEingabe);
					jtType.requestFocus();
				} else {
					lblType.setText(TYPE);
				}
			}
		});
		
		jtAnz.addFocusListener(new FocusListener() {

			@Override
			public void focusGained(FocusEvent e) {
				if(jtAnz.getText().equals("Maximale Anzahl Spieler")) {
					jtAnz.setText("");
				}
			}

			@Override
			public void focusLost(FocusEvent e) {
				if(jtAnz.getText().isEmpty() || jtAnz.getText().equals("Maximale Anzahl Spieler")) {
					lblMaxAnzPly.setText(lblMaxAnzPly.getText()+FEingabe);
					jtAnz.requestFocus();
				} else {
					lblMaxAnzPly.setText(ANZ);
				}
				
			}
			
		});
		
		centerPanel.setLayout(new BoxLayout(centerPanel, BoxLayout.Y_AXIS));
		centerPanel.add(lblName);
		centerPanel.add(jtName);
		centerPanel.add(Box.createRigidArea(new Dimension(0,5)));
		centerPanel.add(lblType);
		centerPanel.add(jtType);
		centerPanel.add(Box.createRigidArea(new Dimension(0,5)));
		centerPanel.add(lblMaxAnzPly);
		centerPanel.add(jtAnz);
		this.getContentPane().add(BorderLayout.CENTER, centerPanel);
	}
	
	public void initializeSouthFrame() {
		
		southPanel = new JPanel();
		buttonOk = new JButton(OK);
		buttonCancel = new JButton(CANCEL);
		buttonOk.addActionListener(new SaveGameActionListener());
		southPanel.add(buttonOk);
		southPanel.add(buttonCancel);
		buttonCancel.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				AddGameFrame.this.dispose();
			}
		});
		this.getContentPane().add(BorderLayout.SOUTH, southPanel);
	}
	
	private class SaveGameActionListener implements ActionListener {

		@Override
		public void actionPerformed(ActionEvent actionEvent) {
			String gameName;
			String gameType;
			int maxAnzPlayer;
			
			gameName = jtName.getText();
			gameType = jtType.getText();
				maxAnzPlayer = Integer.parseInt(jtAnz.getText());
			
			MainFrame.MAIN.getCenter().addGame(gameName, gameType, maxAnzPlayer);
			MainFrame.MAIN.centerFeld.setText("Dieses Spiel wurde hinzugefuegt:\n"+
			MainFrame.MAIN.getCenter().getGames().getLast().toString());
			
			
			AddGameFrame.this.dispose();
		}
		
	}
}
