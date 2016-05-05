package com.jkr.game.area;

/**
 * 2-dimensional {@link GameArea} in the form of a table
 * 
 * @author Jakub Rzeznik
 */
public interface Board<S extends Comparable<S>> extends GameArea {
	
	public int getWidth();
	public int getHeight();
	public void putMark(int i, int j, S mark);
	public boolean canPutMark(int i, int j);
	public boolean isWinConditionMet(int i, int j);
	public boolean isFullyFilled();
}
