package GUI;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

import Center.Center;

public class AddPlayerFrame extends JDialog  {
	//Konstanten
	private static final String VORNAME = "Vorname: ";
	private static final String NAME = "Name: ";
	private static final String NICK = "Profilname: ";
	private static final String OK = "OK";
	private static final String CANCEL = "Abbrechen";
	private static final String FEingabe = "Falsche Eigabe!";
	
	//JPanels
	JPanel centerPanel;
	JPanel southPanel;
	
	//JButtons
	JButton buttonOk;
	JButton buttonCancel;
	
	//JLabels
	JLabel lblVorname;
	JLabel lblName;
	JLabel lblNick;
	
	//JTextFields
	JTextField jtVorname;
	JTextField jtName;
	JTextField jtNick;
	
	
	public AddPlayerFrame(JFrame owner) {
		initializeCenterFrame();
        initializeSouthFrame();
		
        this.setModal(true);
        this.setLocation(500, 150);
        this.pack();
		this.setVisible(true);
		this.setSize(500, 500);
		this.setDefaultCloseOperation(DISPOSE_ON_CLOSE);
	}
	
	private void initializeCenterFrame() {
		centerPanel = new JPanel();
		lblVorname = new JLabel(VORNAME);
		lblName = new JLabel(NAME);
		lblNick = new JLabel(NICK);
		jtVorname = new JTextField("Vorname");
		jtName = new JTextField("Name");
		jtNick = new JTextField("Profilname");
		jtVorname.addFocusListener(new FocusListener() {
			@Override
			public void focusGained(FocusEvent e){
				if(jtVorname.getText().equals("Vorname")) {
				    jtVorname.setText("");
				}
			}

			@Override
			public void focusLost(FocusEvent e) {
				if(jtVorname.getText().isEmpty() || jtVorname.getText().equals("Vorname")) {
					lblVorname.setText(lblVorname.getText()+FEingabe);
					jtVorname.requestFocus();
				} else {
					lblVorname.setText(VORNAME);
				}
				
			}
		});
			
		jtName.addFocusListener(new FocusListener() {
			@Override
			public void focusGained(FocusEvent e){
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
		
		jtNick.addFocusListener(new FocusListener() {
			@Override
			public void focusGained(FocusEvent e) {
				if(jtNick.getText().equals("Profilname")){
				    jtNick.setText("");
				}
			}

			@Override
			public void focusLost(FocusEvent e) {
				if(jtNick.getText().isEmpty() || jtNick.getText().equals("Profilname")) {
					lblNick.setText(lblNick.getText()+FEingabe);
					jtNick.requestFocus();
				} else {
					lblNick.setText(NICK);
				}
			}
		});
		centerPanel.setLayout(new BoxLayout(centerPanel, BoxLayout.Y_AXIS));
		centerPanel.add(lblVorname);
		centerPanel.add(jtVorname);
		centerPanel.add(Box.createRigidArea(new Dimension(0,5)));
		centerPanel.add(lblName);
		centerPanel.add(jtName);
		centerPanel.add(Box.createRigidArea(new Dimension(0,5)));
		centerPanel.add(lblNick);
		centerPanel.add(jtNick);
		this.getContentPane().add(BorderLayout.CENTER, centerPanel);
	}

	private void initializeSouthFrame() {
		southPanel = new JPanel();
		buttonOk = new JButton(OK);
		buttonCancel = new JButton(CANCEL);
		buttonOk.addActionListener(new SavePlayerActionListener());
		southPanel.add(buttonOk);
		southPanel.add(buttonCancel);
		buttonCancel.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				AddPlayerFrame.this.dispose();
			}
		});
		this.getContentPane().add(BorderLayout.SOUTH, southPanel);
	}
	
	private class SavePlayerActionListener implements ActionListener {
		
		@Override
		public void actionPerformed(ActionEvent actionEvent) {
			String playerVorname;
			String playerName;
			String playerNick;
			
			playerVorname = jtVorname.getText();
		    playerName = jtName.getText();
			playerNick = jtNick.getText();
			MainFrame.MAIN.getCenter().addPlayer(playerVorname, playerName, playerNick);
			MainFrame.MAIN.centerFeld.setText("Dieser Spieler wurde hinzugefuegt:\n"+ 
			MainFrame.MAIN.getCenter().getPlayers().getLast().toString());
			      
			AddPlayerFrame.this.dispose();
		}
	}
}
