
package model;

import model.enumeration.BetType;
import model.interfaces.Player;

public class SimplePlayer implements Player {
	private String playerId;
	private String playerName;
	private int points;
	private int bet;
	private BetType betType;

	public SimplePlayer(String playerId, String playerName, int initialPoints) {
		this.playerId = playerId;
		this.playerName = playerName;
		this.points = initialPoints;
		this.bet = 0;
		betType=null;
	}

	@Override
	public String getPlayerName() {
		return playerName;
	}

	@Override
	public void setPlayerName(String playerName) {
		this.playerName = playerName;

	}

	@Override
	public int getPoints() {
		return points;
	}

	@Override
	public void setPoints(int points) {
		this.points = points;

	}

	@Override
	public String getPlayerId() {
		return playerId;
	}

	@Override
	public boolean setBet(int bet) {
		if (bet == 0) {
			resetBet();
			return false;
		} else if (bet > 0 && bet <= points) {
			this.bet = bet;
			return true;
		} else {

			return false;
		}
	}

	@Override
	public int getBet() {
		return bet;
	}

	@Override
	public void setBetType(BetType betType) {
		this.betType = betType;

	}

	@Override
	public BetType getBetType() {
		return betType;
	}

	@Override
	public void resetBet() {
		this.bet = 0;

	}

	/**
	 * @return - a human readable String that lists the values of this Player<br>
	 *         <br>
	 *         e.g "Player: id=1, name=Come In Spinner, bet=100, betType=RED,
	 *         points=900"<br>
	 *         (see OutputTrace.txt)private String playerName;
	 */
	@Override
	public String toString() {
		return "Player: id="+playerId+", name="+playerName+", bet="+bet+", betType="+betType.name()+", points="+points;
	}

}
