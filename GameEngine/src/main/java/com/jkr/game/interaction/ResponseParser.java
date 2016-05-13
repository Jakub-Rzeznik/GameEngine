package com.jkr.game.interaction;

import java.util.Optional;


public interface ResponseParser<T> {
	
	public T parse(String userResponse);
	
	@SuppressWarnings("unchecked")
	public static <S> ResponseParser<S> getParser(Class<S> clazz) {
		if (clazz == Character.class) {
			return ((ResponseParser<S>)new CharacterParser());
		}
		throw new IllegalArgumentException("No ResponseParser support for class: " + clazz.getName());
	}
	
	public static class CharacterParser implements ResponseParser<Character> {
		@Override
		public Character parse(String userResponse) {
			return Optional.ofNullable(userResponse).orElseThrow(() -> new IllegalArgumentException()).charAt(0);
		}
	}

}
