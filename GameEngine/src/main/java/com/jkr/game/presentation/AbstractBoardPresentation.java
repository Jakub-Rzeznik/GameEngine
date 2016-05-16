package com.jkr.game.presentation;

import java.lang.reflect.Array;

import com.jkr.game.area.Board;

public abstract class AbstractBoardPresentation<S, M extends Comparable<M>> implements Presentation<int[], S> {

	private final int width;
	private final int height;
	private final Dictionary<S> dictionary;
	private final Board<M> board;
	private final Class<S> typeClass;

	AbstractBoardPresentation(Board<M> board, Class<S> clazz) {
		this.width = board.getWidth();
		this.height = board.getHeight();
		dictionary = Dictionary.getDictionary(clazz);
		if (width*height > dictionary.getMaxSize()) {
			throw new IllegalArgumentException("Maximum allowed number of fields is: " + dictionary.getMaxSize());
		}
		this.board = board;
		this.typeClass = clazz;
	}

	@Override
	public int[] convertPresentationOrdinalToSpaceAreaOrdinal(S input) {
		return spaceOrdinalToCoordinates(dictionary.toOrdinal(input));
	}
	
	int[] spaceOrdinalToCoordinates(int ordinal) {
		int[] coordinates = new int[2];
		coordinates[0] = assertHeight(ordinal/width);
		coordinates[1] = assertWidth(ordinal%width);
		return coordinates;
	}
	
	int coordinatesToSpaceOrdinal(int x, int y) {
		return x*width + y;
	}
	
	S coordinatesToPresentationOrdinal(int x, int y) {
		return dictionary.toType(coordinatesToSpaceOrdinal(x, y));
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
	
	S[][] getBoardState() {
		M[][] mState = board.getBoardState();
		S[][] sState = (S[][]) Array.newInstance(typeClass, width, height);
		for (int i = 0; i < width; i++) {
			for (int j = 0; j < height; j++) {
				sState[i][j] = boardMark2PresentationSign(mState[i][j]);
			}
		}
		return sState;
	}
	
	abstract S boardMark2PresentationSign(M mark);
	
	S[][] getAvailableTiles() {
		M[][] mState = board.getBoardState();
		S[][] moves = (S[][]) Array.newInstance(typeClass, width, height);
		for (int i = 0; i < width; i++) {
			for (int j = 0; j < height; j++) {
				if (mState[i][j] == null) {
					moves[i][j] = coordinatesToPresentationOrdinal(i, j);
				}
			}
		}
		return moves;
	}

}
