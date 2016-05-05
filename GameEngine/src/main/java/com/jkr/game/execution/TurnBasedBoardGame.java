package com.jkr.game.execution;

import java.util.Collection;
import java.util.LinkedList;
import java.util.List;

import com.jkr.game.area.Board;
import com.jkr.game.area.BoardGame;
import com.jkr.game.interaction.Player;
import com.jkr.game.presentation.Presentation;
import com.jkr.game.setup.MultiplayerGame;

public abstract class TurnBasedBoardGame<M extends Comparable<M>,V> implements ExecutionHandler, BoardGame<M>, MultiplayerGame<M, V>, TurnBasedGame<M, V> {
	
	private final Board<M> board = prepareCleanBoard();
	private final Presentation<int[], V> presentation = getPresentation();
	private final List<Player<M, V>> players = new LinkedList<>();
	
	protected abstract Presentation<int[], V> getPresentation();
	
	@Override
	public void setPlayers(Collection<Player<M, V>> players) {
		this.players.addAll(players);
	}

	@Override
	public void play() throws GameExecutionException {
		if (players.isEmpty()) {
			throwNoPlayersException();
		}
		Player<M, V> player = players.remove(0);
		while (!nextTurnForPlayer(player)) {
			players.add(player);
			player = players.remove(0);
		}
		player.showMessage(" CONGRATULATIONS!\nYou have won the game.");
	}
	
	@Override
	public boolean nextTurnForPlayer(Player<M,V> player) throws GameExecutionException {
		presentation.showToUser();
		int[] coordinates = putMarkForPlayer(player);
		if (board.isWinConditionMet(coordinates[0], coordinates[1])) {
			return true;
		}
		if (board.isFullyFilled()) {
			throw new GameExecutionException("Out of Moves");
		}
		return false;
	}
	
	private int[] putMarkForPlayer(Player<M,V> player) {
		int[] response = askPlayerWhereToPutMark(player);
		while (!board.canPutMark(response[0], response[1])) {
			player.showMessage("The point you have choosen is not available. Please choose again.");
			response = askPlayerWhereToPutMark(player);
		}
		board.putMark(response[0], response[1], player.getDiscriminator());
		return response;
	}
	
	private int[] askPlayerWhereToPutMark(Player<M, V> player) {
		return presentation.convertPresentationOrdinalToSpaceAreaOrdinal(player.askForResponse("Please choose field for putting your mark:").getCargo());
	}
}
