package GUI;

import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;

public class RemovePlayerFrame extends JDialog {
	
	//Konstanten
	private static final String CANCEL = "Abbrechen";
	private static final String OK = "OK";
	private static final String EINGABE = "Profilname des Spielers eingeben:";
	
	//JPanels
	JPanel southPanel;
	JPanel centerPanel;
	
	//JButtons
	JButton buttonCancel;
	JButton buttonOk;
	
	//JLabels
	JLabel lblNick;
	
	//JTextField 
	JTextField jtName;
	
	public RemovePlayerFrame(JFrame owner) {
		
		initializeCenterFrame();
		initializeSouthFrame();
		
		this.setModal(true);
		this.setLocation(500, 150);
		this.pack();
		this.setVisible(true);
		this.setDefaultCloseOperation(DISPOSE_ON_CLOSE);
	}

	public void initializeCenterFrame() {
		centerPanel = new JPanel();
		lblNick = new JLabel(EINGABE);
		jtName = new JTextField();
		centerPanel.setLayout(new BoxLayout(centerPanel, BoxLayout.Y_AXIS));
		centerPanel.add(lblNick);
		centerPanel.add(jtName);
		
		this.getContentPane().add(BorderLayout.CENTER, centerPanel);
	}
	
	public void initializeSouthFrame() {
		southPanel = new JPanel();
		buttonOk = new JButton(OK);
		buttonCancel = new JButton(CANCEL);
		buttonOk.addActionListener(new RemovePlayerActionListener());
		southPanel.add(buttonOk);
		southPanel.add(buttonCancel);
		buttonCancel.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				RemovePlayerFrame.this.dispose();
			}
		});
		this.getContentPane().add(BorderLayout.SOUTH, southPanel);
	}
	
	private class RemovePlayerActionListener implements ActionListener {
		
		@Override
		public void actionPerformed(ActionEvent actionEvent) {
			String playerNick;
			
			playerNick = jtName.getText();
			
			if(MainFrame.MAIN.getCenter().existPlayer(playerNick)) {
				int usersChoice = JOptionPane.showConfirmDialog(null, "Spieler wirklich loeschen?",
						"Sicher?", JOptionPane.YES_NO_OPTION);
				if(usersChoice == JOptionPane.YES_OPTION) {
				    MainFrame.MAIN.getCenter().removePlayer(playerNick);
				    JOptionPane.showMessageDialog(null, "Spieler "+playerNick+" wurde geloescht!");
				    RemovePlayerFrame.this.dispose();
				}
			} else {
				JOptionPane.showMessageDialog(null, "Spieler existiert nicht!",
						"Falsche Eingabe", JOptionPane.ERROR_MESSAGE);
				jtName.requestFocus();
			}
		}
	}

}
