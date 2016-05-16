package com.jkr.game.presentation;

import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import com.jkr.game.area.Board;

public class ConsoleBoardRepresentation<M extends Comparable<M>> extends AbstractBoardPresentation<Character, M>{
	
	public ConsoleBoardRepresentation(Board<M> board) {
		super(board, Character.class);
	}

	@Override
	public void showCurrentState() {
		Stream.of(getBoardState()).forEach(row -> {
			System.out.println(Stream.of(row).map(c -> " " + c + " ").collect(Collectors.joining("|")));
		});
	}
	
	@Override
	public void showAvailableMoves() {
		Stream.of(getAvailableTiles()).forEach(row -> {
			System.out.println(Stream.of(row).map(c -> " " + Optional.ofNullable(c).map(String::valueOf).orElse(" ") + " ").collect(Collectors.joining("|")));
		});
		
	}

	@Override
	Character boardMark2PresentationSign(M mark) {
		return Optional.ofNullable(mark).map(String::valueOf).orElse(" ").charAt(0);
	}
	

}
