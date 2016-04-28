package com.jkr.game.interaction;

public interface Player {

	public Comparable<?> getDiscriminator();
	
	public <T> T askForResponse(String ask, ResponseParser<T> parser);
	
	public void showMessage(String msg);
}
