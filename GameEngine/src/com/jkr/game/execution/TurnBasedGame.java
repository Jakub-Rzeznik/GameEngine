package com.jkr.game.execution;

import com.jkr.game.interaction.Player;

public interface TurnBasedGame {
	
	public boolean nextTurnForPlayer(Player players) throws GameExecutionException;

}
