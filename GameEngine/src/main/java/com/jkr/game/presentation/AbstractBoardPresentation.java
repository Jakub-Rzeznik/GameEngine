package com.jkr.game.presentation;

import com.jkr.game.area.Board;

public abstract class AbstractBoardPresentation<S> implements Presentation<int[], S> {

	private final int width;
	private final int height;
	private final Dictionary<S> dictionary;

	AbstractBoardPresentation(Board<?> board, Dictionary<S> dict) {
		this.width = board.getWidth();
		this.height = board.getHeight();
		dictionary = dict;
		if (width*height > dictionary.getMaxSize()) {
			throw new IllegalArgumentException("Maximum allowed number of fields is: " + dict.getMaxSize());
		}
	}

	@Override
	public int[] convertPresentationOrdinalToSpaceAreaOrdinal(S input) {
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
