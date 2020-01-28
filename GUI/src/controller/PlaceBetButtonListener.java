package controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.Collection;

import javax.swing.JButton;
import javax.swing.JList;
import javax.swing.JOptionPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;

import model.enumeration.BetType;
import model.interfaces.GameEngine;
import model.interfaces.Player;
import view.StatusBar;

public class PlaceBetButtonListener implements ActionListener {
	private GameEngine gameEngine;
	private JTextField textFieldPlayerId;
	private JTextArea textArea;
	private JTextField textFieldBetAmounts;
	private JList<String> list;
	private ArrayList<String> betPlacedIds;
	private StatusBar statusBar;
	private JButton btnOpenWheelPage;
	public PlaceBetButtonListener(GameEngine gameEngine,JTextField textFieldPlayerId,JTextField textFieldBetAmounts,JList<String> list,JTextArea textArea,StatusBar statusBar,JButton btnOpenWheelPage) {
		this.gameEngine=gameEngine;
		this.textFieldPlayerId=textFieldPlayerId;
		this.textArea=textArea;
		this.textFieldBetAmounts=textFieldBetAmounts;
		this.list=list;
		betPlacedIds=new ArrayList<String>();
		this.statusBar=statusBar;
		this.btnOpenWheelPage=btnOpenWheelPage;
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		try {
			String id=textFieldPlayerId.getText();
			int bet=Integer.parseInt(textFieldBetAmounts.getText());
			int betType=list.getSelectedIndex();
			if(betType==-1) {
				JOptionPane.showMessageDialog(null,"Bet type is Not Selected!", "Error", JOptionPane.ERROR_MESSAGE);
				return;
			}
			Player player=gameEngine.getPlayer(id);
			if(player!=null) {
				if(gameEngine.placeBet(player, bet, BetType.values()[betType])) {
					JOptionPane.showMessageDialog(null, "Bet Placed!\nID:"+id+"\nName:"+player.getPlayerName()+"\nInitial Points"+player.getPoints()+"\nBet="+player.getBet()+"\nBetType="+player.getBetType().name());
					
					if(!betPlacedIds.contains(id)) {
						betPlacedIds.add(id);
					}
					resetTextArea(gameEngine.getAllPlayers());
				}else
				{
					JOptionPane.showMessageDialog(null,"Bet Not Placed!\nPlayer not have enough Points.", "Error", JOptionPane.ERROR_MESSAGE);
				}
			}
			else{
				JOptionPane.showMessageDialog(null,"Player for Id:"+id+" not found!", "Error", JOptionPane.ERROR_MESSAGE);
			}
		}catch(NumberFormatException nFE) {
			JOptionPane.showMessageDialog(null, "Error: "+nFE.getMessage()+"\nBet amount Should be a number!", "Error", JOptionPane.ERROR_MESSAGE);
		}catch(Exception sE) {
			
			sE.printStackTrace();
			JOptionPane.showMessageDialog(null, sE.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
		}

	}
	
	private void resetTextArea(Collection<Player> players) {
		textArea.setText("");
		statusBar.setBetCount(betPlacedIds.size());
		for(Player player:players) {
			if(betPlacedIds.contains(player.getPlayerId()))
			{
				textArea.append(player.toString()+"\n");
			}
			else
				textArea.append("Player: id="+player.getPlayerId()+", name="+player.getPlayerName()+", bet="+0+", betType="+null+", points="+player.getPoints()+"\n");
		}
		if(players.size()==betPlacedIds.size()) {
			btnOpenWheelPage.doClick();
			new Thread()
			{
				@Override
				public void run()
				{
					gameEngine.spin(1, 500, 25);
				}
			}.start();
			statusBar.wheelSpinning();
		}
	}

}
