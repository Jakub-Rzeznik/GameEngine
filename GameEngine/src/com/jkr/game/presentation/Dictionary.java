package com.jkr.game.presentation;

public interface Dictionary<T> {
	T toType(int ordinal);
	int toOrdinal(T type);
	int getMaxSize();
	
	@SuppressWarnings("rawtypes")
	public static Dictionary getDictionary(Class clazz) {
		if (clazz == Character.class) {
			return new AlphabeticalDictionary();
		}
		return null;
	}
}