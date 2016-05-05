package com.jkr.game.presentation;

import com.jkr.game.area.GameArea;

/**
 * Dictionary is used to convert {@link GameArea} ordinal into type used for data presentation and vice-versa
 * 
 * @author Jakub Rzeznik
 *
 */
public interface Dictionary<T> {
	T toType(int ordinal);
	int toOrdinal(T type);
	int getMaxSize();
	
	@SuppressWarnings("unchecked")
	public static <S> Dictionary<S> getDictionary(Class<S> clazz) {
		if (clazz == Character.class) {
			return ((Dictionary<S>)new AlphabeticalDictionary());
		}
		return null;
	}
}