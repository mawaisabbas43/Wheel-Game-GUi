/**
 * 
 */
package model;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Random;
import java.util.concurrent.TimeUnit;

import model.enumeration.BetType;
import model.enumeration.Color;
import model.interfaces.GameEngine;
import model.interfaces.Player;
import model.interfaces.Slot;
import view.interfaces.GameEngineCallback;

public class GameEngineImpl implements GameEngine {
	Collection<Player> players;
	GameEngineCallback gameEngineCallback;
	Collection<Slot> wheelSlots;
	
	public GameEngineImpl() {
		players=new ArrayList<Player>();
		gameEngineCallback=null;
		wheelSlots=new ArrayList<Slot>();
		loadSlotsInWheeel();
	}
	
	private void loadSlotsInWheeel() {
		wheelSlots.add(new SlotImpl(0,Color.GREEN00,0));
		wheelSlots.add(new SlotImpl(1,Color.RED,27));
		wheelSlots.add(new SlotImpl(2,Color.BLACK,10));
		wheelSlots.add(new SlotImpl(3,Color.RED,25));
		wheelSlots.add(new SlotImpl(4,Color.BLACK,29));
		wheelSlots.add(new SlotImpl(5,Color.RED,12));
		wheelSlots.add(new SlotImpl(6,Color.BLACK,8));
		wheelSlots.add(new SlotImpl(7,Color.RED,19));
		wheelSlots.add(new SlotImpl(8,Color.BLACK,31));
		wheelSlots.add(new SlotImpl(9,Color.RED,18));
		wheelSlots.add(new SlotImpl(10,Color.BLACK,6));
		wheelSlots.add(new SlotImpl(11,Color.RED,21));
		wheelSlots.add(new SlotImpl(12,Color.BLACK,33));
		wheelSlots.add(new SlotImpl(13,Color.RED,16));
		wheelSlots.add(new SlotImpl(14,Color.BLACK,4));
		wheelSlots.add(new SlotImpl(15,Color.RED,23));
		wheelSlots.add(new SlotImpl(16,Color.BLACK,35));
		wheelSlots.add(new SlotImpl(17,Color.RED,14));
		wheelSlots.add(new SlotImpl(18,Color.BLACK,2));
		wheelSlots.add(new SlotImpl(19,Color.GREEN0,0));
		wheelSlots.add(new SlotImpl(20,Color.BLACK,28));
		wheelSlots.add(new SlotImpl(21,Color.RED,9));
		wheelSlots.add(new SlotImpl(22,Color.BLACK,26));
		wheelSlots.add(new SlotImpl(23,Color.RED,30));
		wheelSlots.add(new SlotImpl(24,Color.BLACK,11));
		wheelSlots.add(new SlotImpl(25,Color.RED,7));
		wheelSlots.add(new SlotImpl(26,Color.BLACK,20));
		wheelSlots.add(new SlotImpl(27,Color.RED,32));
		wheelSlots.add(new SlotImpl(28,Color.BLACK,17));
		wheelSlots.add(new SlotImpl(29,Color.RED,5));
		wheelSlots.add(new SlotImpl(30,Color.BLACK,22));
		wheelSlots.add(new SlotImpl(31,Color.RED,34));
		wheelSlots.add(new SlotImpl(32,Color.BLACK,15));
		wheelSlots.add(new SlotImpl(33,Color.RED,3));
		wheelSlots.add(new SlotImpl(34,Color.BLACK,24));
		wheelSlots.add(new SlotImpl(35,Color.RED,36));
		wheelSlots.add(new SlotImpl(36,Color.BLACK,13));
		wheelSlots.add(new SlotImpl(37,Color.RED,1));
	}
	
	@Override
	public void spin(int initialDelay, int finalDelay, int delayIncrement) {
		try {
			Random rand = new Random();
			int index=rand.nextInt(wheelSlots.size());
			ArrayList<Slot> slots=(ArrayList<Slot>) wheelSlots;
//			Slot []slots=(Slot[]) a.toArray();
			Slot slot=slots.get(index);
			TimeUnit.MILLISECONDS.sleep(initialDelay);
			for(int delay=delayIncrement;delay<=finalDelay;delay+=delayIncrement) {
				
				index=(index+1)%wheelSlots.size();
				slot=slots.get(index);
				TimeUnit.MILLISECONDS.sleep(delay);
				gameEngineCallback.nextSlot(slot, this);
			}
			gameEngineCallback.result(slot, this);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}

	}

	@Override
	public void calculateResult(Slot winningSlot) {
		for(Player player:players) {
			if(player.getBetType()!=null)
			player.getBetType().applyWinLoss(player, winningSlot);
		}

	}

	@Override
	public void addPlayer(Player player) {
		for(Player plr:players) {
			if(plr.getPlayerId().equals(player.getPlayerId())) {
				players.remove(plr);
				players.add(player);
				return;
			}
		}
		players.add(player);
	}

	@Override
	public Player getPlayer(String id) {
		for(Player plr:players) {
			if(plr.getPlayerId().equals(id)) {
			
				return plr;
			}
		}
		return null;
	}

	@Override
	public boolean removePlayer(Player player) {
		for(Player plr:players) {
			if(plr.getPlayerId().equals(player.getPlayerId())) {
				players.remove(plr);
				return true;
			}
		}
		return false;
	}

	@Override
	public void addGameEngineCallback(GameEngineCallback gameEngineCallback) {
		this.gameEngineCallback=gameEngineCallback;

	}

	@Override
	public boolean removeGameEngineCallback(GameEngineCallback gameEngineCallback) {
		if(this.gameEngineCallback.equals(gameEngineCallback)) {
			this.gameEngineCallback=null;
			return true;
		}
		else
		return false;
	}

	@Override
	public Collection<Player> getAllPlayers() {
		return players;
	}

	@Override
	public boolean placeBet(Player player, int bet, BetType betType) {
		if(player.setBet(bet)) {
			player.setBetType(betType);
			return true;
		}
		else
		{
			return false;
		}
	}

	@Override
	public Collection<Slot> getWheelSlots() {
		return wheelSlots;
	}

}
