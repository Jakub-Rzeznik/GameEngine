package com.jkr.game.setup;

import java.util.Collection;

import com.jkr.game.Game;
import com.jkr.game.interaction.Player;

public interface MultiplayerGame<S extends Comparable<S>, T> extends Game {
	
	public Comparable<?>[] getPlayerDiscriminators();
	
	public Collection<Player<S, T>> getPlayers();
	
	public void setPlayers(Collection<Player<S, T>> players);
	
}
