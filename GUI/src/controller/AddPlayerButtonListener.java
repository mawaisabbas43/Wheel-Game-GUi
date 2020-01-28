package controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JOptionPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;

import model.SimplePlayer;
import model.interfaces.GameEngine;
import model.interfaces.Player;
import view.StatusBar;

public class AddPlayerButtonListener implements ActionListener {
	private GameEngine gameEngine;
	private JTextField textFieldPlayerName;
	private JTextField textFieldInitialPoints;
	private JTextArea textArea;
	private int idCount;
	private StatusBar statusBar;
	 public AddPlayerButtonListener(GameEngine gameEngine,JTextField textFieldPlayerName,JTextField textFieldInitialPoints,JTextArea textArea,StatusBar statusBar) {
		this.gameEngine=gameEngine;
		this.textFieldPlayerName=textFieldPlayerName;
		this.textFieldInitialPoints=textFieldInitialPoints;
		this.textArea=textArea;
		idCount=1;
		this.statusBar=statusBar;
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		try {
			int iPoints=Integer.parseInt(textFieldInitialPoints.getText());
			String name=textFieldPlayerName.getText();
			Player player=new SimplePlayer(""+idCount,name,iPoints);
			gameEngine.addPlayer(player);
			JOptionPane.showMessageDialog(null, "Player added!\nID:"+idCount+"\nName:"+name+"\nInitial Points:"+iPoints);
			textArea.append("Player: id="+player.getPlayerId()+", name="+player.getPlayerName()+", bet="+0+", betType="+null+", points="+player.getPoints()+"\n");
			idCount++;
			statusBar.setPlayersCount(gameEngine.getAllPlayers().size());
		}catch(NumberFormatException nFE) {
			JOptionPane.showMessageDialog(null, "Error: "+nFE.getMessage()+"\nInitial Points Should be a number!", "Error", JOptionPane.ERROR_MESSAGE);
		}catch(Exception sE) {
			
			sE.printStackTrace();
			JOptionPane.showMessageDialog(null, sE.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
		}

	}

}
