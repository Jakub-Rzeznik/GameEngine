package com.jkr.game.setup;

import java.util.Collection;
import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

import com.jkr.game.Game;
import com.jkr.game.interaction.Player;

public interface MultiplayerGame<S extends Comparable<S>, T> extends Game {
	
	public S[] getPlayerDiscriminators();
	
	default Collection<Player<S, T>> getPlayers() {
		return PlayersHolder.getPlayers();
	}
	
	default void setPlayers(Collection<Player<S, T>> players) {
		PlayersHolder.putPlayers(players);
	}
	

	@SuppressWarnings("all")
	public static class PlayersHolder {
		
		private static Map threadName2Players = new HashMap();
		
		private static <S extends Comparable<S>, T> Collection<Player<S, T>> getPlayers() {
			return (Collection<Player<S, T>>) Optional.ofNullable(threadName2Players.get(Thread.currentThread().getName())).orElseThrow(() -> new IllegalStateException());
		}
		private static <S extends Comparable<S>, T> void putPlayers(Collection<Player<S, T>> players) {
			if (threadName2Players.containsKey(Thread.currentThread().getName())) {
				throw new IllegalStateException();
			}
			threadName2Players.put(Thread.currentThread().getName(), players);
		}
		
	}
	
	
}
