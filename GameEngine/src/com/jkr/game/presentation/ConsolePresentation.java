package com.jkr.game.presentation;

public interface ConsolePresentation<T, S> {

	public void printToConsole();
	
	public T getForInput(S input);
	
}
