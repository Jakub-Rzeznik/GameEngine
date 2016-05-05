package com.jkr.game.execution;

import com.jkr.game.interaction.Player;

public interface TurnBasedGame<S extends Comparable<S>, T> {
	
	public boolean nextTurnForPlayer(Player<S, T> players) throws GameExecutionException;

}
