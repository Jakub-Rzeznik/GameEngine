package com.jkr.game.presentation;

import com.jkr.game.area.Board;

public class ConsoleBoardRepresentation extends AbstractBoardPresentation<Character>{
	
	public ConsoleBoardRepresentation(Board<?> board) {
		super(board, Dictionary.getDictionary(Character.class));
	}

	@Override
	public void showCurrentState() {
		// TODO Auto-generated method stub
		
	}
	
	@Override
	public void showAvailableMoves() {
		// TODO Auto-generated method stub
		
	}
	

}
