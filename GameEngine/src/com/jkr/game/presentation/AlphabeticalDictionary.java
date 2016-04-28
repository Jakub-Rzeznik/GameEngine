package com.jkr.game.presentation;

class AlphabeticalDictionary implements Dictionary<Character>{

	@Override
	public int getMaxSize() {
		return ('z'-'a') + 1;
	}

	@Override
	public Character toType(int ordinal) {
		return Character.valueOf((char)(((int)'a')+ordinal));
	}

	@Override
	public int toOrdinal(Character type) {
		return type.charValue()-'a';
	}
	
}