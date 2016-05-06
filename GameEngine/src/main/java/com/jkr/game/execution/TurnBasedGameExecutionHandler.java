package com.jkr.game.execution;

import java.util.LinkedList;
import java.util.List;

import com.jkr.game.interaction.Player;
import com.jkr.game.setup.MultiplayerGame;

public class TurnBasedGameExecutionHandler<S extends Comparable<S>, T> implements ExecutionHandler {
	
	private final TurnBasedGame<S, T> game;
	private final List<Player<S, T>> players = new LinkedList<>();

	@SuppressWarnings("unchecked")
	public TurnBasedGameExecutionHandler(TurnBasedGame<S, T> game) {
		this.game = game;
		if (game instanceof MultiplayerGame) {
			players.addAll(((MultiplayerGame<S, T>) game).getPlayers());
		} else {
			throw new IllegalArgumentException(" NOT ALLOWED ");
		}
	}

	@Override
	public void play() throws GameExecutionException {
		if (players.isEmpty()) {
			throw new GameExecutionException("No players to play the game.");
		}
		Player<S, T> player = players.remove(0);
		while (!game.nextTurnForPlayer(player)) {
			players.add(player);
			player = players.remove(0);
		}
		player.showMessage(" CONGRATULATIONS!\nYou have won the game.");
	}

}
