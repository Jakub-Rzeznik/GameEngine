package com.jkr.game.execution;

import com.jkr.game.area.Board;
import com.jkr.game.area.BoardGame;
import com.jkr.game.interaction.Player;
import com.jkr.game.presentation.Presentation;

public abstract class TurnBasedBoardGame<M extends Comparable<M>,V> implements BoardGame<M>, TurnBasedGame<M, V> {
	
	private final Board<M> board = prepareCleanBoard();
	private final Presentation<int[], V> presentation = getPresentation();
	
	protected abstract Presentation<int[], V> getPresentation();
	
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
