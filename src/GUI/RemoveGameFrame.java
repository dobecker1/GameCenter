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

public class RemoveGameFrame extends JDialog{
	

	//Konstanten
	private static final String OK = "OK";
	private static final String CANCEL = "Abbrechen";
	private static final String EINGABE = "Name des zu loeschenden Spiels:";
	
	//JPanels
	JPanel southPanel;
	JPanel centerPanel;
	
	//JButtons
	JButton buttonOk;
	JButton buttonCancel;
	
	//JLabels
	JLabel removelbl;
	
	//JTextField
	JTextField removetxt;
	
	public RemoveGameFrame(JFrame owner) {
		
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
		removelbl = new JLabel(EINGABE);
		removetxt = new JTextField();
		centerPanel.setLayout(new BoxLayout(centerPanel, BoxLayout.Y_AXIS));
		centerPanel.add(removelbl);
		centerPanel.add(removetxt);
		
		this.getContentPane().add(BorderLayout.CENTER, centerPanel);
	}
	
	public void initializeSouthFrame() {
		southPanel = new JPanel();
		buttonOk = new JButton(OK);
		buttonCancel = new JButton(CANCEL);
		buttonOk.addActionListener(new RemoveGameActionListener());
		buttonCancel.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				RemoveGameFrame.this.dispose();
				
			}
			
		});
		southPanel.add(buttonOk);
		southPanel.add(buttonCancel);
		this.getContentPane().add(BorderLayout.SOUTH, southPanel);
	}
	
	private class RemoveGameActionListener implements ActionListener {

		@Override
		public void actionPerformed(ActionEvent actionEvent) {
			String game;
			game = removetxt.getText();
			
			if(MainFrame.MAIN.getCenter().existGame(game)) {
				int usersChoice = JOptionPane.showConfirmDialog(null, "Spiel wirklich loeschen?",
						"Sicher?", JOptionPane.YES_NO_OPTION);
				if(usersChoice == JOptionPane.YES_OPTION) {
					MainFrame.MAIN.getCenter().removeGame(game);
					JOptionPane.showMessageDialog(null, "Spiel "+ game + " wurde geloescht!");
					RemoveGameFrame.this.dispose();
				}
			} else {
				JOptionPane.showMessageDialog(null, "Spiel existiert nicht!",
						"Falsche Eingabe", JOptionPane.ERROR_MESSAGE);
				removetxt.requestFocus();
			}
		}
		
	}
}
