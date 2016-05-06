package com.jkr.game.execution;

import java.util.Collection;

import com.jkr.game.area.Board;
import com.jkr.game.interaction.Player;
import com.jkr.game.presentation.Presentation;
import com.jkr.game.setup.MultiplayerGame;

class Board4Test extends TurnBasedBoardGame<Integer, Character> implements MultiplayerGame<Integer, Character> {
	
	static Board<Integer> board;
	static Presentation<int[], Character> presentation;
	static Collection<Player<Integer,Character>> players;
	
	private Board4Test() {}

	@Override
	public Board<Integer> prepareCleanBoard() {
		return board;
	}

	@Override
	public Comparable<?>[] getPlayerDiscriminators() {
		throw new AssertionError("Should not be called.");
	}

	@Override
	protected Presentation<int[], Character> getPresentation() {
		return presentation;
	}

	@Override
	public Collection<Player<Integer, Character>> getPlayers() {
		return players;
	}

	@Override
	public void setPlayers(Collection<Player<Integer, Character>> players) {
		throw new AssertionError("Should not be called.");
	}
	
	static Board4Test getBoard4TesingTurnBasedBoardGame(Board<Integer> board, Presentation<int[], Character> presentation) {
		clean();
		Board4Test.board = board;
		Board4Test.presentation = presentation;
		return new Board4Test();
	}
	
	private static void clean() {
		board = null;
		presentation = null;
		players = null;
	}
}