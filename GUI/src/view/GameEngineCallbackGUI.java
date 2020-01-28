package view;

import java.util.HashMap;
import java.util.Map;

import javax.swing.JTextArea;
import javax.swing.SwingUtilities;

import model.interfaces.GameEngine;
import model.interfaces.Player;
import model.interfaces.Slot;
import view.interfaces.GameEngineCallback;

public class GameEngineCallbackGUI implements GameEngineCallback {
	private UI frame;
	private StatusBar statusBar;
	private ImagePanel imagePanel;
	private Map<String, Integer> previousPoints;
	private JTextArea textArea;
	public GameEngineCallbackGUI(GameEngine engine) {
		SwingUtilities.invokeLater(new Runnable()
		{
			@Override
			public void run()
			{
				frame = new UI(engine);
				frame.setVisible(true);
				statusBar=frame.getStatusBar();
				imagePanel=frame.getImagePanel();
				textArea=frame.getJTextArea();
			}
		});
		
		previousPoints = new HashMap<>();
	}

	@Override
	public void nextSlot(Slot slot, GameEngine engine) {
		imagePanel.move(slot.getPosition());
	}

	@Override
	public void result(Slot winningSlot, GameEngine engine) {
		statusBar.wheelStopped();
		for(Player player:engine.getAllPlayers()) {
	    	  previousPoints.put(player.getPlayerId(), player.getPoints());
	     }
		engine.calculateResult(winningSlot);
		textArea.setText("");
		for(Player player:engine.getAllPlayers()) {
			int previous=previousPoints.get(player.getPlayerId());
	    	  if(player.getPoints()>previous) {
	    		  textArea.append(printPlayer(player)+", \"Won: Points="+(player.getPoints()-previous)+"\"\n");
	    	  }else if(player.getPoints()<previousPoints.get(player.getPlayerId())){
	    		  textArea.append(printPlayer(player)+", \"Lossed: Points="+(previous-player.getPoints())+"\"\n");
	    	  }else if(player.getPoints()==previousPoints.get(player.getPlayerId())) {
	    		  textArea.append(printPlayer(player)+", \"Not Bet any amount last time!\"\n");
	    	  }
	    	  player.resetBet();
	    	  statusBar.setBetCount(0);
	     }
	}
	
	private String printPlayer(Player player) {
		if(player.getBetType()!=null)
		{
			return player.toString();
		}
		else
			return "Player: id="+player.getPlayerId()+", name="+player.getPlayerName()+", bet="+0+", betType="+null+", points="+player.getPoints();
	}

}
