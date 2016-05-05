package com.jkr.game.presentation;

import static org.junit.Assert.*;

import org.junit.Test;

import com.jkr.game.presentation.AlphabeticalDictionary;

public class AlphabeticalDictionaryTest {
	
	private static final AlphabeticalDictionary dict = new AlphabeticalDictionary();

	@Test
	public void testGetMaxSize() {
		assertEquals(26, dict.getMaxSize());
	}

	@Test
	public void testToType() {
		assertEquals(Character.valueOf('a'), dict.toType(0));
		assertEquals(Character.valueOf('z'), dict.toType(25));
		assertEquals(Character.valueOf('b'), dict.toType(1));
		assertEquals(Character.valueOf('x'), dict.toType(23));
		assertEquals(Character.valueOf('j'), dict.toType(9));
		
	}

	@Test
	public void testToOrdinal() {
		assertEquals(0, dict.toOrdinal('a'));
		assertEquals(25, dict.toOrdinal('z'));
		assertEquals(9, dict.toOrdinal('j'));
		assertEquals(14, dict.toOrdinal('o'));
		assertEquals(23, dict.toOrdinal('x'));
	}

}
