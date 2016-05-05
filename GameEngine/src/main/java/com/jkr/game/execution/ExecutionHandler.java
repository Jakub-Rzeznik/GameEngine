package com.jkr.game.execution;

import com.jkr.game.Game;

public interface ExecutionHandler {
	
	public void play() throws GameExecutionException;
	
	public static ExecutionHandler getHandler(Game game) {
		return null;
	}

}
