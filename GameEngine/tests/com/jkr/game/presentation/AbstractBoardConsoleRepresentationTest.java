package com.jkr.game.presentation;

import static org.junit.Assert.*;

import org.junit.Test;


public class AbstractBoardConsoleRepresentationTest {

	@Test
	public void testGetForInput() {
		TestRepresentation test = new TestRepresentation(4, 6);
		assertPair(test.getForInput('a'), 0, 0);
		assertPair(test.getForInput('b'), 0, 1);
		assertPair(test.getForInput('c'), 0, 2);
		assertPair(test.getForInput('d'), 0, 3);
		assertPair(test.getForInput('e'), 1, 0);
		assertPair(test.getForInput('h'), 1, 3);
		assertPair(test.getForInput('i'), 2, 0);
		assertPair(test.getForInput('l'), 2, 3);
		assertPair(test.getForInput('m'), 3, 0);
		assertPair(test.getForInput('p'), 3, 3);
		assertPair(test.getForInput('q'), 4, 0);
		assertPair(test.getForInput('t'), 4, 3);
		assertPair(test.getForInput('u'), 5, 0);
		assertPair(test.getForInput('v'), 5, 1);
		assertPair(test.getForInput('w'), 5, 2);
		assertPair(test.getForInput('x'), 5, 3);
	}
	
	@Test(expected = IndexOutOfBoundsException.class)
	public void testGetForInPutOfOfBoard() {
		new TestRepresentation(2, 2).getForInput('z');
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
	private static class TestRepresentation extends AbstractBoardConsoleRepresentation<Character> {

		protected TestRepresentation(int width, int height) {
			super(width, height, Dictionary.getDictionary(Character.class));
		}
		
	}

}
