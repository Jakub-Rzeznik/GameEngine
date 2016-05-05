package com.jkr.game.setup;

import com.jkr.game.Game;

public interface SetupHandler {
	
	public void setup() throws GameSetupException;
	
	public static SetupHandler getHandler(Game game) {
		return null;
	}

}
