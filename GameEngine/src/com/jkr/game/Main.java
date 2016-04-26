package com.jkr.game;

import com.jkr.game.execution.ExecutionHandler;
import com.jkr.game.interaction.GameSelector;
import com.jkr.game.setup.SetupHandler;

public class Main {

	public static void main(String[] args) throws Exception {
		Game game = GameSelector.selectGame();
		SetupHandler.getHandler(game).setup();
		ExecutionHandler.getHandler(game).play();
	}

}
