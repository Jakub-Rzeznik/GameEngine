package com.jkr.game.presentation;

public abstract class AbstractBoardConsoleRepresentation<S> implements ConsolePresentation<int[], S> {

	private final int width;
	private final int height;
	private final Dictionary<S> dictionary;
	
	protected AbstractBoardConsoleRepresentation(int width, int height, Dictionary<S> dict) {
		this.width = width;
		this.height = height;
		dictionary = dict;
		if (width*height > dict.getMaxSize()) {
			throw new IllegalArgumentException("Maximum allowed number of fields is: " + dict.getMaxSize());
		}
	}

	@Override
	public void printToConsole() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public int[] getForInput(S input) {
		int ordinal = dictionary.toOrdinal(input);
		int[] coordinates = new int[2];
		coordinates[0] = assertHeight(ordinal/width);
		coordinates[1] = assertWidth(ordinal%width);
		return coordinates;
	}
	
	private int assertHeight(int value) {
		return assertValue(value, height);
	}
	
	private int assertWidth(int value) {
		return assertValue(value, width);
	}
	
	private int assertValue(int value, int threshold) throws IndexOutOfBoundsException {
		if (!(value < threshold)) {
			throw new IndexOutOfBoundsException("Value must be below: " + threshold);
		}
		return value;
	}
	

}
