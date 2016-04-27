package com.jkr.game.setup;

import static org.junit.Assert.*;

import org.junit.Test;

import com.jkr.game.Game;

public class SetupHandlerTest {

	@Test(expected = GameSetupException.class)
	public void testGetHandlerThrowsForNull() {
		SetupHandler.getHandler(null);
	}
	
	@Test(expected = GameSetupException.class)
	public void testGetHandlerThrowsForAnonymousGame() {
		SetupHandler.getHandler(new Game() {});
	}
	
	@Test
	public void testGetHandlerThrowsForMultiplayerGame() {
		assertNotNull(SetupHandler.getHandler(new MultiplayerGame() {
			@Override
			public Comparable<?>[] getPlayerDiscriminators() {
				return null;
			}
		}));
	}
}
