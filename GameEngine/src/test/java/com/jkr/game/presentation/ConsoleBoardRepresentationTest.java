package com.jkr.game.presentation;

import static org.junit.Assert.*;

import org.easymock.EasyMock;
import org.junit.Test;

import com.jkr.game.area.Board;


public class ConsoleBoardRepresentationTest {

	@Test
	public void testGetForInput() {
		TestRepresentation test = new TestRepresentation(4, 6);
		assertPair(test.convertPresentationOrdinalToSpaceAreaOrdinal('a'), 0, 0);
		assertPair(test.convertPresentationOrdinalToSpaceAreaOrdinal('b'), 0, 1);
		assertPair(test.convertPresentationOrdinalToSpaceAreaOrdinal('c'), 0, 2);
		assertPair(test.convertPresentationOrdinalToSpaceAreaOrdinal('d'), 0, 3);
		assertPair(test.convertPresentationOrdinalToSpaceAreaOrdinal('e'), 1, 0);
		assertPair(test.convertPresentationOrdinalToSpaceAreaOrdinal('h'), 1, 3);
		assertPair(test.convertPresentationOrdinalToSpaceAreaOrdinal('i'), 2, 0);
		assertPair(test.convertPresentationOrdinalToSpaceAreaOrdinal('l'), 2, 3);
		assertPair(test.convertPresentationOrdinalToSpaceAreaOrdinal('m'), 3, 0);
		assertPair(test.convertPresentationOrdinalToSpaceAreaOrdinal('p'), 3, 3);
		assertPair(test.convertPresentationOrdinalToSpaceAreaOrdinal('q'), 4, 0);
		assertPair(test.convertPresentationOrdinalToSpaceAreaOrdinal('t'), 4, 3);
		assertPair(test.convertPresentationOrdinalToSpaceAreaOrdinal('u'), 5, 0);
		assertPair(test.convertPresentationOrdinalToSpaceAreaOrdinal('v'), 5, 1);
		assertPair(test.convertPresentationOrdinalToSpaceAreaOrdinal('w'), 5, 2);
		assertPair(test.convertPresentationOrdinalToSpaceAreaOrdinal('x'), 5, 3);
	}
	
	@Test(expected = IndexOutOfBoundsException.class)
	public void testGetForInPutOfOfBoard() {
		new TestRepresentation(2, 2).convertPresentationOrdinalToSpaceAreaOrdinal('z');
	}
	
	private void assertPair(int[] pair, int i, int j) {
		assertEquals(pair[0], i);
		assertEquals(pair[1], j);
	}
	
	
	@Test(expected = IllegalArgumentException.class)
	public void testForTooBig() {
		new TestRepresentation(20, 2);
	}
	

	@SuppressWarnings("unchecked")
	private static class TestRepresentation extends ConsoleBoardRepresentation<Character> {

		protected TestRepresentation(int width, int height) {
			super(createBoardMock(width, height));
		}
		
		private static Board<Character> createBoardMock(int width, int height) {
			Board<Character> board = EasyMock.mock(Board.class);
			EasyMock.expect(board.getHeight()).andReturn(Integer.valueOf(height)).anyTimes();
			EasyMock.expect(board.getWidth()).andReturn(Integer.valueOf(width)).anyTimes();
			EasyMock.replay(board);
			return board;
		}
		
	}

}
