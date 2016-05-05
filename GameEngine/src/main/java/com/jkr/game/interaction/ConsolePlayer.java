package com.jkr.game.interaction;

import com.jkr.game.io.ConsoleIO;

public abstract class ConsolePlayer<S extends Comparable<S>> implements Player<S, String> {
	
	private final S discriminator;
	
	protected ConsolePlayer(S discriminator) {
		this.discriminator = discriminator;
	}

	@Override
	public final S getDiscriminator() {
		return discriminator;
	}

	@Override
	public ConsoleResponse askForResponse(String ask) {
		showMessage(ask);
		return new ConsoleResponse(ConsoleIO.readLine());
	}

	@Override
	public void showMessage(String msg) {
		ConsoleIO.printMessage("Player " + String.valueOf(getDiscriminator()) + ":" + msg);
	}
	
	private static final class ConsoleResponse implements Response<String> {
		private String cargo;
		ConsoleResponse(String cargo) {
			this.cargo = cargo;
		}
		@Override
		public String getCargo() {
			return cargo;
		}
		
	}

}
