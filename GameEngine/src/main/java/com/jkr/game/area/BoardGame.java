package com.jkr.game.area;

/**
 * The game that is played on a {@link Board}
 * 
 * @author Jakub Rzeznik
 */
public interface BoardGame<S extends Comparable<S>> {
	
	public Board<S> prepareCleanBoard();

}
