package com.jkr.game.presentation;

public interface Presentation<I, O> {

	public void showToUser();
	
	public I convertPresentationOrdinalToSpaceAreaOrdinal(O presentationOrdinal);
	
}
