package com.jkr.game.execution;

import com.jkr.game.Game;

public interface ExecutionHandler {
	
	public void play() throws GameExecutionException;
	
	@SuppressWarnings("unchecked")
	public static <S extends Comparable<S>, T>  ExecutionHandler getHandler(Game game) {
		if (game instanceof TurnBasedGame) {
			return new TurnBasedGameExecutionHandler<S, T>((TurnBasedGame<S, T>) game);
		}
		return null;
	}

}
