package com.jkr.game.interaction;

import static org.junit.Assert.*;

import org.junit.Test;

import com.jkr.game.interaction.ConsolePlayer.ConsoleResponse;

public class ConsolePlayerTest {

	@Test
	public void testDiscriminator() {
		Integer i = new Integer(0);
		assertSame(i, new TestPlayer(i).getDiscriminator());
	}
	
	@Test
	public void testConsoleResponse() {
		String str = "";
		assertSame(str, new ConsoleResponse(str).getCargo());
	}
	
	
	private static class TestPlayer extends ConsolePlayer<Integer> {

		protected TestPlayer(Integer discriminator) {
			super(discriminator);
		}
		
	}

}
