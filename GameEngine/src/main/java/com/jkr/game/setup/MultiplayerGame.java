package com.jkr.game.setup;

import java.util.Collection;

import com.jkr.game.Game;
import com.jkr.game.execution.GameExecutionException;
import com.jkr.game.interaction.Player;

public interface MultiplayerGame<S extends Comparable<S>, T> extends Game {
	
	public Comparable<?>[] getPlayerDiscriminators();
	
	public void setPlayers(Collection<Player<S, T>> players);
	
	default void throwNoPlayersException() throws GameExecutionException {
		throw new GameExecutionException("Multiplayer game should have at least 2 players");
	}
	
}
