package com.jkr.game.presentation;

public interface Presentation<I, O> {

	public void showCurrentState();
	
	public void showAvailableMoves();
	
	public I convertPresentationOrdinalToSpaceAreaOrdinal(O presentationOrdinal);
	
}
