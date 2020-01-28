package controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Collection;

import javax.swing.JOptionPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;

import model.interfaces.GameEngine;
import model.interfaces.Player;
import view.StatusBar;

public class DeletePlayerButtonListener implements ActionListener {
	private GameEngine gameEngine;
	private JTextField textFieldPlayerId;
	private JTextArea textArea;
	private StatusBar statusBar;
	 public DeletePlayerButtonListener(GameEngine gameEngine,JTextField textFieldPlayerId,JTextArea textArea,StatusBar statusBar) {
		this.gameEngine=gameEngine;
		this.textFieldPlayerId=textFieldPlayerId;
		this.textArea=textArea;
		this.statusBar=statusBar;
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		try {
			String id=textFieldPlayerId.getText();
			Player player=gameEngine.getPlayer(id);
			if(player!=null) {
				if(gameEngine.removePlayer(player)) {
					JOptionPane.showMessageDialog(null, "Player Deleted!\nID:"+id+"\nName:"+player.getPlayerName()+"\nInitial Points"+player.getPoints());
					resetTextArea(gameEngine.getAllPlayers());
				}else
				{
					JOptionPane.showMessageDialog(null,"Not Deleted!", "Error", JOptionPane.ERROR_MESSAGE);
				}
			}
			else{
				JOptionPane.showMessageDialog(null,"Player for Id:"+id+" not found!", "Error", JOptionPane.ERROR_MESSAGE);
			}
		}catch(Exception sE) {
			
			sE.printStackTrace();
			JOptionPane.showMessageDialog(null, sE.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
		}

	}
	
	private void resetTextArea(Collection<Player> players) {
		textArea.setText("");
		for(Player player:players) {
			if(player.getBetType()!=null)
			{
				textArea.append(player.toString()+"\n");
			}
			else
				textArea.append("Player: id="+player.getPlayerId()+", name="+player.getPlayerName()+", bet="+0+", betType="+null+", points="+player.getPoints()+"\n");
		}
		statusBar.setPlayersCount(gameEngine.getAllPlayers().size());
	}

}
